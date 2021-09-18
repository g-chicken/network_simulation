package analysis.model;

import data.dto.VertexDto;
import org.jetbrains.annotations.NotNull;

/** Vertex is the network vertex. */
public class Vertex {
  private final double coordinateX;
  private final double coordinateY;
  private final String label;

  /**
   * constructor.
   *
   * @param x x-coordinate
   * @param y y-coordinate
   * @param label label
   */
  public Vertex(final double x, final double y, final String label) {
    this.coordinateX = x;
    this.coordinateY = y;
    this.label = label;
  }

  Vertex(final @NotNull VertexDto vertexDto) {
    this(vertexDto.x(), vertexDto.y(), vertexDto.label());
  }

  double getCoordinateX() {
    return coordinateX;
  }

  double getCoordinateY() {
    return coordinateY;
  }

  String getLabel() {
    return label;
  }

  @Override
  public String toString() {
    return String.format("x = %f, y = %f, label = %s", coordinateX, coordinateY, label);
  }
}
