package com.web.lab7.service;

import com.web.lab7.controller.dto.OrderRequest;
import com.web.lab7.controller.dto.OrderResponse;
import com.web.lab7.model.flower.FlowerType;
import com.web.lab7.model.item.Item;
import com.web.lab7.model.order.Order;
import com.web.lab7.strategy.delivery.Delivery;
import com.web.lab7.strategy.payment.Payment;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 * Creates and processes orders based on incoming requests.
 */
@Service
public class OrderService {

    private final FlowerService flowerService;
    private final PaymentService paymentService;
    private final DeliveryService deliveryService;

    public OrderService(FlowerService flowerService,
                        PaymentService paymentService,
                        DeliveryService deliveryService) {
        this.flowerService = flowerService;
        this.paymentService = paymentService;
        this.deliveryService = deliveryService;
    }

    public OrderResponse processOrder(OrderRequest request) {
        Order order = new Order();
        List<String> requestedItems = Optional.ofNullable(request.items()).orElse(List.of());
        if (requestedItems.isEmpty()) {
            order.addItem(defaultItem());
        } else {
            requestedItems.stream()
                    .map(name -> FlowerType.valueOf(name.toUpperCase(Locale.ROOT)))
                    .map(flowerService::createFlower)
                    .forEach(order::addItem);
        }

        Payment payment = resolvePayment(request.payment());
        Delivery delivery = resolveDelivery(request.delivery());
        order.setPaymentStrategy(payment);
        order.setDeliveryStrategy(delivery);

        Order.OrderProcessingResult result = order.processOrder();
        return new OrderResponse(result.totalPrice(), result.paid(), result.deliveryMessages());
    }

    private Item defaultItem() {
        return flowerService.createFlower(FlowerType.ROSE);
    }

    private Payment resolvePayment(String paymentName) {
        List<Payment> strategies = paymentService.getAvailableStrategies();
        if (paymentName == null || paymentName.isBlank()) {
            return strategies.get(0);
        }
        return paymentService.resolve(paymentName);
    }

    private Delivery resolveDelivery(String deliveryName) {
        List<Delivery> strategies = deliveryService.getAvailableStrategies();
        if (deliveryName == null || deliveryName.isBlank()) {
            return strategies.get(0);
        }
        return deliveryService.resolve(deliveryName);
    }
}
