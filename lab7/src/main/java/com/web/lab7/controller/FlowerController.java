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
public final class FlowerController {

    /**
     * Provides catalog data used by the controller.
     */
    private final FlowerService flowerService;

    /**
     * Creates the controller with its dependencies injected by Spring.
     *
     * @param flowerServiceBean service exposing read-only flower data
     */
    public FlowerController(final FlowerService flowerServiceBean) {
        this.flowerService = flowerServiceBean;
    }

    /**
     * Returns the list of flowers exposed by the catalogue service.
     *
     * @return immutable list of sample flowers
     */
    @GetMapping
    public List<Flower> findAll() {
        return flowerService.findAll();
    }
}
