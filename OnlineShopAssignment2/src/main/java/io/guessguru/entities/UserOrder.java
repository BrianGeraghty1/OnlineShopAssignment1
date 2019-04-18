package io.guessguru.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class UserOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "USER_EMAIL", updatable = false)
	private User user;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "order_items", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "item_id"))
	private Set<Item> orderItems;
	private String paymentMethod;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Item> getItems() {
		return orderItems;
	}

	public void setItems(Set<Item> items) {
		this.orderItems = items;
	}

	public UserOrder(User user, Set<Item> items) {
		this.user = user;
		this.orderItems = items;
	}
	
	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	

	public Set<Item> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Set<Item> orderItems) {
		this.orderItems = orderItems;
	}

	public UserOrder() {
		
	}
	
}
