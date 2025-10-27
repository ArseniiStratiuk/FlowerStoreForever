package com.web.lab7.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.web.lab7.model.flower.Flower;
import java.util.List;
import org.junit.jupiter.api.Test;

class FlowerServiceTest {

    @Test
    void findAllReturnsCataloguedFlowers() {
        FlowerService service = new FlowerService();
        List<Flower> all = service.findAll();
        assertNotNull(all);
        assertFalse(all.isEmpty(), "catalog should contain at least one flower");
        assertTrue(all.stream().allMatch(f -> f.getPrice() >= 0), "prices should be non-negative");
    }
}
