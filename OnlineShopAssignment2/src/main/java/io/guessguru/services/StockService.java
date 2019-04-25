package io.guessguru.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.guessguru.entities.Cart;
import io.guessguru.entities.CartItems;
import io.guessguru.entities.Item;

public class StockService {
	
	public StockService() {
		
	}
	public boolean isAvailable(ArrayList<CartItems> cart_items) {

		boolean available = true;

		for (int i = 0; i < cart_items.size(); i++) {
			CartItems cartItem = cart_items.get(i);
			Item item = cart_items.get(i).getItem();

			if (cartItem.getAmount() > item.getQuantity()) {

				available = false;
			}
		}
		return available;
	}
}
