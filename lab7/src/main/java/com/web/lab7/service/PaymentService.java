package com.web.lab7.service;

import com.web.lab7.strategy.payment.CreditCardPaymentStrategy;
import com.web.lab7.strategy.payment.Payment;
import com.web.lab7.strategy.payment.PayPalPaymentStrategy;
import java.util.List;
import java.util.Locale;
import org.springframework.stereotype.Service;

/**
 * Supplies payment strategies for the demo application.
 */
@Service
public class PaymentService {

    public List<Payment> getAvailableStrategies() {
        return List.of(
                new CreditCardPaymentStrategy("4111111111111111", "Demo User"),
                new PayPalPaymentStrategy("demo@example.com")
        );
    }

    public Payment resolve(String name) {
        return getAvailableStrategies().stream()
                .filter(payment -> payment.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown payment method: " + name));
    }

    public List<String> getNames() {
        return getAvailableStrategies().stream()
                .map(payment -> payment.getName().toLowerCase(Locale.ROOT))
                .toList();
    }
}
