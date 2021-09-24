package data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import utils.exceptions.InvalidArguments;

class CreateWattsStrogatzNetworkTest {
  @Test
  void constructor() {
    {
      try {
        CreateWattsStrogatzNetwork got = new CreateWattsStrogatzNetwork(16, 6, 0.2);
        assertEquals(16, got.getVertexNum());
        assertEquals(6, got.getDegree());
        assertEquals(0.2, got.getBeta());
      } catch (InvalidArguments e) {
        assertEquals("", e.toString());
      }
    }

    {
      try {
        CreateWattsStrogatzNetwork got = new CreateWattsStrogatzNetwork(-1, 6, 0.2);
        assertEquals(-1, got.getVertexNum());
      } catch (InvalidArguments e) {
        assertEquals("utils.exceptions.InvalidArguments: Invalid Argument : size of #vertexes less than 0", e.toString());
      }
    }

    {
      try {
        CreateWattsStrogatzNetwork got = new CreateWattsStrogatzNetwork(16, 16, 0.2);
        assertEquals(-1, got.getVertexNum());
      } catch (InvalidArguments e) {
        assertEquals("utils.exceptions.InvalidArguments: Invalid Argument : degree is more than #vertexes", e.toString());
      }
    }

    {
      try {
        CreateWattsStrogatzNetwork got = new CreateWattsStrogatzNetwork(16, 4, 0.2);
        assertEquals(-1, got.getVertexNum());
      } catch (InvalidArguments e) {
        assertEquals("utils.exceptions.InvalidArguments: Invalid Argument : degree is less than log_2(#vertexes)", e.toString());
      }
    }

    {
      try {
        CreateWattsStrogatzNetwork got = new CreateWattsStrogatzNetwork(16, 7, 0.2);
        assertEquals(-1, got.getVertexNum());
      } catch (InvalidArguments e) {
        assertEquals("utils.exceptions.InvalidArguments: Invalid Argument : degree is odd", e.toString());
      }
    }

    {
      try {
        CreateWattsStrogatzNetwork got = new CreateWattsStrogatzNetwork(16, 6, -0.1);
        assertEquals(-1, got.getVertexNum());
      } catch (InvalidArguments e) {
        assertEquals("utils.exceptions.InvalidArguments: Invalid Argument : beta is less than 0", e.toString());
      }
    }

    {
      try {
        CreateWattsStrogatzNetwork got = new CreateWattsStrogatzNetwork(16, 6, 1.01);
        assertEquals(-1, got.getVertexNum());
      } catch (InvalidArguments e) {
        assertEquals("utils.exceptions.InvalidArguments: Invalid Argument : beta is more than 1", e.toString());
      }
    }
  }
}