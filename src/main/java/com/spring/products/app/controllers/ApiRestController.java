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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	@PostMapping("/categories")
	public ResponseEntity<FoodCategorie> createFood(@RequestBody FoodCategorie category) {

		try {

			FoodCategorie _category = categoryRepository.save(category);
			return new ResponseEntity<>(_category, HttpStatus.CREATED);

		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/foods/{foodid}/add-categories")
	public ResponseEntity<Food> addCategoriesToFood(@PathVariable Long foodid, @RequestBody Long[] categoriesids) {

		try {
			Food _food = foodRepository.findById(foodid).orElse(null);
			List<FoodCategorie> _categories = new ArrayList<>(_food.getCategories());
			
			List<Long> ids=new ArrayList<>();
			for(Long actual:categoriesids) {
				ids.add(actual);
			}
			categoryRepository.findAllById(ids).forEach(_categories::add);
			_food.setCategories(new HashSet<FoodCategorie>(_categories));
			foodRepository.save(_food);
			return new ResponseEntity<>(_food, HttpStatus.OK);

		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@GetMapping("/foods/{foodid}")
	public ResponseEntity<Food> findOneFood(@PathVariable Long foodid) {

		try {
			Food _food = foodRepository.findById(foodid).orElse(null);
			
			if(_food!=null) {
				return new ResponseEntity<>(_food, HttpStatus.OK);
				
			}
			else {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
	
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/categories")
	public ResponseEntity<List<FoodCategorie>> getAllCategories() {
		List<FoodCategorie> _categories = new ArrayList<FoodCategorie>();
		try {
			categoryRepository.findAll().forEach(_categories::add);

			if (_categories.isEmpty()) {
				return new ResponseEntity<>(_categories,HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(_categories, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getCause().getMessage());
			return new ResponseEntity<>(_categories, HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

	private Sort.Direction getSortDirection(String direction) {
		if (direction.equals("asc")) {
			return Sort.Direction.ASC;
		} else if (direction.equals("desc")) {
			return Sort.Direction.DESC;
		}

		return Sort.Direction.ASC;
	}

	@GetMapping("/foods")
	public ResponseEntity<List<Food>> findFoods(
			@RequestParam(required = false) List<Long> ids,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer size,
			@RequestParam(required = false) String[] sort) {

		List<Food> foods = new ArrayList<Food>();
		try {
			if (ids == null && name == null && page == null && size == null && sort == null) {
				foodRepository.findAll().forEach(foods::add);
			} else {

				if (ids != null && name == null && page == null && size == null) {
					foodRepository.findAllById(ids).forEach(foods::add);
				} else if (ids == null && name != null && page == null && size == null) {
					logger.info("name " + name);
					foodRepository.findByNameIsContaining(name).forEach(foods::add);

				} else if ((ids == null && name == null) && (size > 0 && page >= 0)) {
					Pageable pageable;
					if (sort != null) {
						List<Order> orders = this.getOrders(sort);
						pageable = PageRequest.of(page, size, Sort.by(orders));
						logger.info("inside sorted");
					} else {
						pageable = PageRequest.of(page, size);
						logger.info("inside pageable");
					}

					foodRepository.findAll(pageable).forEach(foods::add);

				}

			}

			if (foods.isEmpty()) {
				return new ResponseEntity<>(foods, HttpStatus.NOT_FOUND);

			} else {
				return new ResponseEntity<>(foods, HttpStatus.OK);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	private List<Order> getOrders(String[] sort) {

		List<Order> orders = new ArrayList<Order>();

		if (sort[0].contains(",")) {
			// will sort more than 2 fields
			// sortOrder="field, direction"
			for (String sortOrder : sort) {
				String[] _sort = sortOrder.split(",");
				orders.add(new Order(getSortDirection(_sort[1]), _sort[0]));
			}
		} else {
			// sort=[field, direction]
			orders.add(new Order(getSortDirection(sort[1]), sort[0]));
		}

		return orders;

	}

	@GetMapping("/categories/{category}")
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
			logger.error(e.toString());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
