package com.intuit.customercomplaints;

import com.intuit.customercomplaints.model.Complaint;
import com.intuit.customercomplaints.repo.ComplaintRepository;
import com.intuit.customercomplaints.service.ComplaintService;
import org.joda.time.DateTime;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@TestConfiguration
public class TestMocks {

    List<Complaint> complaints = new ArrayList();

    @Bean
    @Primary
    public ComplaintService complaintService() {
        ComplaintService service = Mockito.mock(ComplaintService.class);
        return service;
    }

    @Bean
    @Primary
    public ComplaintRepository complaintRepository() {
        ComplaintRepository repository = Mockito.mock(ComplaintRepository.class);
        Mockito.when(repository.save(any())).thenReturn(any());
        Mockito.when(repository.findAll()).thenReturn(buildComplaintList());
        return repository;
    }

    private List<Complaint> buildComplaintList() {
        for (int i = 1; i <= 5; ++i) {
            Complaint complaint = Complaint.ComplaintBuilder.aComplaint()
                    .caseId(i).customerId(i).provider((i * 1000))
                    .createdErrorCode((i * 3000))
                    .status(i % 5 == 0 ? Complaint.ComplaintStatus.OPEN : Complaint.ComplaintStatus.CLOSED)
                    .ticketCreationDate(DateTime.now().toDate())
                    .lastModifiedDate().productName(i % 2 == 0 ? "RED" : "BLUE")
                    .crmSystem(i % 3 == 0 ? Complaint.CrmSystem.Banana : Complaint.CrmSystem.Strawberry)
                    .build();
            complaints.add(complaint);
        }
        return complaints;
    }

}
