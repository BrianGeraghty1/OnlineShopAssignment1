package io.guessguru.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.guessguru.entities.Item;
import io.guessguru.entities.Rating;
import io.guessguru.entities.User;
import io.guessguru.services.ItemService;
import io.guessguru.services.RatingService;
import io.guessguru.services.UserService;

@Controller
public class RatingController {

	@Autowired
	private ItemService itemService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RatingService ratingService;

	@GetMapping("/viewItem")
	public String viewItem(Model model, @RequestParam("id") int id) {
		
		Item item = itemService.findById(id);
		System.out.println(item.getItemName());
		ArrayList<Rating> ratings = ratingService.findByItem(item);
		System.out.println(ratings.get(0).getComment());
		model.addAttribute("ratings", ratings);
		model.addAttribute("rating", new Rating());
		model.addAttribute("name", item.getItemName());
		model.addAttribute("price", item.getPrice());
		model.addAttribute("manufacturer", item.getManufacturer());
		model.addAttribute("category", item.getCategory());
		model.addAttribute("item", item);

		return "views/itemInfo";
	}

	@PostMapping("/rating")
	public String rateItem(@Valid @ModelAttribute("rating") Rating rating, Model model, BindingResult bindingResult,
			@RequestParam("id") int id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findOne(auth.getName());

		Item item = itemService.findById(id);
		
		ArrayList<Rating> temp_ratings = ratingService.findByItem(item);

		for (int i = 0; i < temp_ratings.size(); i++) {
			Rating ratingExists = temp_ratings.get(i);

			if (ratingExists.getUser().equals(user)) {
				bindingResult.rejectValue("id", "error.rating_exists");
				if (bindingResult.hasErrors()) {
					String errorRatingExists = "";
					model.addAttribute("errorRatingExists", errorRatingExists);
					model.addAttribute("rating", new Rating());
					model.addAttribute("ratings", temp_ratings);
					model.addAttribute("item", item);
					return "views/itemInfo";
				}
			}
		}

		rating.setItem(item);
		rating.setUser(user);
		ratingService.saveRating(rating);

		ArrayList<Rating> ratings = ratingService.findByItem(item);

		String ratingSuccess = "";
		model.addAttribute("ratingSuccess", ratingSuccess);
		model.addAttribute("ratings", ratings);
		model.addAttribute("rating", new Rating());
		model.addAttribute("name", item.getItemName());
		model.addAttribute("price", item.getPrice());
		model.addAttribute("manufacturer", item.getManufacturer());
		model.addAttribute("category", item.getCategory());
		model.addAttribute("item", item);

		return "views/itemInfo";
	}
}
