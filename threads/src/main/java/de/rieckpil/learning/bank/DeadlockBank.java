package de.rieckpil.learning.bank;

public class DeadlockBank implements Bank {

	@Override
	public boolean transfer(Account fromAccount, Account toAccount, int money) {

		if (fromAccount.getMoney() >= money) {
			try {
				fromAccount.lock();
				toAccount.lock();
				fromAccount.setMoney(fromAccount.getMoney() - money);
				toAccount.setMoney(toAccount.getMoney() + money);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				fromAccount.unlock();
				toAccount.unlock();
			}

			return true;
		}

		return false;
	}

}
