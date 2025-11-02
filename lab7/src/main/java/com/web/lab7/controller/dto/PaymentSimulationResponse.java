package com.web.lab7.controller.dto;

/**
 * Response payload describing a simulated payment attempt.
 */
public record PaymentSimulationResponse(
        String method,
        double amount,
        boolean success
) {
}
