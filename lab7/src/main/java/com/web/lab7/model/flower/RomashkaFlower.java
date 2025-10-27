package com.web.lab7.model.flower;

/**
 * Romashka (chamomile) flower implementation.
 */
public class RomashkaFlower extends Flower {

    /**
     * Default sepal length for romashka flowers.
     */
    private static final double DEFAULT_SEPAL_LENGTH = 8;

    /**
     * Default price for romashka flowers.
     */
    private static final double DEFAULT_PRICE = 15;

    /**
     * Creates a romashka flower with default attributes.
     */
    public RomashkaFlower() {
        this(DEFAULT_SEPAL_LENGTH, FlowerColor.WHITE, DEFAULT_PRICE);
    }

    /**
     * Creates a romashka flower with custom attributes.
     *
     * @param sepalLength length of the sepal in centimetres
     * @param color display colour of the romashka flower
     * @param price price of the flower instance
     */
    public RomashkaFlower(final double sepalLength,
                          final FlowerColor color,
                          final double price) {
        super(FlowerType.ROMASHKA, color, sepalLength, price);
    }
}
