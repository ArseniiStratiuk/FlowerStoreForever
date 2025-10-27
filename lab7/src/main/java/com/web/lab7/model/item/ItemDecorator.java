package com.web.lab7.model.item;

public abstract class ItemDecorator extends Item {

    protected final Item item;

    protected ItemDecorator(Item item, String description) {
        super(description);
        this.item = item;
    }

    public Item getWrappedItem() {
        return item;
    }
}
