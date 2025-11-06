package com.web.lab7.controller.dto;

/**
 * Response payload describing a simulated payment attempt.
 *
 * @param method the payment method name
 * @param amount the payment amount
 * @param success whether the payment was successful
 */
public record PaymentSimulationResponse(
        String method,
        double amount,
        boolean success
) {
}
