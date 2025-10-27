package com.web.lab7.strategy.payment;

/**
 * Payment strategy contract. Returning boolean keeps the demo simple.
 */
public interface Payment {

    /**
     * @return human-readable payment strategy identifier
     */
    String getName();

    /**
     * Attempts to pay the provided price using the strategy.
     *
     * @param price amount to pay
     * @return {@code true} if the payment succeeded, otherwise {@code false}
     */
    boolean pay(double price);
}
