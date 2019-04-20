package io.guessguru.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.guessguru.entities.Cart;
import io.guessguru.entities.CartItems;
import io.guessguru.entities.Item;
import io.guessguru.entities.User;
import io.guessguru.services.CartItemsService;
import io.guessguru.services.CartService;
import io.guessguru.services.ItemService;
import io.guessguru.services.UserService;

@Controller
public class CartController {
	@Autowired
	private UserService userService;
	@Autowired
	private CartService cartService;
	@Autowired
	private ItemService itemService;
	@Autowired
	private CartItemsService cartItemsService;

	@GetMapping("/viewCart")
	public String viewCart(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findOne(auth.getName());
		Cart cart = user.getCart();
		// Set <Item> items = cart.getItems();
		List<CartItems> cartItems = new ArrayList<>();
		cartItems.addAll(cart.getCartItems());
		model.addAttribute("cartItems", cartItems);
		model.addAttribute("name", user.getName());
		double price = 0;
		for (int j = 0; j < cartItems.size(); j++) {
			price = price + ((cartItems.get(j).getItem().getPrice()) * (cartItems.get(j).getAmount()));
		}
		model.addAttribute("total", price);
		return "views/viewCart";
	}

	@GetMapping("/addToCart")
	public String addToCart(Model model, @RequestParam("id") int id, @RequestParam(defaultValue = "") String itemName) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findOne(auth.getName());

		Cart cart = user.getCart();
		Item item = itemService.findById(id);
		if (item.getQuantity() > 0) {
			ArrayList<CartItems> cart_items = new ArrayList<CartItems>();
			cart_items.addAll(cart.getCartItems());
			boolean exist = true;

			for (int i = 0; i < cart_items.size(); i++) {
				CartItems current = cart_items.get(i);
				if (current.getItem() == item) {

					int temp = cart_items.get(i).getAmount();
					cart_items.get(i).setAmount(temp + 1);

					cartItemsService.saveCartItems(cart_items.get(i));
					Set<CartItems> updatedList = new HashSet<>(cart_items);
					cart.setCartItems(updatedList);
					exist = false;
				}
			}

			if (exist) {
				CartItems cartItems = new CartItems(cart, item, 1);
				cartItemsService.saveCartItems(cartItems);
				cart_items.add(cartItems);

				Set<CartItems> updatedList = new HashSet<>(cart_items);

				cart.setCartItems(updatedList);
			}

			cartService.saveCart(cart);

			String successMessage = "";
			model.addAttribute("successMessage", successMessage);
			List<Item> items = itemService.findByItemName(itemName);
			model.addAttribute("items", items);

			return "views/listItems";
		} else {
			return "views/noStock";
		}

	}
}
