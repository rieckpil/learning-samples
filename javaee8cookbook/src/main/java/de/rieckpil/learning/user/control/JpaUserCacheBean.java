package de.rieckpil.learning.user.control;

import de.rieckpil.learning.user.entity.JpaUser;
import de.rieckpil.learning.user.entity.User;

import javax.annotation.PostConstruct;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

@Singleton
@Startup
public class JpaUserCacheBean {

    protected Queue<JpaUser> cache = null;

    @PersistenceContext
    private EntityManager em;

    @PostConstruct
    protected void init() {
        System.out.println("... initializing the cache");
        cache = new ConcurrentLinkedQueue<>();
        loadCache();
    }

    protected void loadCache() {
        List<JpaUser> list = em.createQuery("SELECT u FROM JpaUser as u").getResultList();
        list.forEach(cache::add);
    }

    @Lock(LockType.READ)
    public List<JpaUser> get() {
        return cache.stream().collect(Collectors.toList());
    }

}
