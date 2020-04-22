package com.rr.kitchenHelp.dto;

public enum Unit {
    GRAM ("Gramm", "g"),
    KILO("Kilogramm", "kg"),
    MILLILITER("Milliliter", "ml"),
    LITER("Liter", "l"),
    PIECE("Stück", "st"),
    TEASPOON("Teelöffel", "tl"),
    TABLESPOON("Esslöffel", "EL"),
    TEASPOON_ENG("Teelöffel", "tsp"),
    TABLESPOON_ENG("Esslöffel", "tbsp"),
    PIECE_ENG("Stück", "pc");

    private final String full;
    private final String shortName;

    Unit(String full, String shortName) {
        this.full = full;
        this.shortName = shortName;
    }

    public String getFull() {
        return full;
    }

    public String getShortName() {
        return shortName;
    }

    @Override
    public String toString() {
        return this.getShortName();
    }
}
