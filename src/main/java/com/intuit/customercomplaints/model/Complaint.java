package com.intuit.customercomplaints.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "COMPLAINT", uniqueConstraints = @UniqueConstraint(name = "UniqueCaseIdAndCRM", columnNames = {"CASE_ID", "CRM_SYSTEM"}))
public class Complaint implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "CASE_ID", nullable = false)
    private long caseId;

    @Column(name = "CUSTOMER_ID", nullable = false)
    private long customerId;

    @Column(name = "PROVIDER", nullable = false)
    private long provider;

    @Column(name = "CREATED_ERROR_CODE", nullable = false)
    private long createdErrorCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false)
    private ComplaintStatus status;

    @Column(name = "TICKET_CREATION_DATE", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date ticketCreationDate;

    @Column(name = "LAST_MODIFIED_DATE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastModifiedDate;

    @Column(name = "PRODUCT_NAME", nullable = false)
    private String productName;

    @Enumerated(EnumType.STRING)
    @Column(name = "CRM_SYSTEM", nullable = false)
    private CrmSystem crmSystem;

    public enum ComplaintStatus {
        OPEN,
        CLOSED
    }

    public enum CrmSystem {
        Banana,
        Strawberry
    }


    public static final class ComplaintBuilder {
        private long id;
        private long caseId;
        private long customerId;
        private long provider;
        private long createdErrorCode;
        private ComplaintStatus status;
        private Date ticketCreationDate;
        private Date lastModifiedDate;
        private String productName;
        private CrmSystem crmSystem;

        private ComplaintBuilder() {
        }

        public static ComplaintBuilder aComplaint() {
            return new ComplaintBuilder();
        }

        public ComplaintBuilder caseId(long caseId) {
            this.caseId = caseId;
            return this;
        }

        public ComplaintBuilder customerId(long customerId) {
            this.customerId = customerId;
            return this;
        }

        public ComplaintBuilder provider(long provider) {
            this.provider = provider;
            return this;
        }

        public ComplaintBuilder createdErrorCode(long createdErrorCode) {
            this.createdErrorCode = createdErrorCode;
            return this;
        }

        public ComplaintBuilder status(ComplaintStatus status) {
            this.status = status;
            return this;
        }

        public ComplaintBuilder ticketCreationDate(Date ticketCreationDate) {
            if (this.ticketCreationDate == null) {
                this.ticketCreationDate = ticketCreationDate;
            }
            return this;
        }

        public ComplaintBuilder lastModifiedDate() {
            this.lastModifiedDate = DateTime.now().toDate();
            return this;
        }

        public ComplaintBuilder productName(String productName) {
            this.productName = productName;
            return this;
        }

        public ComplaintBuilder crmSystem(CrmSystem crmSystem) {
            this.crmSystem = crmSystem;
            return this;
        }

        public Complaint build() {
            Complaint complaint = new Complaint();
            complaint.setId(id);
            complaint.setCaseId(caseId);
            complaint.setCustomerId(customerId);
            complaint.setProvider(provider);
            complaint.setCreatedErrorCode(createdErrorCode);
            complaint.setStatus(status);
            complaint.setTicketCreationDate(ticketCreationDate);
            complaint.setLastModifiedDate(lastModifiedDate);
            complaint.setProductName(productName);
            complaint.setCrmSystem(crmSystem);
            return complaint;
        }
    }
}
