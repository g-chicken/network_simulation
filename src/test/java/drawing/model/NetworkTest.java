package drawing.model;

import static org.junit.jupiter.api.Assertions.*;

import data.dto.LinkDto;
import data.dto.NetworkDto;
import data.dto.VertexDto;
import org.junit.jupiter.api.Test;
import utils.mathematics.Coordination;

class NetworkTest {
  @Test
  void constructorForNetworkDTO() {
    //   0 --(0:2)-- 1 --(1:4)-- 2 --(2:5)-- 3
    //   |           |           |
    // (3:1)       (4:1)       (5:1)
    //   |           |           |
    //   4 --(6:1)-- 5 --(7:2)-- 6 --(8:3)-- 7
    VertexDto[] vertexDtoes = new VertexDto[] {
        new VertexDto(new Coordination(1.0, 0.1), "0"),
        new VertexDto(new Coordination(2.0, 0.2), "1"),
        new VertexDto(new Coordination(3.0, 0.3), "2"),
        new VertexDto(new Coordination(4.0, 0.4), "3"),
        new VertexDto(new Coordination(5.0, 0.5), "4"),
        new VertexDto(new Coordination(6.0, 0.6), "5"),
        new VertexDto(new Coordination(7.0, 0.7), "6"),
        new VertexDto(new Coordination(8.0, 0.8), "7")
    };
    LinkDto[] linkDtoes = new LinkDto[] {
        new LinkDto(2.0, 0.0, 1, 0, "0"),
        new LinkDto(2.0, 0.0, 0, 1, "1"),
        new LinkDto(4.0, 0.0, 2, 1, "2"),
        new LinkDto(4.0, 0.0, 1, 2, "3"),
        new LinkDto(5.0, 0.0, 3, 2, "4"),
        new LinkDto(5.0, 0.0, 2, 3, "5"),
        new LinkDto(1.0, 0.0, 4, 0, "6"),
        new LinkDto(1.0, 0.0, 0, 4, "7"),
        new LinkDto(1.0, 0.0, 5, 1, "8"),
        new LinkDto(1.0, 0.0, 1, 5, "9"),
        new LinkDto(1.0, 0.0, 6, 2, "10"),
        new LinkDto(1.0, 0.0, 2, 6, "11"),
        new LinkDto(1.0, 0.0, 5, 4, "12"),
        new LinkDto(1.0, 0.0, 4, 5, "13"),
        new LinkDto(2.0, 0.0, 6, 5, "14"),
        new LinkDto(2.0, 0.0, 5, 6, "15"),
        new LinkDto(3.0, 0.0, 7, 6, "16"),
        new LinkDto(3.0, 0.0, 6, 7, "17"),
    };
    int[] tails = new int[] {0, 1, 1, 2, 2, 3, 0, 4, 1, 5, 2, 6, 4, 5, 5, 6, 6, 7};
    int[] heads = new int[] {1, 0, 2, 1, 3, 2, 4, 0, 5, 1, 6, 2, 5, 4, 6, 5, 7, 6};
    double[] weights = new double[] {2, 2, 4, 4, 5, 5, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 3, 3};
    int[] first = new int[] {0, 1, 3, 5, 7, 9, 11, 17};
    int[] adjList = new int[] {6, 2, 8, 4, 10, -1, -1, 12, -1, 13, -1, 15, -1, 14, -1, 16, -1, -1};
    String label = "network";
    NetworkDto dto = new NetworkDto(vertexDtoes, linkDtoes, label);
    Network got = new Network(dto);
    Vertex[] vertexes = new Vertex[] {
        new Vertex(new Coordination(1.0, 0.1), "0"),
        new Vertex(new Coordination(2.0, 0.2), "1"),
        new Vertex(new Coordination(3.0, 0.3), "2"),
        new Vertex(new Coordination(4.0, 0.4), "3"),
        new Vertex(new Coordination(5.0, 0.5), "4"),
        new Vertex(new Coordination(6.0, 0.6), "5"),
        new Vertex(new Coordination(7.0, 0.7), "6"),
        new Vertex(new Coordination(8.0, 0.8), "7")
    };
    Link[] links = new Link[] {
        new Link(0.0, "0"),
        new Link(0.0, "1"),
        new Link(0.0, "2"),
        new Link(0.0, "3"),
        new Link(0.0, "4"),
        new Link(0.0, "5"),
        new Link(0.0, "6"),
        new Link(0.0, "7"),
        new Link(0.0, "8"),
        new Link(0.0, "9"),
        new Link(0.0, "10"),
        new Link(0.0, "11"),
        new Link(0.0, "12"),
        new Link(0.0, "13"),
        new Link(0.0, "14"),
        new Link(0.0, "15"),
        new Link(0.0, "16"),
        new Link(0.0, "17"),
    };
    Network want = new Network(vertexes, links, tails, heads, weights, first, adjList, label);

    assertEquals(want.getVertexNum(), got.getVertexNum());
    assertEquals(want.getLinkNum(), got.getLinkNum());
    assertEquals(want.getVertexes().length, got.getVertexes().length);
    assertEquals(want.getLinks().length, got.getLinks().length);
    assertEquals(want.getLabel(), got.getLabel());

    for (int i = 0; i < want.getVertexNum(); i++) {
      assertEquals(want.getVertexes()[i].getCoordination(),
          got.getVertexes()[i].getCoordination());
      assertEquals(want.getVertexes()[i].getRate(), got.getVertexes()[i].getRate());
      assertEquals(want.getVertexes()[i].getLabel(), got.getVertexes()[i].getLabel());
    }

    for (int i = 0; i < want.getLinkNum(); i++) {
      assertEquals(want.getLinks()[i], got.getLinks()[i]);
    }
  }

  @Test
  void constructorForAnalysisNetwork() {
    //   0 --(0:2)-- 1 --(1:4)-- 2 --(2:5)-- 3
    //   |           |           |
    // (3:1)       (4:1)       (5:1)
    //   |           |           |
    //   4 --(6:1)-- 5 --(7:2)-- 6 --(8:3)-- 7
    analysis.model.Vertex[] analysisVertexes =
        new analysis.model.Vertex[] {
            new analysis.model.Vertex("0"),
            new analysis.model.Vertex("1"),
            new analysis.model.Vertex("2"),
            new analysis.model.Vertex("3"),
            new analysis.model.Vertex("4"),
            new analysis.model.Vertex("5"),
            new analysis.model.Vertex("6"),
            new analysis.model.Vertex("7"),
        };
    analysis.model.Link[] analysisLinks =
        new analysis.model.Link[] {
            new analysis.model.Link("0"),
            new analysis.model.Link("1"),
            new analysis.model.Link("2"),
            new analysis.model.Link("3"),
            new analysis.model.Link("4"),
            new analysis.model.Link("5"),
            new analysis.model.Link("6"),
            new analysis.model.Link("7"),
            new analysis.model.Link("8"),
            new analysis.model.Link("9"),
            new analysis.model.Link("10"),
            new analysis.model.Link("11"),
            new analysis.model.Link("12"),
            new analysis.model.Link("13"),
            new analysis.model.Link("14"),
            new analysis.model.Link("15"),
            new analysis.model.Link("16"),
            new analysis.model.Link("17"),
        };
    int[] tails = new int[] {0, 1, 1, 2, 2, 3, 0, 4, 1, 5, 2, 6, 4, 5, 5, 6, 6, 7};
    int[] heads = new int[] {1, 0, 2, 1, 3, 2, 4, 0, 5, 1, 6, 2, 5, 4, 6, 5, 7, 6};
    double[] weights = new double[] {2, 2, 4, 4, 5, 5, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 3, 3};
    int[] first = new int[] {0, 1, 3, 5, 7, 9, 11, 17};
    int[] adjList =
        new int[] {6, 2, 8, 4, 10, -1, -1, 12, -1, 13, -1, 15, -1, 14, -1, 16, -1, -1};
    String label = "network";
    analysis.model.Network network =
        new analysis.model.Network(analysisVertexes, analysisLinks, tails, heads, weights, first,
            adjList, label);

    {
      double[] vertexRate = new double[] {
          1.0, 0.8, 0.1, 0.2, 0.0, 0.5, 0.7, 0.3
      };
      double[] linkRate = new double[] {
          1.0, 0.8, 0.1, 0.2, 0.0, 0.5, 0.7, 0.3, 0.4,
          1.0, 0.8, 0.1, 0.2, 0.0, 0.5, 0.7, 0.3, 0.4
      };
      Network got = new Network(network, vertexRate, linkRate);

      Vertex[] vertexes = new Vertex[] {
          new Vertex(1.0, "0"),
          new Vertex(0.8, "1"),
          new Vertex(0.1, "2"),
          new Vertex(0.2, "3"),
          new Vertex(0.0, "4"),
          new Vertex(0.5, "5"),
          new Vertex(0.7, "6"),
          new Vertex(0.3, "7"),
      };
      Link[] links = new Link[] {
          new Link(1.0, "0"),
          new Link(0.8, "1"),
          new Link(0.1, "2"),
          new Link(0.2, "3"),
          new Link(0.0, "4"),
          new Link(0.5, "5"),
          new Link(0.7, "6"),
          new Link(0.3, "7"),
          new Link(0.4, "8"),
          new Link(1.0, "9"),
          new Link(0.8, "10"),
          new Link(0.1, "11"),
          new Link(0.2, "12"),
          new Link(0.0, "13"),
          new Link(0.5, "14"),
          new Link(0.7, "15"),
          new Link(0.3, "16"),
          new Link(0.4, "17"),
      };
      Network want = new Network(vertexes, links, tails, heads, weights, first, adjList, label);

      assertEquals(want.getVertexNum(), got.getVertexNum());
      assertEquals(want.getLinkNum(), got.getLinkNum());
      assertEquals(want.getVertexes().length, got.getVertexes().length);
      assertEquals(want.getLinks().length, got.getLinks().length);
      assertEquals(want.getLabel(), got.getLabel());

      for (int i = 0; i < want.getVertexNum(); i++) {
        assertEquals(want.getVertexes()[i].getCoordination(),
            got.getVertexes()[i].getCoordination());
        assertEquals(want.getVertexes()[i].getRate(), got.getVertexes()[i].getRate());
        assertEquals(want.getVertexes()[i].getLabel(), got.getVertexes()[i].getLabel());
      }

      for (int i = 0; i < want.getLinkNum(); i++) {
        assertEquals(want.getLinks()[i], got.getLinks()[i]);
      }
    }

    {
      double[] vertexRate = new double[] {};
      double[] linkRate = new double[] {};
      Network got = new Network(network, vertexRate, linkRate);

      Vertex[] vertexes = new Vertex[] {
          new Vertex(0.0, "0"),
          new Vertex(0.0, "1"),
          new Vertex(0.0, "2"),
          new Vertex(0.0, "3"),
          new Vertex(0.0, "4"),
          new Vertex(0.0, "5"),
          new Vertex(0.0, "6"),
          new Vertex(0.0, "7"),
      };
      Link[] links = new Link[] {
          new Link(0.0, "0"),
          new Link(0.0, "1"),
          new Link(0.0, "2"),
          new Link(0.0, "3"),
          new Link(0.0, "4"),
          new Link(0.0, "5"),
          new Link(0.0, "6"),
          new Link(0.0, "7"),
          new Link(0.0, "8"),
          new Link(0.0, "9"),
          new Link(0.0, "10"),
          new Link(0.0, "11"),
          new Link(0.0, "12"),
          new Link(0.0, "13"),
          new Link(0.0, "14"),
          new Link(0.0, "15"),
          new Link(0.0, "16"),
          new Link(0.0, "17"),
      };
      Network want = new Network(vertexes, links, tails, heads, weights, first, adjList, label);

      assertEquals(want.getVertexNum(), got.getVertexNum());
      assertEquals(want.getLinkNum(), got.getLinkNum());
      assertEquals(want.getVertexes().length, got.getVertexes().length);
      assertEquals(want.getLinks().length, got.getLinks().length);
      assertEquals(want.getLabel(), got.getLabel());

      for (int i = 0; i < want.getVertexNum(); i++) {
        assertEquals(want.getVertexes()[i].getCoordination(),
            got.getVertexes()[i].getCoordination());
        assertEquals(want.getVertexes()[i].getRate(), got.getVertexes()[i].getRate());
        assertEquals(want.getVertexes()[i].getLabel(), got.getVertexes()[i].getLabel());
      }

      for (int i = 0; i < want.getLinkNum(); i++) {
        assertEquals(want.getLinks()[i], got.getLinks()[i]);
      }
    }

    {
      double[] vertexRate = new double[] {1.0, 0.8, 0.1, 0.2, 0.0, 0.5};
      double[] linkRate = new double[] {1.0, 0.8, 0.1, 0.2, 0.0, 0.5, 0.7, 0.3, 0.4};
      Network got = new Network(network, vertexRate, linkRate);

      Vertex[] vertexes = new Vertex[] {
          new Vertex(1.0, "0"),
          new Vertex(0.8, "1"),
          new Vertex(0.1, "2"),
          new Vertex(0.2, "3"),
          new Vertex(0.0, "4"),
          new Vertex(0.5, "5"),
          new Vertex(0.0, "6"),
          new Vertex(0.0, "7"),
      };
      Link[] links = new Link[] {
          new Link(1.0, "0"),
          new Link(0.8, "1"),
          new Link(0.1, "2"),
          new Link(0.2, "3"),
          new Link(0.0, "4"),
          new Link(0.5, "5"),
          new Link(0.7, "6"),
          new Link(0.3, "7"),
          new Link(0.4, "8"),
          new Link(0.0, "9"),
          new Link(0.0, "10"),
          new Link(0.0, "11"),
          new Link(0.0, "12"),
          new Link(0.0, "13"),
          new Link(0.0, "14"),
          new Link(0.0, "15"),
          new Link(0.0, "16"),
          new Link(0.0, "17"),
      };
      Network want = new Network(vertexes, links, tails, heads, weights, first, adjList, label);

      assertEquals(want.getVertexNum(), got.getVertexNum());
      assertEquals(want.getLinkNum(), got.getLinkNum());
      assertEquals(want.getVertexes().length, got.getVertexes().length);
      assertEquals(want.getLinks().length, got.getLinks().length);
      assertEquals(want.getLabel(), got.getLabel());

      for (int i = 0; i < want.getVertexNum(); i++) {
        assertEquals(want.getVertexes()[i].getCoordination(),
            got.getVertexes()[i].getCoordination());
        assertEquals(want.getVertexes()[i].getRate(), got.getVertexes()[i].getRate());
        assertEquals(want.getVertexes()[i].getLabel(), got.getVertexes()[i].getLabel());
      }

      for (int i = 0; i < want.getLinkNum(); i++) {
        assertEquals(want.getLinks()[i], got.getLinks()[i]);
      }
    }

    {
      Network got = new Network(network, null, null);

      Vertex[] vertexes = new Vertex[] {
          new Vertex(0.0, "0"),
          new Vertex(0.0, "1"),
          new Vertex(0.0, "2"),
          new Vertex(0.0, "3"),
          new Vertex(0.0, "4"),
          new Vertex(0.0, "5"),
          new Vertex(0.0, "6"),
          new Vertex(0.0, "7"),
      };
      Link[] links = new Link[] {
          new Link(0.0, "0"),
          new Link(0.0, "1"),
          new Link(0.0, "2"),
          new Link(0.0, "3"),
          new Link(0.0, "4"),
          new Link(0.0, "5"),
          new Link(0.0, "6"),
          new Link(0.0, "7"),
          new Link(0.0, "8"),
          new Link(0.0, "9"),
          new Link(0.0, "10"),
          new Link(0.0, "11"),
          new Link(0.0, "12"),
          new Link(0.0, "13"),
          new Link(0.0, "14"),
          new Link(0.0, "15"),
          new Link(0.0, "16"),
          new Link(0.0, "17"),
      };
      Network want = new Network(vertexes, links, tails, heads, weights, first, adjList, label);

      assertEquals(want.getVertexNum(), got.getVertexNum());
      assertEquals(want.getLinkNum(), got.getLinkNum());
      assertEquals(want.getVertexes().length, got.getVertexes().length);
      assertEquals(want.getLinks().length, got.getLinks().length);
      assertEquals(want.getLabel(), got.getLabel());

      for (int i = 0; i < want.getVertexNum(); i++) {
        assertEquals(want.getVertexes()[i].getCoordination(),
            got.getVertexes()[i].getCoordination());
        assertEquals(want.getVertexes()[i].getRate(), got.getVertexes()[i].getRate());
        assertEquals(want.getVertexes()[i].getLabel(), got.getVertexes()[i].getLabel());
      }

      for (int i = 0; i < want.getLinkNum(); i++) {
        assertEquals(want.getLinks()[i], got.getLinks()[i]);
      }
    }
  }

  @Test
  void setVertexCoordination() {
    // #vertexes = 0
    {
      Vertex[] vertices = new Vertex[] {};
      Link[] links = new Link[] {};
      int[] tails = new int[] {};
      int[] heads = new int[] {};
      double[] weights = new double[] {};
      int[] first = new int[] {};
      int[] adjList = new int[] {};
      String label = "#vertexes is 0";
      Network network = new Network(vertices, links, tails, heads, weights, first, adjList, label);

      network.setVertexCoordination();
    }

    // #vertexes = 1
    {
      Vertex[] vertices = new Vertex[] {new Vertex(new Coordination(0.0, 0.0), 0.0, "0")};
      Link[] links = new Link[] {};
      int[] tails = new int[] {};
      int[] heads = new int[] {};
      double[] weights = new double[] {};
      int[] first = new int[] {};
      int[] adjList = new int[] {};
      String label = "#vertexes is 1";
      Network network = new Network(vertices, links, tails, heads, weights, first, adjList, label);

      network.setVertexCoordination();

      assertEquals(1, network.getVertexNum());
      assertEquals(1, network.getVertexes().length);
      assertTrue(network.getVertexes()[0].getCoordination().getCoordinateX() < 1.0);
      assertTrue(network.getVertexes()[0].getCoordination().getCoordinateX() >= 0.0);
      assertTrue(network.getVertexes()[0].getCoordination().getCoordinateY() < 1.0);
      assertTrue(network.getVertexes()[0].getCoordination().getCoordinateY() >= 0.0);
    }

    // #vertexes = 2
    {
      Vertex[] vertices = new Vertex[] {
          new Vertex(0.0, "0"),
          new Vertex(0.0, "1"),
      };
      Link[] links = new Link[] {
          new Link(0.0, "0"),
          new Link(0.0, "1"),
      };
      int[] tails = new int[] {0, 1};
      int[] heads = new int[] {1, 0};
      double[] weights = new double[] {1.0, 1.0};
      int[] first = new int[] {0, 1};
      int[] adjList = new int[] {-1, -1};
      String label = "#vertexes is 2";
      Network network = new Network(vertices, links, tails, heads, weights, first, adjList, label);

      network.setVertexCoordination();

      assertEquals(2, network.getVertexNum());
      assertEquals(2, network.getVertexes().length);

      for (int i = 0; i < 2; i++) {
        assertTrue(network.getVertexes()[i].getCoordination().getCoordinateX() < 1.0);
        assertTrue(network.getVertexes()[i].getCoordination().getCoordinateX() >= 0.0);
        assertTrue(network.getVertexes()[i].getCoordination().getCoordinateY() < 1.0);
        assertTrue(network.getVertexes()[i].getCoordination().getCoordinateY() >= 0.0);
      }
    }

    // K_3
    {
      Vertex[] vertices = new Vertex[] {
          new Vertex(0.0, "0"),
          new Vertex(0.0, "1"),
          new Vertex(0.0, "2"),
      };
      Link[] links = new Link[] {
          new Link(0.0, "0"),
          new Link(0.0, "1"),
          new Link(0.0, "2"),
          new Link(0.0, "3"),
          new Link(0.0, "4"),
          new Link(0.0, "5"),
      };
      int[] tails = new int[] {0, 1, 0, 2, 1, 2};
      int[] heads = new int[] {1, 0, 2, 0, 2, 1};
      double[] weights = new double[] {1.0, 1.0, 1.0, 1.0, 1.0, 1.0};
      int[] first = new int[] {0, 1, 3};
      int[] adjList = new int[] {2, 4, -1, 5, -1, -1};
      String label = "K_3";
      Network network = new Network(vertices, links, tails, heads, weights, first, adjList, label);

      network.setVertexCoordination();

      assertEquals(3, network.getVertexNum());
      assertEquals(3, network.getVertexes().length);

      for (int i = 0; i < 3; i++) {
        assertTrue(network.getVertexes()[i].getCoordination().getCoordinateX() < 1.0);
        assertTrue(network.getVertexes()[i].getCoordination().getCoordinateX() >= 0.0);
        assertTrue(network.getVertexes()[i].getCoordination().getCoordinateY() < 1.0);
        assertTrue(network.getVertexes()[i].getCoordination().getCoordinateY() >= 0.0);
      }
    }
  }
}