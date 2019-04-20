package io.guessguru.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import io.guessguru.entities.Cart;
import io.guessguru.entities.Item;
import io.guessguru.entities.User;
import io.guessguru.services.CartService;
import io.guessguru.services.UserService;

@Controller
public class CartController {
	@Autowired
	private UserService userService;
	@Autowired
	private CartService cartService;
	
	@GetMapping("/viewCart")
	public String viewCart(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findOne(auth.getName());
		Cart cart = user.getCart();
		Set <Item> items = cart.getItems();
		model.addAttribute("items", items);
		model.addAttribute("name", user.getName());
		
		return "views/shoppingCart";
	}
}
