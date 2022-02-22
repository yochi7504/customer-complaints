package com.intuit.customercomplaints.schedule;

import com.intuit.customercomplaints.service.CRMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class JobInvocator {

    @Autowired
    CRMService crmService;

    @Value("${run.crm.job}")
    Boolean runJob;

    @Scheduled(fixedRateString = "${fixedRate.in.milliseconds:14400000}")
    public void scheduleTask() {
        if (runJob) {

            System.out.println("schedule tasks using fixed rate jobs");

            crmService.getAllFromCRM();
        }
    }


}
