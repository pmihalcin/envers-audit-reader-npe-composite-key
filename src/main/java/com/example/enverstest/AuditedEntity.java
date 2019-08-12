package com.example.enverstest;


import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Audited
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class AuditedEntity implements Serializable {

    protected String createdBy = "-999";
    protected LocalDateTime createdTimestamp = LocalDateTime.now();

    protected String modifiedBy;
    protected LocalDateTime modifiedTimestamp;

    @CreatedBy
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @CreatedDate
    public LocalDateTime getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(LocalDateTime createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    @LastModifiedBy
    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    @LastModifiedDate
    public LocalDateTime getModifiedTimestamp() {
        return modifiedTimestamp;
    }

    public void setModifiedTimestamp(LocalDateTime modifiedTimestamp) {
        this.modifiedTimestamp = modifiedTimestamp;
    }

    @Override
    public String toString() {
        return "AuditedEntity{" +
                "createdBy='" + createdBy + '\'' +
                ", createdTimestamp=" + createdTimestamp +
                ", modifiedBy='" + modifiedBy + '\'' +
                ", modifiedTimestamp=" + modifiedTimestamp +
                '}';
    }

}
