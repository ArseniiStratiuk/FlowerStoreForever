package com.web.lab7.model.flower;

/**
 * Supported flower colors with hexadecimal representation for quick rendering.
 */
public enum FlowerColor {
    RED("#FF0000"),
    GREEN("#008000"),
    BLUE("#0000FF"),
    WHITE("#FFFFFF"),
    YELLOW("#FFFF00");

    private final String hexCode;

    FlowerColor(String hexCode) {
        this.hexCode = hexCode;
    }

    public String getHexCode() {
        return hexCode;
    }

    @Override
    public String toString() {
        return hexCode;
    }
}
