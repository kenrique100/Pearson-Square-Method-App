package com.api.feedFormulation.controller;

import com.api.feedFormulation.model.FeedRequest;
import com.api.feedFormulation.model.FeedResponse;
import com.api.feedFormulation.service.FeedFormulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for handling feed calculation requests.
 * This controller exposes an endpoint for clients to send feed calculation requests
 * and receive calculated feed responses.
 */
@RestController
@RequestMapping("/api/feed")
public class FeedFormulationController {
    /**
     * Service for calculating feed compositions.
     */
    @Autowired
    private FeedFormulationService feedFormulationService;

    @PostMapping("/calculate")
    public ResponseEntity<FeedResponse> calculateFeed(@RequestBody FeedRequest request) {
        // Basic validation
        if (request.getQuantity() <= 0 || request.getTargetCpValue() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(null); // or include an error message or custom error response
        }

        // Call the service to calculate the feed
        FeedResponse response = feedFormulationService.calculateFeed(request);

        return ResponseEntity.ok(response);
    }
}
