package com.web.lab7.strategy.delivery;

import com.web.lab7.model.item.Item;

/**
 * Express delivery using DHL.
 */
public class DHLDeliveryStrategy implements Delivery {

    private final String warehouse;

    public DHLDeliveryStrategy(String warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public String getName() {
        return "dhl";
    }

    @Override
    public String deliver(Item item) {
        return "Delivering " + item.getDescription() + " via DHL from " + warehouse;
    }

    public String getWarehouse() {
        return warehouse;
    }
}
