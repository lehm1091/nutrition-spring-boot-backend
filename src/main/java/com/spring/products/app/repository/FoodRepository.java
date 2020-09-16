package com.spring.products.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.spring.products.app.entity.Food;

public interface FoodRepository extends JpaRepository<Food, Long> {
	
	List<Food> findByNameContains(String name);
	List<Food> findTop10ByOrderByIdDesc();
	List<Food> findByNameIsContaining(String name);

}
