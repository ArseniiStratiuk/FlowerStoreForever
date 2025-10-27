package com.web.lab7.model.flower;

/**
 * Supported flower colors with hexadecimal representation for quick rendering.
 */
public enum FlowerColor {
    /**
     * Classic red tone for roses and tulips.
     */
    RED("#FF0000"),
    /**
     * Natural green shade often used for cacti.
     */
    GREEN("#008000"),
    /**
     * Vivid blue variant for decorative arrangements.
     */
    BLUE("#0000FF"),
    /**
     * Neutral white tone for romashka flowers.
     */
    WHITE("#FFFFFF"),
    /**
     * Bright yellow suitable for festive bouquets.
     */
    YELLOW("#FFFF00");

    /**
     * Hexadecimal colour representation.
     */
    private final String hexCode;

    FlowerColor(final String hexCodeValue) {
        this.hexCode = hexCodeValue;
    }

    /**
     * @return hexadecimal string representing the colour
     */
    public String getHexCode() {
        return hexCode;
    }

    /**
     * @return hexadecimal colour representation
     */
    @Override
    public String toString() {
        return hexCode;
    }
}
