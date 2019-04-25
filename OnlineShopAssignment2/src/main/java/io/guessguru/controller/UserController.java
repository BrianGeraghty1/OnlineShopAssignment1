package io.guessguru.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.guessguru.entities.User;
import io.guessguru.entities.UserOrder;
import io.guessguru.services.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/users")
	public String listUsers(Model model, @RequestParam(defaultValue = "") String name) {
		model.addAttribute("users", userService.findByName(name));
		return "views/list";
	}
	
	@GetMapping("/viewHistory")
	public String orderHistory(Model model, @RequestParam("email") String email) {
		User user = userService.findOne(email);
		model.addAttribute("name", user.getName());
		model.addAttribute("orders", user.getOrders());
		
		
		
		return "views/viewHistory";
	}

}
