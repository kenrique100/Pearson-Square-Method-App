package com.api.feedFormulation.service;


import com.api.feedFormulation.model.FeedRequest;
import com.api.feedFormulation.model.FeedResponse;

/**
 * Service interface for calculating the feed composition based on
 * the Pearson square method. This interface defines the contract for
 * implementing the feed calculation logic.
 */
public interface FeedFormulationService {
    /**
     * Calculates the feed composition based on the given request.
     * the feed request containing quantity and target CP value
     * calculated feed response containing categorized ingredients
     */
    FeedResponse calculateFeed(FeedRequest request);
}
