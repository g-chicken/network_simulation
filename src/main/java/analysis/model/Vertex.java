package analysis.model;

import data.dto.VertexDto;

/** Vertex is the network vertex. */
public class Vertex {
  private final double coordinateX;
  private final double coordinateY;
  private final String label;

  public Vertex(final double x, final double y, final String label) {
    this.coordinateX = x;
    this.coordinateY = y;
    this.label = label;
  }

  Vertex(final VertexDto vertexDto) {
    this(vertexDto.x(), vertexDto.y(), vertexDto.label());
  }

  @Override
  public String toString() {
    return String.format("x = %f, y = %f, label = %s", coordinateX, coordinateY, label);
  }
}
