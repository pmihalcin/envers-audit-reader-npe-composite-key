package com.example.enverstest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationTypeRepository extends JpaRepository<NotificationType, String> {
//    Trigger findByEventTypeAndNotificationType(final String eventType, ...);
}
