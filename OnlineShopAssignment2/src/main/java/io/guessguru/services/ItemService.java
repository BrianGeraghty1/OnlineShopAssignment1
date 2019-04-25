package io.guessguru.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.guessguru.entities.CartItems;
import io.guessguru.entities.Item;
import io.guessguru.repositories.ItemRepository;

@Service
public class ItemService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	public void saveItem(Item item) {
		itemRepository.save(item);
	}
	
	public boolean isItemPresent(String itemName) {
		// TODO Auto-generated method stub
		Item i=itemRepository.findByItemName(itemName);
		if(i!=null)
			return true;
		
		return false;
	}
	
	public List<Item> findByItemName(String itemName) {
		return  itemRepository.findByItemNameLike("%"+itemName+"%");
	}
	
	public Item findById(int id) {
		return itemRepository.findById(id);
	}
	
	public void updateStock(ArrayList<CartItems> cartItems) {
		for (int i =0; i< cartItems.size(); i++) {
			CartItems cartItem = cartItems.get(i);
			Item item = cartItem.getItem();
			
			int stockLevel = item.getQuantity() - cartItem.getAmount();
			item.setQuantity(stockLevel);
			this.saveItem(item);
		}
	}
	
	public void deleteItem(int id) {
		itemRepository.delete(id);
	}

}
