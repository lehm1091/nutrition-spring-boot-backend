package com.spring.products.app.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "foods")
public class Food implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column
	@NotNull
	@NotEmpty
	private String name;

	@Column(name = "serving_size")
	@NotNull
	private Double servingSize;

	@Column
	@NotNull
	private Double calories;

	@Column(columnDefinition = "double default 0")
	
	private Double protein;

	@Column(columnDefinition = "double default 0")
	private Double cholesterol;

	@Column
	private Double carbohydrate;

	@Column(name = "sugar", columnDefinition = "double default 0")
	private Double sugar;

	@Column(name = "added_sugar",columnDefinition = "double default 0")
	private Double addedSugar;

	@Column(columnDefinition = "double default 0")
	private Double fiber;

	@Column(name = "vitamin_a", columnDefinition = "double default 0")
	private Double vitaminA;

	@Column(name = "vitamin_d", columnDefinition = "double default 0")
	private Double vitaminD;

	@Column(name = "vitamin_c", columnDefinition = "double default 0")
	private Double vitaminC;

	@Column(columnDefinition = "double default 0")
	private Double calcium;

	@Column(columnDefinition = "double default 0")
	private Double iron;

	@Column(columnDefinition = "double default 0")
	private Double potassium;

	@Column(columnDefinition = "double default 0")
	private Double sodium;


	@Column(name = "saturated_fat", columnDefinition = "double default 0")
	private Double saturatedFat;

	@Column(name = "trans_Fat", columnDefinition = "double default 0")
	private Double transFat;
	
	@Column(name = "monounsaturated_fat", columnDefinition = "double default 0")
	private Double monounsaturatedFat;
	
	@Column(name = "polyunSaturated_fat", columnDefinition = "double default 0")
	private Double polyunSaturatedFat;

	
	@Column(name = "fat", columnDefinition = "double default 0")
	private Double fat;
	

	@OneToMany(mappedBy = "food")
	private Set<Ingredient> ingredients;

	@ManyToMany
	@JoinTable(
			name = "food_has_categories",
			joinColumns = @JoinColumn(name = "food_id"),
			inverseJoinColumns = @JoinColumn(name = "food_categorie_id"))
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Set<FoodCategorie> categories;

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

	public Double getServingSize() {
		return servingSize;
	}

	public void setServingSize(Double servingSize) {
		this.servingSize = servingSize;
	}

	public Double getCalories() {
		return calories;
	}

	public void setCalories(Double calories) {
		this.calories = calories;
	}

	public Double getProtein() {
		return protein;
	}

	public void setProtein(Double protein) {
		this.protein = protein;
	}

	public Double getCholesterol() {
		return cholesterol;
	}

	public void setCholesterol(Double cholesterol) {
		this.cholesterol = cholesterol;
	}

	public Double getCarbohydrate() {
		return carbohydrate;
	}

	public void setCarbohydrate(Double carbohydrate) {
		this.carbohydrate = carbohydrate;
	}

	public Double getSugar() {
		return sugar;
	}

	public void setSugar(Double sugar) {
		this.sugar = sugar;
	}

	public Double getAddedSugar() {
		return addedSugar;
	}

	public void setAddedSugar(Double addedSugar) {
		this.addedSugar = addedSugar;
	}

	public Double getFiber() {
		return fiber;
	}

	public void setFiber(Double fibre) {
		this.fiber = fibre;
	}

	public Double getVitaminA() {
		return vitaminA;
	}

	public void setVitaminA(Double vitaminA) {
		this.vitaminA = vitaminA;
	}

	public Double getVitaminD() {
		return vitaminD;
	}

	public void setVitaminD(Double vitaminD) {
		this.vitaminD = vitaminD;
	}

	public Double getVitaminC() {
		return vitaminC;
	}

	public void setVitaminC(Double vitaminC) {
		this.vitaminC = vitaminC;
	}

	public Double getCalcium() {
		return calcium;
	}

	public void setCalcium(Double calcium) {
		this.calcium = calcium;
	}

	public Double getIron() {
		return iron;
	}

	public void setIron(Double iron) {
		this.iron = iron;
	}

	public Double getPotassium() {
		return potassium;
	}

	public void setPotassium(Double potassium) {
		this.potassium = potassium;
	}

	public Double getSodium() {
		return sodium;
	}

	public void setSodium(Double sodium) {
		this.sodium = sodium;
	}

	public Double getSaturatedFat() {
		return saturatedFat;
	}

	public void setSaturatedFat(Double saturatedFat) {
		this.saturatedFat = saturatedFat;
	}

	public Double getTransFat() {
		return transFat;
	}

	public void setTransFat(Double transFat) {
		this.transFat = transFat;
	}

	public Double getMonounsaturatedFat() {
		return monounsaturatedFat;
	}

	public void setMonounsaturatedFat(Double monounsaturatedFat) {
		this.monounsaturatedFat = monounsaturatedFat;
	}

	public Double getPolyunSaturatedFat() {
		return polyunSaturatedFat;
	}

	public void setPolyunSaturatedFat(Double polyunSaturatedFat) {
		this.polyunSaturatedFat = polyunSaturatedFat;
	}

	public Double getFat() {
		return fat;
	}

	public void setFat(Double fat) {
		this.fat = fat;
	}

	public Set<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Set<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public Set<FoodCategorie> getCategories() {
		return categories;
	}

	public void setCategories(Set<FoodCategorie> categories) {
		this.categories = categories;
	}

	
	

	
}
