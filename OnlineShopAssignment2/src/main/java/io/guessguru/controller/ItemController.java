package io.guessguru.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.guessguru.entities.Item;
import io.guessguru.services.CartService;
import io.guessguru.services.ItemService;
import io.guessguru.services.UserService;

@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;
	@Autowired
	private UserService userService;
	@Autowired
	private CartService cartService;

	@GetMapping("/addProduct")
	public String addProduct(Model model) {
		model.addAttribute("item", new Item());
		return "views/addProduct";
	}

	@PostMapping("/addProduct")
	public String registerUser(@Valid Item item, BindingResult bindingResult, Model model,
			@RequestParam("category") String category) {
		if (bindingResult.hasErrors()) {
			return "views/addProduct";
		}
		if (itemService.isItemPresent(item.getItemName())) {
			model.addAttribute("exist", true);

			return "views/addProduct";

		} else if (category.equalsIgnoreCase("default")) {
			model.addAttribute("selection", true);

			return "views/addProduct";
		}
		itemService.saveItem(item);

		return "views/successProduct";

	}

	@GetMapping("/updateQuantity")
	public String updateQuantity(Model model, @RequestParam int id) {
		Item item = itemService.findById(id);
		model.addAttribute("id", item.getId());
		model.addAttribute("quantity", item.getQuantity());
		return "views/updateQuantity";
	}

	@PostMapping("/updateQuantity")
	public String addQuantity(Model model, @RequestParam int quantity, @RequestParam int id) {
		Item item = itemService.findById(id);
		item.setQuantity(item.getQuantity() + quantity);
		itemService.saveItem(item);
		return "views/listItems";
	}

	@GetMapping("/products")
	public String listUsers(Model model, @RequestParam(defaultValue = "") String itemName) {
		model.addAttribute("items", itemService.findByItemName(itemName));
		return "views/listItems";
	}
	@DeleteMapping("/deleteItem")
	public String deleteProduct(Model model, @RequestParam("id") int id) {
		itemService.deleteItem(id);
		model.addAttribute("items", itemService.findByItemName(""));
		
		return "views/listItems";
		
	}
}
