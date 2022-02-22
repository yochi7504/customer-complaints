package com.intuit.customercomplaints.repo;

import com.intuit.customercomplaints.model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    @Query("SELECT c FROM Complaint c WHERE c.caseId = :caseId AND c.crmSystem = :crmSystem")
    Complaint getByUniqueConstraint(@Param("caseId") long caseId, @Param("crmSystem") Complaint.CrmSystem crmSystem);

}
