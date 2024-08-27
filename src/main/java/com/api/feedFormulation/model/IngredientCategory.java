package com.api.feedFormulation.model;

import lombok.*;

import java.util.List;

/**
 * Model class representing a category of ingredients.
 * Ingredients are grouped into categories such as proteins, carbohydrates, minerals, vitamins, and others.
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Builder
@Getter
@Setter
public class IngredientCategory {
    private  List<Ingredient> proteins;       // List of protein ingredients
    private   List<Ingredient> carbohydrates;  // List of carbohydrate ingredients
    private List<Ingredient> minerals;       // List of minerals ingredients
    private List<Ingredient> vitamins;       // List of vitamins ingredients
    private  List<Ingredient> others;         // List of others ingredients
}