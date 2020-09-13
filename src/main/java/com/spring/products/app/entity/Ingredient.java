package com.spring.products.app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Ingredients")

public class Ingredient implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	

	@ManyToOne
	@JoinColumn(name="food_id")
	private Food food;
	

	@ManyToOne
	@JoinColumn(name="recipe_id")
	private Recipe recipe;
	
	@Column(name="number_of_units")
	private Double numberOfUnits;

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public Double getNumberOfUnits() {
		return numberOfUnits;
	}

	public void setNumberOfUnits(Double numberOfUnits) {
		this.numberOfUnits = numberOfUnits;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	
}
