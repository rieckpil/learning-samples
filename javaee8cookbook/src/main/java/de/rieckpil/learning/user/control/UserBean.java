package de.rieckpil.learning.user.control;

import de.rieckpil.learning.user.entity.JpaUser;

import javax.batch.operations.JobOperator;
import javax.batch.operations.JobStartException;
import javax.batch.runtime.BatchRuntime;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@Named
@RequestScoped
public class UserBean {

    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    public void add(JpaUser jpaUser){
        em.persist(jpaUser);
    }

    public void update(JpaUser jpaUser){
        em.merge(jpaUser);
    }

    public void remove(JpaUser jpaUser){
        em.remove(jpaUser);
    }

    public void run() {
        try {
            JobOperator job = BatchRuntime.getJobOperator();
            long jobId = job.start("acess-user", new Properties());
            System.out.println("Job started: " + jobId);
        } catch (JobStartException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<JpaUser> get() {
        return em
                .createQuery("SELECT u FROM JpaUser as u", JpaUser.class)
                .getResultList();
    }

    public JpaUser findById(Long id){
        return em.find(JpaUser.class, id);
    }

    public Long getTimestamp(){
        return new Date().getTime();
    }

}
