package com.web.lab7.controller.dto;

import java.util.List;

/**
 * Response payload summarising processed order.
 */
public record OrderResponse(double totalPrice, boolean paid, List<String> deliveryMessages) {
}
