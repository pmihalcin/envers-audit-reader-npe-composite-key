package com.example.enverstest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.transaction.TransactionDefinition.PROPAGATION_REQUIRES_NEW;

import java.util.List;

import org.hibernate.envers.AuditReaderFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TriggerRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    @Autowired
    private TriggerRepository triggerRepository;
    
    @Autowired
    private NotificationTypeRepository notificationTypeRepository;
    
    @Test
    public void testAudit() {
        TransactionStatus status = platformTransactionManager.getTransaction(new DefaultTransactionDefinition(PROPAGATION_REQUIRES_NEW));

        NotificationType notificationType = new NotificationType("code");
        NotificationType savedNotificationType = notificationTypeRepository.save(notificationType);
        Trigger trigger = new Trigger("str", savedNotificationType);
        Trigger savedTrigger = triggerRepository.save(trigger);
        
        savedTrigger.setActive(!savedTrigger.isActive());
        triggerRepository.save(savedTrigger);

        // to make insert
        this.testEntityManager.flush();
        this.platformTransactionManager.commit(status);

        List resultList = AuditReaderFactory
                .get(testEntityManager.getEntityManager())
                .createQuery()
                .forRevisionsOfEntity(Trigger.class, true, true)
//                .add(AuditEntity.property("notificationType").eq(notificationType))
                .getResultList();

        assertEquals("Trigger was not added",1, triggerRepository.count());
        assertEquals("Trigger can't be found be event type", "str", triggerRepository.findByPk_NotificationTypeOrderByPk_eventType(savedNotificationType).get(0).getEventType());
        assertEquals("Audit trail was not added", 1, resultList.size());
        
        
    }
}