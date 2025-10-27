package com.web.lab7.controller;

import com.web.lab7.controller.dto.OrderRequest;
import com.web.lab7.controller.dto.OrderResponse;
import com.web.lab7.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Processes orders using the configured strategies.
 */
@RestController
@RequestMapping("/api/orders")
public final class OrderController {

    /**
     * Coordinates order processing logic.
     */
    private final OrderService orderService;

    /**
     * Constructs the controller with the required service.
     *
     * @param orderServiceBean application service that processes orders
     */
    public OrderController(final OrderService orderServiceBean) {
        this.orderService = orderServiceBean;
    }

    /**
     * Creates a new order using the strategies selected in the request.
     *
     * @param request order payload containing items and strategies
     * @return processed order summary
     */
    @PostMapping
    public OrderResponse createOrder(@RequestBody final OrderRequest request) {
        return orderService.processOrder(request);
    }
}
