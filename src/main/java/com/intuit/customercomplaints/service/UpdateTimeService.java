package com.intuit.customercomplaints.service;

import com.intuit.customercomplaints.model.UpdateTime;
import com.intuit.customercomplaints.repo.UpdateTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@EnableScheduling
public class UpdateTimeService {

    @Autowired
    UpdateTimeRepository repository;

    public Date getLastUpdatedDate() {
        return repository.getLastUpdatedDate();
    }

    public void save(UpdateTime updateTime) {
        repository.save(updateTime);
    }

}
