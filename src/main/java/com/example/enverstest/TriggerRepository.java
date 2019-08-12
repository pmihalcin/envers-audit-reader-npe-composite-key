package com.example.enverstest;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TriggerRepository extends JpaRepository<Trigger, Long> {
    List<Trigger> findByPk_NotificationTypeOrderByPk_eventType(final NotificationType notificationType);
}
