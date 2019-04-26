package io.guessguru.entities;

import java.util.ArrayList;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="USER_EMAIL", nullable = false)
	private User user;
	@OneToMany(mappedBy="cart")
	private Set<CartItems> cartItems;
	private static Cart cart;

	public Set<CartItems> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Set<CartItems> cartItems) {
		this.cartItems = cartItems;
	}

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

	public Cart(User user) {
		this.user = user;
	}
	
	public Cart() {
		
	}
	
	public static Cart getInstance(User user) {
		if(cart == null) {
			cart = new Cart(user);
		}
		return cart;
	}
	
	public double calculateTotal() {
		double total =0;
		
		ArrayList<CartItems> cart_items = new ArrayList<CartItems>();
		cart_items.addAll(this.getCartItems());
		for(int i=0; i<cart_items.size(); i++) {
			Item item = cart_items.get(i).getItem();
			total += (item.getPrice()*cart_items.get(i).getAmount());
		}
		
		return total;
	}
	
}
