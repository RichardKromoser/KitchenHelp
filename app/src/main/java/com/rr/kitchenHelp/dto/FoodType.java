package com.rr.kitchenHelp.dto;

public enum FoodType {
    FRUIT("Frucht"),
    VEGETABLE("Gemüse"),
    PROTEIN("Protein"),
    PANTRY("Haltbares"),
    SPICE("Gewürz");

    private final String full;

    FoodType(String full) {
        this.full = full;
    }

    public String getFull() {
        return full;
    }

    @Override
    public String toString() {
        return this.getFull();
    }
}
