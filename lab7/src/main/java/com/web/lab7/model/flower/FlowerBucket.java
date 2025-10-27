package com.web.lab7.model.flower;

import com.web.lab7.model.item.Item;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Collection of flowers that can be sold as a single item.
 */
public final class FlowerBucket extends Item {

    /**
     * Mutable collection of flowers contained within the bucket.
     */
    private final List<Flower> flowers = new ArrayList<>();

    /**
     * Creates an empty flower bucket.
     */
    public FlowerBucket() {
        super("Flower bucket");
    }

    /**
     * Adds a flower to the bucket.
     *
     * @param flower flower instance to add
     */
    public void addFlower(final Flower flower) {
        flowers.add(flower);
    }

    /**
     * Returns an immutable view of the flowers contained in the bucket.
     *
     * @return unmodifiable list of flowers
     */
    public List<Flower> getFlowers() {
        return Collections.unmodifiableList(flowers);
    }

    /**
     * Finds all flowers matching the provided type.
     *
     * @param type type to search for
     * @return list of flowers of the requested type
     */
    public List<Flower> searchFlower(final FlowerType type) {
        return flowers.stream()
                .filter(flower -> flower.getType() == type)
                .toList();
    }

    @Override
    public double price() {
        return flowers.stream().mapToDouble(Flower::price).sum();
    }
}
