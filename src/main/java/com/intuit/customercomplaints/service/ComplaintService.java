package com.intuit.customercomplaints.service;

import com.intuit.customercomplaints.model.Complaint;
import com.intuit.customercomplaints.repo.ComplaintRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@EnableScheduling
public class ComplaintService {

    @Autowired
    ComplaintRepository repository;

    @Autowired
    UpdateTimeService updateTimeService;

    @Autowired
    CRMService crmService;

    public Iterable<Complaint> all() {
        List<Complaint> all;
        Date lastUpdatedDate = updateTimeService.getLastUpdatedDate();

        if (lastUpdatedDate != null && getDiffBetweenDates(DateTime.now().toDate(), lastUpdatedDate) < 15) {
            all = repository.findAll();
            System.out.println("Get all from DB");
        } else {
            all = crmService.getAllFromCRM();
            System.out.println("Get all from CRM");
        }

        return all;
    }

    public Iterable<Complaint> getAllWithFilter(Integer provider, Integer errorCode, String status) {
        List<Complaint> all = (List<Complaint>) all();

        boolean filterByProvider = provider != null && provider > 0;
        boolean filterByErrorCode = errorCode != null && errorCode > 0;
        boolean filterByStatus = status != null && !status.isBlank();
        return all
                .stream()
                .filter(c ->
                        (!filterByProvider || c.getProvider() == provider) &&
                                (!filterByErrorCode || c.getCreatedErrorCode() == errorCode) &&
                                (!filterByStatus || c.getStatus().name().equalsIgnoreCase(status)))
                .collect(Collectors.toList());
    }

    public void save(Complaint complaint) {
        Complaint originalComplaint = repository.getByUniqueConstraint(complaint.getCaseId(), complaint.getCrmSystem());
        if (originalComplaint != null) {
            complaint.setId(originalComplaint.getId());
            complaint.setTicketCreationDate(originalComplaint.getTicketCreationDate());
        }
        repository.save(complaint);
    }

    private long getDiffBetweenDates(Date now, Date lastUpdatedDate) {
        long diffInMilliseconds = Math.abs(now.getTime() - lastUpdatedDate.getTime());
        return TimeUnit.MINUTES.convert(diffInMilliseconds, TimeUnit.MILLISECONDS);
    }

}
