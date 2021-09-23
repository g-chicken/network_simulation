package drawing.model;

import utils.mathematics.Coordination;

/**
 * Vertex express a vertex to draw.
 */
public class Vertex {
  private final Coordination coordination;
  private final double rate;
  private final String label;

  Vertex(final double coordinateX, final double coordinateY, final double rate,
         final String label) {
    coordination = new Coordination(coordinateX, coordinateY);
    this.rate = rate;
    this.label = label;
  }

  public Vertex(final double rate, final String label) {
    this(-1, -1, rate, label);
  }

  boolean equalCoordination(final Coordination c) {
    return coordination.equals(c);
  }

  void setCoordination(final Coordination c) {
    if (c == null) {
      return;
    }

    coordination.setCoordinateX(c.getCoordinateX());
    coordination.setCoordinateY(c.getCoordinateY());
  }

  public Coordination getCoordination() {
    return coordination;
  }

  public double getRate() {
    return rate;
  }

  public String getLabel() {
    return label;
  }
}
