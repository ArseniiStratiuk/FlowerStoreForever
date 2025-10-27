package com.web.lab7.model.item;

/**
 * Base abstraction for purchasable items within the flower store.
 */
public abstract class Item {

    /**
     * Human-readable description shown to clients.
     */
    private String description;

    /**
     * Creates an item with an empty description.
     */
    protected Item() {
        this("");
    }

    /**
     * Creates an item with the provided description.
     *
     * @param itemDescription text describing the item contents
     */
    protected Item(final String itemDescription) {
        this.description = itemDescription;
    }

    /**
     * Updates the description for inheriting classes.
     *
     * @param itemDescription new description to apply
     */
    protected void setDescription(final String itemDescription) {
        this.description = itemDescription;
    }

    /**
     * @return current item description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Calculates the current price of the item.
     *
     * @return price represented as a double
     */
    public abstract double price();
}
