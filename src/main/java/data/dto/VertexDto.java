package data.dto;

/**
 * VertexDto is dto of network's vertex.
 *
 * @param x coordinate x
 * @param y coordinate y
 * @param label label
 */
public record VertexDto(double x, double y, String label) {
  /**
   * distance calculates the distance between this and the v.
   *
   * @param v other vertex
   * @return distance between this and the v.
   */
  public double distance(@org.jetbrains.annotations.NotNull VertexDto v) {
    double subX = this.x - v.x;
    double subY = this.y - v.y;

    return Math.sqrt(subX * subX + subY * subY);
  }
}
