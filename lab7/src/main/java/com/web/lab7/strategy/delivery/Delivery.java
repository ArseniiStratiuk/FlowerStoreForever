package com.web.lab7.strategy.delivery;

import com.web.lab7.model.item.Item;

/**
 * Delivery strategy contract.
 */
public interface Delivery {

    /**
     * @return human-readable name of the delivery strategy
     */
    String getName();

    /**
     * Produces a delivery message for the provided item.
     *
     * @param item item being delivered
     * @return delivery message
     */
    String deliver(Item item);
}
