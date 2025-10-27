package com.web.lab7.model.flower;

/**
 * Romashka (chamomile) flower implementation.
 */
public class RomashkaFlower extends Flower {

    public RomashkaFlower() {
        this(8, FlowerColor.WHITE, 15);
    }

    public RomashkaFlower(double sepalLength, FlowerColor color, double price) {
        super(FlowerType.ROMASHKA, color, sepalLength, price);
    }
}
