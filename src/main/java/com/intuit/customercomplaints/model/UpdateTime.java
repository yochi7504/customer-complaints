package com.intuit.customercomplaints.model;


import lombok.Data;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "UPDATE_TIME")
public class UpdateTime implements Serializable {

    @Id
    @Column(name = "LAST_UPDATED_DATE", nullable = false)
    private Date lastUpdatedDate;

    public static final class UpdatesBuilder {
        private Date lastUpdatedDate;

        private UpdatesBuilder() {
        }

        public static UpdatesBuilder aUpdate() {
            return new UpdatesBuilder();
        }

        public UpdatesBuilder lastUpdatedDate() {
            this.lastUpdatedDate = DateTime.now().toDate();
            return this;
        }

        public UpdateTime build() {
            UpdateTime update = new UpdateTime();
            update.setLastUpdatedDate(lastUpdatedDate);
            return update;
        }
    }
}
