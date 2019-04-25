package io.guessguru.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import io.guessguru.entities.Item;
import io.guessguru.entities.Rating;

public interface RatingRepository  extends JpaRepository<Rating, Integer>{

	ArrayList<Rating> findByItemId(int id);

	ArrayList<Rating> findByItem(Item item);
 
}
