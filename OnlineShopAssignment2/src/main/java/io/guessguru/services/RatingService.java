package io.guessguru.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.guessguru.entities.Item;
import io.guessguru.entities.Rating;
import io.guessguru.repositories.RatingRepository;

@Service
public class RatingService {

	@Autowired
	private RatingRepository ratingRepository;
	
	public ArrayList<Rating> findByItem(Item item) {
		return ratingRepository.findByItem(item);
	}
	
	public void saveRating(Rating rating) {
		ratingRepository.save(rating);
	}
	

}
