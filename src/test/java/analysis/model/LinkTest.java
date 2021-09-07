package analysis.model;

import data.dto.LinkDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkTest {
  @Test
  void constructor() {
    class TestCase {
      private final LinkDto dto;
      private final Link want;

      private TestCase(LinkDto dto, Link want) {
        this.dto = dto;
        this.want = want;
      }
    }

    TestCase[] testCases =
        new TestCase[] {
          new TestCase(new LinkDto(1.3, 4.9, 1, 5, "test"), new Link(1.3, 4.9, 1, 5, "test")),
          new TestCase(new LinkDto(0.0, -1.0, 0, 0, ""), new Link(0.0, -1.0, 0, 0, "")),
        };

    for (TestCase tc : testCases) {
      assertEquals(tc.want.toString(), new Link(tc.dto).toString());
    }
  }
}
