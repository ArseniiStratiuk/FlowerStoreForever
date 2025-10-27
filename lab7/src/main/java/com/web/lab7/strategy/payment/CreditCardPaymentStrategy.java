package com.web.lab7.strategy.payment;

/**
 * Simulated credit card payment.
 */
public class CreditCardPaymentStrategy implements Payment {

    private final String cardNumber;
    private final String cardHolder;

    public CreditCardPaymentStrategy(String cardNumber, String cardHolder) {
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
    }

    @Override
    public String getName() {
        return "credit-card";
    }

    @Override
    public boolean pay(double price) {
        return price > 0 && cardNumber != null && cardNumber.length() >= 4 && cardHolder != null;
    }

    public String getMaskedCardNumber() {
        if (cardNumber == null || cardNumber.length() < 4) {
            return "****";
        }
        return "****-****-****-" + cardNumber.substring(cardNumber.length() - 4);
    }

    public String getCardHolder() {
        return cardHolder;
    }
}
