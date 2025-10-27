package com.web.lab7.service;

import com.web.lab7.model.flower.CactusFlower;
import com.web.lab7.model.flower.Flower;
import com.web.lab7.model.flower.FlowerColor;
import com.web.lab7.model.flower.FlowerType;
import com.web.lab7.model.flower.RomashkaFlower;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;
import org.springframework.stereotype.Service;

/**
 * Provides flower catalogue data for controllers and services.
 */
@Service
public final class FlowerService {

        /**
         * Default cactus sepal length used for catalogue entries.
         */
        private static final double CACTUS_SEPAL_LENGTH = 12;

        /**
         * Default cactus price used for catalogue entries.
         */
        private static final double CACTUS_PRICE = 30;

        /**
         * Default romashka sepal length used for catalogue entries.
         */
        private static final double ROMASHKA_SEPAL_LENGTH = 9;

        /**
         * Default romashka price used for catalogue entries.
         */
        private static final double ROMASHKA_PRICE = 12;

        /**
         * Default rose sepal length used for catalogue entries.
         */
        private static final double ROSE_SEPAL_LENGTH = 20;

        /**
         * Default rose price used for catalogue entries.
         */
        private static final double ROSE_PRICE = 40;

        /**
         * Default tulip sepal length used for catalogue entries.
         */
        private static final double TULIP_SEPAL_LENGTH = 18;

        /**
         * Default tulip price used for catalogue entries.
         */
        private static final double TULIP_PRICE = 22;

    /**
     * In-memory catalogue mapping flower types to suppliers.
     */
    private final Map<FlowerType, Supplier<Flower>> catalog =
            new EnumMap<>(FlowerType.class);

    /**
     * Populates the catalogue with sample flower suppliers.
     */
    public FlowerService() {
        catalog.put(
                FlowerType.CACTUS,
                () -> new CactusFlower(
                        CACTUS_SEPAL_LENGTH,
                        FlowerColor.GREEN,
                        CACTUS_PRICE
                )
        );
        catalog.put(
                FlowerType.ROMASHKA,
                () -> new RomashkaFlower(
                        ROMASHKA_SEPAL_LENGTH,
                        FlowerColor.WHITE,
                        ROMASHKA_PRICE
                )
        );
        catalog.put(
                FlowerType.ROSE,
                () -> new Flower(
                        FlowerType.ROSE,
                        FlowerColor.RED,
                        ROSE_SEPAL_LENGTH,
                        ROSE_PRICE
                )
        );
        catalog.put(
                FlowerType.TULIP,
                () -> new Flower(
                        FlowerType.TULIP,
                        FlowerColor.YELLOW,
                        TULIP_SEPAL_LENGTH,
                        TULIP_PRICE
                )
        );
    }

    /**
     * Provides all flowers defined in the catalogue.
     *
     * @return immutable list of catalogued flowers
     */
    public List<Flower> findAll() {
        return catalog.values().stream().map(Supplier::get).toList();
    }

    /**
     * Creates a new flower instance for the requested type.
     *
     * @param type flower type to instantiate
     * @return newly created flower
     */
    public Flower createFlower(final FlowerType type) {
        final Supplier<Flower> supplier = catalog.get(
                Objects.requireNonNull(type, "type")
        );
        if (supplier == null) {
            throw new IllegalArgumentException("Unknown flower type: " + type);
        }
        return supplier.get();
    }
}
