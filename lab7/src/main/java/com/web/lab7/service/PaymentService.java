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
public final class PaymentService {

    /**
     * Creates the sample payment strategies used across the application.
     *
     * @return immutable list of strategies
     */
    public List<Payment> getAvailableStrategies() {
        return List.of(
                new CreditCardPaymentStrategy("4111111111111111", "Demo User"),
                new PayPalPaymentStrategy("demo@example.com")
        );
    }

    /**
     * Resolves a payment strategy by its identifier.
     *
     * @param name case-insensitive payment identifier
     * @return matching payment strategy
     */
    public Payment resolve(final String name) {
        return getAvailableStrategies().stream()
                .filter(payment -> payment.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Unknown payment method: " + name
                ));
    }

    /**
     * Lists the identifiers of configured payment strategies.
     *
     * @return payment strategy names
     */
    public List<String> getNames() {
        return getAvailableStrategies().stream()
                .map(payment -> payment.getName().toLowerCase(Locale.ROOT))
                .toList();
    }
}
