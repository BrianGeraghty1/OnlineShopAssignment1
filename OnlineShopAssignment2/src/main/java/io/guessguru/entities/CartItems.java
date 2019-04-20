package io.guessguru.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@IdClass(CartItemsID.class)
public class CartItems {
	@Id
	@ManyToOne
	@JoinColumn(name="cart_id", referencedColumnName="id")
	private Cart cart;
	@Id
	@ManyToOne
	@JoinColumn(name="item_id", referencedColumnName="id")
	private Item item;
	private int amount;
	
	public CartItems() {
		
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public CartItems(Cart cart, Item item, int amount) {
		this.cart = cart;
		this.item = item;
		this.amount = amount;
	}
	
}
