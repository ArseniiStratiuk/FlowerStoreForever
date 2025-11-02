package com.web.lab7.config;

import com.web.lab7.model.flower.Flower;
import com.web.lab7.model.flower.FlowerColor;
import com.web.lab7.model.flower.FlowerType;
import com.web.lab7.repository.FlowerRepository;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Inserts a small set of flowers when the database is empty.
 */
@Component
public class FlowerDataInitializer implements CommandLineRunner {

    private final FlowerRepository flowerRepository;

    public FlowerDataInitializer(final FlowerRepository flowerRepository) {
        this.flowerRepository = flowerRepository;
    }

    @Override
    public void run(final String... args) {
        if (flowerRepository.count() > 0) {
            return;
        }
        final List<Flower> seedFlowers = List.of(
                new Flower(FlowerType.ROSE, FlowerColor.RED, 20, 40),
                new Flower(FlowerType.TULIP, FlowerColor.YELLOW, 18, 22),
                new Flower(FlowerType.ROMASHKA, FlowerColor.WHITE, 9, 12),
                new Flower(FlowerType.CACTUS, FlowerColor.GREEN, 12, 30)
        );
        flowerRepository.saveAll(seedFlowers);
    }
}
