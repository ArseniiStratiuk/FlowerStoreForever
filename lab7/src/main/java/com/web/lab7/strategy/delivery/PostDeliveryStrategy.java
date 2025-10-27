package com.web.lab7.strategy.delivery;

import com.web.lab7.model.item.Item;

/**
 * Delivery through national post.
 */
public final class PostDeliveryStrategy implements Delivery {

    /**
     * Postal address used in delivery messages.
     */
    private final String address;

    /**
     * Creates the strategy for a specific postal address.
     *
     * @param destinationAddress destination address used in messages
     */
    public PostDeliveryStrategy(final String destinationAddress) {
        this.address = destinationAddress;
    }

    @Override
    public String getName() {
        return "post";
    }

    @Override
    public String deliver(final Item item) {
        return String.format(
                "Delivering %s via post to %s",
                item.getDescription(),
                address
        );
    }

    /**
     * @return configured postal address
     */
    public String getAddress() {
        return address;
    }
}
