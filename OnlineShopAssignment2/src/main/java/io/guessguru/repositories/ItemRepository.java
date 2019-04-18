package io.guessguru.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.guessguru.entities.Item;

public interface ItemRepository  extends JpaRepository<Item, Integer>{
	Item findByItemName(String itemName);
 
}
