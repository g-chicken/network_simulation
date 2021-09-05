package data.dto;

/**
 * NetworkDto expresses network.
 *
 * @param vertexDtoes network's vertexes
 * @param linkDtoes network's links
 * @param label label
 */
public record NetworkDto(VertexDto[] vertexDtoes, LinkDto[] linkDtoes, String label) {
  public int getVertexNum() {
    return vertexDtoes().length;
  }

  public int getLinkNum() {
    return linkDtoes().length;
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder(String.format("label = %s", this.label()));

    stringBuilder.append("vertex\n");
    for (VertexDto v : this.vertexDtoes()) {
      stringBuilder.append(String.format("   %s\n", v));
    }

    stringBuilder.append("link\n");
    for (LinkDto l : this.linkDtoes()) {
      stringBuilder.append(String.format("   %s\n", l));
    }

    return stringBuilder.toString();
  }
}