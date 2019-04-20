package io.guessguru.entities;

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
	/*@ManyToMany
	@JoinTable(name = "CART_ITEMS", joinColumns = {
			@JoinColumn(name = "CART_ID", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "ITEM_ID", referencedColumnName = "id") })
	@ElementCollection(targetClass = Item.class)
	private Set<Item> items;*/

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
	
/*	public void addItem(Item item) {
		items.add(item);
	}*/
}
