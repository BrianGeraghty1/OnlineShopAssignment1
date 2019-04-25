package io.guessguru.entities;

public interface PaymentMethod {
	 
	  public boolean pay(double amount);
	  public boolean checkDate(String expiry);
	  public boolean checkNumber(String cardNumber);
	}
