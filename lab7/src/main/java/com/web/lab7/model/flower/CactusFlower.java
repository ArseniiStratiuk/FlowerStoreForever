package com.web.lab7.model.flower;

/**
 * Simple cactus flower implementation.
 */
public class CactusFlower extends Flower {

    /**
     * Default sepal length for cactus flowers.
     */
    private static final double DEFAULT_SEPAL_LENGTH = 10;

    /**
     * Default price for cactus flowers.
     */
    private static final double DEFAULT_PRICE = 25;

    /**
     * Creates a cactus flower with default attributes.
     */
    public CactusFlower() {
        this(DEFAULT_SEPAL_LENGTH, FlowerColor.GREEN, DEFAULT_PRICE);
    }

    /**
     * Creates a cactus flower with custom attributes.
     *
     * @param sepalLength length of the sepal in centimetres
     * @param color display colour of the cactus flower
     * @param price price of the flower instance
     */
    public CactusFlower(final double sepalLength,
                        final FlowerColor color,
                        final double price) {
        super(FlowerType.CACTUS, color, sepalLength, price);
    }
}
