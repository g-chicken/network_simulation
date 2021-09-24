package data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import utils.exceptions.InvalidArguments;

class CreateRandomNetworkTest {
  @Test
  void constructor() {
    try {
      CreateRandomNetwork got = new CreateRandomNetwork(10, 20, true);
      assertEquals(10, got.getVertexNum());
      assertEquals(20, got.getLinkNum());
      assertTrue(got.getUndirected());
    } catch (InvalidArguments e) {
      assertEquals("", e.toString());
    }

    try {
      CreateRandomNetwork got = new CreateRandomNetwork(10, 21, false);
      assertEquals(10, got.getVertexNum());
      assertEquals(21, got.getLinkNum());
      assertFalse(got.getUndirected());
    } catch (InvalidArguments e) {
      assertEquals("", e.toString());
    }

    try {
      CreateRandomNetwork got = new CreateRandomNetwork(0, 0, false);
      assertEquals(0, got.getVertexNum());
      assertEquals(0, got.getLinkNum());
      assertFalse(got.getUndirected());
    } catch (InvalidArguments e) {
      assertEquals("", e.toString());
    }

    try {
      new CreateRandomNetwork(-1, 21, false);
    } catch (InvalidArguments e) {
      assertEquals("utils.exceptions.InvalidArguments: Invalid Argument : size of #vertexes less than 0", e.toString());
    }

    try {
      new CreateRandomNetwork(10, -1, false);
    } catch (InvalidArguments e) {
      assertEquals("utils.exceptions.InvalidArguments: Invalid Argument : size of #links less than 0", e.toString());
    }

    try {
      new CreateRandomNetwork(10, 21, true);
    } catch (InvalidArguments e) {
      assertEquals("utils.exceptions.InvalidArguments: Invalid Argument : in case of undirected, #links must be even number", e.toString());
    }
  }
}