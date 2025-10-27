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
public class DeliveryService {

    public List<Delivery> getAvailableStrategies() {
        return List.of(
                new PostDeliveryStrategy("Kyiv, Main Street 1"),
                new DHLDeliveryStrategy("DHL Warehouse #42")
        );
    }

    public Delivery resolve(String name) {
        return getAvailableStrategies().stream()
                .filter(delivery -> delivery.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown delivery method: " + name));
    }

    public List<String> getNames() {
        return getAvailableStrategies().stream()
                .map(delivery -> delivery.getName().toLowerCase(Locale.ROOT))
                .toList();
    }
}
