package de.rieckpil.learning.user.control;

import de.rieckpil.learning.user.entity.JpaUser;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

@Stateless
public class UserBean {

    @PersistenceContext(unitName = "ch02-jpa-pu", type = PersistenceContextType.TRANSACTION)
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

    public JpaUser findById(Long id){
        return em.find(JpaUser.class, id);
    }

}
