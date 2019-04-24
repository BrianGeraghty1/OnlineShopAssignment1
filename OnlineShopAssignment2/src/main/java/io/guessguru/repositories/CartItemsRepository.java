package io.guessguru.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.guessguru.entities.CartItems;
import io.guessguru.entities.Item;

public interface CartItemsRepository  extends JpaRepository<CartItems, Long>{
	List<CartItems> findByCartId(int cartId);
	CartItems findByItemId(int itemId);
}
