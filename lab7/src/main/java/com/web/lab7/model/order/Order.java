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
public class Order {

    private final LinkedList<Item> items = new LinkedList<>();
    private Payment payment;
    private Delivery delivery;

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public List<Item> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void setPaymentStrategy(Payment payment) {
        this.payment = payment;
    }

    public void setDeliveryStrategy(Delivery delivery) {
        this.delivery = delivery;
    }

    public Payment getPaymentStrategy() {
        return payment;
    }

    public Delivery getDeliveryStrategy() {
        return delivery;
    }

    public double calculateTotalPrice() {
        return items.stream().mapToDouble(Item::price).sum();
    }

    public OrderProcessingResult processOrder() {
        if (payment == null) {
            throw new IllegalStateException("Payment strategy must be set before processing an order");
        }
        if (delivery == null) {
            throw new IllegalStateException("Delivery strategy must be set before processing an order");
        }
        double total = calculateTotalPrice();
        boolean paid = payment.pay(total);
        List<String> deliveries = items.stream()
                .map(delivery::deliver)
                .toList();
        return new OrderProcessingResult(total, paid, deliveries);
    }

    public record OrderProcessingResult(double totalPrice, boolean paid, List<String> deliveryMessages) {
    }
}
