package io.guessguru.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.guessguru.entities.Item;
import io.guessguru.entities.User;

public interface ItemRepository  extends JpaRepository<Item, Integer>{
	Item findByItemName(String itemName);
	List<Item> findByItemNameLike(String itemName);
	Item findById(int id);
}
