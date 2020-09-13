package com.spring.products.app.controllers.response;

public class FoodHasCategories {
	private Long foodId;
	private String categoryNames[];
	public Long getFoodId() {
		return foodId;
	}
	public void setFoodId(Long foodId) {
		this.foodId = foodId;
	}
	public String[] getCategoryNames() {
		return categoryNames;
	}
	public void setCategoryNames(String[] categoryNames) {
		this.categoryNames = categoryNames;
	}

}
