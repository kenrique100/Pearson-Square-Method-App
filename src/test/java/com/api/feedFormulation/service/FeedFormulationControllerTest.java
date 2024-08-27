package com.api.feedFormulation.service;

import com.api.feedFormulation.controller.FeedFormulationController;
import com.api.feedFormulation.model.FeedRequest;
import com.api.feedFormulation.model.FeedResponse;
import com.api.feedFormulation.model.Ingredient;
import com.api.feedFormulation.model.IngredientCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * Unit test class for FeedFormulationController.
 * This class tests the endpoints for feed calculation.
 */
@EnableWebMvc
public class FeedFormulationControllerTest {

    private MockMvc mockMvc;

    @Mock
    private FeedFormulationService feedFormulationService;

    @InjectMocks
    private FeedFormulationController feedFormulationController;

    /**
     * Initialize the mock environment before each test.
     * This method sets up the MockMvc object to simulate HTTP requests.
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(feedFormulationController).build();
    }

    /**
     * Test for a valid feed calculation request.
     * This method sends a POST request to the API and verifies the response.
     */
    @Test
    public void calculateFeed_validInput_returnsCorrectResponse() throws Exception {
        // Arrange
        FeedRequest request = new FeedRequest();
        request.setQuantity(1); // 1 kg of feed
        request.setTargetCpValue(20); // 20% target CP value

        // Create realistic ingredient data for the response
        Ingredient soya = new Ingredient("Soya beans", 44.0, 0.3);
        Ingredient groundnuts = new Ingredient("Groundnuts", 45.0, 0.2);
        Ingredient bloodMeal = new Ingredient("Blood Meal", 85.0, 0.1);
        Ingredient fishMeal = new Ingredient("Fish Meal", 72.0, 0.15);
        Ingredient maize = new Ingredient("Maize", 8.5, 0.5);
        Ingredient cassava = new Ingredient("Cassava", 2.0, 0.2);
        Ingredient diPhosphateCalcium = new Ingredient("DiPhosphate Calcium", 0.0, 0.01);
        Ingredient boneMeal = new Ingredient("Bone Meal", 0.0, 0.01);
        Ingredient marineShellFlour = new Ingredient("Marine Shell Flour", 0.0, 0.01);
        Ingredient salt = new Ingredient("Salt", 0.0, 0.005);
        Ingredient vitaminC = new Ingredient("Vitamin C", 0.0, 0.002);
        Ingredient premix = new Ingredient("Premix", 0.0, 0.002);
        Ingredient concentrate = new Ingredient("Concentrate", 0.0, 0.02);
        Ingredient palmOil = new Ingredient("Palm Oil", 0.0, 0.015);
        Ingredient antiToxin = new Ingredient("Anti-toxin", 0.0, 0.005);

        // Group ingredients by category
        List<Ingredient> proteins = new ArrayList<>();
        proteins.add(soya);
        proteins.add(groundnuts);
        proteins.add(bloodMeal);
        proteins.add(fishMeal);

        List<Ingredient> carbohydrates = new ArrayList<>();
        carbohydrates.add(maize);
        carbohydrates.add(cassava);

        List<Ingredient> minerals = new ArrayList<>();
        minerals.add(diPhosphateCalcium);
        minerals.add(boneMeal);
        minerals.add(marineShellFlour);
        minerals.add(salt);

        List<Ingredient> vitamins = new ArrayList<>();
        vitamins.add(vitaminC);
        vitamins.add(premix);

        List<Ingredient> others = new ArrayList<>();
        others.add(concentrate);
        others.add(palmOil);
        others.add(antiToxin);

        IngredientCategory ingredientCategory = new IngredientCategory();
        ingredientCategory.setProteins(proteins);
        ingredientCategory.setCarbohydrates(carbohydrates);
        ingredientCategory.setMinerals(minerals);
        ingredientCategory.setVitamins(vitamins);
        ingredientCategory.setOthers(others);

        FeedResponse response = new FeedResponse();
        response.setQuantity(1.0); // Total quantity of feed in kg
        response.setTargetCpValue(20.0); // Target CP value in percentage
        response.setIngredients(ingredientCategory);

        // Mock the service method to return the prepared response
        when(feedFormulationService.calculateFeed(any(FeedRequest.class))).thenReturn(response);

        // Act & Assert
        mockMvc.perform(post("/api/feed/calculate")
                        .contentType("application/json")
                        .content("{\"quantity\":1,\"targetCpValue\":20}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.quantity", is(1.0)))
                .andExpect(jsonPath("$.targetCpValue", is(20.0)))
                .andExpect(jsonPath("$.ingredients.proteins", hasSize(4))) // Check number of protein ingredients
                .andExpect(jsonPath("$.ingredients.proteins[0].name", is("Soya beans")))
                .andExpect(jsonPath("$.ingredients.proteins[0].cpValue", is(44.0)))
                .andExpect(jsonPath("$.ingredients.proteins[0].quantityKg", is(0.3)))
                .andExpect(jsonPath("$.ingredients.proteins[1].name", is("Groundnuts")))
                .andExpect(jsonPath("$.ingredients.proteins[1].cpValue", is(45.0)))
                .andExpect(jsonPath("$.ingredients.proteins[1].quantityKg", is(0.2)))
                .andExpect(jsonPath("$.ingredients.proteins[2].name", is("Blood Meal")))
                .andExpect(jsonPath("$.ingredients.proteins[2].cpValue", is(85.0)))
                .andExpect(jsonPath("$.ingredients.proteins[2].quantityKg", is(0.1)))
                .andExpect(jsonPath("$.ingredients.proteins[3].name", is("Fish Meal")))
                .andExpect(jsonPath("$.ingredients.proteins[3].cpValue", is(72.0)))
                .andExpect(jsonPath("$.ingredients.proteins[3].quantityKg", is(0.15)))
                .andExpect(jsonPath("$.ingredients.carbohydrates", hasSize(2))) // Check number of carbohydrate ingredients
                .andExpect(jsonPath("$.ingredients.carbohydrates[0].name", is("Maize")))
                .andExpect(jsonPath("$.ingredients.carbohydrates[0].cpValue", is(8.5)))
                .andExpect(jsonPath("$.ingredients.carbohydrates[0].quantityKg", is(0.5)))
                .andExpect(jsonPath("$.ingredients.carbohydrates[1].name", is("Cassava")))
                .andExpect(jsonPath("$.ingredients.carbohydrates[1].cpValue", is(2.0)))
                .andExpect(jsonPath("$.ingredients.carbohydrates[1].quantityKg", is(0.2)))
                .andExpect(jsonPath("$.ingredients.minerals", hasSize(4))) // Check number of mineral ingredients
                .andExpect(jsonPath("$.ingredients.minerals[0].name", is("DiPhosphate Calcium")))
                .andExpect(jsonPath("$.ingredients.minerals[0].cpValue", is(0.0)))
                .andExpect(jsonPath("$.ingredients.minerals[0].quantityKg", is(0.01)))
                .andExpect(jsonPath("$.ingredients.minerals[1].name", is("Bone Meal")))
                .andExpect(jsonPath("$.ingredients.minerals[1].cpValue", is(0.0)))
                .andExpect(jsonPath("$.ingredients.minerals[1].quantityKg", is(0.01)))
                .andExpect(jsonPath("$.ingredients.minerals[2].name", is("Marine Shell Flour")))
                .andExpect(jsonPath("$.ingredients.minerals[2].cpValue", is(0.0)))
                .andExpect(jsonPath("$.ingredients.minerals[2].quantityKg", is(0.01)))
                .andExpect(jsonPath("$.ingredients.minerals[3].name", is("Salt")))
                .andExpect(jsonPath("$.ingredients.minerals[3].cpValue", is(0.0)))
                .andExpect(jsonPath("$.ingredients.minerals[3].quantityKg", is(0.005)))
                .andExpect(jsonPath("$.ingredients.vitamins", hasSize(2))) // Check number of vitamin ingredients
                .andExpect(jsonPath("$.ingredients.vitamins[0].name", is("Vitamin C")))
                .andExpect(jsonPath("$.ingredients.vitamins[0].cpValue", is(0.0)))
                .andExpect(jsonPath("$.ingredients.vitamins[0].quantityKg", is(0.002)))
                .andExpect(jsonPath("$.ingredients.vitamins[1].name", is("Premix")))
                .andExpect(jsonPath("$.ingredients.vitamins[1].cpValue", is(0.0)))
                .andExpect(jsonPath("$.ingredients.vitamins[1].quantityKg", is(0.002)))
                .andExpect(jsonPath("$.ingredients.others", hasSize(3))) // Check number of other ingredients
                .andExpect(jsonPath("$.ingredients.others[0].name", is("Concentrate")))
                .andExpect(jsonPath("$.ingredients.others[0].cpValue", is(0.0)))
                .andExpect(jsonPath("$.ingredients.others[0].quantityKg", is(0.02)))
                .andExpect(jsonPath("$.ingredients.others[1].name", is("Palm Oil")))
                .andExpect(jsonPath("$.ingredients.others[1].cpValue", is(0.0)))
                .andExpect(jsonPath("$.ingredients.others[1].quantityKg", is(0.015)))
                .andExpect(jsonPath("$.ingredients.others[2].name", is("Anti-toxin")))
                .andExpect(jsonPath("$.ingredients.others[2].cpValue", is(0.0)))
                .andExpect(jsonPath("$.ingredients.others[2].quantityKg", is(0.005)));

        // Verify that the service method was called once
        verify(feedFormulationService, times(1)).calculateFeed(any(FeedRequest.class));
    }

    /**
     * Test for an invalid feed calculation request where the target CP value is out of range.
     * This method sends a POST request to the API and expects a bad request response.
     */
    @Test
    void calculateFeed_invalidInput_returnsBadRequest() throws Exception {
        // Example of invalid input JSON
        String invalidInputJson = "{ \"feedQuantity\": -10, \"targetCP\": 0 }";

        mockMvc.perform(post("/api/feed/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidInputJson))
                .andExpect(status().isBadRequest());
    }


    /**
     * Test for a valid feed calculation request with a different target CP value.
     * This method sends a POST request to the API and verifies the response.
     */
    @Test
    public void calculateFeed_validInput_differentCPValue() throws Exception {
        // Arrange
        FeedRequest request = new FeedRequest();
        request.setQuantity(2.0); // 2 kg of feed
        request.setTargetCpValue(15); // 15% target CP value

        // Create realistic ingredient data for the response
        Ingredient soya = new Ingredient("Soya beans", 44.0, 0.4);
        Ingredient groundnuts = new Ingredient("Groundnuts", 45.0, 0.3);
        Ingredient maize = new Ingredient("Maize", 8.5, 0.8);
        Ingredient cassava = new Ingredient("Cassava", 2.0, 0.3);

        // Group ingredients by category
        List<Ingredient> proteins = new ArrayList<>();
        proteins.add(soya);
        proteins.add(groundnuts);

        List<Ingredient> carbohydrates = new ArrayList<>();
        carbohydrates.add(maize);
        carbohydrates.add(cassava);

        IngredientCategory ingredientCategory = new IngredientCategory();
        ingredientCategory.setProteins(proteins);
        ingredientCategory.setCarbohydrates(carbohydrates);

        FeedResponse response = new FeedResponse();
        response.setQuantity(2.0); // Total quantity of feed in kg
        response.setTargetCpValue(15); // Target CP value in percentage
        response.setIngredients(ingredientCategory);

        // Mock the service method to return the prepared response
        when(feedFormulationService.calculateFeed(any(FeedRequest.class))).thenReturn(response);

        // Act & Assert
        mockMvc.perform(post("/api/feed/calculate")
                        .contentType("application/json")
                        .content("{\"quantity\":2,\"targetCpValue\":15}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.quantity", is(2.0)))
                .andExpect(jsonPath("$.targetCpValue", is(15.0)))
                .andExpect(jsonPath("$.ingredients.proteins", hasSize(2))) // Check number of protein ingredients
                .andExpect(jsonPath("$.ingredients.proteins[0].name", is("Soya beans")))
                .andExpect(jsonPath("$.ingredients.proteins[0].cpValue", is(44.0)))
                .andExpect(jsonPath("$.ingredients.proteins[0].quantityKg", is(0.4)))
                .andExpect(jsonPath("$.ingredients.proteins[1].name", is("Groundnuts")))
                .andExpect(jsonPath("$.ingredients.proteins[1].cpValue", is(45.0)))
                .andExpect(jsonPath("$.ingredients.proteins[1].quantityKg", is(0.3)))
                .andExpect(jsonPath("$.ingredients.carbohydrates", hasSize(2))) // Check number of carbohydrate ingredients
                .andExpect(jsonPath("$.ingredients.carbohydrates[0].name", is("Maize")))
                .andExpect(jsonPath("$.ingredients.carbohydrates[0].cpValue", is(8.5)))
                .andExpect(jsonPath("$.ingredients.carbohydrates[0].quantityKg", is(0.8)))
                .andExpect(jsonPath("$.ingredients.carbohydrates[1].name", is("Cassava")))
                .andExpect(jsonPath("$.ingredients.carbohydrates[1].cpValue", is(2.0)))
                .andExpect(jsonPath("$.ingredients.carbohydrates[1].quantityKg", is(0.3)));

        // Verify that the service method was called once
        verify(feedFormulationService, times(1)).calculateFeed(any(FeedRequest.class));
    }
}
