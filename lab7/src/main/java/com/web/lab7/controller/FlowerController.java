package com.web.lab7.controller;

import com.web.lab7.model.flower.Flower;
import com.web.lab7.service.FlowerService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Exposes read-only flower catalogue.
 */
@RestController
@RequestMapping("/api/flowers")
public class FlowerController {

    private final FlowerService flowerService;

    public FlowerController(FlowerService flowerService) {
        this.flowerService = flowerService;
    }

    @GetMapping
    public List<Flower> findAll() {
        return flowerService.findAll();
    }
}
