package de.rieckpil.learning;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Bootstrapper {

	public static void main(String[] args) throws InterruptedException {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("prod");

		Bootstrapper b = new Bootstrapper();

		for (int i = 0; i < 10; i++) {
			b.new Worker(emf.createEntityManager()).start();
			Thread.sleep(5000);
		}

	}

	public class Worker extends Thread {

		private EntityManager em;

		public Worker(EntityManager em) {
			this.em = em;
		}

		@Override
		public void run() {
			while (true) {
				try {
					this.em.getTransaction().begin();
					this.em.persist(new Person(UUID.randomUUID().toString()));
					this.em.getTransaction().commit();
					this.em.close();
					return;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	}

}
