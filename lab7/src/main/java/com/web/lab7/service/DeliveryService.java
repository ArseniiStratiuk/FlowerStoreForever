package com.web.lab7.service;

import com.web.lab7.strategy.delivery.DHLDeliveryStrategy;
import com.web.lab7.strategy.delivery.Delivery;
import com.web.lab7.strategy.delivery.PostDeliveryStrategy;
import java.util.List;
import java.util.Locale;
import org.springframework.stereotype.Service;

/**
 * Supplies delivery strategies for the demo application.
 */
@Service
public final class DeliveryService {

    /**
     * Creates the sample delivery strategies used by the application.
     *
     * @return immutable list of strategies
     */
    public List<Delivery> getAvailableStrategies() {
        return List.of(
                new PostDeliveryStrategy("Kyiv, Main Street 1"),
                new DHLDeliveryStrategy("DHL Warehouse #42")
        );
    }

    /**
     * Resolves a delivery strategy by its identifier.
     *
     * @param name case-insensitive delivery identifier
     * @return matching delivery strategy
     */
    public Delivery resolve(final String name) {
        return getAvailableStrategies().stream()
                .filter(delivery -> delivery.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Unknown delivery method: " + name
                ));
    }

    /**
     * Lists the identifiers of configured delivery strategies.
     *
     * @return delivery method names
     */
    public List<String> getNames() {
        return getAvailableStrategies().stream()
                .map(delivery -> delivery.getName().toLowerCase(Locale.ROOT))
                .toList();
    }
}
