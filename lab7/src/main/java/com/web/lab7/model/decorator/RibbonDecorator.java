package com.web.lab7.model.decorator;

import com.web.lab7.model.item.Item;
import com.web.lab7.model.item.ItemDecorator;

/**
 * Adds a premium ribbon to the item.
 */
public class RibbonDecorator extends ItemDecorator {

    public RibbonDecorator(Item item) {
        super(item, "Ribbon");
    }

    @Override
    public double price() {
        return 40 + item.price();
    }

    @Override
    public String getDescription() {
        return item.getDescription() + ", tied with a ribbon";
    }
}
