package com.example.outsourcing_project.activitylog;

import com.example.outsourcing_project.activitylog.domain.entity.ActivityLog;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.springframework.stereotype.Repository;

@Repository
public class ActivityLogRepository {
    private final EntityManagerFactory emf;

    public ActivityLogRepository(EntityManagerFactory emf){
        this.emf = emf;
    }

    public void activityLogSave(ActivityLog activityLog) {
        EntityManager entityManager = emf.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.persist(activityLog);

        transaction.commit();

        entityManager.close();
    }
}
