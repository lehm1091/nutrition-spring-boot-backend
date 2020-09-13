package com.spring.products.app.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.products.app.controllers.response.FoodHasCategories;
import com.spring.products.app.entity.Food;
import com.spring.products.app.entity.FoodCategorie;
import com.spring.products.app.repository.FoodCategorieRepository;
import com.spring.products.app.repository.FoodRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ApiRestController {

	final private Logger logger = LoggerFactory.getLogger(ApiRestController.class);

	@Autowired
	private FoodRepository foodRepository;
	@Autowired
	private FoodCategorieRepository categoryRepository;

	@PostMapping("/foods")
	public ResponseEntity<Food> createFood(@RequestBody Food food) {

		try {
			logger.info("_food:" + food.toString());
			Food _food = foodRepository.save(food);
			return new ResponseEntity<>(_food, HttpStatus.CREATED);

		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/foods/categories")
	public ResponseEntity<Food> addCategoriesToFood(@RequestBody FoodHasCategories response) {

		try {
			if (response.getCategoryNames().length > 0) {
				logger.info("response:" + response.toString());
				Food _food = foodRepository.findById(response.getFoodId()).orElse(null);
				List<FoodCategorie> _categories = new ArrayList<>(_food.getCategories());
				for (String actual : response.getCategoryNames()) {
					_categories.add(categoryRepository.findByName(actual));
				}

				_food.setCategories(new HashSet<FoodCategorie>(_categories));
				foodRepository.save(_food);

				return new ResponseEntity<>(_food, HttpStatus.CREATED);
			}

			else {
				return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);

			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/foods/categories")
	public ResponseEntity<List<FoodCategorie>> getAllCategories() {
		List<FoodCategorie> _categories = new ArrayList<FoodCategorie>();
		try {
			categoryRepository.findAll().forEach(_categories::add);

			if (_categories.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			logger.info("size of list", _categories.size() + "");

			return new ResponseEntity<>(_categories, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getCause().getMessage());
			return new ResponseEntity<>(_categories, HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

	@GetMapping("/foods")
	public ResponseEntity<List<Food>> getAllTutorials(@RequestParam(required = false) String name,
			@RequestParam(required = false) Boolean all) {
		try {
			List<Food> foods = new ArrayList<Food>();

			if (name != null) {
				if (name.length() < 1) {
					foodRepository.findTop10ByOrderByIdDesc().forEach(foods::add);
					logger.info(name);
					logger.info("empty");
				} else {
					foodRepository.findByNameContaining(name).forEach(foods::add);
					logger.info("containing");
				}

			} else if (all == true) {
				foodRepository.findAll().forEach(foods::add);

			} else {
				foodRepository.findTop10ByOrderByIdDesc().forEach(foods::add);
			}

			if (foods.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(foods, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/foods/categories/{category}")
	public ResponseEntity<List<Food>> getAllTutorials(@PathVariable String category) {
		try {
			List<Food> foods = new ArrayList<Food>();

			if (category != null) {

				categoryRepository.findByName(category).getFoodItems().forEach(foods::add);
			}

			if (foods.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(foods, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/foods/{id}")
	public ResponseEntity<Food> getFoodById(@PathVariable Long id) {
		Optional<Food> _food = foodRepository.findById(id);
		if (_food.isPresent()) {
			return new ResponseEntity<Food>(_food.get(), HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

	@GetMapping("/foods/compare/{ids}")
	public ResponseEntity<List<Food>> compareFoods(@PathVariable List<Long> ids) {
			try {
				List<Food> foods = new ArrayList<Food>();

				if (ids != null || !ids.isEmpty()) {

					foodRepository.findAllById(ids).forEach(foods::add);
					
				}

				if (foods.isEmpty()) {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);}
				
				return new ResponseEntity<>(foods, HttpStatus.OK);
				
				
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}

	}

}
