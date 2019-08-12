package com.example.enverstest;

import static org.hibernate.envers.RelationTargetAuditMode.NOT_AUDITED;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.envers.Audited;

@Audited
//@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "TRIGGERS")
public class Trigger extends CompositelyIdentifiedEntity<Trigger.TriggerPK> {
//    private String eventType;
//    private NotificationType notificationType;
    private boolean active;

//    protected TriggerPK pk;
//
//    @EmbeddedId
//    public TriggerPK getPk() {
//        return pk;
//    }
//
//    public void setPk(TriggerPK pk) {
//        this.pk = pk;
//    }

    private Trigger() {
    }

    public Trigger(final String eventType, final NotificationType notificationType) {
        this.pk = new TriggerPK(eventType, notificationType);
//        this.eventType = eventType;
//        this.notificationType = notificationType;
    }
    @Transient
//    @Column(name = "EVENT_TYPE", insertable = false, updatable = false, nullable = false)
    public String getEventType() {
        return this.pk.eventType;
    }

//    public void setEventType(final String eventType) {
//        this.eventType = eventType;
//    }

    @Transient
    public NotificationType getNotificationType() {
        return this.pk.notificationType;
    }

//    public void setNotificationType(final NotificationType notificationType) {
//        this.notificationType = notificationType;
//    }

    public boolean isActive() {
        return active;
    }

    public void setActive(final boolean active) {
        this.active = active;
    }

    @Embeddable
    public static class TriggerPK implements Serializable {

        private String eventType;
        private NotificationType notificationType;

        private TriggerPK() {
        }

        public TriggerPK(String eventType, NotificationType notificationType) {
            this.eventType = eventType;
            this.notificationType = notificationType;
        }

        @Column(name = "EVENT_TYPE", nullable = false, insertable = false, updatable = false)
        public String getEventType() {
            return eventType;
        }

        private void setEventType(String eventType) {
            this.eventType = eventType;
        }

        @Audited(targetAuditMode = NOT_AUDITED)
        @ManyToOne
        @JoinColumn(name = "NOTIFICATION_TYPE_CODE", insertable = false, updatable = false, nullable = false)
        public NotificationType getNotificationType() {
            return notificationType;
        }

        private void setNotificationType(NotificationType notificationType) {
            this.notificationType = notificationType;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof TriggerPK)) return false;
            TriggerPK that = (TriggerPK) o;
            return Objects.equals(getEventType(), that.getEventType()) &&
                    Objects.equals(getNotificationType(), that.getNotificationType());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getEventType(), getNotificationType());
        }

        @Override
        public String toString() {
            return "TriggerPK{" +
                    "eventType='" + eventType + '\'' +
                    ", notificationType=" + notificationType +
                    '}';
        }
    }
}
