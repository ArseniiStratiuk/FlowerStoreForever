package com.web.lab7.strategy.payment;

/**
 * Payment strategy contract. Returning boolean keeps the demo simple.
 */
public interface Payment {

    String getName();

    boolean pay(double price);
}
