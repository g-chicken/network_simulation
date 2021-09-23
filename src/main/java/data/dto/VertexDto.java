package data.dto;

import utils.exceptions.InvalidArguments;
import utils.mathematics.Coordination;

/**
 * VertexDto is dto of network's vertex.
 *
 * @param c     Coordination
 * @param label label
 */
public record VertexDto(Coordination c, String label) {
  /**
   * distance calculates the distance between this and the v.
   *
   * @param v other vertex
   * @return distance between this and the v.
   */
  public double distance(VertexDto v) throws InvalidArguments {
    return c.distance(v.c);
  }
}
