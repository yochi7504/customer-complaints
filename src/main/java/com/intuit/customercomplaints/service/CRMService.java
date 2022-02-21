package com.intuit.customercomplaints.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intuit.customercomplaints.model.Complaint;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.intuit.customercomplaints.model.Complaint.ComplaintBuilder.aComplaint;
import static com.intuit.customercomplaints.model.UpdateTime.UpdatesBuilder.aUpdate;

@Service
public class CRMService {

    @Autowired
    ComplaintService complaintService;

    @Autowired
    UpdateTimeService updateTimeService;

    public List<Complaint> getAllFromCRM() {
        List<Complaint> complaintList = new ArrayList<>();
        try {
            complaintList.addAll(getAllFromCRM(Complaint.CrmSystem.Banana.name()));
            complaintList.addAll(getAllFromCRM(Complaint.CrmSystem.Strawberry.name()));
            complaintList.forEach(complaint -> complaintService.save(complaint));
        } catch (Exception e) {
            System.out.println("Failed to fetch data from CRM system");

            for (int i = 1; i <= 5; i++) {
                Complaint complaint = aComplaint().caseId(i).customerId(i)
                        .provider(i * 1500).createdErrorCode(i * 3000)
                        .status(i % 5 == 0 ? Complaint.ComplaintStatus.OPEN : Complaint.ComplaintStatus.CLOSED)
                        .ticketCreationDate(DateTime.now().toDate()).lastModifiedDate()
                        .productName(i % 2 == 0 ? "RED" : "BLUE").crmSystem(i % 3 == 0 ? Complaint.CrmSystem.Banana : Complaint.CrmSystem.Strawberry)
                        .build();
                complaintService.save(complaint);
                complaintList.add(complaint);
            }
        }

        updateTimeService.save(aUpdate().lastUpdatedDate().build());

        return complaintList;
    }

//    private List<Complaint> extracted(Complaint.CrmSystem crmSystem) {
//        new Thread(() -> {
//            List<Complaint> complaintList = null;
//            try {
//                complaintList = getAllFromCRM(crmSystem.name());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return complaintList;
//        }).start();
//    }

    public List<Complaint> getAllFromCRM(String path) throws IOException {
        if (path.isBlank()) {
            return null;
        }
        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet httpGet = new HttpGet("http://fakeurl/homeassignment/" + path + "/");
        httpGet.addHeader("accept", "application/json");
        org.apache.http.HttpResponse response = httpClient.execute(httpGet);

        if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatusLine().getStatusCode());
        }

        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader((response.getEntity().getContent())));

        String line;
        StringBuilder stringBuilder = new StringBuilder();
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }

        bufferedReader.close();

        ObjectMapper mapper = new ObjectMapper();
        return Arrays.asList(mapper.readValue(stringBuilder.toString(), Complaint[].class));
    }

}
