package com.web.lab7.strategy.delivery;

import com.web.lab7.model.item.Item;

/**
 * Delivery strategy contract.
 */
public interface Delivery {

    String getName();

    String deliver(Item item);
}
