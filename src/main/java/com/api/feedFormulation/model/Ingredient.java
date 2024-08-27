package com.api.feedFormulation.model;

/**
 * Model class representing an ingredient used in the feed.
 * Each ingredient has a name, a crude protein (CP) value, and a quantity in kilograms.
 */
public class Ingredient {
    private String name; // Name of the ingredient
    private double cpValue; // Crude protein value of the ingredient
    private double quantityKg; // Quantity of the ingredient in kilograms


    // Constructor to initialize an ingredient with its name, CP value, and quantity.
    public Ingredient(String name, double cpValue, double quantityKg) {
        this.name = name;
        this.cpValue = cpValue;
        this.quantityKg = quantityKg;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCpValue() {
        return cpValue;
    }

    public void setCpValue(double cpValue) {
        this.cpValue = cpValue;
    }

    public double getQuantityKg() {
        return quantityKg;
    }

    public void setQuantityKg(double quantityKg) {
        this.quantityKg = quantityKg;
    }
}
