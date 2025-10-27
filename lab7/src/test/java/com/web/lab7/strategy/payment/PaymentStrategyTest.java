package com.web.lab7.strategy.payment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class PaymentStrategyTest {

    @Test
    void creditCardPaymentSucceedsForValidCard() {
        CreditCardPaymentStrategy payment = new CreditCardPaymentStrategy("1111222233334444", "Test User");
        assertTrue(payment.pay(100));
    }

    @Test
    void creditCardPaymentFailsForInvalidCard() {
        CreditCardPaymentStrategy payment = new CreditCardPaymentStrategy("12", "Test User");
        assertFalse(payment.pay(100));
    }

    @Test
    void payPalPaymentRequiresEmail() {
        PayPalPaymentStrategy payment = new PayPalPaymentStrategy("buyer@example.com");
        assertTrue(payment.pay(45));
    }

    @Test
    void payPalPaymentFailsForMissingEmail() {
        PayPalPaymentStrategy payment = new PayPalPaymentStrategy("invalid-email");
        assertFalse(payment.pay(45));
    }
}
