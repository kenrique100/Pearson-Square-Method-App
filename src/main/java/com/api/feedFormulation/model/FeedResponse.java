package com.api.feedFormulation.model;

/**
 * Model class representing the response body for the feed calculation API.
 * This class contains the quantity of feed, the target crude protein (CP) value,
 * and the categorized list of ingredients.
 */
public class FeedResponse {
    private double quantity;                // Total quantity of feed in kilograms
    private double targetCpValue;           // Target crude protein value as a percentage
    private IngredientCategory ingredients; // Categorized list of ingredients used in the feed
    // private String customerId;
    //    private String customerName;
    //    private Date createdDate;

    // Getters and Setters
    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getTargetCpValue() {
        return targetCpValue;
    }

    public void setTargetCpValue(double targetCpValue) {
        this.targetCpValue = targetCpValue;
    }

    public IngredientCategory getIngredients() {
        return ingredients;
    }

    public void setIngredients(IngredientCategory ingredients) {
        this.ingredients = ingredients;
    }
    /*public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }*/
}
