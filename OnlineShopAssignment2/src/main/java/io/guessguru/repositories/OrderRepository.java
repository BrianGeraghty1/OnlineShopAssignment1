package io.guessguru.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.guessguru.entities.UserOrder;

public interface OrderRepository  extends JpaRepository<UserOrder, Integer>{
	UserOrder findByUser(String username);
	UserOrder findById(int id);
}
