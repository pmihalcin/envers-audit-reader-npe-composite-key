package com.example.enverstest;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "NOTIFICATION_TYPE")
public class NotificationType {

    @Id
    private String code;

    public NotificationType() {
    }

    public NotificationType(final String code) {
        this.code = code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
