package analysis.model;

import data.dto.LinkDto;
import data.dto.NetworkDto;
import data.dto.VertexDto;
import org.junit.jupiter.api.Test;
import utils.exceptions.InvalidArguments;

import static org.junit.jupiter.api.Assertions.*;

class NetworkTest {
  @Test
  void constructor() {
    class TestCase {
      private final NetworkDto dto;
      private final Network want;

      private TestCase(NetworkDto dto, Network want) {
        this.dto = dto;
        this.want = want;
      }
    }

    TestCase[] testCases =
        new TestCase[] {
          new TestCase(
              new NetworkDto(
                  new VertexDto[] {new VertexDto(1.0, 2.0, "1"), new VertexDto(3.0, 4.0, "2")},
                  new LinkDto[] {new LinkDto(0.0, 1.0, 0, 1, "1")},
                  "test"),
              new Network(
                  new Vertex[] {new Vertex(1.0, 2.0, "1"), new Vertex(3.0, 4.0, "2")},
                  new Link[] {new Link(0.0, 1.0, 0, 1, "1")},
                  "test"))
        };

    for (TestCase tc : testCases) {
      assertEquals(tc.want.toString(), new Network(tc.dto).toString());
    }
  }

  @Test
  void setAdjacencyList() {
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
    Network network = new Network(vertices, links, "test");
    Network want =
        new Network(
            vertices,
            links,
            new int[] {0, 1, 3, 5, 7, 9, 11, 17},
            new int[] {6, 2, 8, 4, 10, -1, -1, 12, -1, 13, -1, 15, -1, 14, -1, 16, -1, -1},
            "test");

    network.setAdjacencyList();

    assertEquals(want.getFirst().length, network.getFirst().length);
    for (int i = 0; i < want.getFirst().length; i++) {
      assertEquals(want.getFirst()[i], network.getFirst()[i], "first (index = " + i + ")");
    }

    assertEquals(want.getAdjList().length, network.getAdjList().length);
    for (int i = 0; i < want.getAdjList().length; i++) {
      assertEquals(want.getAdjList()[i], network.getAdjList()[i], "adjList (index = " + i + ")");
    }
  }

  @Test
  void degree() {
    //   0 --(0:2)-- 1 --(1:4)-- 2 --(2:5)-- 3
    //   |           |           |
    // (3:1)       (4:1)       (5:1)
    //   |           |           |
    //   4 --(6:1)-- 5 --(7:2)-- 6 --(8:3)-- 7
    Network network =
        new Network(
            new Vertex[] {
              new Vertex(0.0, 0.0, "0"),
              new Vertex(0.0, 0.0, "1"),
              new Vertex(0.0, 0.0, "2"),
              new Vertex(0.0, 0.0, "3"),
              new Vertex(0.0, 0.0, "4"),
              new Vertex(0.0, 0.0, "5"),
              new Vertex(0.0, 0.0, "6"),
              new Vertex(0.0, 0.0, "7"),
            },
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
            },
            new int[] {0, 1, 3, 5, 7, 9, 11, 17},
            new int[] {6, 2, 8, 4, 10, -1, -1, 12, -1, 13, -1, 15, -1, 14, -1, 16, -1, -1},
            "test");
    int[] degrees = new int[] {2, 3, 3, 1, 2, 3, 3, 1};

    for (int i = 0; i < 8; i++) {
      try {
        assertEquals(degrees[i], network.getDegree(i));
      } catch (InvalidArguments e) {
        assertEquals("", e.toString());
      }
    }
  }
}
