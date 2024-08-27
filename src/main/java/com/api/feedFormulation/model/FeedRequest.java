package com.api.feedFormulation.model;

/**
 * Model class representing the request body for the feed calculation API.
 * This class contains the quantity of the feed and the target crude protein (CP) value.
 */
public class FeedRequest {
    private double quantity;  // Quantity of feed in kilograms
    private double targetCpValue;  // Target crude protein value as a percentage
    //private String customerName;
    // Getters and Setters
    // Gets the quantity of feed in kilograms.
    public double getQuantity() {
        return quantity;
    }

    //Sets the quantity of feed in kilograms.
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    // Gets the target crude protein value as a percentage.
    public double getTargetCpValue() {
        return targetCpValue;
    }

    // Sets the target crude protein value as a percentage.
    public void setTargetCpValue(double targetCpValue) {
        this.targetCpValue = targetCpValue;
    }
    /*public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }*/
}
