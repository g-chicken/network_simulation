package utils.network;

import java.util.Arrays;

/** CoreNetwork has minimal elements of a network. */
public class CoreNetwork {
  protected final int vertexNum;
  protected final int linkNum;
  protected final int[] tails; // [link index] = index of tail vertex
  protected final int[] heads; // [link index] = index of head vertex
  protected final double[] weights; // [link index] = weight (length) of link
  protected final int[] first;
  protected final int[] adjList;

  protected CoreNetwork(
      final int vertexNum,
      final int linkNum,
      final int[] tails,
      final int[] heads,
      final double[] weights,
      final int[] first,
      final int[] adjList) {
    this.vertexNum = vertexNum;
    this.linkNum = linkNum;
    this.tails = tails;
    this.heads = heads;
    this.weights = weights;
    this.first = first;
    this.adjList = adjList;
  }

  protected CoreNetwork(
      final int vertexNum,
      final int linkNum,
      final int[] tails,
      final int[] heads,
      final double[] weights) {
    this.vertexNum = vertexNum;
    this.linkNum = linkNum;
    this.tails = tails;
    this.heads = heads;
    this.weights = weights;
    this.first = new int[this.vertexNum];
    this.adjList = new int[this.linkNum];
  }

  protected CoreNetwork(final int vertexNum, final int linkNum) {
    this.vertexNum = vertexNum;
    this.linkNum = linkNum;
    tails = new int[linkNum];
    heads = new int[linkNum];
    weights = new double[linkNum];
    first = new int[vertexNum];
    adjList = new int[linkNum];
  }

  protected void setAdjacencyList() {
    if (vertexNum == 0 || linkNum == 0) {
      return;
    }

    Arrays.fill(first, -1);

    for (int e = linkNum - 1; e >= 0; e--) {
      int v = tails[e];
      adjList[e] = first[v];
      first[v] = e;
    }
  }

  public int getVertexNum() {
    return vertexNum;
  }

  public int getLinkNum() {
    return linkNum;
  }

  public int[] getTails() {
    return tails;
  }

  public int[] getHeads() {
    return heads;
  }

  public double[] getWeights() {
    return weights;
  }

  public int[] getFirst() {
    return first;
  }

  public int[] getAdjList() {
    return adjList;
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();

    stringBuilder.append(String.format("#vertexes = %4d\n", vertexNum));
    stringBuilder.append(String.format("#links    = %4d\n", linkNum));

    stringBuilder.append("adjacency list\n");
    stringBuilder.append("tail : head\n");

    for (int i = 0; i < vertexNum; i++) {
      StringBuilder s = new StringBuilder(String.format("%4d : ", i));

      int e = first[i];
      while (e > -1) {
        s.append(String.format("%4d", heads[e]));

        e = adjList[e];
        if (e > -1) {
          s.append(", ");
        }
      }

      stringBuilder.append(s).append("\n");
    }

    return stringBuilder.toString();
  }
}
