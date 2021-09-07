package analysis.model;

import data.dto.VertexDto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VertexTest {
  @Test
  void constructor() {
    class TestCase {
      private final VertexDto dto;
      private final Vertex want;

      private TestCase(VertexDto dto, Vertex want) {
        this.dto = dto;
        this.want = want;
      }
    }

    TestCase[] testCases =
        new TestCase[] {
          new TestCase(new VertexDto(1.3, 4.9, "test"), new Vertex(1.3, 4.9, "test")),
          new TestCase(new VertexDto(0.0, 0.0, ""), new Vertex(0.0, 0.0, "")),
        };

    for (TestCase tc : testCases) {
      assertEquals(tc.want.toString(), new Vertex(tc.dto).toString());
    }
  }
}
