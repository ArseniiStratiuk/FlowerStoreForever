package com.web.lab7.strategy.payment;

/**
 * Simulated PayPal payment.
 */
public final class PayPalPaymentStrategy implements Payment {

    /**
     * Email account used to represent the payer.
     */
    private final String email;

    /**
     * Creates the strategy with a PayPal account email.
     *
     * @param accountEmail email associated with the PayPal account
     */
    public PayPalPaymentStrategy(final String accountEmail) {
        this.email = accountEmail;
    }

    @Override
    public String getName() {
        return "paypal";
    }

    @Override
    public boolean pay(final double price) {
        return price > 0 && email != null && email.contains("@");
    }

    /**
     * @return configured PayPal email address
     */
    public String getEmail() {
        return email;
    }
}
