package com.web.lab7.model.decorator;

import com.web.lab7.model.item.Item;
import com.web.lab7.model.item.ItemDecorator;

/**
 * Adds paper wrapping to any item.
 */
public class PaperDecorator extends ItemDecorator {

    public PaperDecorator(Item item) {
        super(item, "Paper wrap");
    }

    @Override
    public double price() {
        return 13 + item.price();
    }

    @Override
    public String getDescription() {
        return item.getDescription() + ", wrapped in paper";
    }
}
