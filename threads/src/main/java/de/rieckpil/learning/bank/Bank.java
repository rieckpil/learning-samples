package de.rieckpil.learning.bank;

public interface Bank {
	boolean transfer(Account fromAccount, Account toAccount, int money);
}

