package utils.mathematics;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import utils.exceptions.InvalidArguments;

class CoordinationTest {
  @Test
  void equals() {
    assertEquals(new Coordination(0.0, 0.0), new Coordination(0.0, 0.0));
    assertEquals(new Coordination(12.11901, -2.412), new Coordination(12.11901, -2.412));
    assertNotEquals(new Coordination(-0.992, 5.1), new Coordination(-0.991, 5.1));
    assertNotEquals(new Coordination(-0.992, 5.1), new Coordination(-0.992, 5.1001));
    assertNotEquals(new Coordination(-0.992, 5.1), new Coordination(-0.991, 5.1001));
    assertNotEquals(new Coordination(0.0, 0.0), new Coordination(0.0, 1.0e-10));
    assertNotEquals(new Coordination(0.0, 0.0), new Coordination(1.0e-10, 0.0));
    assertNotEquals(new Coordination(0.0, 0.0), 123);
    assertNotEquals(new Coordination(0.0, 0.0), -4.1);
    assertNotEquals(new Coordination(0.0, 0.0), "test");
  }

  @Test
  void distanceFromOrigin() {
    try {
      assertEquals(new Coordination(3, 4).distanceFromOrigin(), 5);
      assertEquals(new Coordination(12, -5).distanceFromOrigin(), 13);
      assertEquals(new Coordination(-1, -1).distanceFromOrigin(), Math.sqrt(2));
    } catch (InvalidArguments e) {
      assertEquals("", e.toString());
    }
  }

  @Test
  void distance() {
    try {
      assertEquals(new Coordination(3, 4).distance(new Coordination(0, 0)), 5);
      assertEquals(new Coordination(-2.3, -5.4).distance(new Coordination(1.7, -2.4)), 5);


      double d = new Coordination(-2.3, -5.4).distance(new Coordination(-1.3, -4.4));
      assertTrue(Math.abs(d - Math.sqrt(2)) < 1.0e-15);
      assertFalse(Math.abs(d - Math.sqrt(2)) < 1.0e-16);
    } catch (InvalidArguments e) {
      assertEquals("", e.toString());
    }

    try {
      assertEquals(new Coordination(3, 4).distance(null), 0.0);
    } catch (InvalidArguments e) {
      assertEquals(
          "utils.exceptions.InvalidArguments: Invalid Argument : argument coordination is null",
          e.toString());
    }
  }

  @Test
  void add() {
    Coordination c = new Coordination(1.2, -3.2);

    c.add(new Coordination(5.3, 1.3));
    assertEquals(c, new Coordination(6.5, -1.9));

    c.add(new Coordination(-11.2, 5.1));
    assertEquals(c, new Coordination(-4.7, 3.2));

    c.add(null);
    assertEquals(c, new Coordination(-4.7, 3.2));

    c.add(new Coordination(4.7, -3.2));
    assertEquals(c, new Coordination(0, 0));
  }

  @Test
  void sub() {
    Coordination c = new Coordination(1.2, -3.2);

    c.sub(new Coordination(5.3, 1.3));
    assertEquals(c, new Coordination(-4.1, -4.5));

    c.sub(new Coordination(-11.2, 5.1));
    assertEquals(c, new Coordination(7.1, -9.6));

    c.sub(null);
    assertEquals(c, new Coordination(7.1, -9.6));

    c.sub(new Coordination(7.1, -9.6));
    assertEquals(c, new Coordination(0, 0));
  }
}