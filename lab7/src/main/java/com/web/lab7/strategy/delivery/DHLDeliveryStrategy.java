package com.web.lab7.strategy.delivery;

import com.web.lab7.model.item.Item;

/**
 * Express delivery using DHL.
 */
public final class DHLDeliveryStrategy implements Delivery {

    /**
     * Warehouse location used to describe the delivery.
     */
    private final String warehouse;

    /**
     * Creates the strategy with a specific warehouse location.
     *
     * @param warehouseLocation warehouse description used in messages
     */
    public DHLDeliveryStrategy(final String warehouseLocation) {
        this.warehouse = warehouseLocation;
    }

    @Override
    public String getName() {
        return "dhl";
    }

    @Override
    public String deliver(final Item item) {
        return String.format(
                "Delivering %s via DHL from %s",
                item.getDescription(),
                warehouse
        );
    }

    /**
     * @return warehouse location used by the strategy
     */
    public String getWarehouse() {
        return warehouse;
    }
}
