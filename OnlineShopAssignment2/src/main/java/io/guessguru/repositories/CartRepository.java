package io.guessguru.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.guessguru.entities.Cart;

public interface CartRepository  extends JpaRepository<Cart, Integer>{
	Cart findById(int id);
	Cart findByUser(String email);
 
}
