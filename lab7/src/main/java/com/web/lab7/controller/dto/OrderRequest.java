package com.web.lab7.controller.dto;

import java.util.List;

/**
 * Request payload for creating demo orders.
 */
public record OrderRequest(List<String> items, String payment, String delivery) {
}
