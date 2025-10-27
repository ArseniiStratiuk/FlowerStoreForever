package com.web.lab7.model.item;

public abstract class Item {

    protected String description;

    protected Item() {
        this("");
    }

    protected Item(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public abstract double price();
}
