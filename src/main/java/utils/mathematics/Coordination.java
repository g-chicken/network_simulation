package utils.mathematics;

import utils.exceptions.InvalidArguments;

/**
 * Coordination is a coordination.
 */
public class Coordination {
  private static final double ERR = 1.0e-15;
  private double coordinateX;
  private double coordinateY;

  public Coordination(final double x, final double y) {
    coordinateX = x;
    coordinateY = y;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Coordination other) {
      return Math.abs(coordinateX - other.getCoordinateX()) < ERR
          && Math.abs(coordinateY - other.getCoordinateY()) < ERR;
    }

    return false;
  }

  public double distanceFromOrigin() throws InvalidArguments {
    return this.distance(new Coordination(0.0, 0.0));
  }

  /**
   * distance calc the distance between mine and other coordination.
   * if argument is null, throw invalid argument exception.
   *
   * @param c Coordination class
   * @return distance
   * @throws InvalidArguments invalid argument
   */
  public double distance(final Coordination c) throws InvalidArguments {
    if (c == null) {
      throw new InvalidArguments("argument coordination is null");
    }

    return Math.sqrt((coordinateX - c.getCoordinateX()) * (coordinateX - c.getCoordinateX())
        + (coordinateY - c.getCoordinateY()) * (coordinateY - c.getCoordinateY()));
  }

  /**
   * add is to add my coordination's scalar to other coordination's scalar.
   * if argument is null, do nothing.
   *
   * @param c Coordination
   */
  public void add(final Coordination c) {
    if (c == null) {
      return;
    }

    coordinateX += c.getCoordinateX();
    coordinateY += c.getCoordinateY();
  }

  /**
   * sub is to sub other coordination's scalar from my coordination's scalar.
   * if argument is null, do nothing.
   *
   * @param c Coordination
   */
  public void sub(final Coordination c) {
    if (c == null) {
      return;
    }

    coordinateX -= c.getCoordinateX();
    coordinateY -= c.getCoordinateY();
  }

  public double getCoordinateX() {
    return coordinateX;
  }

  public double getCoordinateY() {
    return coordinateY;
  }

  public void setCoordinateX(double coordinateX) {
    this.coordinateX = coordinateX;
  }

  public void setCoordinateY(double coordinateY) {
    this.coordinateY = coordinateY;
  }

  @Override
  public String toString() {
    return String.format("(%36.15f, %36.15f)", coordinateX, coordinateY);
  }
}
