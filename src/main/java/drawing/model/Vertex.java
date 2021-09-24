package drawing.model;

import utils.mathematics.Coordination;

/**
 * Vertex express a vertex to draw.
 */
public class Vertex {
  private final Coordination coordination;
  private final double rate;
  private final String label;

  Vertex(final Coordination coordination, final double rate, final String label) {
    this.coordination = coordination;
    this.rate = rate;
    this.label = label;
  }

  public Vertex(final double rate, final String label) {
    this(new Coordination(0.0, 0.0), rate, label);
  }

  public Vertex(final Coordination coordination, final String label) {
    this(coordination, 0.0, label);
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

  @Override
  public String toString() {
    return String.format("(coordination, rate, label) = (%s, %12.10f, %s)",
        coordination, rate, label);
  }
}
