package com.spring.products.app.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.products.app.entity.Food;
import com.spring.products.app.entity.FoodCategorie;

@Service
public interface JPAService {
	
	 List<FoodCategorie> getAllFoodCategories();
	 void saveFood();
	 
	
	

}
