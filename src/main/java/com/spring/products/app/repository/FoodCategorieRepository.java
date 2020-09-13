package com.spring.products.app.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.spring.products.app.entity.FoodCategorie;

public interface FoodCategorieRepository extends CrudRepository<FoodCategorie, Long> {
	FoodCategorie findByName(String name);

}
