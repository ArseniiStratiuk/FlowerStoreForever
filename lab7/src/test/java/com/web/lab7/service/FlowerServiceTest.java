package com.web.lab7.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.web.lab7.PostgresContainerTest;
import com.web.lab7.model.flower.Flower;
import com.web.lab7.model.flower.FlowerColor;
import com.web.lab7.model.flower.FlowerType;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FlowerServiceTest extends PostgresContainerTest {

    @Autowired
    private FlowerService flowerService;

    @Test
    void findAllReturnsPersistedFlowers() {
        List<Flower> all = flowerService.findAll();
        assertNotNull(all);
        assertFalse(all.isEmpty(), "database should contain seed data");
    }

    @Test
    void savePersistsNewFlower() {
    Flower saved = flowerService.save(
        new Flower(FlowerType.TULIP, FlowerColor.BLUE, 10, 15)
    );
        assertNotNull(saved.getId(), "saved flower should have an id");
    }
}
