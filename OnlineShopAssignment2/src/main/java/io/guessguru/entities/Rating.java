package io.guessguru.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Rating {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String comment;
	
	private int rate;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_email")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "item_id", updatable = false)
	private Item item;
	
	public Rating () {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Rating(String comment, int rate, User user, Item item) {
		this.comment = comment;
		this.rate = rate;
		this.user = user;
		this.item = item;
	}
	
	
}
