package com.web.lab7.controller.dto;

import com.web.lab7.model.flower.FlowerColor;
import com.web.lab7.model.flower.FlowerType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

/**
 * Payload describing a flower to be persisted via the REST API.
 */
public record CreateFlowerRequest(
        @NotNull(message = "type is required")
        FlowerType type,
        @NotNull(message = "color is required")
        FlowerColor color,
        @PositiveOrZero(message = "sepalLength must be zero or positive")
        double sepalLength,
        @PositiveOrZero(message = "price must be zero or positive")
        double price
) {
}
