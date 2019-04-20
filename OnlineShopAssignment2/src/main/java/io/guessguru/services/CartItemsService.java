package io.guessguru.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.guessguru.entities.CartItems;
import io.guessguru.entities.Item;
import io.guessguru.repositories.CartItemsRepository;

@Service
public class CartItemsService {
	
	@Autowired
	private CartItemsRepository cartItemsRepository;
	
	public void saveCartItems(CartItems cartItems) {
		cartItemsRepository.save(cartItems);
	}
	
	public CartItems findByCartId(int cartId) {
	return cartItemsRepository.findByCartId(cartId);
	}

	public CartItems findByItemId(int itemId) {
	return cartItemsRepository.findByItemId(itemId);
	}

}
