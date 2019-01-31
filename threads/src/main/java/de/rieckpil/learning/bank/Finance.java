package de.rieckpil.learning.bank;

import java.util.Random;

public class Finance {

	private static final Random rng = new Random(42);

	private static void transferRandomMoney(final int threadId, final Bank bank, final Account from, final Account to) {

		final int money = rng.nextInt(200) + 1;
		if (from != to) {
			if (bank.transfer(from, to, money)) {
				System.out.printf("[%d] Transfer from %s to %s successful%n", threadId, from, to);
			} else {
				System.out.printf("[%d] Transfer from %s to %s failed%n", threadId, from, to);
			}
		}
	}

	private static void runTransfers(final Bank bank, final int numThreads) {

		final Account[] accounts = new Account[numThreads];
		for (int i = 0; i < numThreads; ++i) {
			accounts[i] = new Account(rng.nextInt(150) + 50);
		}

		final Thread[] threads = new Thread[numThreads];
		Finance f = new Finance();

		for (int i = 0; i < numThreads; ++i) {
			Account fromAccount = accounts[i];
			Account toAccount = i == (numThreads - 1) ? accounts[0] : accounts[i + 1];
			threads[i] = f.new MoneyThread(fromAccount, toAccount, bank);
		}

		for (Thread thread : threads) {
			thread.start();
		}

	}

	public static void main(String[] args) {
		runTransfers(new DeadlockBank(), 3);
		// runTransfers(new QuickLockingBank(), 5);
		// runTransfers(new OrderedBank(), 7);
		// runTransfers(new ManagedBank(), 9);
	}

	class MoneyThread extends Thread {

		private Account fromAccount;
		private Account toAccount;
		private Bank bank;

		public MoneyThread(Account fromAccount, Account toAccount, Bank bank) {
			this.fromAccount = fromAccount;
			this.toAccount = toAccount;
			this.bank = bank;
		}

		@Override
		public void run() {
			while (true) {
				Finance.transferRandomMoney(Long.valueOf(this.getId()).intValue(), bank, fromAccount, toAccount);
			}
		}

	}
}
