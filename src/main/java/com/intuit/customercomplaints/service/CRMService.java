package com.intuit.customercomplaints.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intuit.customercomplaints.model.Complaint;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
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

    @Value("${crm.url}")
    String crmUrl;

    public List<Complaint> getAllFromCRM() {
        List<Complaint> complaintList = new ArrayList<>();
        try {
            complaintList.addAll(getAllFromCRM(Complaint.CrmSystem.Banana.name()));
            complaintList.addAll(getAllFromCRM(Complaint.CrmSystem.Strawberry.name()));
            complaintList.forEach(complaint -> complaintService.save(complaint));
        } catch (Exception e) {
            System.out.println("Failed to fetch data from CRM system");

            GenerateDataForFakeURL(complaintList);
        }

        updateTimeService.save(aUpdate().lastUpdatedDate().build());

        return complaintList;
    }

    private void GenerateDataForFakeURL(List<Complaint> complaintList) {
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

    public List<Complaint> getAllFromCRM(String path) throws IOException {
        if (path.isBlank()) {
            return null;
        }
        OkHttpClient client = new OkHttpClient.Builder().build();
        String url = crmUrl + path + "/";

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("content-type", "application/json")
                .addHeader("Accept-Language", "en-US,en;q=0.5")
                .build();

        String data = client.newCall(request).execute().body().string();

        ObjectMapper mapper = new ObjectMapper();
        return Arrays.asList(mapper.readValue(data, Complaint[].class));
    }

}
