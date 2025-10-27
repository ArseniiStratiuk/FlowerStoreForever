package com.web.lab7.controller.dto;

import java.util.List;

/**
 * Response payload summarising processed order.
 *
 * @param totalPrice calculated price of all items
 * @param paid flag indicating whether payment succeeded
 * @param deliveryMessages messages produced during delivery simulation
 */
public record OrderResponse(
        double totalPrice,
        boolean paid,
        List<String> deliveryMessages) {
}
