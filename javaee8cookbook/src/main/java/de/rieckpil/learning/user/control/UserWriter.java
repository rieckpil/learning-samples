package de.rieckpil.learning.user.control;

import de.rieckpil.learning.user.entity.JpaUser;

import javax.batch.api.chunk.AbstractItemWriter;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Named
@Dependent
public class UserWriter extends AbstractItemWriter {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public void writeItems(List list) {
        for (JpaUser user : (List<JpaUser>) list) {
            entityManager.persist(user);
        }
    }
}
