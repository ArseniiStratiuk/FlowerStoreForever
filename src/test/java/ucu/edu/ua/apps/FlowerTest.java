package ucu.edu.ua.apps;

import java.util.Random;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Test class for Flower. */
public final class FlowerTest {
  /** Random number generator for test values. */
  private static final Random RANDOM_GENERATOR = new Random();
  /** Maximum price value for testing. */
  private static final int MAX_PRICE = 100;
  /** The flower instance being tested. */
  private Flower flower;

  /** Initializes the test environment before each test. */
  @BeforeEach
  public void init() {
    flower = new Flower();
  }

  /** Tests that the price can be set and retrieved correctly. */
  @Test
  public void testPrice() {
    int price = RANDOM_GENERATOR.nextInt(MAX_PRICE);
    flower.setPrice(price);
    Assertions.assertEquals(price, flower.getPrice());
  }

  /** Tests that the color can be set and retrieved correctly. */
  @Test
  public void testColor() {
    FlowerColor color = FlowerColor.RED;
    flower.setColor(color);
    Assertions.assertEquals(color, flower.getColor());
  }
}
