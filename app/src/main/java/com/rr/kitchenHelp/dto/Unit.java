package com.rr.kitchenHelp.dto;

public enum Unit {
    GRAM ("Gramm", "g"),
    MILLILITER("Milliliter", "ml"),
    PIECE("Stück", "st"),
    TEASPOON("Teelöffel", "tl"),
    TABLESPOON("Esslöffel", "EL");

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
