package com.airhacks.ping.boundary;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.airhacks.ping.entity.Ping;

@Stateless
public class PingManager {

	@PersistenceContext
	EntityManager em;

	public Ping savePing(Ping ping) {
		ping = em.merge(ping);
		return ping;
	}

}
