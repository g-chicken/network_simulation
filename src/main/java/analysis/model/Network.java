package analysis.model;

import data.dto.LinkDto;
import data.dto.NetworkDto;
import java.util.LinkedList;
import org.jetbrains.annotations.NotNull;
import utils.exceptions.InvalidArguments;
import utils.network.CoreNetwork;

/** Network express a network. */
public class Network extends CoreNetwork {
  private final Vertex[] vertexes;
  private final Link[] links;
  private final String label;

  /**
   * constructor.
   *
   * @param vertexes #vertexes
   * @param links #links
   * @param tails link of tail
   * @param heads link of head
   * @param weights link of weight (length)
   * @param first adjacency list
   * @param adjList adjacency list
   * @param label network's label
   */
  public Network(
      final Vertex @NotNull [] vertexes,
      final Link @NotNull [] links,
      final int[] tails,
      final int[] heads,
      final double[] weights,
      final int[] first,
      final int[] adjList,
      final String label) {
    super(vertexes.length, links.length, tails, heads, weights, first, adjList);
    this.vertexes = vertexes;
    this.links = links;
    this.label = label;
  }

  /**
   * Network is constructor.
   *
   * @param networkDto a network inputted data
   */
  public Network(final @NotNull NetworkDto networkDto) {
    super(networkDto.getVertexNum(), networkDto.getLinkNum());

    vertexes = new Vertex[networkDto.getVertexNum()];
    for (int i = 0; i < networkDto.getVertexNum(); i++) {
      vertexes[i] = new Vertex(networkDto.vertexDtoes()[i]);
    }

    links = new Link[networkDto.getLinkNum()];
    for (int i = 0; i < networkDto.getLinkNum(); i++) {
      LinkDto dto = networkDto.linkDtoes()[i];
      links[i] = new Link(dto.label());
      tails[i] = dto.tailVertexIndex();
      heads[i] = dto.headVertexIndex();
      weights[i] = dto.weight();
    }

    label = networkDto.label();

    setAdjacencyList();
  }

  /**
   * getDegree get degree of the vertexIndex.
   *
   * @param vertexIndex vertex index
   * @return degree
   * @throws InvalidArguments invalid argument exception
   */
  public int getDegree(final int vertexIndex) throws InvalidArguments {
    if (vertexIndex < 0 || vertexIndex >= getVertexNum()) {
      throw new InvalidArguments("invalid vertexIndex");
    }

    int degree = 0;
    int e = first[vertexIndex];
    while (e > -1) {
      degree++;
      e = adjList[e];
    }

    return degree;
  }

  /**
   * searchPathNum count origin-destination paths.
   *
   * @param originVertexIndex origin vertex index
   * @param destinationVertexIndex destination vertex index
   * @return #paths
   * @throws InvalidArguments invalid argument exception
   */
  public int searchPathNum(final int originVertexIndex, final int destinationVertexIndex)
      throws InvalidArguments {
    if (originVertexIndex < 0 || originVertexIndex >= vertexNum) {
      throw new InvalidArguments(
          String.format("invalid origin vertex index (vertex index = %d)", originVertexIndex));
    }

    if (destinationVertexIndex < 0 || destinationVertexIndex >= vertexNum) {
      throw new InvalidArguments(
          String.format(
              "invalid destination vertex index (vertex index = %d)", destinationVertexIndex));
    }

    if (originVertexIndex == destinationVertexIndex) {
      return 0;
    }

    LinkedList<Integer> stack =
        new LinkedList<>() {
          {
            add(originVertexIndex);
          }
        };

    return depthFirstSearch(stack, destinationVertexIndex, 0);
  }

  private int depthFirstSearch(
      final LinkedList<Integer> stack, final int destinationVertexIndex, int pathNum) {
    if (stack == null || stack.size() == 0) {
      return pathNum;
    }

    int v = stack.peekLast();
    int e = first[v];

    while (e > -1) {
      int headV = heads[e];

      if (headV == destinationVertexIndex) {
        pathNum++;
      } else if (!stack.contains(headV)) {
        stack.add(headV);
        pathNum = depthFirstSearch(stack, destinationVertexIndex, pathNum);
      }

      e = adjList[e];
    }

    stack.pollLast();

    return pathNum;
  }

  public Vertex[] getVertexes() {
    return vertexes;
  }

  public Link[] getLinks() {
    return links;
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder(String.format("label = %s\n", label));

    stringBuilder.append("vertex\n");
    for (Vertex v : vertexes) {
      stringBuilder.append(String.format("   %s\n", v));
    }

    stringBuilder.append("link\n");
    for (Link l : links) {
      stringBuilder.append(String.format("   %s\n", l));
    }

    return stringBuilder.toString();
  }
}
