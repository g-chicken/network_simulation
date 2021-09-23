package analysis.model;

import data.dto.LinkDto;
import data.dto.NetworkDto;
import data.dto.VertexDto;
import org.junit.jupiter.api.Test;
import utils.exceptions.InvalidArguments;
import utils.mathematics.Coordination;

import static org.junit.jupiter.api.Assertions.*;

class NetworkTest {
  @Test
  void constructor() {
    //   0 --(0:2)-- 1 --(1:4)-- 2 --(2:5)-- 3
    //   |           |           |
    // (3:1)       (4:1)       (5:1)
    //   |           |           |
    //   4 --(6:1)-- 5 --(7:2)-- 6 --(8:3)-- 7
    Vertex[] vertices =
        new Vertex[] {
            new Vertex("0"),
            new Vertex("1"),
            new Vertex("2"),
            new Vertex("3"),
            new Vertex("4"),
            new Vertex("5"),
            new Vertex("6"),
            new Vertex("7"),
        };
    Link[] links =
        new Link[] {
            new Link("0"),
            new Link("1"),
            new Link("2"),
            new Link("3"),
            new Link("4"),
            new Link("5"),
            new Link("6"),
            new Link("7"),
            new Link("8"),
            new Link("9"),
            new Link("10"),
            new Link("11"),
            new Link("12"),
            new Link("13"),
            new Link("14"),
            new Link("15"),
            new Link("16"),
            new Link("17"),
        };
    int[] tails = new int[] {0, 1, 1, 2, 2, 3, 0, 4, 1, 5, 2, 6, 4, 5, 5, 6, 6, 7};
    int[] heads = new int[] {1, 0, 2, 1, 3, 2, 4, 0, 5, 1, 6, 2, 5, 4, 6, 5, 7, 6};
    double[] weights = new double[] {2, 2, 4, 4, 5, 5, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 3, 3};
    int[] first = new int[] {0, 1, 3, 5, 7, 9, 11, 17};
    int[] adjList = new int[] {6, 2, 8, 4, 10, -1, -1, 12, -1, 13, -1, 15, -1, 14, -1, 16, -1, -1};
    String label = "network";

    // raw constructor
    {
      Network network = new Network(vertices, links, tails, heads, weights, first, adjList, label);

      assertEquals(8, network.getVertexNum());
      assertEquals(18, network.getLinkNum());
      assertEquals(18, network.getTails().length);
      assertEquals(18, network.getHeads().length);
      assertEquals(18, network.getWeights().length);
      assertEquals(8, network.getFirst().length);
      assertEquals(18, network.getAdjList().length);

      for (int i = 0; i < 8; i++) {
        assertEquals(vertices[i].toString(), network.getVertexes()[i].toString());
        assertEquals(first[i], network.getFirst()[i]);
      }

      for (int i = 0; i < 18; i++) {
        assertEquals(links[i].toString(), network.getLinks()[i].toString());
        assertEquals(tails[i], network.getTails()[i]);
        assertEquals(heads[i], network.getHeads()[i]);
        assertEquals(weights[i], network.getWeights()[i]);
        assertEquals(adjList[i], network.getAdjList()[i]);
      }
    }

    // network dto constructor
    {
      VertexDto[] vertexDtoes = new VertexDto[8];
      for (int i = 0; i < 8; i++) {
        vertexDtoes[i] =
            new VertexDto(new Coordination(0.0, 0.0), vertices[i].label());
      }

      LinkDto[] linkDtoes = new LinkDto[18];
      for (int i = 0; i < 18; i++) {
        linkDtoes[i] = new LinkDto(weights[i], -1.0, heads[i], tails[i], links[i].label());
      }

      NetworkDto dto = new NetworkDto(vertexDtoes, linkDtoes, label);
      Network network = new Network(dto);

      assertEquals(8, network.getVertexNum());
      assertEquals(18, network.getLinkNum());
      assertEquals(18, network.getTails().length);
      assertEquals(18, network.getHeads().length);
      assertEquals(18, network.getWeights().length);
      assertEquals(8, network.getFirst().length);
      assertEquals(18, network.getAdjList().length);

      for (int i = 0; i < 8; i++) {
        assertEquals(vertices[i].toString(), network.getVertexes()[i].toString());
        assertEquals(first[i], network.getFirst()[i]);
      }

      for (int i = 0; i < 18; i++) {
        assertEquals(links[i].toString(), network.getLinks()[i].toString());
        assertEquals(tails[i], network.getTails()[i]);
        assertEquals(heads[i], network.getHeads()[i]);
        assertEquals(weights[i], network.getWeights()[i]);
        assertEquals(adjList[i], network.getAdjList()[i]);
      }
    }
  }

  @Test
  void degree() {
    //   0 --(0:2)-- 1 --(1:4)-- 2 --(2:5)-- 3
    //   |           |           |
    // (3:1)       (4:1)       (5:1)
    //   |           |           |
    //   4 --(6:1)-- 5 --(7:2)-- 6 --(8:3)-- 7
    Vertex[] vertices =
        new Vertex[] {
            new Vertex("0"),
            new Vertex("1"),
            new Vertex("2"),
            new Vertex("3"),
            new Vertex("4"),
            new Vertex("5"),
            new Vertex("6"),
            new Vertex("7"),
        };
    Link[] links =
        new Link[] {
            new Link("0"),
            new Link("1"),
            new Link("2"),
            new Link("3"),
            new Link("4"),
            new Link("5"),
            new Link("6"),
            new Link("7"),
            new Link("8"),
            new Link("9"),
            new Link("10"),
            new Link("11"),
            new Link("12"),
            new Link("13"),
            new Link("14"),
            new Link("15"),
            new Link("16"),
            new Link("17"),
        };
    int[] tails = new int[] {0, 1, 1, 2, 2, 3, 0, 4, 1, 5, 2, 6, 4, 5, 5, 6, 6, 7};
    int[] heads = new int[] {1, 0, 2, 1, 3, 2, 4, 0, 5, 1, 6, 2, 5, 4, 6, 5, 7, 6};
    double[] weights = new double[] {2, 2, 4, 4, 5, 5, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 3, 3};
    int[] first = new int[] {0, 1, 3, 5, 7, 9, 11, 17};
    int[] adjList = new int[] {6, 2, 8, 4, 10, -1, -1, 12, -1, 13, -1, 15, -1, 14, -1, 16, -1, -1};
    String label = "network";
    int[] degrees = new int[] {2, 3, 3, 1, 2, 3, 3, 1};
    Network network = new Network(vertices, links, tails, heads, weights, first, adjList, label);

    for (int i = 0; i < 8; i++) {
      try {
        assertEquals(degrees[i], network.getDegree(i));
      } catch (InvalidArguments e) {
        assertEquals("", e.toString());
      }
    }

    try {
      assertEquals(-1, network.getDegree(-1));
    } catch (InvalidArguments e) {
      assertEquals(
          "utils.exceptions.InvalidArguments: Invalid Argument : invalid vertexIndex",
          e.toString());
    }

    try {
      assertEquals(-1, network.getDegree(9));
    } catch (InvalidArguments e) {
      assertEquals(
          "utils.exceptions.InvalidArguments: Invalid Argument : invalid vertexIndex",
          e.toString());
    }
  }

  @Test
  void isConnected() {
    //   0 --(0:2)-- 1 --(1:4)-- 2 --(2:5)-- 3
    //   |           |           |
    // (3:1)       (4:1)       (5:1)
    //   |           |           |
    //   4 --(6:1)-- 5 --(7:2)-- 6 --(8:3)-- 7
    {
      Vertex[] vertices =
          new Vertex[] {
              new Vertex("0"),
              new Vertex("1"),
              new Vertex("2"),
              new Vertex("3"),
              new Vertex("4"),
              new Vertex("5"),
              new Vertex("6"),
              new Vertex("7"),
          };
      Link[] links =
          new Link[] {
              new Link("0"),
              new Link("1"),
              new Link("2"),
              new Link("3"),
              new Link("4"),
              new Link("5"),
              new Link("6"),
              new Link("7"),
              new Link("8"),
              new Link("9"),
              new Link("10"),
              new Link("11"),
              new Link("12"),
              new Link("13"),
              new Link("14"),
              new Link("15"),
              new Link("16"),
              new Link("17"),
          };
      int[] tails = new int[] {0, 1, 1, 2, 2, 3, 0, 4, 1, 5, 2, 6, 4, 5, 5, 6, 6, 7};
      int[] heads = new int[] {1, 0, 2, 1, 3, 2, 4, 0, 5, 1, 6, 2, 5, 4, 6, 5, 7, 6};
      double[] weights = new double[] {2, 2, 4, 4, 5, 5, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 3, 3};
      int[] first = new int[] {0, 1, 3, 5, 7, 9, 11, 17};
      int[] adjList =
          new int[] {6, 2, 8, 4, 10, -1, -1, 12, -1, 13, -1, 15, -1, 14, -1, 16, -1, -1};
      String label = "network";
      Network network = new Network(vertices, links, tails, heads, weights, first, adjList, label);

      // origin = 0, destination = 0
      {
        try {
          assertTrue(network.isConnected(0, 0));
        } catch (InvalidArguments e) {
          assertEquals("", e.toString());
        }
      }

      // origin = 0, destination = 1
      {
        try {
          assertTrue(network.isConnected(0, 1));
        } catch (InvalidArguments e) {
          assertEquals("", e.toString());
        }
      }

      // origin = 0, destination = 2
      {
        try {
          assertTrue(network.isConnected(0, 2));
        } catch (InvalidArguments e) {
          assertEquals("", e.toString());
        }
      }

      // origin = 0, destination = 3
      {
        try {
          assertTrue(network.isConnected(0, 3));
        } catch (InvalidArguments e) {
          assertEquals("", e.toString());
        }
      }

      // origin = 0, destination = 4
      {
        try {
          assertTrue(network.isConnected(0, 4));
        } catch (InvalidArguments e) {
          assertEquals("", e.toString());
        }
      }

      // origin = 0, destination = 5
      {
        try {
          assertTrue(network.isConnected(0, 5));
        } catch (InvalidArguments e) {
          assertEquals("", e.toString());
        }
      }

      // origin = 0, destination = 6
      {
        try {
          assertTrue(network.isConnected(0, 6));
        } catch (InvalidArguments e) {
          assertEquals("", e.toString());
        }
      }

      // origin = 0, destination = 7
      {
        try {
          assertTrue(network.isConnected(0, 7));
        } catch (InvalidArguments e) {
          assertEquals("", e.toString());
        }
      }

      // origin = 1, destination = 1
      {
        try {
          assertTrue(network.isConnected(1, 1));
        } catch (InvalidArguments e) {
          assertEquals("", e.toString());
        }
      }

      // origin = 1, destination = 2
      {
        try {
          assertTrue(network.isConnected(1, 2));
        } catch (InvalidArguments e) {
          assertEquals("", e.toString());
        }
      }

      // origin = 1, destination = 3
      {
        try {
          assertTrue(network.isConnected(1, 3));
        } catch (InvalidArguments e) {
          assertEquals("", e.toString());
        }
      }

      // origin = 1, destination = 4
      {
        try {
          assertTrue(network.isConnected(1, 4));
        } catch (InvalidArguments e) {
          assertEquals("", e.toString());
        }
      }

      // origin = 1, destination = 5
      {
        try {
          assertTrue(network.isConnected(1, 5));
        } catch (InvalidArguments e) {
          assertEquals("", e.toString());
        }
      }

      // origin = 1, destination = 6
      {
        try {
          assertTrue(network.isConnected(1, 6));
        } catch (InvalidArguments e) {
          assertEquals("", e.toString());
        }
      }

      // origin = 1, destination = 7
      {
        try {
          assertTrue(network.isConnected(1, 7));
        } catch (InvalidArguments e) {
          assertEquals("", e.toString());
        }
      }

      // exception
      {
        try {
          assertTrue(network.isConnected(-1, 1));
        } catch (InvalidArguments e) {
          assertEquals(
              "utils.exceptions.InvalidArguments: Invalid Argument : invalid origin vertex index (vertex index = -1)",
              e.toString());
        }

        try {
          assertTrue(network.isConnected(8, 1));
        } catch (InvalidArguments e) {
          assertEquals(
              "utils.exceptions.InvalidArguments: Invalid Argument : invalid origin vertex index (vertex index = 8)",
              e.toString());
        }

        try {
          assertTrue(network.isConnected(5, -1));
        } catch (InvalidArguments e) {
          assertEquals(
              "utils.exceptions.InvalidArguments: Invalid Argument : invalid destination vertex index (vertex index = -1)",
              e.toString());
        }

        try {
          assertTrue(network.isConnected(5, 8));
        } catch (InvalidArguments e) {
          assertEquals(
              "utils.exceptions.InvalidArguments: Invalid Argument : invalid destination vertex index (vertex index = 8)",
              e.toString());
        }
      }
    }

    //   0 --(0:1)-- 1     3 --(3:1)-- 4
    //   |           |     |           |
    // (1:1)         |   (4:1)       (5:1)
    //   |           |     |           |
    //   2 --(2:1)---+     5 --(6:1)-- 6
    {
      Vertex[] vertices =
          new Vertex[] {
              new Vertex("0"),
              new Vertex("1"),
              new Vertex("2"),
              new Vertex("3"),
              new Vertex("4"),
              new Vertex("5"),
              new Vertex("6"),
          };
      Link[] links =
          new Link[] {
              new Link("0"),
              new Link("1"),
              new Link("2"),
              new Link("3"),
              new Link("4"),
              new Link("5"),
              new Link("6"),
              new Link("7"),
              new Link("8"),
              new Link("9"),
              new Link("10"),
              new Link("11"),
              new Link("12"),
              new Link("13"),
          };
      int[] tails = new int[] {0, 1, 0, 2, 1, 2, 3, 4, 3, 5, 4, 6, 5, 6};
      int[] heads = new int[] {1, 0, 2, 0, 2, 1, 4, 3, 5, 3, 6, 4, 6, 5};
      double[] weights = new double[] {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
      int[] first = new int[] {0, 1, 3, 6, 7, 9, 11};
      int[] adjList = new int[] {2, 4, -1, 5, -1, -1, 8, 10, -1, 12, -1, 13, -1, -1};
      String label = "network";
      Network network = new Network(vertices, links, tails, heads, weights, first, adjList, label);

      // origin = 1, destination = 2
      {
        try {
          assertTrue(network.isConnected(1, 2));
        } catch (InvalidArguments e) {
          assertEquals("", e.toString());
        }
      }

      // origin = 1, destination = 3
      {
        try {
          assertFalse(network.isConnected(1, 3));
        } catch (InvalidArguments e) {
          assertEquals("", e.toString());
        }
      }

      // origin = 6, destination = 3
      {
        try {
          assertTrue(network.isConnected(6, 3));
        } catch (InvalidArguments e) {
          assertEquals("", e.toString());
        }
      }

      // origin = 6, destination = 0
      {
        try {
          assertFalse(network.isConnected(6, 0));
        } catch (InvalidArguments e) {
          assertEquals("", e.toString());
        }
      }
    }
  }
}
