package com.rr.kitchenHelp.dto;

public class Ingredient {
    private String name;
    private FoodType foodType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FoodType getFoodType() {
        return foodType;
    }

    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
    }

    @Override
    public String toString(){
        return this.name;
    }

    public Ingredient(String name, FoodType foodType) {
        this.name = name;
        this.foodType = foodType;
    }

    public Ingredient(){

    }
}
