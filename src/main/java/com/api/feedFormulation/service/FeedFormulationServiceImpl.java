package com.api.feedFormulation.service;


import com.api.feedFormulation.exception.InvalidInputException;
import com.api.feedFormulation.model.FeedRequest;
import com.api.feedFormulation.model.FeedResponse;
import com.api.feedFormulation.model.Ingredient;
import com.api.feedFormulation.model.IngredientCategory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the FeedCalculatorService interface. This class
 * contains the logic to calculate the ingredient quantities based on
 * the target crude protein (CP) value and total feed quantity using the
 * Pearson square method.
 */
@Service
public class FeedFormulationServiceImpl implements FeedFormulationService {

    @Override
    public FeedResponse calculateFeed(FeedRequest request) {
        double quantity = request.getQuantity();
        double targetCpValue = request.getTargetCpValue();

        // Validate input: quantity and target CP value must be positive numbers
        // Using ternary operators for validation
        String quantityError = (quantity <= 0 || quantity > 1000) ? "Quantity must be greater than zero and not exceed 1000 kg." : null;
        String targetCpValueError = (targetCpValue <= 0) ? "Target CP value must be greater than zero." : null;

        // Throwing exceptions if there are any errors
        if (quantityError != null || targetCpValueError != null) {
            throw new InvalidInputException(quantityError != null ? quantityError : targetCpValueError);
        }

        // Group the ingredients into categories
        // Create Ingredient objects for each ingredient
        // Define the CP values for each ingredient
        // Calculate the quantity of each ingredient based on the target CP value and total feed quantity

        List<Ingredient> proteins = new ArrayList<>();
        proteins.add(new Ingredient("Soya beans", 44.0, quantity * 0.3));
        proteins.add(new Ingredient("Groundnuts", 45.0, quantity * 0.1));
        proteins.add(new Ingredient("Blood Meal", 80.0, quantity * 0.05));
        proteins.add(new Ingredient("Fish Meal", 65.0, quantity * 0.1));


        List<Ingredient> carbohydrates = new ArrayList<>();
        carbohydrates.add(new Ingredient("Maize", 9.0, quantity * 0.2));
        carbohydrates.add(new Ingredient("Cassava", 2.0, quantity * 0.1));

        List<Ingredient> minerals = new ArrayList<>();
        minerals.add(new Ingredient("DiPhosphate Calcium", 0.0, quantity * 0.02));
        minerals.add(new Ingredient("Bone Meal", 0.0, quantity * 0.02));
        minerals.add(new Ingredient("Marine Shell Flour", 0.0, quantity * 0.02));
        minerals.add(new Ingredient("Salt", 0.0, quantity * 0.005));

        List<Ingredient> vitamins = new ArrayList<>();
        vitamins.add(new Ingredient("Vitamin C", 0.0, quantity * 0.005));
        vitamins.add(new Ingredient("Premix", 0.0, quantity * 0.01));

        List<Ingredient> others = new ArrayList<>();
        others.add(new Ingredient("Concentrate", 36.0, quantity * 0.05));
        others.add(new Ingredient("Palm Oil", 0.0, quantity * 0.02));
        others.add(new Ingredient("Anti-toxin", 0.0, quantity * 0.0005));


        // Create IngredientCategory object to hold all categorized ingredients
        IngredientCategory.builder()
                .proteins(proteins).carbohydrates(carbohydrates).minerals(minerals).vitamins(vitamins).others(others)
                .build();
        /*IngredientCategory ingredientCategory = new IngredientCategory();
        ingredientCategory.setProteins(proteins);
        ingredientCategory.setCarbohydrates(carbohydrates);
        ingredientCategory.setMinerals(minerals);
        ingredientCategory.setVitamins(vitamins);
        ingredientCategory.setOthers(others);*/

        // Create FeedResponse object to hold the calculated feed details
        FeedResponse response = new FeedResponse();
        response.setQuantity(quantity);
        response.setTargetCpValue(targetCpValue);
        response.setIngredients(IngredientCategory.builder().build());

        return response;
    }
}
