package com.web.lab7.model.decorator;

import com.web.lab7.model.item.Item;
import com.web.lab7.model.item.ItemDecorator;

/**
 * Adds a decorative basket to the item.
 */
public class BasketDecorator extends ItemDecorator {

    public BasketDecorator(Item item) {
        super(item, "Basket decoration");
    }

    @Override
    public double price() {
        return 4 + item.price();
    }

    @Override
    public String getDescription() {
        return item.getDescription() + ", placed in a basket";
    }
}
