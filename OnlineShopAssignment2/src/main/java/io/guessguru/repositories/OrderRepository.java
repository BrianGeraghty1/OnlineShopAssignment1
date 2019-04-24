package io.guessguru.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.guessguru.entities.UserOrder;

public interface OrderRepository extends JpaRepository<UserOrder, Integer> {

	UserOrder findById(int id);

	ArrayList<UserOrder> findOrdersByUserEmail(String email);
}
