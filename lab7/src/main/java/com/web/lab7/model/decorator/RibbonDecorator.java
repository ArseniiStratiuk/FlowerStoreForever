package com.web.lab7.model.decorator;

import com.web.lab7.model.item.Item;
import com.web.lab7.model.item.ItemDecorator;

/**
 * Adds a premium ribbon to the item.
 */
public final class RibbonDecorator extends ItemDecorator {

    /**
     * Additional cost for a ribbon decoration.
     */
    private static final double RIBBON_PRICE = 40;

    /**
     * Creates a ribbon decorator around the provided item.
     *
     * @param item item to decorate with a ribbon
     */
    public RibbonDecorator(final Item item) {
        super(item, "Ribbon");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double price() {
        return RIBBON_PRICE + getWrappedItem().price();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return getWrappedItem().getDescription() + ", tied with a ribbon";
    }
}
