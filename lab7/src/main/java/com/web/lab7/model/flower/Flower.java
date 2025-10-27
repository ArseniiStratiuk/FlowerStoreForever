package com.web.lab7.model.flower;

import com.web.lab7.model.item.Item;

/**
 * Domain object describing a single flower.
 */
public class Flower extends Item {

    /**
     * Sepal length in centimetres.
     */
    private double sepalLength;

    /**
     * Visual colour representation of the flower.
     */
    private FlowerColor color;

    /**
     * Price of the individual flower.
     */
    private double price;

    /**
     * Specific flower type (rose, tulip, etc.).
     */
    private FlowerType type;

    /**
     * Creates a flower with default values.
     */
    public Flower() {
        this(FlowerType.ROSE, FlowerColor.RED, 0, 0);
    }

    /**
     * Creates a flower with fully specified attributes.
     *
     * @param flowerType flower type
     * @param flowerColor colour representation
     * @param initialSepalLength sepal length in centimetres
     * @param initialPrice price of the flower
     */
    public Flower(final FlowerType flowerType,
                  final FlowerColor flowerColor,
                  final double initialSepalLength,
                  final double initialPrice) {
        super(flowerType + " flower");
        this.type = flowerType;
        this.color = flowerColor;
        this.sepalLength = initialSepalLength;
        this.price = initialPrice;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double price() {
        return price;
    }

    /**
     * @return the sepal length
     */
    public double getSepalLength() {
        return sepalLength;
    }

    /**
     * Updates the sepal length value.
     *
     * @param newSepalLength new sepal length in centimetres
     */
    public void setSepalLength(final double newSepalLength) {
        this.sepalLength = newSepalLength;
    }

    /**
     * @return current colour of the flower
     */
    public FlowerColor getColor() {
        return color;
    }

    /**
     * Updates the colour of the flower.
     *
     * @param newColor new colour
     */
    public void setColor(final FlowerColor newColor) {
        this.color = newColor;
    }

    /**
     * Updates the price value.
     *
     * @param newPrice new price
     */
    public void setPrice(final double newPrice) {
        this.price = newPrice;
    }

    /**
     * @return current price of the flower
     */
    public double getPrice() {
        return price;
    }

    /**
     * @return current flower type
     */
    public FlowerType getType() {
        return type;
    }

    /**
     * Updates the flower type and adjusts the description accordingly.
     *
     * @param newType new flower type
     */
    public void setType(final FlowerType newType) {
        this.type = newType;
        setDescription(newType + " flower");
    }
}
