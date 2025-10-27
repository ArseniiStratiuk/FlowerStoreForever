package com.web.lab7.strategy.payment;

/**
 * Simulated PayPal payment.
 */
public class PayPalPaymentStrategy implements Payment {

    private final String email;

    public PayPalPaymentStrategy(String email) {
        this.email = email;
    }

    @Override
    public String getName() {
        return "paypal";
    }

    @Override
    public boolean pay(double price) {
        return price > 0 && email != null && email.contains("@");
    }

    public String getEmail() {
        return email;
    }
}
