package com.web.lab7.strategy.payment;

/**
 * Simulated credit card payment.
 */
public final class CreditCardPaymentStrategy implements Payment {

    /**
     * Number of digits revealed at the end of a masked card number.
     */
    private static final int CARD_SUFFIX_LENGTH = 4;

    /**
     * Placeholder segment inserted for masked card sections.
     */
    private static final String MASKED_SEGMENT = "****";

    /**
     * Card number used for mock validation and masking.
     */
    private final String cardNumber;

    /**
     * Name of the card holder performing the transaction.
     */
    private final String cardHolder;

    /**
     * Creates the strategy with card information.
     *
     * @param cardNumberValue credit card number
     * @param cardHolderName card holder name
     */
    public CreditCardPaymentStrategy(final String cardNumberValue,
                                     final String cardHolderName) {
        this.cardNumber = cardNumberValue;
        this.cardHolder = cardHolderName;
    }

    @Override
    public String getName() {
        return "credit-card";
    }

    @Override
    public boolean pay(final double price) {
        return price > 0
                && cardNumber != null
                && cardNumber.length() >= CARD_SUFFIX_LENGTH
                && cardHolder != null;
    }

    /**
     * Provides a masked representation of the configured card number.
     *
     * @return masked card number
     */
    public String getMaskedCardNumber() {
        if (cardNumber == null || cardNumber.length() < CARD_SUFFIX_LENGTH) {
            return MASKED_SEGMENT;
        }
        return String.format(
                "%s-%s-%s-%s",
                MASKED_SEGMENT,
                MASKED_SEGMENT,
                MASKED_SEGMENT,
                cardNumber.substring(cardNumber.length() - CARD_SUFFIX_LENGTH)
        );
    }

    /**
     * @return configured card holder name
     */
    public String getCardHolder() {
        return cardHolder;
    }
}
