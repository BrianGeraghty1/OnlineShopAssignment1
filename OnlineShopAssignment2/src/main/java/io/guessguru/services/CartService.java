package io.guessguru.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.guessguru.entities.Cart;
import io.guessguru.entities.Item;
import io.guessguru.repositories.CartRepository;
import io.guessguru.repositories.ItemRepository;

@Service
public class CartService {
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private ItemRepository itemRepository;

	public void saveCart(Cart cart) {
		cartRepository.save(cart);
	}
	
	public void addItem(Item item, Cart cart) {
		
	}
	
	public Cart findById(int id) {
		return cartRepository.findById(id);
	}
	
	public Cart findByUser(String email) {
		return cartRepository.findByUser(email);
	}

}
