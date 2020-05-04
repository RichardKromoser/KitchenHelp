package com.rr.kitchenHelp.dto;

import java.util.Arrays;
import java.util.List;

public class Recipe {
    private String image;
    private String category;
    private String name;
    private String time;
    private String ingredients;
    private String instructions;
    //private List<String> ingredients= new ArrayList<>();
    //private List<String> instructions = new ArrayList<>();

    public Recipe(String image, String instructions, String name, String ingredients, String time, String category) {
        this.image = image;
        this.category = category;
        this.name = name;
        this.time = time;
        this.ingredients=ingredients;
        this.instructions=instructions;
        //StringToList(ingredients, this.ingredients);
        //StringToList(instructions, this.instructions);
    }

    public Recipe(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    /*public List<String> getIngredients() {
        return ingredients;
    }*/
    public String getIngredients() {
        return ingredients;
    }
    /*public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }*/
    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    /*public List<String> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<String> instructions) {
        this.instructions = instructions;
    }*/

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    /*public void addIngredient(String ingredient) {
        this.ingredients.add(ingredient);
    }

    public void addInstruction(String instruction) {
        this.instructions.add(instruction);
    }*/

    private void StringToList(String listAsString, List<String> listToAdd) {
        if (listAsString != null && !listAsString.isEmpty()) {
            String[] ingredientsAsArray = listAsString.split("\\|");

            listToAdd.addAll(Arrays.asList(ingredientsAsArray));
        } else {
            listToAdd.add("Kein Eintrag vorhanden");
        }
    }
}
