package utils.network;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoreNetworkTest {
  @Test
  void constructor() {
    int[] tails = new int[] {0, 1, 1, 2, 2, 3, 0, 4, 1, 5, 2, 6, 4, 5, 5, 6, 6, 7};
    int[] heads = new int[] {1, 0, 2, 1, 3, 2, 4, 0, 5, 1, 6, 2, 5, 4, 6, 5, 7, 6};
    double[] weights = new double[] {2, 2, 4, 4, 5, 5, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 3, 3};
    int[] first = new int[] {0, 1, 3, 5, 7, 9, 11, 17};
    int[] adjList = new int[] {6, 2, 8, 4, 10, -1, -1, 12, -1, 13, -1, 15, -1, 14, -1, 16, -1, -1};
    CoreNetwork network = new CoreNetwork(8, 18, tails, heads, weights, first, adjList);

    assertEquals(8, network.vertexNum);
    assertEquals(18, network.linkNum);
    assertEquals(18, network.tails.length);
    assertEquals(18, network.heads.length);
    assertEquals(18, network.weights.length);
    assertEquals(8, network.first.length);
    assertEquals(18, network.adjList.length);

    for (int i = 0; i < 8; i++) {
      assertEquals(first[i], network.first[i]);
    }

    for (int i = 0; i < 18; i++) {
      assertEquals(tails[i], network.tails[i]);
      assertEquals(heads[i], network.heads[i]);
      assertEquals(weights[i], network.weights[i]);
      assertEquals(adjList[i], network.adjList[i]);
    }
  }

  @Test
  void setAdjacencyList() {
    {
      //   0 --(0:2)-- 1 --(1:4)-- 2 --(2:5)-- 3
      //   |           |           |
      // (3:1)       (4:1)       (5:1)
      //   |           |           |
      //   4 --(6:1)-- 5 --(7:2)-- 6 --(8:3)-- 7
      int[] tails = new int[] {0, 1, 1, 2, 2, 3, 0, 4, 1, 5, 2, 6, 4, 5, 5, 6, 6, 7};
      int[] heads = new int[] {1, 0, 2, 1, 3, 2, 4, 0, 5, 1, 6, 2, 5, 4, 6, 5, 7, 6};
      double[] weights = new double[] {2, 2, 4, 4, 5, 5, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 3, 3};
      int[] first = new int[] {0, 1, 3, 5, 7, 9, 11, 17};
      int[] adjList =
          new int[] {6, 2, 8, 4, 10, -1, -1, 12, -1, 13, -1, 15, -1, 14, -1, 16, -1, -1};
      CoreNetwork want = new CoreNetwork(8, 18, tails, heads, weights, first, adjList);
      CoreNetwork network = new CoreNetwork(8, 18, tails, heads, weights);

      assertEquals(want.vertexNum, network.vertexNum);
      assertEquals(want.linkNum, network.linkNum);
      assertEquals(want.tails.length, network.tails.length);
      assertEquals(want.heads.length, network.heads.length);
      assertEquals(want.weights.length, network.weights.length);
      assertEquals(want.first.length, network.first.length);
      assertEquals(want.adjList.length, network.adjList.length);

      for (int i = 0; i < 9; i++) {
        assertEquals(want.tails[i], network.tails[i]);
        assertEquals(want.heads[i], network.heads[i]);
        assertEquals(want.weights[i], network.weights[i]);
      }

      network.setAdjacencyList();

      for (int i = 0; i < 8; i++) {
        assertEquals(want.first[i], network.first[i]);
      }

      for (int i = 0; i < 9; i++) {
        assertEquals(want.adjList[i], network.adjList[i]);
      }
    }

    {
      // no links
      int[] tails = new int[] {};
      int[] heads = new int[] {};
      double[] weights = new double[] {};
      int[] first = new int[] {-1, -1, -1, -1, -1, -1, -1, -1};
      int[] adjList = new int[] {};
      CoreNetwork want = new CoreNetwork(8, 0, tails, heads, weights, first, adjList);
      CoreNetwork network = new CoreNetwork(8, 0, tails, heads, weights);

      assertEquals(want.vertexNum, network.vertexNum);
      assertEquals(want.linkNum, network.linkNum);
      assertEquals(want.tails.length, network.tails.length);
      assertEquals(want.heads.length, network.heads.length);
      assertEquals(want.weights.length, network.weights.length);
      assertEquals(want.first.length, network.first.length);
      assertEquals(want.adjList.length, network.adjList.length);

      network.setAdjacencyList();

      for (int i = 0; i < 8; i++) {
        assertEquals(want.first[i], network.first[i]);
      }
    }
  }
}
