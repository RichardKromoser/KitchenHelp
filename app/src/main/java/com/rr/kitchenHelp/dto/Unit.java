package com.rr.kitchenHelp.dto;

public enum Unit {
    GRAM("Gramm", "g", "g"),
    KILO("Kilogramm", "kg", "kg"),
    MILLILITER("Milliliter", "ml", "ml"),
    LITER("Liter", "l", "l"),
    PIECE("Stück", "st", "st"),
    TEASPOON("Teelöffel", "tl", "tl"),
    TABLESPOON("Esslöffel", "EL", "EL"),
    TEASPOON_ENG("Teelöffel", "tsp", "tl"),
    TABLESPOON_ENG("Esslöffel", "tbsp", "EL"),
    PIECE_ENG("Stück", "pc", "st"),
    CUP("Cup", "cup", "cup");

    private final String full;
    private final String shortName;
    private final String displayName;

    Unit(String full, String shortName, String displayName) {
        this.full = full;
        this.shortName = shortName;
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
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
