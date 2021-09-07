package utils.network;

import analysis.model.Link;
import analysis.model.Network;
import java.util.Arrays;
import utils.exceptions.DoNotExecution;
import utils.exceptions.InvalidArguments;

/** Dijkstra implements the dijkstra method (search for the shortest path). */
public class Dijkstra {
  private final int vertexNum;
  private final int linkNum;
  private final int[] tails;
  private final int[] heads;
  private final double[] linkWeights;
  private int[] first;
  private int[] adjList;
  private int[] path;
  private Dheap dheap;

  /**
   * Dijkstra is constructor.
   *
   * @param vertexNum # of vertexes
   * @param linkNum # of links
   * @param heads indexes of link's head
   * @param tails indexes of link's tail
   * @param weights weights of link's weight (length)
   */
  public Dijkstra(
      final int vertexNum,
      final int linkNum,
      final int[] heads,
      final int[] tails,
      final double[] weights) {
    this.vertexNum = vertexNum;
    this.linkNum = linkNum;
    this.heads = heads;
    this.tails = tails;
    this.linkWeights = weights;

    setAdjacencyList();
    initialize();
  }

  /**
   * Dijkstra is constructor.
   *
   * @param network the network of analysis pkg.
   */
  public Dijkstra(Network network) {
    vertexNum = network.getVertexNum();
    linkNum = network.getLinkNum();

    heads = new int[linkNum];
    tails = new int[linkNum];
    linkWeights = new double[linkNum];

    for (int i = 0; i < network.getLinkNum(); i++) {
      Link l = network.getLinks()[i];

      heads[i] = l.getHeadIndex();
      tails[i] = l.getTailIndex();
      linkWeights[i] = l.getWeight();
    }

    first = new int[vertexNum];
    for (int i = 0; i < network.getVertexNum(); i++) {
      first[i] = network.getFirst()[i];
    }

    adjList = new int[linkNum];
    for (int i = 0; i < network.getLinkNum(); i++) {
      adjList[i] = network.getAdjList()[i];
    }

    initialize();
  }

  /**
   * dijkstra calc shortest paths.
   *
   * @param startVertexIndex vertex index of start
   * @return result of dijkstra method
   */
  public DijkstraResult dijkstra(final int startVertexIndex)
      throws InvalidArguments, DoNotExecution {
    DijkstraResult result = new DijkstraResult(startVertexIndex, vertexNum);

    // initialize
    path[startVertexIndex] = 0;
    result.setPathWeight(startVertexIndex, 0.0);

    dheap.setFirstHeap(startVertexIndex);
    dheap.setHeapIndexesTo0(startVertexIndex);
    dheap.setHeapNumTo1();

    while (dheap.getHeapNum() > 0) {
      int v1 = dheap.pop();
      dheap.shiftDown(result.getPathWeights());

      int e = first[v1];
      while (e > -1) {
        int v2 = heads[e];

        if (path[v2] == -1) {
          result.setPathWeight(v2, result.getPathWeight(v1) + linkWeights[e]);
          path[v2] = e;

          dheap.add(v2);
          dheap.shiftUp(v2, result.getPathWeights());
        } else if (result.getPathWeight(v2) > result.getPathWeight(v1) + linkWeights[e]) {
          result.setPathWeight(v2, result.getPathWeight(v1) + linkWeights[e]);
          path[v2] = e;
          dheap.shiftUp(v2, result.getPathWeights());
        }

        e = adjList[e];
      }
    }

    return result;
  }

  private void setAdjacencyList() {
    if (vertexNum == 0 || linkNum == 0) {
      return;
    }

    first = new int[vertexNum];
    Arrays.fill(first, -1);

    adjList = new int[linkNum];

    for (int e = linkNum - 1; e >= 0; e--) {
      int v = tails[e];
      adjList[e] = first[v];
      first[v] = e;
    }
  }

  private void initialize() {
    path = new int[vertexNum];
    dheap = new Dheap((vertexNum + linkNum - 1) / vertexNum, vertexNum);

    Arrays.fill(path, -1);
  }

  int getVertexNum() {
    return vertexNum;
  }

  int getLinkNum() {
    return linkNum;
  }

  int[] getHeads() {
    return heads;
  }

  int[] getTails() {
    return tails;
  }

  double[] getLinkWeights() {
    return linkWeights;
  }

  int[] getFirst() {
    return first;
  }

  int[] getAdjList() {
    return adjList;
  }
}
