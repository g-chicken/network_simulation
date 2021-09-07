package utils.network;

import analysis.model.Link;
import analysis.model.Network;
import analysis.model.Vertex;
import org.junit.jupiter.api.Test;
import utils.exceptions.DoNotExecution;
import utils.exceptions.InvalidArguments;

import static org.junit.jupiter.api.Assertions.*;

class DijkstraTest {
  @Test
  void constructor() {
    //   0 --(0:2)-- 1 --(1:4)-- 2 --(2:5)-- 3
    //   |           |           |
    // (3:1)       (4:1)       (5:1)
    //   |           |           |
    //   4 --(6:1)-- 5 --(7:2)-- 6 --(8:3)-- 7
    Vertex[] vertices =
        new Vertex[] {
          new Vertex(0.0, 0.0, "0"),
          new Vertex(0.0, 0.0, "1"),
          new Vertex(0.0, 0.0, "2"),
          new Vertex(0.0, 0.0, "3"),
          new Vertex(0.0, 0.0, "4"),
          new Vertex(0.0, 0.0, "5"),
          new Vertex(0.0, 0.0, "6"),
          new Vertex(0.0, 0.0, "7"),
        };
    Link[] links =
        new Link[] {
          new Link(2, 0, 1, 0, "0"),
          new Link(2, 0, 0, 1, "1"),
          new Link(4, 0, 2, 1, "2"),
          new Link(4, 0, 1, 2, "3"),
          new Link(5, 0, 3, 2, "4"),
          new Link(5, 0, 2, 3, "5"),
          new Link(1, 0, 4, 0, "6"),
          new Link(1, 0, 0, 4, "7"),
          new Link(1, 0, 5, 1, "8"),
          new Link(1, 0, 1, 5, "9"),
          new Link(1, 0, 6, 2, "10"),
          new Link(1, 0, 2, 6, "11"),
          new Link(1, 0, 5, 4, "12"),
          new Link(1, 0, 4, 5, "13"),
          new Link(2, 0, 6, 5, "14"),
          new Link(2, 0, 5, 6, "15"),
          new Link(3, 0, 7, 6, "16"),
          new Link(3, 0, 6, 7, "17"),
        };
    int[] tails = new int[] {0, 1, 1, 2, 2, 3, 0, 4, 1, 5, 2, 6, 4, 5, 5, 6, 6, 7};
    int[] heads = new int[] {1, 0, 2, 1, 3, 2, 4, 0, 5, 1, 6, 2, 5, 4, 6, 5, 7, 6};
    double[] weights = new double[] {2, 2, 4, 4, 5, 5, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 3, 3};
    int[] first = new int[] {0, 1, 3, 5, 7, 9, 11, 17};
    int[] adjList = new int[] {6, 2, 8, 4, 10, -1, -1, 12, -1, 13, -1, 15, -1, 14, -1, 16, -1, -1};

    {
      Dijkstra dijkstra = new Dijkstra(8, 18, heads, tails, weights);

      assertEquals(8, dijkstra.getVertexNum());
      assertEquals(18, dijkstra.getLinkNum(), "link num");

      assertEquals(8, dijkstra.getFirst().length, "first length");
      for (int i = 0; i < 8; i++) {
        assertEquals(first[i], dijkstra.getFirst()[i], "fist (index = " + i + ")");
      }

      assertEquals(18, dijkstra.getHeads().length, "heads length");
      assertEquals(18, dijkstra.getTails().length, "tails length");
      assertEquals(18, dijkstra.getLinkWeights().length, "link weights length");
      assertEquals(18, dijkstra.getAdjList().length, "adjList length");
      for (int i = 0; i < 18; i++) {
        assertEquals(heads[i], dijkstra.getHeads()[i], "head (index = " + i + ")");
        assertEquals(tails[i], dijkstra.getTails()[i], "tail (index = " + i + ")");
        assertEquals(weights[i], dijkstra.getLinkWeights()[i], "link weight (index = " + i + ")");
        assertEquals(adjList[i], dijkstra.getAdjList()[i], "adjList (index = " + i + ")");
      }
    }

    {
      Network network = new Network(vertices, links, first, adjList, "test");
      Dijkstra dijkstra = new Dijkstra(network);

      assertEquals(8, dijkstra.getVertexNum());
      assertEquals(18, dijkstra.getLinkNum(), "link num");

      assertEquals(8, dijkstra.getFirst().length, "first length");
      for (int i = 0; i < 8; i++) {
        assertEquals(first[i], dijkstra.getFirst()[i], "fist (index = " + i + ")");
      }

      assertEquals(18, dijkstra.getHeads().length, "heads length");
      assertEquals(18, dijkstra.getTails().length, "tails length");
      assertEquals(18, dijkstra.getLinkWeights().length, "link weights length");
      assertEquals(18, dijkstra.getAdjList().length, "adjList length");
      for (int i = 0; i < 18; i++) {
        assertEquals(heads[i], dijkstra.getHeads()[i], "head (index = " + i + ")");
        assertEquals(tails[i], dijkstra.getTails()[i], "tail (index = " + i + ")");
        assertEquals(weights[i], dijkstra.getLinkWeights()[i], "link weight (index = " + i + ")");
        assertEquals(adjList[i], dijkstra.getAdjList()[i], "adjList (index = " + i + ")");
      }
    }
  }

  @Test
  void dijkstra() {
    //   0 --(0:2)-- 1 --(1:4)-- 2 --(2:5)-- 3
    //   |           |           |
    // (3:1)       (4:1)       (5:1)
    //   |           |           |
    //   4 --(6:1)-- 5 --(7:2)-- 6 --(8:3)-- 7
    Vertex[] vertices =
        new Vertex[] {
          new Vertex(0.0, 0.0, "0"),
          new Vertex(0.0, 0.0, "1"),
          new Vertex(0.0, 0.0, "2"),
          new Vertex(0.0, 0.0, "3"),
          new Vertex(0.0, 0.0, "4"),
          new Vertex(0.0, 0.0, "5"),
          new Vertex(0.0, 0.0, "6"),
          new Vertex(0.0, 0.0, "7"),
        };
    Link[] links =
        new Link[] {
          new Link(2, 0, 1, 0, "0"),
          new Link(2, 0, 0, 1, "1"),
          new Link(4, 0, 2, 1, "2"),
          new Link(4, 0, 1, 2, "3"),
          new Link(5, 0, 3, 2, "4"),
          new Link(5, 0, 2, 3, "5"),
          new Link(1, 0, 4, 0, "6"),
          new Link(1, 0, 0, 4, "7"),
          new Link(1, 0, 5, 1, "8"),
          new Link(1, 0, 1, 5, "9"),
          new Link(1, 0, 6, 2, "10"),
          new Link(1, 0, 2, 6, "11"),
          new Link(1, 0, 5, 4, "12"),
          new Link(1, 0, 4, 5, "13"),
          new Link(2, 0, 6, 5, "14"),
          new Link(2, 0, 5, 6, "15"),
          new Link(3, 0, 7, 6, "16"),
          new Link(3, 0, 6, 7, "17"),
        };
    int[] first = new int[] {0, 1, 3, 5, 7, 9, 11, 17};
    int[] adjList = new int[] {6, 2, 8, 4, 10, -1, -1, 12, -1, 13, -1, 15, -1, 14, -1, 16, -1, -1};
    Network network = new Network(vertices, links, first, adjList, "test");
    Dijkstra dijkstra = new Dijkstra(network);

    for (int i = 0; i < 8; i++) {
      try {
        dijkstra.dijkstra(i);
      } catch (InvalidArguments | DoNotExecution e) {
        //assertEquals("", e.toString(), "start vertex = " + i);
      }
    }
  }
}
