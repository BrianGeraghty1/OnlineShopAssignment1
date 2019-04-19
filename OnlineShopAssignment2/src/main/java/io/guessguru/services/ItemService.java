package io.guessguru.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.guessguru.entities.Item;
import io.guessguru.entities.User;
import io.guessguru.repositories.ItemRepository;

@Service
public class ItemService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	public void addItem(Item item) {
		itemRepository.save(item);
	}
	
	public boolean isItemPresent(String itemName) {
		// TODO Auto-generated method stub
		Item i=itemRepository.findByItemName(itemName);
		if(i!=null)
			return true;
		
		return false;
	}

}
