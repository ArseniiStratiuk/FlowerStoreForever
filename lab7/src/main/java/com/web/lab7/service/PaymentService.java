package com.web.lab7.service;

import com.web.lab7.strategy.payment.CreditCardPaymentStrategy;
import com.web.lab7.strategy.payment.Payment;
import com.web.lab7.strategy.payment.PayPalPaymentStrategy;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 * Supplies payment strategies for the demo application.
 */
@Service
public final class PaymentService {

    /**
     * Immutable list of configured payment strategies.
     */
    private final List<Payment> strategies;

    /**
     * Quick lookup map keyed by lowercase strategy name.
     */
    private final Map<String, Payment> strategiesByName;

    /**
     * Constructor for PaymentService.
     */
    public PaymentService() {
        this.strategies = List.of(
                new CreditCardPaymentStrategy("4111111111111111", "Demo User"),
                new PayPalPaymentStrategy("demo@example.com")
        );
        this.strategiesByName = strategies.stream()
                .collect(Collectors.toUnmodifiableMap(
                        payment -> payment.getName().toLowerCase(Locale.ROOT),
                        Function.identity()
                ));
    }

    /**
     * @return immutable list of strategies
     */
    public List<Payment> getAvailableStrategies() {
        return strategies;
    }

    /**
     * Resolves a payment strategy by its identifier.
     *
     * @param name case-insensitive payment identifier
     * @return matching payment strategy
     */
    public Payment resolve(final String name) {
    final Payment payment = strategiesByName.get(
        name.toLowerCase(Locale.ROOT)
    );
    if (payment == null) {
        throw new IllegalArgumentException("Unknown payment method: " + name);
    }
    return payment;
    }

    /**
     * Lists the identifiers of configured payment strategies.
     *
     * @return payment strategy names
     */
    public List<String> getNames() {
    return strategies.stream()
        .map(payment -> payment.getName().toLowerCase(Locale.ROOT))
        .toList();
    }
}
