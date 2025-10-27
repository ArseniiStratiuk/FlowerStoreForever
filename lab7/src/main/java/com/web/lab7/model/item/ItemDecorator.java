package com.web.lab7.model.item;

/**
 * Base decorator that delegates pricing and description to wrapped items.
 */
public abstract class ItemDecorator extends Item {

    /**
     * Item instance being decorated with additional behaviour.
     */
    private final Item wrappedItem;

    /**
     * Creates a decorator instance linked to the provided item.
     *
     * @param item item being decorated
     * @param description description of the decorator itself
     */
    protected ItemDecorator(final Item item, final String description) {
        super(description);
        this.wrappedItem = item;
    }

    /**
     * @return the wrapped item for additional processing by subclasses
     */
    public Item getWrappedItem() {
        return wrappedItem;
    }
}
