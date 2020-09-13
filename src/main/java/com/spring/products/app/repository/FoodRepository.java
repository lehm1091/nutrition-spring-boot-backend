package com.spring.products.app.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.spring.products.app.entity.Food;

public interface FoodRepository extends CrudRepository<Food, Long> {
	
	List<Food> findByNameContaining(String name);
	List<Food> findTop10ByOrderByIdDesc();

}
