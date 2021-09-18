package utils.network;

import analysis.model.Link;
import analysis.model.Network;
import analysis.model.Vertex;
import org.junit.jupiter.api.Test;
import utils.exceptions.DoNotExecution;
import utils.exceptions.InvalidArguments;

import java.util.ArrayList;

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
          new Vertex(1.0, 0.1, "0"),
          new Vertex(2.0, 0.2, "1"),
          new Vertex(3.0, 0.3, "2"),
          new Vertex(4.0, 0.4, "3"),
          new Vertex(5.0, 0.5, "4"),
          new Vertex(6.0, 0.6, "5"),
          new Vertex(7.0, 0.7, "6"),
          new Vertex(8.0, 0.8, "7"),
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

    {
      Dijkstra dijkstra =
          new Dijkstra(vertices.length, links.length, tails, heads, weights, first, adjList);

      assertEquals(8, dijkstra.getVertexNum());
      assertEquals(18, dijkstra.getLinkNum());
      assertEquals(18, dijkstra.getTails().length);
      assertEquals(18, dijkstra.getHeads().length);
      assertEquals(18, dijkstra.getWeights().length);
      assertEquals(8, dijkstra.getFirst().length);
      assertEquals(18, dijkstra.getAdjList().length);

      for (int i = 0; i < 8; i++) {
        assertEquals(first[i], dijkstra.getFirst()[i]);
      }

      for (int i = 0; i < 18; i++) {
        assertEquals(tails[i], dijkstra.getTails()[i]);
        assertEquals(heads[i], dijkstra.getHeads()[i]);
        assertEquals(weights[i], dijkstra.getWeights()[i]);
        assertEquals(adjList[i], dijkstra.getAdjList()[i]);
      }
    }

    {
      Network network = new Network(vertices, links, tails, heads, weights, first, adjList, label);
      Dijkstra dijkstra = new Dijkstra(network);

      assertEquals(8, dijkstra.getVertexNum());
      assertEquals(18, dijkstra.getLinkNum());
      assertEquals(18, dijkstra.getTails().length);
      assertEquals(18, dijkstra.getHeads().length);
      assertEquals(18, dijkstra.getWeights().length);
      assertEquals(8, dijkstra.getFirst().length);
      assertEquals(18, dijkstra.getAdjList().length);

      for (int i = 0; i < 8; i++) {
        assertEquals(first[i], dijkstra.getFirst()[i]);
      }

      for (int i = 0; i < 18; i++) {
        assertEquals(tails[i], dijkstra.getTails()[i]);
        assertEquals(heads[i], dijkstra.getHeads()[i]);
        assertEquals(weights[i], dijkstra.getWeights()[i]);
        assertEquals(adjList[i], dijkstra.getAdjList()[i]);
      }
    }
  }

  @Test
  void dijkstra() {
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

      Dijkstra dijkstra = new Dijkstra(8, 18, tails, heads, weights, first, adjList);

      // start = 0
      {
        ArrayList<Integer> p0 = new ArrayList<>();
        ArrayList<Integer> p1 =
            new ArrayList<>() {
              {
                add(0);
              }
            };
        ArrayList<Integer> p2 =
            new ArrayList<>() {
              {
                add(11);
              }
            };
        ArrayList<Integer> p3 =
            new ArrayList<>() {
              {
                add(4);
              }
            };
        ArrayList<Integer> p4 =
            new ArrayList<>() {
              {
                add(6);
              }
            };
        ArrayList<Integer> p5 =
            new ArrayList<>() {
              {
                add(12);
              }
            };
        ArrayList<Integer> p6 =
            new ArrayList<>() {
              {
                add(14);
              }
            };
        ArrayList<Integer> p7 =
            new ArrayList<>() {
              {
                add(16);
              }
            };
        ArrayList<ArrayList<Integer>> paths =
            new ArrayList<>() {
              {
                add(p0);
                add(p1);
                add(p2);
                add(p3);
                add(p4);
                add(p5);
                add(p6);
                add(p7);
              }
            };

        try {
          dijkstra.dijkstra(0);
        } catch (InvalidArguments | DoNotExecution e) {
          assertEquals("", e.toString());
        }

        ArrayList<ArrayList<Integer>> got = dijkstra.getPaths();

        assertEquals(paths.size(), got.size());
        for (int i = 0; i < 8; i++) {
          assertEquals(paths.get(i).size(), got.get(i).size(), "vertex index = " + i);

          for (int i1 = 0; i1 < paths.get(i).size(); i1++) {
            assertEquals(paths.get(i).get(i1), got.get(i).get(i1));
          }
        }
      }

      // start = 1
      {
        ArrayList<Integer> p0 =
            new ArrayList<>() {
              {
                add(1);
              }
            };
        ArrayList<Integer> p1 = new ArrayList<>();
        ArrayList<Integer> p2 =
            new ArrayList<>() {
              {
                add(2);
                add(11);
              }
            };
        ArrayList<Integer> p3 =
            new ArrayList<>() {
              {
                add(4);
              }
            };
        ArrayList<Integer> p4 =
            new ArrayList<>() {
              {
                add(13);
              }
            };
        ArrayList<Integer> p5 =
            new ArrayList<>() {
              {
                add(8);
              }
            };
        ArrayList<Integer> p6 =
            new ArrayList<>() {
              {
                add(14);
              }
            };
        ArrayList<Integer> p7 =
            new ArrayList<>() {
              {
                add(16);
              }
            };
        ArrayList<ArrayList<Integer>> paths =
            new ArrayList<>() {
              {
                add(p0);
                add(p1);
                add(p2);
                add(p3);
                add(p4);
                add(p5);
                add(p6);
                add(p7);
              }
            };

        try {
          dijkstra.dijkstra(1);
        } catch (InvalidArguments | DoNotExecution e) {
          assertEquals("", e.toString());
        }

        ArrayList<ArrayList<Integer>> got = dijkstra.getPaths();

        assertEquals(paths.size(), got.size());
        for (int i = 0; i < 8; i++) {
          assertEquals(paths.get(i).size(), got.get(i).size(), "vertex index = " + i);

          for (int i1 = 0; i1 < paths.get(i).size(); i1++) {
            assertEquals(paths.get(i).get(i1), got.get(i).get(i1));
          }
        }
      }

      // start = 2
      {
        ArrayList<Integer> p0 =
            new ArrayList<>() {
              {
                add(7);
              }
            };
        ArrayList<Integer> p1 =
            new ArrayList<>() {
              {
                add(3);
                add(9);
              }
            };
        ArrayList<Integer> p2 = new ArrayList<>();
        ArrayList<Integer> p3 =
            new ArrayList<>() {
              {
                add(4);
              }
            };
        ArrayList<Integer> p4 =
            new ArrayList<>() {
              {
                add(13);
              }
            };
        ArrayList<Integer> p5 =
            new ArrayList<>() {
              {
                add(15);
              }
            };
        ArrayList<Integer> p6 =
            new ArrayList<>() {
              {
                add(10);
              }
            };
        ArrayList<Integer> p7 =
            new ArrayList<>() {
              {
                add(16);
              }
            };
        ArrayList<ArrayList<Integer>> paths =
            new ArrayList<>() {
              {
                add(p0);
                add(p1);
                add(p2);
                add(p3);
                add(p4);
                add(p5);
                add(p6);
                add(p7);
              }
            };

        try {
          dijkstra.dijkstra(2);
        } catch (InvalidArguments | DoNotExecution e) {
          assertEquals("", e.toString());
        }

        ArrayList<ArrayList<Integer>> got = dijkstra.getPaths();

        assertEquals(paths.size(), got.size());
        for (int i = 0; i < 8; i++) {
          assertEquals(paths.get(i).size(), got.get(i).size(), "vertex index = " + i);

          for (int i1 = 0; i1 < paths.get(i).size(); i1++) {
            assertEquals(paths.get(i).get(i1), got.get(i).get(i1));
          }
        }
      }

      // start = 3
      {
        ArrayList<Integer> p0 =
            new ArrayList<>() {
              {
                add(7);
              }
            };
        ArrayList<Integer> p1 =
            new ArrayList<>() {
              {
                add(3);
                add(9);
              }
            };
        ArrayList<Integer> p2 =
            new ArrayList<>() {
              {
                add(5);
              }
            };
        ArrayList<Integer> p3 = new ArrayList<>();
        ArrayList<Integer> p4 =
            new ArrayList<>() {
              {
                add(13);
              }
            };
        ArrayList<Integer> p5 =
            new ArrayList<>() {
              {
                add(15);
              }
            };
        ArrayList<Integer> p6 =
            new ArrayList<>() {
              {
                add(10);
              }
            };
        ArrayList<Integer> p7 =
            new ArrayList<>() {
              {
                add(16);
              }
            };
        ArrayList<ArrayList<Integer>> paths =
            new ArrayList<>() {
              {
                add(p0);
                add(p1);
                add(p2);
                add(p3);
                add(p4);
                add(p5);
                add(p6);
                add(p7);
              }
            };

        try {
          dijkstra.dijkstra(3);
        } catch (InvalidArguments | DoNotExecution e) {
          assertEquals("", e.toString());
        }

        ArrayList<ArrayList<Integer>> got = dijkstra.getPaths();

        assertEquals(paths.size(), got.size());
        for (int i = 0; i < 8; i++) {
          assertEquals(paths.get(i).size(), got.get(i).size(), "vertex index = " + i);

          for (int i1 = 0; i1 < paths.get(i).size(); i1++) {
            assertEquals(paths.get(i).get(i1), got.get(i).get(i1));
          }
        }
      }

      // start = 4
      {
        ArrayList<Integer> p0 =
            new ArrayList<>() {
              {
                add(7);
              }
            };
        ArrayList<Integer> p1 =
            new ArrayList<>() {
              {
                add(9);
              }
            };
        ArrayList<Integer> p2 =
            new ArrayList<>() {
              {
                add(11);
              }
            };
        ArrayList<Integer> p3 =
            new ArrayList<>() {
              {
                add(4);
              }
            };
        ArrayList<Integer> p4 = new ArrayList<>();
        ArrayList<Integer> p5 =
            new ArrayList<>() {
              {
                add(12);
              }
            };
        ArrayList<Integer> p6 =
            new ArrayList<>() {
              {
                add(14);
              }
            };
        ArrayList<Integer> p7 =
            new ArrayList<>() {
              {
                add(16);
              }
            };
        ArrayList<ArrayList<Integer>> paths =
            new ArrayList<>() {
              {
                add(p0);
                add(p1);
                add(p2);
                add(p3);
                add(p4);
                add(p5);
                add(p6);
                add(p7);
              }
            };

        try {
          dijkstra.dijkstra(4);
        } catch (InvalidArguments | DoNotExecution e) {
          assertEquals("", e.toString());
        }

        ArrayList<ArrayList<Integer>> got = dijkstra.getPaths();

        assertEquals(paths.size(), got.size());
        for (int i = 0; i < 8; i++) {
          assertEquals(paths.get(i).size(), got.get(i).size(), "vertex index = " + i);

          for (int i1 = 0; i1 < paths.get(i).size(); i1++) {
            assertEquals(paths.get(i).get(i1), got.get(i).get(i1));
          }
        }
      }

      // start = 5
      {
        ArrayList<Integer> p0 =
            new ArrayList<>() {
              {
                add(7);
              }
            };
        ArrayList<Integer> p1 =
            new ArrayList<>() {
              {
                add(9);
              }
            };
        ArrayList<Integer> p2 =
            new ArrayList<>() {
              {
                add(11);
              }
            };
        ArrayList<Integer> p3 =
            new ArrayList<>() {
              {
                add(4);
              }
            };
        ArrayList<Integer> p4 =
            new ArrayList<>() {
              {
                add(13);
              }
            };
        ArrayList<Integer> p5 = new ArrayList<>();
        ArrayList<Integer> p6 =
            new ArrayList<>() {
              {
                add(14);
              }
            };
        ArrayList<Integer> p7 =
            new ArrayList<>() {
              {
                add(16);
              }
            };
        ArrayList<ArrayList<Integer>> paths =
            new ArrayList<>() {
              {
                add(p0);
                add(p1);
                add(p2);
                add(p3);
                add(p4);
                add(p5);
                add(p6);
                add(p7);
              }
            };

        try {
          dijkstra.dijkstra(5);
        } catch (InvalidArguments | DoNotExecution e) {
          assertEquals("", e.toString());
        }

        ArrayList<ArrayList<Integer>> got = dijkstra.getPaths();

        assertEquals(paths.size(), got.size());
        for (int i = 0; i < 8; i++) {
          assertEquals(paths.get(i).size(), got.get(i).size(), "vertex index = " + i);

          for (int i1 = 0; i1 < paths.get(i).size(); i1++) {
            assertEquals(paths.get(i).get(i1), got.get(i).get(i1));
          }
        }
      }

      // start = 6
      {
        ArrayList<Integer> p0 =
            new ArrayList<>() {
              {
                add(7);
              }
            };
        ArrayList<Integer> p1 =
            new ArrayList<>() {
              {
                add(9);
              }
            };
        ArrayList<Integer> p2 =
            new ArrayList<>() {
              {
                add(11);
              }
            };
        ArrayList<Integer> p3 =
            new ArrayList<>() {
              {
                add(4);
              }
            };
        ArrayList<Integer> p4 =
            new ArrayList<>() {
              {
                add(13);
              }
            };
        ArrayList<Integer> p5 =
            new ArrayList<>() {
              {
                add(15);
              }
            };
        ArrayList<Integer> p6 = new ArrayList<>();
        ArrayList<Integer> p7 =
            new ArrayList<>() {
              {
                add(16);
              }
            };
        ArrayList<ArrayList<Integer>> paths =
            new ArrayList<>() {
              {
                add(p0);
                add(p1);
                add(p2);
                add(p3);
                add(p4);
                add(p5);
                add(p6);
                add(p7);
              }
            };

        try {
          dijkstra.dijkstra(6);
        } catch (InvalidArguments | DoNotExecution e) {
          assertEquals("", e.toString());
        }

        ArrayList<ArrayList<Integer>> got = dijkstra.getPaths();

        assertEquals(paths.size(), got.size());
        for (int i = 0; i < 8; i++) {
          assertEquals(paths.get(i).size(), got.get(i).size(), "vertex index = " + i);

          for (int i1 = 0; i1 < paths.get(i).size(); i1++) {
            assertEquals(paths.get(i).get(i1), got.get(i).get(i1));
          }
        }
      }

      // start = 7
      {
        ArrayList<Integer> p0 =
            new ArrayList<>() {
              {
                add(7);
              }
            };
        ArrayList<Integer> p1 =
            new ArrayList<>() {
              {
                add(9);
              }
            };
        ArrayList<Integer> p2 =
            new ArrayList<>() {
              {
                add(11);
              }
            };
        ArrayList<Integer> p3 =
            new ArrayList<>() {
              {
                add(4);
              }
            };
        ArrayList<Integer> p4 =
            new ArrayList<>() {
              {
                add(13);
              }
            };
        ArrayList<Integer> p5 =
            new ArrayList<>() {
              {
                add(15);
              }
            };
        ArrayList<Integer> p6 =
            new ArrayList<>() {
              {
                add(17);
              }
            };
        ArrayList<Integer> p7 = new ArrayList<>();
        ArrayList<ArrayList<Integer>> paths =
            new ArrayList<>() {
              {
                add(p0);
                add(p1);
                add(p2);
                add(p3);
                add(p4);
                add(p5);
                add(p6);
                add(p7);
              }
            };

        try {
          dijkstra.dijkstra(7);
        } catch (InvalidArguments | DoNotExecution e) {
          assertEquals("", e.toString());
        }

        ArrayList<ArrayList<Integer>> got = dijkstra.getPaths();

        assertEquals(paths.size(), got.size());
        for (int i = 0; i < 8; i++) {
          assertEquals(paths.get(i).size(), got.get(i).size(), "vertex index = " + i);

          for (int i1 = 0; i1 < paths.get(i).size(); i1++) {
            assertEquals(paths.get(i).get(i1), got.get(i).get(i1));
          }
        }
      }
    }

    {
      //   0 --(0:1)-- 1    3 --(3:1)-- 4
      //   |           |    |           |
      // (1:1)         |  (4:1)       (5:1)
      //   |           |    |           |
      //   2 --(2:1)---+    5 --(6:1)-- 6
      int[] tails = new int[] {0, 1, 0, 2, 1, 2, 3, 4, 3, 5, 4, 6, 5, 6};
      int[] heads = new int[] {1, 0, 2, 0, 2, 1, 4, 3, 5, 3, 6, 4, 6, 5};
      double[] weights = new double[] {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
      int[] first = new int[] {0, 1, 3, 6, 7, 9, 11};
      int[] adjList = new int[] {2, 4, -1, 5, -1, -1, 8, 10, -1, 12, -1, 13, -1, -1};

      Dijkstra dijkstra = new Dijkstra(7, 14, tails, heads, weights, first, adjList);

      // start = 0
      {
        ArrayList<Integer> p0 = new ArrayList<>();
        ArrayList<Integer> p1 =
            new ArrayList<>() {
              {
                add(0);
              }
            };
        ArrayList<Integer> p2 =
            new ArrayList<>() {
              {
                add(2);
              }
            };
        ArrayList<Integer> p3 = new ArrayList<>();
        ArrayList<Integer> p4 = new ArrayList<>();
        ArrayList<Integer> p5 = new ArrayList<>();
        ArrayList<Integer> p6 = new ArrayList<>();
        ArrayList<ArrayList<Integer>> paths =
            new ArrayList<>() {
              {
                add(p0);
                add(p1);
                add(p2);
                add(p3);
                add(p4);
                add(p5);
                add(p6);
              }
            };

        try {
          dijkstra.dijkstra(0);
        } catch (InvalidArguments | DoNotExecution e) {
          assertEquals("", e.toString());
        }

        ArrayList<ArrayList<Integer>> got = dijkstra.getPaths();

        assertEquals(paths.size(), got.size());
        for (int i = 0; i < 7; i++) {
          assertEquals(paths.get(i).size(), got.get(i).size(), "vertex index = " + i);

          for (int i1 = 0; i1 < paths.get(i).size(); i1++) {
            assertEquals(paths.get(i).get(i1), got.get(i).get(i1));
          }
        }
      }

      // start = 4
      {
        ArrayList<Integer> p0 = new ArrayList<>();
        ArrayList<Integer> p1 = new ArrayList<>();
        ArrayList<Integer> p2 = new ArrayList<>();
        ArrayList<Integer> p3 =
            new ArrayList<>() {
              {
                add(7);
              }
            };
        ArrayList<Integer> p4 = new ArrayList<>();
        ArrayList<Integer> p5 =
            new ArrayList<>() {
              {
                add(8);
                add(13);
              }
            };
        ArrayList<Integer> p6 =
            new ArrayList<>() {
              {
                add(10);
              }
            };
        ArrayList<ArrayList<Integer>> paths =
            new ArrayList<>() {
              {
                add(p0);
                add(p1);
                add(p2);
                add(p3);
                add(p4);
                add(p5);
                add(p6);
              }
            };

        try {
          dijkstra.dijkstra(4);
        } catch (InvalidArguments | DoNotExecution e) {
          assertEquals("", e.toString());
        }

        ArrayList<ArrayList<Integer>> got = dijkstra.getPaths();

        assertEquals(paths.size(), got.size());
        for (int i = 0; i < 7; i++) {
          assertEquals(paths.get(i).size(), got.get(i).size(), "vertex index = " + i);

          for (int i1 = 0; i1 < paths.get(i).size(); i1++) {
            assertEquals(paths.get(i).get(i1), got.get(i).get(i1));
          }
        }
      }
    }

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

      Dijkstra dijkstra = new Dijkstra(8, 18, tails, heads, weights, first, adjList);

      try {
        dijkstra.dijkstra(100);
      } catch (InvalidArguments e) {
        assertEquals(
            "utils.exceptions.InvalidArguments: Invalid Argument : invalid start vertex index (vertex index = 100)",
            e.toString());
      } catch (DoNotExecution e) {
        assertEquals("", e.toString());
      }

      try {
        dijkstra.dijkstra(-1);
      } catch (InvalidArguments e) {
        assertEquals(
            "utils.exceptions.InvalidArguments: Invalid Argument : invalid start vertex index (vertex index = -1)",
            e.toString());
      } catch (DoNotExecution e) {
        assertEquals("", e.toString());
      }
    }
  }
}
