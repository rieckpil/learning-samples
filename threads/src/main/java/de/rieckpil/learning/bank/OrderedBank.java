package de.rieckpil.learning.bank;

public class OrderedBank implements Bank {

	@Override
	public boolean transfer(Account fromAccount, Account toAccount, int money) {
		if (fromAccount.getMoney() >= money) {
			try {
				if (fromAccount.getId() < toAccount.getId()) {
					fromAccount.lock();
					toAccount.lock();
				} else {
					toAccount.lock();
					fromAccount.lock();
				}
				fromAccount.setMoney(fromAccount.getMoney() - money);
				toAccount.setMoney(toAccount.getMoney() + money);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				fromAccount.unlock();
				toAccount.unlock();
			}

			return true;
		} else {
			return false;
		}
	}

}
