package io.guessguru.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.guessguru.entities.UserOrder;
import io.guessguru.repositories.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	public UserOrder findById(int id) {
	return orderRepository.findById(id);
	}


	public ArrayList<UserOrder> findOrdersByUserEmail(String email) {
	return orderRepository.findOrdersByUserEmail(email);
	}

	public void saveOrder(UserOrder order) {
	orderRepository.save(order);
	}

}
