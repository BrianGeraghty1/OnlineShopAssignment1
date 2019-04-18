package io.guessguru.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

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
	@ManyToMany(mappedBy = "items")
	private Set<Cart> carts;
	@ManyToMany(mappedBy = "orderItems")
	private Set<UserOrder> orders;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Set<Cart> getCarts() {
		return carts;
	}

	public void setCarts(Set<Cart> carts) {
		this.carts = carts;
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

	public Item(String itemName, String manufacturer, String category, double price, int quantity) {
		this.itemName = itemName;
		this.manufacturer = manufacturer;
		this.category = category;
		this.price = price;
		this.quantity = quantity;
	}

}
