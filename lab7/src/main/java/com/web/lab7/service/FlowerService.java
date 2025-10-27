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
public class FlowerService {

    private final Map<FlowerType, Supplier<Flower>> catalog = new EnumMap<>(FlowerType.class);

    public FlowerService() {
        catalog.put(FlowerType.CACTUS, () -> new CactusFlower(12, FlowerColor.GREEN, 30));
        catalog.put(FlowerType.ROMASHKA, () -> new RomashkaFlower(9, FlowerColor.WHITE, 12));
        catalog.put(FlowerType.ROSE, () -> new Flower(FlowerType.ROSE, FlowerColor.RED, 20, 40));
        catalog.put(FlowerType.TULIP, () -> new Flower(FlowerType.TULIP, FlowerColor.YELLOW, 18, 22));
    }

    public List<Flower> findAll() {
        return catalog.values().stream().map(Supplier::get).toList();
    }

    public Flower createFlower(FlowerType type) {
        Supplier<Flower> supplier = catalog.get(Objects.requireNonNull(type, "type"));
        if (supplier == null) {
            throw new IllegalArgumentException("Unknown flower type: " + type);
        }
        return supplier.get();
    }
}
