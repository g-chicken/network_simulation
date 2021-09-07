package analysis.model;

import data.dto.NetworkDto;
import java.util.Arrays;
import utils.exceptions.InvalidArguments;

/** Network express a network. */
public class Network {
  private final Vertex[] vertices;
  private final Link[] links;
  private int[] first;
  private int[] adjList;
  private final String label;

  /**
   * Network is constructor.
   *
   * @param vertices network's vertexes
   * @param links network's links
   * @param first adjacency list
   * @param adjList adjacency list
   * @param label label
   */
  public Network(
      final Vertex[] vertices,
      final Link[] links,
      final int[] first,
      final int[] adjList,
      final String label) {
    this.vertices = vertices;
    this.links = links;
    this.first = first;
    this.adjList = adjList;
    this.label = label;
  }

  public Network(final Vertex[] vertices, final Link[] links, final String label) {
    this(vertices, links, new int[vertices.length], new int[links.length], label);
  }

  /**
   * Network is constructor.
   *
   * @param networkDto a network inputted data
   */
  public Network(final NetworkDto networkDto) {
    vertices = new Vertex[networkDto.getVertexNum()];
    for (int i = 0; i < networkDto.getVertexNum(); i++) {
      vertices[i] = new Vertex(networkDto.vertexDtoes()[i]);
    }

    links = new Link[networkDto.getLinkNum()];
    for (int i = 0; i < networkDto.getLinkNum(); i++) {
      links[i] = new Link(networkDto.linkDtoes()[i]);
    }

    label = networkDto.label();
  }

  /** setAdjacencyList construct an adjacency list. */
  public void setAdjacencyList() {
    if (vertices == null || links == null) {
      return;
    }

    first = new int[this.getVertexNum()];
    Arrays.fill(first, -1);

    adjList = new int[this.getLinkNum()];

    for (int e = this.getLinkNum() - 1; e >= 0; e--) {
      int v = links[e].getTailIndex();
      adjList[e] = first[v];
      first[v] = e;
    }
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

  public int getVertexNum() {
    return vertices.length;
  }

  public int getLinkNum() {
    return links.length;
  }

  public Link[] getLinks() {
    return links;
  }

  public int[] getFirst() {
    return first;
  }

  public int[] getAdjList() {
    return adjList;
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder(String.format("label = %s\n", label));

    stringBuilder.append("vertex\n");
    for (Vertex v : vertices) {
      stringBuilder.append(String.format("   %s\n", v));
    }

    stringBuilder.append("link\n");
    for (Link l : links) {
      stringBuilder.append(String.format("   %s\n", l));
    }

    return stringBuilder.toString();
  }
}
