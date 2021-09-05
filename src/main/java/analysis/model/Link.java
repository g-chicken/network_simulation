package analysis.model;

import data.dto.LinkDto;

/** Link is the network link. */
public class Link {
  private final double weight;
  private final double capacity;
  private final int headIndex;
  private final int tailIndex;
  private final String label;

  Link(
      final double weight,
      final double capacity,
      final int headIndex,
      final int tailIndex,
      final String label) {
    this.weight = weight;
    this.capacity = capacity;
    this.headIndex = headIndex;
    this.tailIndex = tailIndex;
    this.label = label;
  }

  Link(final LinkDto linkDto) {
    weight = linkDto.weight();
    capacity = linkDto.capacity();
    headIndex = linkDto.headVertexIndex();
    tailIndex = linkDto.tailVertexIndex();
    label = linkDto.label();
  }

  public int getHeadIndex() {
    return headIndex;
  }

  public int getTailIndex() {
    return tailIndex;
  }

  public double getWeight() {
    return weight;
  }

  @Override
  public String toString() {
    return String.format(
        "weight = %f, capacity = %f, head_index = %d, tail_index = %d, label = %s",
        weight, capacity, headIndex, tailIndex, label);
  }
}
