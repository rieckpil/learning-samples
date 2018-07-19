package de.rieckpil.learning.jsf.control;

import de.rieckpil.learning.jsf.entity.Message;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class MessageService {

    @PersistenceContext
    private EntityManager em;

    public void create(Message message) {
        em.persist(message);
    }

    public List<Message> list() {

        return em.createQuery("SELECT m FROM Message m", Message.class).getResultList();

    }

}
