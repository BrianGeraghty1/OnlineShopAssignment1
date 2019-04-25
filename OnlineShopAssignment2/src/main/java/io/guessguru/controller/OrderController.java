package io.guessguru.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.guessguru.entities.Cart;
import io.guessguru.entities.CartItems;
import io.guessguru.entities.Item;
import io.guessguru.entities.MasterCard;
import io.guessguru.entities.User;
import io.guessguru.entities.UserOrder;
import io.guessguru.entities.Visa;
import io.guessguru.entities.PurchaseFacadeImp;
import io.guessguru.services.CartItemsService;
import io.guessguru.services.CartService;
import io.guessguru.services.ItemService;
import io.guessguru.services.OrderService;
import io.guessguru.services.StockService;
import io.guessguru.services.UserService;

@Controller
public class OrderController {
	@Autowired
	private CartService cartService;

	@Autowired
	private CartItemsService cartItemsService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private UserService userService;

	@Autowired
	private ItemService itemService;

	public PurchaseFacadeImp purchaseFacade;

	@GetMapping("/order")
	public String order(Model model, @RequestParam("id") int id) {
		Cart cart = cartService.findById(id);

		ArrayList<CartItems> cart_items = new ArrayList<CartItems>();
		cart_items.addAll(cart.getCartItems());

		double total = cart.calculateTotal();

		Set<Item> items = new HashSet<>();

		for (int i = 0; i < cart_items.size(); i++) {
			CartItems cartItem = cart_items.get(i);
			Item item = itemService.findById(cartItem.getItem().getId());

			items.add(item);
		}

		model.addAttribute("cart", cart);
		model.addAttribute("items", items);
		model.addAttribute("total", total);

		return "views/order";
	}

	@PostMapping("/order")
	public String order(Model model, @Valid @ModelAttribute("userOrder") UserOrder userOrder,
			@RequestParam("total") double total, HttpServletRequest request) {
		String view = "";
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findOne(auth.getName());
		Set<Item> items = new HashSet<>();
		Cart cart = user.getCart();
		ArrayList<CartItems> cart_items = new ArrayList<CartItems>();
		cart_items.addAll(cart.getCartItems());

		purchaseFacade = new PurchaseFacadeImp();

		if (purchaseFacade.placeOrder(cart_items)) {

			items.addAll(items);

			UserOrder order = new UserOrder(user, items, total);

			if (request.getParameter("payment_method").equals("Visa")) {

				Visa visa = new Visa(request.getParameter("name"), request.getParameter("cardNumber"),
						request.getParameter("expires"));

				if (order.pay(visa, cart)) {
					orderService.saveOrder(order);
					itemService.updateStock(cart_items);
					cartItemsService.emptyCart(cartItemsService.findByCartId(cart.getId()));

					view = "views/visaSuccess";
				} else {
					String visaError = "";
					model.addAttribute("visaError", visaError);
					model.addAttribute("total", total);
					view = "views/order";
				}
			} else if (request.getParameter("payment_method").equals("Mastercard")) {

				MasterCard mastercard = new MasterCard(request.getParameter("name"), request.getParameter("cardNumber"),
						request.getParameter("expires"));

				if (order.pay(mastercard, cart)) {
					orderService.saveOrder(order);
					itemService.updateStock(cart_items);
					cartItemsService.emptyCart(cartItemsService.findByCartId(cart.getId()));

					view = "views/masterSuccess";
				} else {
					String mastercardError = "";
					model.addAttribute("total", total);
					model.addAttribute("mastercardError", mastercardError);
					view = "views/order";
				}
			}
			return view;

		}
		else {
			model.addAttribute("total", total);
		return "views/order";
		}
	}
}
