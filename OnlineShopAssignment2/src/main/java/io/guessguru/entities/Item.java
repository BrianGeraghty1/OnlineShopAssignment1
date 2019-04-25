package io.guessguru.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String itemName;
	private String manufacturer;
	private String category;
	private int quantity;
	private double price;
	
	@OneToMany(mappedBy="item")
	private Set<CartItems> cartItems;
/*	@ManyToMany(mappedBy = "items")
	private Set<Cart> carts;*/
	@ManyToMany(mappedBy = "orderItems")
	private Set<UserOrder> orders;
	
	@OneToMany
	@JoinColumn(name = "item_id")
	private Set<Rating> ratings;

	public Set<CartItems> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Set<CartItems> cartItems) {
		this.cartItems = cartItems;
	}

	public Set<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(Set<Rating> ratings) {
		this.ratings = ratings;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<UserOrder> getOrders() {
		return orders;
	}

	public void setOrders(Set<UserOrder> orders) {
		this.orders = orders;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Item(String itemName, String manufacturer, String category, double price) {
		this.itemName = itemName;
		this.manufacturer = manufacturer;
		this.category = category;
		this.price = price;
	}

	public Item() {
	}

}
