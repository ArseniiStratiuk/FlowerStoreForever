package com.web.lab7.model.order;

import com.web.lab7.model.item.Item;
import com.web.lab7.strategy.delivery.Delivery;
import com.web.lab7.strategy.payment.Payment;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Aggregate root coordinating items, payments and delivery strategies.
 */
public final class Order {

    /**
     * Exception message used when payment strategy is missing.
     */
    private static final String PAYMENT_NOT_SET_MESSAGE =
        "Payment strategy must be set before processing an order";

    /**
     * Exception message used when delivery strategy is missing.
     */
    private static final String DELIVERY_NOT_SET_MESSAGE =
        "Delivery strategy must be set before processing an order";

    /**
     * Items that compose the current order.
     */
    private final LinkedList<Item> items = new LinkedList<>();

    /**
     * Payment strategy applied to the order.
     */
    private Payment payment;

    /**
     * Delivery strategy applied to the order.
     */
    private Delivery delivery;

    /**
     * Adds an item to the order.
     *
     * @param item shopping cart item to add
     */
    public void addItem(final Item item) {
        items.add(item);
    }

    /**
     * Removes an item from the order.
     *
     * @param item shopping cart item to remove
     */
    public void removeItem(final Item item) {
        items.remove(item);
    }

    /**
     * Returns an immutable snapshot of the current items.
     *
     * @return unmodifiable list of order items
     */
    public List<Item> getItems() {
        return Collections.unmodifiableList(items);
    }

    /**
     * Configures the payment strategy for subsequent processing.
     *
     * @param paymentStrategy payment strategy to assign
     */
    public void setPaymentStrategy(final Payment paymentStrategy) {
        this.payment = paymentStrategy;
    }

    /**
     * Configures the delivery strategy for subsequent processing.
     *
     * @param deliveryStrategy delivery strategy to assign
     */
    public void setDeliveryStrategy(final Delivery deliveryStrategy) {
        this.delivery = deliveryStrategy;
    }

    /**
     * @return currently configured payment strategy
     */
    public Payment getPaymentStrategy() {
        return payment;
    }

    /**
     * @return currently configured delivery strategy
     */
    public Delivery getDeliveryStrategy() {
        return delivery;
    }

    /**
     * Calculates the total price of all items in the order.
     *
     * @return sum of item prices
     */
    public double calculateTotalPrice() {
        return items.stream().mapToDouble(Item::price).sum();
    }

    /**
     * Executes order processing by charging payment and performing delivery.
     *
     * @return result object describing the operation
     */
    public OrderProcessingResult processOrder() {
        if (payment == null) {
            throw new IllegalStateException(PAYMENT_NOT_SET_MESSAGE);
        }
        if (delivery == null) {
            throw new IllegalStateException(DELIVERY_NOT_SET_MESSAGE);
        }
        final double total = calculateTotalPrice();
        final boolean paid = payment.pay(total);
        final List<String> deliveries = items.stream()
                .map(delivery::deliver)
                .toList();
        return new OrderProcessingResult(total, paid, deliveries);
    }

    /**
     * Immovable record describing processing results.
     *
     * @param totalPrice calculated price of all items
     * @param paid result of the payment attempt
     * @param deliveryMessages messages produced during delivery
     */
    public record OrderProcessingResult(
            double totalPrice,
            boolean paid,
            List<String> deliveryMessages) {
    }
}
