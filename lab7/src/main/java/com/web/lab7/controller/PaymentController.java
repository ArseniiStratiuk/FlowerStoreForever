package com.web.lab7.controller;

import com.web.lab7.service.PaymentService;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Simple API to inspect and test payment strategies.
 */
@RestController
@RequestMapping("/api/payments")
public final class PaymentController {

    /**
     * Provides access to available payment strategies.
     */
    private final PaymentService paymentService;

    /**
     * Creates the controller with its required dependencies.
     *
     * @param paymentServiceBean service exposing payment strategies
     */
    public PaymentController(final PaymentService paymentServiceBean) {
        this.paymentService = paymentServiceBean;
    }

    /**
     * Lists the names of configured payment strategies.
     *
     * @return strategy identifiers
     */
    @GetMapping
    public List<String> paymentMethods() {
        return paymentService.getNames();
    }

    /**
     * Simulates a payment attempt with the provided method and amount.
     *
     * @param method payment method identifier, defaults to credit card
     * @param amount transaction amount in arbitrary currency units
     * @return execution result including method, amount, and success flag
     */
    @GetMapping("/simulate")
    public Map<String, Object> simulatePayment(
            @RequestParam(defaultValue = "credit-card") final String method,
            @RequestParam(defaultValue = "100") final double amount) {
        final boolean result = paymentService.resolve(method).pay(amount);
        return Map.of(
                "method", method,
                "amount", amount,
                "success", result
        );
    }
}
