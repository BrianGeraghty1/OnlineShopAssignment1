package io.guessguru.entities;

import java.util.Date;

public class Visa implements PaymentMethod {

	private final String name;
	private final String cardNumber;
	private final String expires;

	
	public String getName() {
		return name;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public String getExpires() {
		return expires;
	}
	

	public Visa(String name, String cardNumber, String expires) {
	    super();
	    this.name = name;
	    this.cardNumber = cardNumber;
	    this.expires = expires;
	  }

	@Override
	public boolean pay(double amount) {
		if(this.cardNumber.length()!=16) {
			 return false;
		 }
		return true;
	}

}
