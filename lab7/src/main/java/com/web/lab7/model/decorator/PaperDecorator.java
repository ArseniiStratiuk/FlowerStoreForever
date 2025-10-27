package com.web.lab7.model.decorator;

import com.web.lab7.model.item.Item;
import com.web.lab7.model.item.ItemDecorator;

/**
 * Adds paper wrapping to any item.
 */
public final class PaperDecorator extends ItemDecorator {

    /**
     * Additional cost applied for paper wrapping.
     */
    private static final double PAPER_WRAP_PRICE = 13;

    /**
     * Creates a decorator that wraps the supplied item in paper.
     *
     * @param item item to be wrapped
     */
    public PaperDecorator(final Item item) {
        super(item, "Paper wrap");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double price() {
        return PAPER_WRAP_PRICE + getWrappedItem().price();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return getWrappedItem().getDescription() + ", wrapped in paper";
    }
}
