package com.web.lab7.model.flower;

import com.web.lab7.model.item.Item;

/**
 * Domain object describing a single flower.
 */
public class Flower extends Item {

    private double sepalLength;
    private FlowerColor color;
    private double price;
    private FlowerType type;

    public Flower() {
        this(FlowerType.ROSE, FlowerColor.RED, 0, 0);
    }

    public Flower(FlowerType type, FlowerColor color, double sepalLength, double price) {
        super(type + " flower");
        this.type = type;
        this.color = color;
        this.sepalLength = sepalLength;
        this.price = price;
    }

    @Override
    public double price() {
        return price;
    }

    public double getSepalLength() {
        return sepalLength;
    }

    public void setSepalLength(double sepalLength) {
        this.sepalLength = sepalLength;
    }

    public FlowerColor getColor() {
        return color;
    }

    public void setColor(FlowerColor color) {
        this.color = color;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public FlowerType getType() {
        return type;
    }

    public void setType(FlowerType type) {
        this.type = type;
        this.description = type + " flower";
    }
}
