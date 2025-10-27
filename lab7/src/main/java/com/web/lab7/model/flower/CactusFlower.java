package com.web.lab7.model.flower;

/**
 * Simple cactus flower implementation.
 */
public class CactusFlower extends Flower {

    public CactusFlower() {
        this(10, FlowerColor.GREEN, 25);
    }

    public CactusFlower(double sepalLength, FlowerColor color, double price) {
        super(FlowerType.CACTUS, color, sepalLength, price);
    }
}
