package ucu.edu.ua.apps;

import java.util.Random;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Test class for FlowerBucket. */
public final class FlowerBucketTest {
  /** Random number generator for test values. */
  private static final Random RANDOM_GENERATOR = new Random();
  /** Maximum quantity value for testing. */
  private static final int MAX_QUANTITY = 1000;
  /** Maximum price value for testing. */
  private static final int MAX_PRICE = 100;

  /** The flower bucket instance being tested. */
  private FlowerBucket flowerBucket;

  /** Initializes the test environment before each test. */
  @BeforeEach
  public void init() {
    flowerBucket = new FlowerBucket();
  }

  /** Tests that the total price is calculated correctly. */
  @Test
  public void testPrice() {
    int price = RANDOM_GENERATOR.nextInt(MAX_PRICE);
    int quantity = RANDOM_GENERATOR.nextInt(MAX_QUANTITY);
    Flower flower = new Rose();
    flower.setPrice(price);
    FlowerPack flowerPack = new FlowerPack(flower, quantity);
    flowerBucket.addFlowerPack(flowerPack);
    Assertions.assertEquals(price * quantity, flowerBucket.getPrice());
  }
}
