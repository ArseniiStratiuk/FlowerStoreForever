package com.web.lab7.controller.dto;

import java.util.List;

/**
 * Request payload for creating demo orders.
 *
 * @param items requested flower identifiers
 * @param payment chosen payment strategy
 * @param delivery chosen delivery strategy
 */
public record OrderRequest(
        List<String> items,
        String payment,
        String delivery) {
}
