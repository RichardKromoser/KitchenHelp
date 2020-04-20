package com.rr.kitchenHelp;


public class RecipeIngredient {
    private java.lang.String piece;
    private String unit;
    private java.lang.String name;

    public RecipeIngredient(java.lang.String piece, String unit, java.lang.String name) {
        this.piece = piece;
        this.unit = unit;
        this.name = name;
    }

    public java.lang.String getPiece() {
        return piece;
    }

    public void setPiece(java.lang.String piece) {
        this.piece = piece;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public java.lang.String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }
}
