package com.intuit.customercomplaints;

import com.intuit.customercomplaints.model.Complaint;
import com.intuit.customercomplaints.repo.ComplaintRepository;
import com.intuit.customercomplaints.service.ComplaintService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Collection;

@SpringBootTest(classes = {CustomerComplaintsApplication.class, TestMocks.class})
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringJUnitConfig
class CustomerComplaintsApplicationTests {

    //    @MockBean
//    static
    @Autowired
    ComplaintService service;

    @Autowired
    ComplaintRepository repository;

//    static final List<Complaint> complaints = new ArrayList<>();

//    @BeforeAll
//    static void beforeAll(){
////        MockitoAnnotations.initMocks(this);
////        MockitoAnnotations.openMocks(service);
////        service = Mockito.mock(ComplaintService.class);
//
//        for (int i = 1 ; i <= 5 ; i++) {
//            Complaint complaint = aComplaint().caseId(i).customerId(i)
//                    .provider(i * 1000).createdErrorCode(i * 3000)
//                    .status(i % 5 == 0 ? Complaint.ComplaintStatus.OPEN : Complaint.ComplaintStatus.CLOSED)
//                    .ticketCreationDate(Dates.nowUTC()).lastModifiedDate()
//                    .productName(i % 2 == 0 ? "RED" : "BLUE").crmSystem(i % 3 == 0 ? Complaint.CrmSystem.Banana : Complaint.CrmSystem.Strawberry)
//                    .build();
////            Mockito.when(service.save(complaint)).thenReturn(complaint);
//            complaints.add(complaint);
//        }
//
////        Mockito.when(service.all()).thenReturn(complaints);
//    }

    @Test
    void testFilterByProvider() {
//        Mockito.when(repository.findAll()).thenReturn(complaints);

        Collection<Complaint> allWithFilter = (Collection<Complaint>) service.getAllWithFilter(1000, null, null);
        System.out.println("*********** testFilterByProvider ***********");
        System.out.println("*********** service.all() ***********");
        System.out.println(service.all().toString());
        System.out.println(allWithFilter.toString());
        System.out.println("********************************************");
    }

//	@Test
//	void testFilterByErrorCode() {
//        Assertions.assertEquals(1, service.getAllWithFilter(null, 3000, null));
//	}
//
//	@Test
//	void testFilterByStatus() {
//        Assertions.assertEquals(4, service.getAllWithFilter(null, null, Complaint.ComplaintStatus.CLOSED.name()));
//	}
//
//	@Test
//	void testFilter() {
//        Assertions.assertEquals(1, service.getAllWithFilter(2000, 6000, Complaint.ComplaintStatus.CLOSED.name()));
//	}

}
