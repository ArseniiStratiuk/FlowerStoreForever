package com.web.lab7.controller;

import com.web.lab7.controller.dto.CreateFlowerRequest;
import com.web.lab7.model.flower.Flower;
import com.web.lab7.model.flower.FlowerType;
import com.web.lab7.service.FlowerService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Exposes CRUD endpoints for flowers stored in the database.
 */
@RestController
@RequestMapping("/api/v1/flower")
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

    /**
     * Returns the first flower of the provided type if a record exists.
     *
     * @param type flower species to search for
     * @return HTTP 200 with a flower or 404 if none exist
     */
    @GetMapping("/type/{type}")
    public ResponseEntity<Flower> findByType(@PathVariable final FlowerType type) {
        return ResponseEntity.of(flowerService.findFirstByType(type));
    }

    /**
     * Persists a new flower in the catalogue.
     *
     * @param request payload describing the flower to create
     * @return newly created flower instance
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Flower create(@Valid @RequestBody final CreateFlowerRequest request) {
        final Flower flower = new Flower(
                request.type(),
                request.color(),
                request.sepalLength(),
                request.price()
        );
        return flowerService.save(flower);
    }
}
