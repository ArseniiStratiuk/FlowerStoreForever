package com.web.lab7.strategy.delivery;

import com.web.lab7.model.item.Item;

/**
 * Delivery through national post.
 */
public class PostDeliveryStrategy implements Delivery {

    private final String address;

    public PostDeliveryStrategy(String address) {
        this.address = address;
    }

    @Override
    public String getName() {
        return "post";
    }

    @Override
    public String deliver(Item item) {
        return "Delivering " + item.getDescription() + " via post to " + address;
    }

    public String getAddress() {
        return address;
    }
}
