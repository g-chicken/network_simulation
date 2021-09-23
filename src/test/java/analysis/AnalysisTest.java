package analysis;

import data.dto.LinkDto;
import data.dto.NetworkDto;
import data.dto.VertexDto;
import org.junit.jupiter.api.Test;
import utils.mathematics.Coordination;

import static java.lang.Double.NaN;
import static org.junit.jupiter.api.Assertions.*;

class AnalysisTest {
  @Test
  void calcDegreeCentrality() {
    //   0 --(0:2)-- 1 --(1:4)-- 2 --(2:5)-- 3
    //   |           |           |
    // (3:1)       (4:1)       (5:1)
    //   |           |           |
    //   4 --(6:1)-- 5 --(7:2)-- 6 --(8:3)-- 7
    VertexDto[] vertexDtoes =
        new VertexDto[] {
            new VertexDto(new Coordination(1.0, 0.1), "0"),
            new VertexDto(new Coordination(2.0, 0.2), "1"),
            new VertexDto(new Coordination(3.0, 0.3), "2"),
            new VertexDto(new Coordination(4.0, 0.4), "3"),
            new VertexDto(new Coordination(5.0, 0.5), "4"),
            new VertexDto(new Coordination(6.0, 0.6), "5"),
            new VertexDto(new Coordination(7.0, 0.7), "6"),
            new VertexDto(new Coordination(8.0, 0.8), "7")
        };
    LinkDto[] linkDtoes =
        new LinkDto[] {
            new LinkDto(2.0, -1, 1, 0, "0"),
            new LinkDto(2.0, -1, 0, 1, "1"),
            new LinkDto(4.0, -1, 2, 1, "2"),
            new LinkDto(4.0, -1, 1, 2, "3"),
            new LinkDto(5.0, -1, 3, 2, "4"),
            new LinkDto(5.0, -1, 2, 3, "5"),
            new LinkDto(1.0, -1, 4, 0, "6"),
            new LinkDto(1.0, -1, 0, 4, "7"),
            new LinkDto(1.0, -1, 5, 1, "8"),
            new LinkDto(1.0, -1, 1, 5, "9"),
            new LinkDto(1.0, -1, 6, 2, "10"),
            new LinkDto(1.0, -1, 2, 6, "11"),
            new LinkDto(1.0, -1, 5, 4, "12"),
            new LinkDto(1.0, -1, 4, 5, "13"),
            new LinkDto(2.0, -1, 6, 5, "14"),
            new LinkDto(2.0, -1, 5, 6, "15"),
            new LinkDto(3.0, -1, 7, 6, "16"),
            new LinkDto(3.0, -1, 6, 7, "17")
        };
    String label = "network";
    NetworkDto networkDto = new NetworkDto(vertexDtoes, linkDtoes, label);
    Analysis analysis = new Analysis(networkDto);
    analysis.calcDegreeCentrality();

    double[] want =
        new double[] {
            2.0 / 7.0, 3.0 / 7.0, 3.0 / 7.0, 1.0 / 7.0, 2.0 / 7.0, 3.0 / 7.0, 3.0 / 7.0, 1.0 / 7.0
        };
    double[] got = analysis.getDegreeCentrality();

    assertEquals(want.length, got.length);
    for (int i = 0; i < want.length; i++) {
      assertEquals(want[i], got[i], "index = " + i);
    }
  }

  @Test
  void calcVertexClosenessCentrality() {
    // 4-complete-graph (K_4)
    {
      VertexDto[] vertexDtoes =
          new VertexDto[] {
              new VertexDto(new Coordination(0.0, 0.0), "0"),
              new VertexDto(new Coordination(1.0, 0.0), "1"),
              new VertexDto(new Coordination(0.0, 1.0), "2"),
              new VertexDto(new Coordination(1.0, 1.0), "3"),
          };
      LinkDto[] linkDtoes =
          new LinkDto[] {
              new LinkDto(1.0, -1.0, 0, 1, "0"),
              new LinkDto(1.0, -1.0, 1, 0, "1"),
              new LinkDto(1.0, -1.0, 0, 2, "2"),
              new LinkDto(1.0, -1.0, 2, 0, "3"),
              new LinkDto(1.0, -1.0, 0, 3, "4"),
              new LinkDto(1.0, -1.0, 3, 0, "5"),
              new LinkDto(1.0, -1.0, 1, 2, "6"),
              new LinkDto(1.0, -1.0, 2, 1, "7"),
              new LinkDto(1.0, -1.0, 1, 3, "8"),
              new LinkDto(1.0, -1.0, 3, 1, "9"),
              new LinkDto(1.0, -1.0, 2, 3, "10"),
              new LinkDto(1.0, -1.0, 3, 2, "11"),
          };
      String label = "K_4";
      NetworkDto networkDto = new NetworkDto(vertexDtoes, linkDtoes, label);
      Analysis analysis = new Analysis(networkDto);
      analysis.calcVertexClosenessCentrality();

      double[] want = new double[] {0.0, 0.0, 0.0, 0.0};
      double[] got = analysis.getVertexClosenessCentrality();

      assertEquals(want.length, got.length);

      for (int i = 0; i < want.length; i++) {
        assertEquals(want[i], got[i]);
      }
    }

    // 5-complete-graph (K_5)
    {
      VertexDto[] vertexDtoes =
          new VertexDto[] {
              new VertexDto(new Coordination(0.5, 0.0), "0"),
              new VertexDto(new Coordination(1.5, 0.0), "1"),
              new VertexDto(new Coordination(0.0, 0.5), "2"),
              new VertexDto(new Coordination(2.0, 0.5), "3"),
              new VertexDto(new Coordination(1.0, 1.0), "4"),
          };
      LinkDto[] linkDtoes =
          new LinkDto[] {
              new LinkDto(1.0, -1.0, 0, 1, "0"),
              new LinkDto(1.0, -1.0, 1, 0, "1"),
              new LinkDto(1.0, -1.0, 0, 2, "2"),
              new LinkDto(1.0, -1.0, 2, 0, "3"),
              new LinkDto(1.0, -1.0, 0, 3, "4"),
              new LinkDto(1.0, -1.0, 3, 0, "5"),
              new LinkDto(1.0, -1.0, 0, 4, "6"),
              new LinkDto(1.0, -1.0, 4, 0, "7"),
              new LinkDto(1.0, -1.0, 1, 2, "8"),
              new LinkDto(1.0, -1.0, 2, 1, "9"),
              new LinkDto(1.0, -1.0, 1, 3, "10"),
              new LinkDto(1.0, -1.0, 3, 1, "11"),
              new LinkDto(1.0, -1.0, 1, 4, "12"),
              new LinkDto(1.0, -1.0, 4, 1, "13"),
              new LinkDto(1.0, -1.0, 2, 3, "14"),
              new LinkDto(1.0, -1.0, 3, 2, "15"),
              new LinkDto(1.0, -1.0, 2, 4, "16"),
              new LinkDto(1.0, -1.0, 4, 2, "17"),
              new LinkDto(1.0, -1.0, 3, 4, "18"),
              new LinkDto(1.0, -1.0, 4, 3, "19"),
          };
      String label = "K_5";
      NetworkDto networkDto = new NetworkDto(vertexDtoes, linkDtoes, label);
      Analysis analysis = new Analysis(networkDto);
      analysis.calcVertexClosenessCentrality();

      double[] want = new double[] {0.0, 0.0, 0.0, 0.0, 0.0};
      double[] got = analysis.getVertexClosenessCentrality();

      assertEquals(want.length, got.length);

      for (int i = 0; i < want.length; i++) {
        assertEquals(want[i], got[i]);
      }
    }

    // independent set (no link)
    {
      VertexDto[] vertexDtoes =
          new VertexDto[] {
              new VertexDto(new Coordination(0.0, 0.0), "0"),
              new VertexDto(new Coordination(1.0, 0.0), "1"),
              new VertexDto(new Coordination(0.0, 1.0), "2"),
              new VertexDto(new Coordination(1.0, 1.0), "3"),
          };
      LinkDto[] linkDtoes = new LinkDto[] {};
      String label = "independent_set";
      NetworkDto networkDto = new NetworkDto(vertexDtoes, linkDtoes, label);
      Analysis analysis = new Analysis(networkDto);
      analysis.calcVertexClosenessCentrality();

      double[] want = new double[] {NaN, NaN, NaN, NaN};
      double[] got = analysis.getVertexClosenessCentrality();

      assertEquals(want.length, got.length);

      for (int i = 0; i < want.length; i++) {
        assertEquals(want[i], got[i]);
      }
    }

    // path (3 vertexes)
    {
      VertexDto[] vertexDtoes =
          new VertexDto[] {
              new VertexDto(new Coordination(0.0, 0.0), "0"),
              new VertexDto(new Coordination(1.0, 0.0), "1"),
              new VertexDto(new Coordination(2.0, 0.0), "2"),
          };
      LinkDto[] linkDtoes =
          new LinkDto[] {
              new LinkDto(1.0, -1.0, 0, 1, "0"),
              new LinkDto(1.0, -1.0, 1, 0, "1"),
              new LinkDto(1.0, -1.0, 1, 2, "2"),
              new LinkDto(1.0, -1.0, 2, 1, "3"),
          };
      String label = "path";
      NetworkDto networkDto = new NetworkDto(vertexDtoes, linkDtoes, label);
      Analysis analysis = new Analysis(networkDto);
      analysis.calcVertexClosenessCentrality();

      double[] want = new double[] {0.0, 1.0, 0.0};
      double[] got = analysis.getVertexClosenessCentrality();

      assertEquals(want.length, got.length);

      for (int i = 0; i < want.length; i++) {
        assertEquals(want[i], got[i], "index = " + i);
      }
    }

    // path (4 vertexes)
    {
      VertexDto[] vertexDtoes =
          new VertexDto[] {
              new VertexDto(new Coordination(0.0, 0.0), "0"),
              new VertexDto(new Coordination(1.0, 0.0), "1"),
              new VertexDto(new Coordination(2.0, 0.0), "2"),
              new VertexDto(new Coordination(3.0, 0.0), "3"),
          };
      LinkDto[] linkDtoes =
          new LinkDto[] {
              new LinkDto(1.0, -1.0, 0, 1, "0"),
              new LinkDto(1.0, -1.0, 1, 0, "1"),
              new LinkDto(1.0, -1.0, 1, 2, "2"),
              new LinkDto(1.0, -1.0, 2, 1, "3"),
              new LinkDto(1.0, -1.0, 2, 3, "4"),
              new LinkDto(1.0, -1.0, 3, 2, "5"),
          };
      String label = "path";
      NetworkDto networkDto = new NetworkDto(vertexDtoes, linkDtoes, label);
      Analysis analysis = new Analysis(networkDto);
      analysis.calcVertexClosenessCentrality();

      double[] want = new double[] {0.0, 4.0 / 6.0, 4.0 / 6.0, 0.0};
      double[] got = analysis.getVertexClosenessCentrality();

      assertEquals(want.length, got.length);

      for (int i = 0; i < want.length; i++) {
        assertEquals(want[i], got[i], "index = " + i);
      }
    }

    // 0 --(0:1.0)-- 1 --(1:1.0)--  2  -----(2:1.0)-----  3
    //               |            |   |                 / |
    //               |            | (4:1.0)            /  |
    //            (3:1.0)         |   |               /   |
    //               |            |   |  +--(5:1.0)--+ (6:1.0)
    //               |            |   | /                 |
    //               4 --(7:1.0)--+   5 ----(8:1.0)-----  6
    {
      VertexDto[] vertexDtoes =
          new VertexDto[] {
              new VertexDto(new Coordination(0.0, 1.0), "0"),
              new VertexDto(new Coordination(1.0, 1.0), "1"),
              new VertexDto(new Coordination(2.0, 1.0), "2"),
              new VertexDto(new Coordination(3.0, 1.0), "3"),
              new VertexDto(new Coordination(1.0, 0.0), "4"),
              new VertexDto(new Coordination(2.0, 0.0), "5"),
              new VertexDto(new Coordination(3.0, 0.0), "6"),
          };
      LinkDto[] linkDtoes =
          new LinkDto[] {
              new LinkDto(1.0, -1.0, 1, 0, "0"),
              new LinkDto(1.0, -1.0, 0, 1, "1"),
              new LinkDto(1.0, -1.0, 2, 1, "2"),
              new LinkDto(1.0, -1.0, 1, 2, "3"),
              new LinkDto(1.0, -1.0, 3, 2, "4"),
              new LinkDto(1.0, -1.0, 2, 3, "5"),
              new LinkDto(1.0, -1.0, 4, 1, "6"),
              new LinkDto(1.0, -1.0, 1, 4, "7"),
              new LinkDto(1.0, -1.0, 5, 2, "8"),
              new LinkDto(1.0, -1.0, 2, 5, "9"),
              new LinkDto(1.0, -1.0, 5, 3, "10"),
              new LinkDto(1.0, -1.0, 3, 5, "11"),
              new LinkDto(1.0, -1.0, 6, 3, "12"),
              new LinkDto(1.0, -1.0, 3, 6, "13"),
              new LinkDto(1.0, -1.0, 4, 2, "14"),
              new LinkDto(1.0, -1.0, 2, 4, "15"),
              new LinkDto(1.0, -1.0, 6, 5, "16"),
              new LinkDto(1.0, -1.0, 5, 6, "17"),
          };
      String label = "network";
      NetworkDto networkDto = new NetworkDto(vertexDtoes, linkDtoes, label);
      Analysis analysis = new Analysis(networkDto);
      analysis.calcVertexClosenessCentrality();

      double[] want =
          new double[] {0.0, 10.0 / 30.0, 18.0 / 30.0, 4.0 / 30.0, 0.0, 4.0 / 30.0, 0.0};
      double[] got = analysis.getVertexClosenessCentrality();

      assertEquals(want.length, got.length);

      for (int i = 0; i < want.length; i++) {
        assertEquals(want[i], got[i], "index = " + i);
      }
    }

    //   0 --(0:2)-> 1 --(1:4)-> 2 <-(2:5)-- 3
    //   |           |           ^
    //   |           |           |
    // (3:1)       (4:1)       (5:1)
    //   |           |           |
    //   V           V           |
    //   4 <-(6:1)-- 5 --(7:2)-> 6 --(8:3)-> 7
    {
      VertexDto[] vertexDtoes =
          new VertexDto[] {
              new VertexDto(new Coordination(1.0, 0.1), "0"),
              new VertexDto(new Coordination(2.0, 0.2), "1"),
              new VertexDto(new Coordination(3.0, 0.3), "2"),
              new VertexDto(new Coordination(4.0, 0.4), "3"),
              new VertexDto(new Coordination(5.0, 0.5), "4"),
              new VertexDto(new Coordination(6.0, 0.6), "5"),
              new VertexDto(new Coordination(7.0, 0.7), "6"),
              new VertexDto(new Coordination(8.0, 0.8), "7")
          };
      LinkDto[] linkDtoes =
          new LinkDto[] {
              new LinkDto(2.0, -1, 0, 1, "0"),
              new LinkDto(4.0, -1, 1, 2, "1"),
              new LinkDto(5.0, -1, 3, 2, "2"),
              new LinkDto(1.0, -1, 0, 4, "3"),
              new LinkDto(1.0, -1, 1, 5, "4"),
              new LinkDto(1.0, -1, 6, 2, "5"),
              new LinkDto(1.0, -1, 5, 4, "6"),
              new LinkDto(2.0, -1, 5, 6, "7"),
              new LinkDto(3.0, -1, 6, 7, "8")
          };
      String label = "network";
      NetworkDto networkDto = new NetworkDto(vertexDtoes, linkDtoes, label);
      Analysis analysis = new Analysis(networkDto);
      analysis.calcVertexClosenessCentrality();

      double[] want = new double[] {0.0, 4.0 / 12.0, 0.0, 0.0, 0.0, 6.0 / 12.0, 5.0 / 13.0, 0.0};
      double[] got = analysis.getVertexClosenessCentrality();

      assertEquals(want.length, got.length);

      for (int i = 0; i < want.length; i++) {
        assertEquals(want[i], got[i], "index = " + i);
      }
    }
  }

  @Test
  void calcLinkClosenessCentrality() {
    // 4-complete-graph (K_4)
    {
      VertexDto[] vertexDtoes =
          new VertexDto[] {
              new VertexDto(new Coordination(0.0, 0.0), "0"),
              new VertexDto(new Coordination(1.0, 0.0), "1"),
              new VertexDto(new Coordination(0.0, 1.0), "2"),
              new VertexDto(new Coordination(1.0, 1.0), "3"),
          };
      LinkDto[] linkDtoes =
          new LinkDto[] {
              new LinkDto(1.0, -1.0, 0, 1, "0"),
              new LinkDto(1.0, -1.0, 1, 0, "1"),
              new LinkDto(1.0, -1.0, 0, 2, "2"),
              new LinkDto(1.0, -1.0, 2, 0, "3"),
              new LinkDto(1.0, -1.0, 0, 3, "4"),
              new LinkDto(1.0, -1.0, 3, 0, "5"),
              new LinkDto(1.0, -1.0, 1, 2, "6"),
              new LinkDto(1.0, -1.0, 2, 1, "7"),
              new LinkDto(1.0, -1.0, 1, 3, "8"),
              new LinkDto(1.0, -1.0, 3, 1, "9"),
              new LinkDto(1.0, -1.0, 2, 3, "10"),
              new LinkDto(1.0, -1.0, 3, 2, "11"),
          };
      String label = "K_4";
      NetworkDto networkDto = new NetworkDto(vertexDtoes, linkDtoes, label);
      Analysis analysis = new Analysis(networkDto);
      analysis.calcLinkClosenessCentrality();

      double[] want =
          new double[] {
              1.0 / 12.0,
              1.0 / 12.0,
              1.0 / 12.0,
              1.0 / 12.0,
              1.0 / 12.0,
              1.0 / 12.0,
              1.0 / 12.0,
              1.0 / 12.0,
              1.0 / 12.0,
              1.0 / 12.0,
              1.0 / 12.0,
              1.0 / 12.0
          };
      double[] got = analysis.getLinkClosenessCentrality();

      assertEquals(want.length, got.length);

      for (int i = 0; i < want.length; i++) {
        assertEquals(want[i], got[i], "index = " + i);
      }
    }

    // 5-complete-graph (K_5)
    {
      VertexDto[] vertexDtoes =
          new VertexDto[] {
              new VertexDto(new Coordination(0.5, 0.0), "0"),
              new VertexDto(new Coordination(1.5, 0.0), "1"),
              new VertexDto(new Coordination(0.0, 0.5), "2"),
              new VertexDto(new Coordination(2.0, 0.5), "3"),
              new VertexDto(new Coordination(1.0, 1.0), "4"),
          };
      LinkDto[] linkDtoes =
          new LinkDto[] {
              new LinkDto(1.0, -1.0, 0, 1, "0"),
              new LinkDto(1.0, -1.0, 1, 0, "1"),
              new LinkDto(1.0, -1.0, 0, 2, "2"),
              new LinkDto(1.0, -1.0, 2, 0, "3"),
              new LinkDto(1.0, -1.0, 0, 3, "4"),
              new LinkDto(1.0, -1.0, 3, 0, "5"),
              new LinkDto(1.0, -1.0, 0, 4, "6"),
              new LinkDto(1.0, -1.0, 4, 0, "7"),
              new LinkDto(1.0, -1.0, 1, 2, "8"),
              new LinkDto(1.0, -1.0, 2, 1, "9"),
              new LinkDto(1.0, -1.0, 1, 3, "10"),
              new LinkDto(1.0, -1.0, 3, 1, "11"),
              new LinkDto(1.0, -1.0, 1, 4, "12"),
              new LinkDto(1.0, -1.0, 4, 1, "13"),
              new LinkDto(1.0, -1.0, 2, 3, "14"),
              new LinkDto(1.0, -1.0, 3, 2, "15"),
              new LinkDto(1.0, -1.0, 2, 4, "16"),
              new LinkDto(1.0, -1.0, 4, 2, "17"),
              new LinkDto(1.0, -1.0, 3, 4, "18"),
              new LinkDto(1.0, -1.0, 4, 3, "19"),
          };
      String label = "K_5";
      NetworkDto networkDto = new NetworkDto(vertexDtoes, linkDtoes, label);
      Analysis analysis = new Analysis(networkDto);
      analysis.calcLinkClosenessCentrality();

      double[] want =
          new double[] {
              1.0 / 20.0,
              1.0 / 20.0,
              1.0 / 20.0,
              1.0 / 20.0,
              1.0 / 20.0,
              1.0 / 20.0,
              1.0 / 20.0,
              1.0 / 20.0,
              1.0 / 20.0,
              1.0 / 20.0,
              1.0 / 20.0,
              1.0 / 20.0,
              1.0 / 20.0,
              1.0 / 20.0,
              1.0 / 20.0,
              1.0 / 20.0,
              1.0 / 20.0,
              1.0 / 20.0,
              1.0 / 20.0,
              1.0 / 20.0
          };
      double[] got = analysis.getLinkClosenessCentrality();

      assertEquals(want.length, got.length);

      for (int i = 0; i < want.length; i++) {
        assertEquals(want[i], got[i]);
      }
    }

    // independent set (no link)
    {
      VertexDto[] vertexDtoes =
          new VertexDto[] {
              new VertexDto(new Coordination(0.0, 0.0), "0"),
              new VertexDto(new Coordination(1.0, 0.0), "1"),
              new VertexDto(new Coordination(0.0, 1.0), "2"),
              new VertexDto(new Coordination(1.0, 1.0), "3"),
          };
      LinkDto[] linkDtoes = new LinkDto[] {};
      String label = "independent_set";
      NetworkDto networkDto = new NetworkDto(vertexDtoes, linkDtoes, label);
      Analysis analysis = new Analysis(networkDto);
      analysis.calcVertexClosenessCentrality();

      double[] want = new double[] {};
      double[] got = analysis.getLinkClosenessCentrality();

      assertEquals(want.length, got.length);
    }

    // path (3 vertexes)
    {
      VertexDto[] vertexDtoes =
          new VertexDto[] {
              new VertexDto(new Coordination(0.0, 0.0), "0"),
              new VertexDto(new Coordination(1.0, 0.0), "1"),
              new VertexDto(new Coordination(2.0, 0.0), "2"),
          };
      LinkDto[] linkDtoes =
          new LinkDto[] {
              new LinkDto(1.0, -1.0, 0, 1, "0"),
              new LinkDto(1.0, -1.0, 1, 0, "1"),
              new LinkDto(1.0, -1.0, 1, 2, "2"),
              new LinkDto(1.0, -1.0, 2, 1, "3"),
          };
      String label = "path";
      NetworkDto networkDto = new NetworkDto(vertexDtoes, linkDtoes, label);
      Analysis analysis = new Analysis(networkDto);
      analysis.calcLinkClosenessCentrality();

      double[] want = new double[] {2.0 / 6.0, 2.0 / 6.0, 2.0 / 6.0, 2.0 / 6.0};
      double[] got = analysis.getLinkClosenessCentrality();

      assertEquals(want.length, got.length);

      for (int i = 0; i < want.length; i++) {
        assertEquals(want[i], got[i], "index = " + i);
      }
    }

    // path (4 vertexes)
    {
      VertexDto[] vertexDtoes =
          new VertexDto[] {
              new VertexDto(new Coordination(0.0, 0.0), "0"),
              new VertexDto(new Coordination(1.0, 0.0), "1"),
              new VertexDto(new Coordination(2.0, 0.0), "2"),
              new VertexDto(new Coordination(3.0, 0.0), "3"),
          };
      LinkDto[] linkDtoes =
          new LinkDto[] {
              new LinkDto(1.0, -1.0, 0, 1, "0"),
              new LinkDto(1.0, -1.0, 1, 0, "1"),
              new LinkDto(1.0, -1.0, 1, 2, "2"),
              new LinkDto(1.0, -1.0, 2, 1, "3"),
              new LinkDto(1.0, -1.0, 2, 3, "4"),
              new LinkDto(1.0, -1.0, 3, 2, "5"),
          };
      String label = "path";
      NetworkDto networkDto = new NetworkDto(vertexDtoes, linkDtoes, label);
      Analysis analysis = new Analysis(networkDto);
      analysis.calcLinkClosenessCentrality();

      double[] want =
          new double[] {3.0 / 12.0, 3.0 / 12.0, 4.0 / 12.0, 4.0 / 12.0, 3.0 / 12.0, 3.0 / 12.0};
      double[] got = analysis.getLinkClosenessCentrality();

      assertEquals(want.length, got.length);

      for (int i = 0; i < want.length; i++) {
        assertEquals(want[i], got[i], "index = " + i);
      }
    }

    // 0 --(0:1.0)-- 1 --(1:1.0)--  2  -----(2:1.0)-----  3
    //               |            |   |                 / |
    //               |            | (4:1.0)            /  |
    //            (3:1.0)         |   |               /   |
    //               |            |   |  +--(5:1.0)--+ (6:1.0)
    //               |            |   | /                 |
    //               4 --(7:1.0)--+   5 ----(8:1.0)-----  6
    {
      VertexDto[] vertexDtoes =
          new VertexDto[] {
              new VertexDto(new Coordination(0.0, 1.0), "0"),
              new VertexDto(new Coordination(1.0, 1.0), "1"),
              new VertexDto(new Coordination(2.0, 1.0), "2"),
              new VertexDto(new Coordination(3.0, 1.0), "3"),
              new VertexDto(new Coordination(1.0, 0.0), "4"),
              new VertexDto(new Coordination(2.0, 0.0), "5"),
              new VertexDto(new Coordination(3.0, 0.0), "6"),
          };
      LinkDto[] linkDtoes =
          new LinkDto[] {
              new LinkDto(1.0, -1.0, 1, 0, "0"),
              new LinkDto(1.0, -1.0, 0, 1, "1"),
              new LinkDto(1.0, -1.0, 2, 1, "2"),
              new LinkDto(1.0, -1.0, 1, 2, "3"),
              new LinkDto(1.0, -1.0, 3, 2, "4"),
              new LinkDto(1.0, -1.0, 2, 3, "5"),
              new LinkDto(1.0, -1.0, 4, 1, "6"),
              new LinkDto(1.0, -1.0, 1, 4, "7"),
              new LinkDto(1.0, -1.0, 5, 2, "8"),
              new LinkDto(1.0, -1.0, 2, 5, "9"),
              new LinkDto(1.0, -1.0, 5, 3, "10"),
              new LinkDto(1.0, -1.0, 3, 5, "11"),
              new LinkDto(1.0, -1.0, 6, 3, "12"),
              new LinkDto(1.0, -1.0, 3, 6, "13"),
              new LinkDto(1.0, -1.0, 4, 2, "14"),
              new LinkDto(1.0, -1.0, 2, 4, "15"),
              new LinkDto(1.0, -1.0, 6, 5, "16"),
              new LinkDto(1.0, -1.0, 5, 6, "17"),
          };
      String label = "network";
      NetworkDto networkDto = new NetworkDto(vertexDtoes, linkDtoes, label);
      Analysis analysis = new Analysis(networkDto);
      analysis.calcLinkClosenessCentrality();

      double[] want =
          new double[] {
              6.0 / 42.0,
              6.0 / 42.0,
              8.0 / 42.0,
              8.0 / 42.0,
              6.0 / 42.0,
              6.0 / 42.0,
              2.0 / 42.0,
              2.0 / 42.0,
              6.0 / 42.0,
              6.0 / 42.0,
              1.0 / 42.0,
              1.0 / 42.0,
              3.0 / 42.0,
              3.0 / 42.0,
              4.0 / 42.0,
              4.0 / 42.0,
              3.0 / 42.0,
              3.0 / 42.0
          };
      double[] got = analysis.getLinkClosenessCentrality();

      assertEquals(want.length, got.length);

      for (int i = 0; i < want.length; i++) {
        assertEquals(want[i], got[i], "index = " + i);
      }
    }

    //   0 --(0:2)-> 1 --(1:4)-> 2 <-(2:5)-- 3
    //   |           |           ^
    //   |           |           |
    // (3:1)       (4:1)       (5:1)
    //   |           |           |
    //   V           V           |
    //   4 <-(6:1)-- 5 --(7:2)-> 6 --(8:3)-> 7
    {
      VertexDto[] vertexDtoes =
          new VertexDto[] {
              new VertexDto(new Coordination(1.0, 0.1), "0"),
              new VertexDto(new Coordination(2.0, 0.2), "1"),
              new VertexDto(new Coordination(3.0, 0.3), "2"),
              new VertexDto(new Coordination(4.0, 0.4), "3"),
              new VertexDto(new Coordination(5.0, 0.5), "4"),
              new VertexDto(new Coordination(6.0, 0.6), "5"),
              new VertexDto(new Coordination(7.0, 0.7), "6"),
              new VertexDto(new Coordination(8.0, 0.8), "7")
          };
      LinkDto[] linkDtoes =
          new LinkDto[] {
              new LinkDto(2.0, -1, 0, 1, "0"),
              new LinkDto(4.0, -1, 1, 2, "1"),
              new LinkDto(5.0, -1, 3, 2, "2"),
              new LinkDto(1.0, -1, 0, 4, "3"),
              new LinkDto(1.0, -1, 1, 5, "4"),
              new LinkDto(1.0, -1, 6, 2, "5"),
              new LinkDto(1.0, -1, 5, 4, "6"),
              new LinkDto(2.0, -1, 5, 6, "7"),
              new LinkDto(3.0, -1, 6, 7, "8")
          };
      String label = "network";
      NetworkDto networkDto = new NetworkDto(vertexDtoes, linkDtoes, label);
      Analysis analysis = new Analysis(networkDto);
      analysis.calcLinkClosenessCentrality();

      double[] want =
          new double[] {
              5.0 / 18.0,
              1.0 / 18.0,
              1.0 / 18.0,
              1.0 / 18.0,
              8.0 / 18.0,
              3.0 / 18.0,
              2.0 / 18.0,
              8.0 / 18.0,
              4.0 / 18.0
          };
      double[] got = analysis.getLinkClosenessCentrality();

      assertEquals(want.length, got.length);

      for (int i = 0; i < want.length; i++) {
        assertEquals(want[i], got[i], "index = " + i);
      }
    }
  }
}
