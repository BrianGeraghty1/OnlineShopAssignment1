package io.guessguru.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.guessguru.entities.CartItems;
import io.guessguru.repositories.CartItemsRepository;

@Service
public class CartItemsService {

	@Autowired
	private CartItemsRepository cartItemsRepository;

	public void saveCartItems(CartItems cartItems) {
		cartItemsRepository.save(cartItems);
	}

	public List<CartItems> findByCartId(int cartId) {
		return cartItemsRepository.findByCartId(cartId);
	}

	public void emptyCart(List<CartItems> list) {
		cartItemsRepository.delete(list);
	}

	public CartItems findByItemId(int itemId) {
		return cartItemsRepository.findByItemId(itemId);
	}

	public void deleteTheItemFromCart(Long id) {

		cartItemsRepository.delete(id);
	}

}
