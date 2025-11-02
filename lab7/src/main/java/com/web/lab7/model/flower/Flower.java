package com.web.lab7.model.flower;

import com.web.lab7.model.item.Item;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Domain object describing a single flower.
 */
@Entity
@Table(name = "flowers")
public class Flower extends Item {

    /**
     * Technical identifier managed by the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Sepal length in centimetres.
     */
    @Column(name = "sepal_length", nullable = false)
    private double sepalLength;

    /**
     * Visual colour representation of the flower.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "color", nullable = false)
    private FlowerColor color;

    /**
     * Price of the individual flower.
     */
    @Column(name = "price", nullable = false)
    private double price;

    /**
     * Specific flower type (rose, tulip, etc.).
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "flower_type", nullable = false)
    private FlowerType type;

    /**
     * Creates a flower with default values.
     */
    public Flower() {
        this(FlowerType.ROSE, FlowerColor.RED, 0, 0);
    }

    /**
     * Creates a flower with fully specified attributes.
     *
     * @param flowerType flower type
     * @param flowerColor colour representation
     * @param initialSepalLength sepal length in centimetres
     * @param initialPrice price of the flower
     */
    public Flower(final FlowerType flowerType,
                  final FlowerColor flowerColor,
                  final double initialSepalLength,
                  final double initialPrice) {
        super(flowerType + " flower");
        this.type = flowerType;
        this.color = flowerColor;
        this.sepalLength = initialSepalLength;
        this.price = initialPrice;
    }

    /**
     * @return identifier assigned by the database
     */
    public Long getId() {
        return id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double price() {
        return price;
    }

    /**
     * @return the sepal length
     */
    public double getSepalLength() {
        return sepalLength;
    }

    /**
     * Updates the sepal length value.
     *
     * @param newSepalLength new sepal length in centimetres
     */
    public void setSepalLength(final double newSepalLength) {
        this.sepalLength = newSepalLength;
    }

    /**
     * @return current colour of the flower
     */
    public FlowerColor getColor() {
        return color;
    }

    /**
     * Updates the colour of the flower.
     *
     * @param newColor new colour
     */
    public void setColor(final FlowerColor newColor) {
        this.color = newColor;
    }

    /**
     * Updates the price value.
     *
     * @param newPrice new price
     */
    public void setPrice(final double newPrice) {
        this.price = newPrice;
    }

    /**
     * @return current price of the flower
     */
    public double getPrice() {
        return price;
    }

    /**
     * @return current flower type
     */
    public FlowerType getType() {
        return type;
    }

    /**
     * Updates the flower type and adjusts the description accordingly.
     *
     * @param newType new flower type
     */
    public void setType(final FlowerType newType) {
        this.type = newType;
        setDescription(newType + " flower");
    }
}
