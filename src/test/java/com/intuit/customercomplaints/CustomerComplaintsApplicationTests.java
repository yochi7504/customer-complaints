package com.intuit.customercomplaints;

import com.intuit.customercomplaints.model.Complaint;
import com.intuit.customercomplaints.repo.ComplaintRepository;
import com.intuit.customercomplaints.service.CRMService;
import com.intuit.customercomplaints.service.ComplaintService;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static com.intuit.customercomplaints.model.Complaint.ComplaintBuilder.aComplaint;
import static org.junit.Assert.assertEquals;

@TestPropertySource(
        locations = "classpath:application-test.properties")
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class CustomerComplaintsApplicationTests {

    @Autowired
    ComplaintService complaintService;

    @Autowired
    CRMService crmService;

    @Autowired
    ComplaintRepository repository;

//    @Ignore
    @Before
    public void setUp() {
        for (int i = 1 ; i <= 5 ; i++) {
            Complaint complaint = aComplaint().caseId(i).customerId(i)
                    .provider(i * 1000).createdErrorCode(i * 3000)
                    .status(i % 5 == 0 ? Complaint.ComplaintStatus.OPEN : Complaint.ComplaintStatus.CLOSED)
                    .ticketCreationDate(DateTime.now().toDate()).lastModifiedDate()
                    .productName(i % 2 == 0 ? "RED" : "BLUE").crmSystem(i % 3 == 0 ? Complaint.CrmSystem.Banana : Complaint.CrmSystem.Strawberry)
                    .build();
            repository.save(complaint);
        }
    }

//    @Ignore
    @Test
    void testDataFromCRM() {
        System.out.println("-------------testDataFromCRM--------------");
        List<Complaint> allFromCRM = crmService.getAllFromCRM();
        assertEquals(5, allFromCRM.size());
    }

//    @Ignore
    @Test
    void testDataFromDB() {
        System.out.println("-------------testDataFromDB--------------");
        List<Complaint> all = repository.findAll();
        System.out.println(all);
    }

//    @Ignore
	@Test
	void testFilterByProvider() {
        assertEquals(1, ((ArrayList) complaintService.getAllWithFilter(1500, null, null)).size());
	}

//    @Ignore
	@Test
	void testFilterByErrorCode() {
        assertEquals(1, ((ArrayList) complaintService.getAllWithFilter(null, 3000, null)).size());
	}

//    @Ignore
    @Test
	void testFilterByStatus() {
        assertEquals(4, ((ArrayList) complaintService.getAllWithFilter(null, null, Complaint.ComplaintStatus.CLOSED.name())).size());
	}

//    @Ignore
	@Test
	void testFilter() {
        assertEquals(1, ((ArrayList) complaintService.getAllWithFilter(3000, 6000, Complaint.ComplaintStatus.CLOSED.name())).size());
	}

}
