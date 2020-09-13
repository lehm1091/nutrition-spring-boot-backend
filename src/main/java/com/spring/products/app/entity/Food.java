package com.spring.products.app.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.*;

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

	@Column(name = "calories_from_fat")
	@NotNull
	private Double caloriesFromFat;

	@Column
	@NotNull
	private Double protein;

	@Column
	private Double cholesterol;

	@Column
	private Double carbohydrate;

	@Column(name = "total_sugar")
	private Double totalSugar;

	@Column(name = "added_sugar")
	private Double addedSugar;

	@Column
	private Double fibre;

	@Column(name = "vitamin_a")
	private Double vitaminA;

	@Column(name = "vitamin_d")
	private Double vitaminD;

	@Column(name = "vitamin_c")
	private Double VitaminC;

	@Column
	private Double calcium;

	@Column
	private Double iron;

	@Column
	private Double potassium;

	@Column
	private Double sodium;

	@Column
	private Double fat;

	@Column(name = "saturated_fat")
	private Double saturatedFat;

	@Column(name = "transFat")
	private Double transFat;

	@Column(name = "total_fat")
	private Double totalFat;

	@OneToMany(mappedBy = "food")
	private Set<Ingredient> ingredients;

	@ManyToMany
	@JoinTable(
			name = "food_has_categories",
			joinColumns = @JoinColumn(name = "food_id"),
			inverseJoinColumns = @JoinColumn(name = "food_categorie_id"))
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

	public Double getCaloriesFromFat() {
		return caloriesFromFat;
	}

	public void setCaloriesFromFat(Double caloriesFromFat) {
		this.caloriesFromFat = caloriesFromFat;
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

	public Double getTotalSugar() {
		return totalSugar;
	}

	public void setTotalSugar(Double totalSugar) {
		this.totalSugar = totalSugar;
	}

	public Double getAddedSugar() {
		return addedSugar;
	}

	public void setAddedSugar(Double addedSugar) {
		addedSugar = addedSugar;
	}

	public Double getFibre() {
		return fibre;
	}

	public void setFibre(Double fibre) {
		this.fibre = fibre;
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
		return VitaminC;
	}

	public void setVitaminC(Double vitaminC) {
		VitaminC = vitaminC;
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

	public Double getFat() {
		return fat;
	}

	public void setFat(Double fat) {
		this.fat = fat;
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

	public Double getTotalFat() {
		return totalFat;
	}

	public void setTotalFat(Double totalFat) {
		this.totalFat = totalFat;
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

	@Override
	public String toString() {
		return String.format(
				"Food [id=%s, name=%s, servingSize=%s, calories=%s, caloriesFromFat=%s, protein=%s, cholesterol=%s, carbohydrate=%s, totalSugar=%s, addedSugar=%s, fibre=%s, vitaminA=%s, vitaminD=%s, VitaminC=%s, calcium=%s, iron=%s, potassium=%s, sodium=%s, fat=%s, saturatedFat=%s, transFat=%s, totalFat=%s, ingredients=%s, categories=%s]",
				id, name, servingSize, calories, caloriesFromFat, protein, cholesterol, carbohydrate, totalSugar,
				addedSugar, fibre, vitaminA, vitaminD, VitaminC, calcium, iron, potassium, sodium, fat, saturatedFat,
				transFat, totalFat, ingredients, categories);
	}

	
}
