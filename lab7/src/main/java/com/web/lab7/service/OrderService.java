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
public final class OrderService {

    /**
     * Provides catalogue data for order items.
     */
    private final FlowerService flowerService;

    /**
     * Supplies payment strategies for orders.
     */
    private final PaymentService paymentService;

    /**
     * Supplies delivery strategies for orders.
     */
    private final DeliveryService deliveryService;

    /**
     * Creates the service with its required collaborators.
     *
     * @param flowerServiceBean service used to create flower items
     * @param paymentServiceBean service providing payment strategies
     * @param deliveryServiceBean service providing delivery strategies
     */
    public OrderService(final FlowerService flowerServiceBean,
                        final PaymentService paymentServiceBean,
                        final DeliveryService deliveryServiceBean) {
        this.flowerService = flowerServiceBean;
        this.paymentService = paymentServiceBean;
        this.deliveryService = deliveryServiceBean;
    }

    /**
     * Processes an order request, applying default strategies when none are
     *     provided.
     *
     * @param request payload describing the desired order
     * @return processed order summary
     */
    public OrderResponse processOrder(final OrderRequest request) {
        final Order order = new Order();
        final List<String> requestedItems = Optional
                .ofNullable(request.items())
                .orElse(List.of());
        if (requestedItems.isEmpty()) {
            order.addItem(defaultItem());
        } else {
            requestedItems.stream()
                    .map(name -> FlowerType.valueOf(
                            name.toUpperCase(Locale.ROOT)
                    ))
                    .map(flowerService::requireByType)
                    .forEach(order::addItem);
        }

        final Payment payment = resolvePayment(request.payment());
        final Delivery delivery = resolveDelivery(request.delivery());
        order.setPaymentStrategy(payment);
        order.setDeliveryStrategy(delivery);

        final Order.OrderProcessingResult result = order.processOrder();
        return new OrderResponse(
                result.totalPrice(),
                result.paid(),
                result.deliveryMessages()
        );
    }

    /**
     * Creates a default item to ensure every order contains at least one entry.
     *
     * @return default flower item
     */
    private Item defaultItem() {
        return flowerService.requireByType(FlowerType.ROSE);
    }

    /**
     * Resolves the payment strategy, falling back to the first available one.
     *
     * @param paymentName requested payment identifier
     * @return resolved payment strategy
     */
    private Payment resolvePayment(final String paymentName) {
    final List<Payment> strategies = paymentService
        .getAvailableStrategies();
        if (paymentName == null || paymentName.isBlank()) {
            return strategies.get(0);
        }
        return paymentService.resolve(paymentName);
    }

    /**
     * Resolves the delivery strategy, falling back to the first available one.
     *
     * @param deliveryName requested delivery identifier
     * @return resolved delivery strategy
     */
    private Delivery resolveDelivery(final String deliveryName) {
    final List<Delivery> strategies = deliveryService
        .getAvailableStrategies();
        if (deliveryName == null || deliveryName.isBlank()) {
            return strategies.get(0);
        }
        return deliveryService.resolve(deliveryName);
    }
}
