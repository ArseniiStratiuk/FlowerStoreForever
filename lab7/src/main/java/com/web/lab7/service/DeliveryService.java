package com.web.lab7.service;

import com.web.lab7.strategy.delivery.DHLDeliveryStrategy;
import com.web.lab7.strategy.delivery.Delivery;
import com.web.lab7.strategy.delivery.PostDeliveryStrategy;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 * Supplies delivery strategies for the demo application.
 */
@Service
public final class DeliveryService {

    /**
     * Immutable list of configured delivery strategies.
     */
    private final List<Delivery> strategies;

    /**
     * Quick lookup map keyed by lowercase strategy name.
     */
    private final Map<String, Delivery> strategiesByName;

    /**
     * Constructor for DeliveryService.
     */
    public DeliveryService() {
        this.strategies = List.of(
                new PostDeliveryStrategy("Kyiv, Main Street 1"),
                new DHLDeliveryStrategy("DHL Warehouse #42")
        );
        this.strategiesByName = strategies.stream()
                .collect(Collectors.toUnmodifiableMap(
                        delivery -> delivery.getName().toLowerCase(Locale.ROOT),
                        Function.identity()
                ));
    }

    /**
     * @return immutable list of strategies
     */
    public List<Delivery> getAvailableStrategies() {
        return strategies;
    }

    /**
     * Resolves a delivery strategy by its identifier.
     *
     * @param name case-insensitive delivery identifier
     * @return matching delivery strategy
     */
    public Delivery resolve(final String name) {
    final Delivery delivery = strategiesByName.get(
        name.toLowerCase(Locale.ROOT)
    );
    if (delivery == null) {
        throw new IllegalArgumentException("Unknown delivery method: " + name);
    }
    return delivery;
    }

    /**
     * Lists the identifiers of configured delivery strategies.
     *
     * @return delivery method names
     */
    public List<String> getNames() {
    return strategies.stream()
        .map(delivery -> delivery.getName().toLowerCase(Locale.ROOT))
        .toList();
    }
}
