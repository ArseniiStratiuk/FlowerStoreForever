package com.web.lab7.controller.dto;

/**
 * Response payload for delivery simulation requests.
 *
 * @param method the delivery method name
 * @param itemDescription description of the item being delivered
 * @param message the delivery confirmation message
 */
public record DeliverySimulationResponse(
        String method,
        String itemDescription,
        String message
) {
}
