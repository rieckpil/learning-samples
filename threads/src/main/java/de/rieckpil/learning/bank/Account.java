package de.rieckpil.learning.bank;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {

	private int id = counter++;
	private int money;
	private Lock lock = new ReentrantLock();

	private static int counter = 0;

	public Account(final int initialMoney) {
		this.money = initialMoney;
	}

	public int getId() {
		return this.id;
	}

	public int getMoney() {
		return this.money;
	}

	public void setMoney(final int newMoney) {
		this.money = newMoney;
	}

	public void lock() throws InterruptedException {
		this.lock.lock();
	}

	public void unlock() {
		this.lock.unlock();
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", money=" + money + "]";
	}
}
