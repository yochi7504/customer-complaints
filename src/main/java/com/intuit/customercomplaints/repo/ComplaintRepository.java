package com.intuit.customercomplaints.repo;

import com.intuit.customercomplaints.model.Complaint;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    @Query("SELECT c FROM Complaint c WHERE c.caseId = ?1 AND c.crmSystem = ?2")
    Complaint getByUniqueConstraint(@Param("caseId") long caseId, @Param("crmSystem") Complaint.CrmSystem crmSystem);

}
