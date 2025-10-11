package ucu.edu.ua.apps;

import lombok.AllArgsConstructor;

/**
 * Filter for searching flowers by their type.
 * This is a final implementation of the SearchFilter interface.
 */
@AllArgsConstructor
public final class FlowerTypeFilter implements SearchFilter {
  /** The flower type to filter by. */
  private FlowerType flowerType;

  /**
   * Checks if the flower matches the specified type.
   *
   * @param flower the flower to check
   * @return true if the flower matches the type, false otherwise
   */
  @Override
  public boolean match(final Flower flower) {
    return flower.getFlowerType() == flowerType;
  }
}
