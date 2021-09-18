package utils.network;

import analysis.model.Network;
import java.util.ArrayList;
import java.util.Arrays;
import org.jetbrains.annotations.NotNull;
import utils.exceptions.DoNotExecution;
import utils.exceptions.InvalidArguments;

/** Dijkstra implements the dijkstra method (search for the shortest path). */
public class Dijkstra extends CoreNetwork {
  private final ArrayList<ArrayList<Integer>> paths;

  Dijkstra(
      final int vertexNum,
      final int linkNum,
      final int[] tails,
      final int[] heads,
      final double[] weights,
      final int[] first,
      final int[] adjList) {
    super(vertexNum, linkNum, tails, heads, weights, first, adjList);

    paths = new ArrayList<>();
    for (int i = 0; i < vertexNum; i++) {
      paths.add(new ArrayList<>());
    }
  }

  /**
   * Dijkstra is constructor.
   *
   * @param network the network of analysis pkg.
   */
  public Dijkstra(@NotNull Network network) {
    super(network.vertexNum, network.linkNum, network.tails, network.heads, network.weights);
    setAdjacencyList();

    paths = new ArrayList<>();
    for (int i = 0; i < vertexNum; i++) {
      paths.add(new ArrayList<>());
    }
  }

  /**
   * dijkstra calc shortest paths.
   *
   * @param startVertexIndex vertex index of start
   */
  public void dijkstra(final int startVertexIndex) throws InvalidArguments, DoNotExecution {
    if (startVertexIndex >= vertexNum || startVertexIndex < 0) {
      throw new InvalidArguments(
          String.format("invalid start vertex index (vertex index = %d)", startVertexIndex));
    }

    // initialize
    paths.forEach(ArrayList::clear);

    double[] pathWeights = new double[vertexNum];
    Arrays.fill(pathWeights, -1.0);

    Dheap dheap = new Dheap((vertexNum + linkNum - 1) / vertexNum, vertexNum);
    dheap.setFirstHeap(startVertexIndex);
    dheap.setHeapIndexesTo0(startVertexIndex);
    dheap.setHeapNumTo1();

    while (dheap.getHeapNum() > 0) {
      int v1 = dheap.getFirst();
      dheap.shiftDown(pathWeights);

      int e = first[v1];
      while (e > -1) {
        int v2 = heads[e];

        if (v2 != startVertexIndex) {
          ArrayList<Integer> p = paths.get(v2);

          if (p.size() == 0) {
            pathWeights[v2] = pathWeights[v1] + weights[e];
            p.add(e);

            dheap.add(v2);
            dheap.shiftUp(v2, pathWeights);
          } else if (pathWeights[v2] > pathWeights[v1] + weights[e]) {
            pathWeights[v2] = pathWeights[v1] + weights[e];
            p.clear();
            p.add(e);

            dheap.shiftUp(v2, pathWeights);
          } else if (pathWeights[v2] == pathWeights[v1] + weights[e]) {
            p.add(e);
          }
        }

        e = adjList[e];
      }
    }
  }

  ArrayList<ArrayList<Integer>> getPaths() {
    return paths;
  }
}
