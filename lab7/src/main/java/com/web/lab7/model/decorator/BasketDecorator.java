package com.web.lab7.model.decorator;

import com.web.lab7.model.item.Item;
import com.web.lab7.model.item.ItemDecorator;

/**
 * Adds a decorative basket to the item.
 */
public final class BasketDecorator extends ItemDecorator {

    /**
     * Additional price for including a basket.
     */
    private static final double BASKET_PRICE = 4;

    /**
     * Creates a basket decorator around the provided item.
     *
     * @param item item to decorate with a basket
     */
    public BasketDecorator(final Item item) {
        super(item, "Basket decoration");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double price() {
        return BASKET_PRICE + getWrappedItem().price();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return getWrappedItem().getDescription() + ", placed in a basket";
    }
}
