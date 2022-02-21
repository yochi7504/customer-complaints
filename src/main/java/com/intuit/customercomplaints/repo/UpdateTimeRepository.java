package com.intuit.customercomplaints.repo;

import com.intuit.customercomplaints.model.UpdateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface UpdateTimeRepository extends JpaRepository<UpdateTime, Date> {

    @Query("SELECT t FROM UpdateTime t ORDER BY t.lastUpdatedDate desc")
    UpdateTime getLastUpdatedDate();

}
