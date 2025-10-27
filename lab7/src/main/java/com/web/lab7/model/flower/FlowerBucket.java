package com.web.lab7.model.flower;

import com.web.lab7.model.item.Item;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Collection of flowers that can be sold as a single item.
 */
public class FlowerBucket extends Item {

    private final List<Flower> flowers = new ArrayList<>();

    public FlowerBucket() {
        super("Flower bucket");
    }

    public void addFlower(Flower flower) {
        flowers.add(flower);
    }

    public List<Flower> getFlowers() {
        return Collections.unmodifiableList(flowers);
    }

    public List<Flower> searchFlower(FlowerType type) {
        return flowers.stream()
                .filter(flower -> flower.getType() == type)
                .toList();
    }

    @Override
    public double price() {
        return flowers.stream().mapToDouble(Flower::price).sum();
    }
}
