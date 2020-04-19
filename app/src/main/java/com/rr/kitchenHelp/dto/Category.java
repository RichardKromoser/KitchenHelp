package com.rr.kitchenHelp.dto;

public class Category {
    private String cattype;

    public Category(String id) {
        switch (id) {
            case "0":
                this.cattype = "Frühstück";
            case "1":
                this.cattype = "Hauptgericht";
            case "2":
                this.cattype = "Snack";
        }
    }

    public String getCattype() {
        return cattype;
    }

    public void setCattype(String cattype) {
        this.cattype = cattype;
    }
}
