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
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    public List<String> paymentMethods() {
        return paymentService.getNames();
    }

    @GetMapping("/simulate")
    public Map<String, Object> simulatePayment(@RequestParam(defaultValue = "credit-card") String method,
                                               @RequestParam(defaultValue = "100") double amount) {
        boolean result = paymentService.resolve(method).pay(amount);
        return Map.of("method", method, "amount", amount, "success", result);
    }
}
