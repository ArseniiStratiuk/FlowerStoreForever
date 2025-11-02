package com.web.lab7.controller.dto;

/**
 * Response payload for delivery simulation requests.
 */
public record DeliverySimulationResponse(
        String method,
        String itemDescription,
        String message
) {
}
