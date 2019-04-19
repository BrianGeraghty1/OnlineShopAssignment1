package io.guessguru.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import io.guessguru.entities.Item;
import io.guessguru.entities.User;
import io.guessguru.services.ItemService;

@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;
	
	@GetMapping("/addProduct")
	public String addProduct(Model model) {
		model.addAttribute("item", new Item());
		return "views/addProduct";
	}
	
	@PostMapping("/addProduct")
    public String registerUser(@Valid Item item, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "views/addProduct";
		}
		if(itemService.isItemPresent(item.getItemName())) {
			model.addAttribute("exist",true);

			return "views/addProduct";

		}
		itemService.addItem(item);
		
		return "views/successProduct";

	}

}
