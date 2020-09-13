package com.spring.products.app.entity;

import java.io.Serializable;
import java.util.Set;

import javax.annotation.*;
import javax.persistence.*;

@Entity
@Table(name="Recipes")
public class Recipe implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column
	private String name;
	
	@Column
	private Double servings;
	
	@Column(name="serving_size")
	private Double servingSize;
	
	@Column
	private String note;
	
	@OneToMany(mappedBy="recipe")
	private Set<Ingredient> ingredients;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getServings() {
		return servings;
	}

	public void setServings(Double servings) {
		this.servings = servings;
	}

	public Double getServingSize() {
		return servingSize;
	}

	public void setServingSize(Double servingSize) {
		this.servingSize = servingSize;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Set<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Set<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	
	
	
	
	
	
}
