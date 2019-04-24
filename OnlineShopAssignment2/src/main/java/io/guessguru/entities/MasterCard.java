package io.guessguru.entities;

import java.util.Date;

public class MasterCard implements PaymentMethod {
	 
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

	public MasterCard(String name, String cardNumber, String string) {
	    super();
	    this.name = name;
	    this.cardNumber = cardNumber;
	    this.expires = string;
	  }
	 
	  @Override
	  public boolean pay(double amount) {
		 if(this.cardNumber.length()!=15) {
			 return false;
		 }
	    return true; 
	  }
	 
	}
