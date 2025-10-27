package com.web.lab7.controller;

import com.web.lab7.model.flower.FlowerType;
import com.web.lab7.service.DeliveryService;
import com.web.lab7.service.FlowerService;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Simple API to inspect and test delivery strategies.
 */
@RestController
@RequestMapping("/api/deliveries")
public class DeliveryController {

    private final DeliveryService deliveryService;
    private final FlowerService flowerService;

    public DeliveryController(DeliveryService deliveryService, FlowerService flowerService) {
        this.deliveryService = deliveryService;
        this.flowerService = flowerService;
    }

    @GetMapping
    public List<String> deliveryMethods() {
        return deliveryService.getNames();
    }

    @GetMapping("/simulate")
    public Map<String, String> simulateDelivery(@RequestParam(defaultValue = "post") String method,
                                                @RequestParam(defaultValue = "ROSE") String itemType) {
        String message = deliveryService.resolve(method)
                .deliver(flowerService.createFlower(FlowerType.valueOf(itemType.toUpperCase())));
        return Map.of("method", method, "message", message);
    }
}
