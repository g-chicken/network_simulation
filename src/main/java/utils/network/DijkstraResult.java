package utils.network;

import java.util.ArrayList;
import java.util.Arrays;

/** DijkstraResult express the result of dijkstra method. */
public class DijkstraResult {
  private final int startVertexIndex;
  private final double[] pathWeights;
  private final ArrayList<ArrayList<Integer>> shortestPaths;

  DijkstraResult(final int startVertexIndex, final int vertexNum) {
    this.startVertexIndex = startVertexIndex;

    pathWeights = new double[vertexNum];
    Arrays.fill(pathWeights, -1.0);
    pathWeights[startVertexIndex] = 0.0;

    shortestPaths = new ArrayList<>();
    for (int i = 0; i < vertexNum; i++) {
      shortestPaths.add(new ArrayList<>());
    }
  }

  double getPathWeight(final int index) {
    return pathWeights[index];
  }

  void setPathWeight(final int index, double pathWeight) {
    pathWeights[index] = pathWeight;
  }

  double[] getPathWeights() {
    return pathWeights;
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder =
        new StringBuilder(String.format("start vertex index = %d\n", startVertexIndex));

    for (int i = 0; i < pathWeights.length; i++) {
      stringBuilder.append(
          String.format("   destination = %d (total weight = %9.5f)\n", i, pathWeights[i]));
    }

    return stringBuilder.toString();
  }
}
