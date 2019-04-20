package io.guessguru.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="USER_EMAIL", nullable = false)
	private User user;

	@ManyToMany
	@JoinTable(name = "CART_ITEMS", joinColumns = {
			@JoinColumn(name = "CART_ID", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "ITEM_ID", referencedColumnName = "id") })
	@ElementCollection(targetClass = Item.class)
	private Set<Item> items;

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
	return items;
	}

	public void setItems(Set<Item> items) {
	this.items = items;
	}

	public Cart(User user) {
		this.user = user;
	}
	
	public Cart() {
		
	}
	
	public void addItem(Item item) {
		items.add(item);
	}
}
