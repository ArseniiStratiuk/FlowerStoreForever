package com.web.lab7.controller;

import com.web.lab7.controller.dto.DeliverySimulationResponse;
import com.web.lab7.model.flower.Flower;
import com.web.lab7.model.flower.FlowerType;
import com.web.lab7.service.DeliveryService;
import com.web.lab7.service.FlowerService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Simple API to inspect and test delivery strategies.
 */
@RestController
@RequestMapping("/api/v1/delivery")
public final class DeliveryController {

    /**
     * Provides available delivery strategies.
     */
    private final DeliveryService deliveryService;

    /**
     * Supplies sample items for simulation responses.
     */
    private final FlowerService flowerService;

    /**
     * Creates the controller with Spring-injected dependencies.
     *
     * @param deliveryServiceBean service listing delivery strategies
     * @param flowerServiceBean service capable of instantiating flowers
     */
    public DeliveryController(final DeliveryService deliveryServiceBean,
                              final FlowerService flowerServiceBean) {
        this.deliveryService = deliveryServiceBean;
        this.flowerService = flowerServiceBean;
    }

    /**
     * Lists the identifiers of available delivery strategies.
     *
     * @return delivery method names
     */
    @GetMapping
    public List<String> deliveryMethods() {
        return deliveryService.getNames();
    }

    /**
     * Simulates delivery of a flower bucket using the selected strategy.
     *
     * @param method delivery method identifier, defaults to postal service
     * @param itemType flower type to wrap for delivery message
     * @return map containing selected method and generated delivery message
     */
    @GetMapping("/simulate")
    public DeliverySimulationResponse simulateDelivery(
            @RequestParam(defaultValue = "post") final String method,
            @RequestParam(defaultValue = "ROSE") final String itemType) {
        final FlowerType type = FlowerType.valueOf(itemType.toUpperCase());
        final Flower flower = flowerService.requireByType(type);
        final String message = deliveryService.resolve(method).deliver(flower);
        return new DeliverySimulationResponse(
                method,
                flower.getDescription(),
                message
        );
    }
}
