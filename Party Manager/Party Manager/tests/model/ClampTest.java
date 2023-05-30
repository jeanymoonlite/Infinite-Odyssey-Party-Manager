package model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * A testing class for the functional {@code Clamp} class.
 */
public class ClampTest {


  @Test
  public void lowerBound() {
    assertEquals(0, Clamp.run(-1, 0, 100));
    assertEquals(50, Clamp.run(20, 50, 100));
  }

  @Test
  public void upperBound() {
    assertEquals(100.0, Clamp.run(120.0, 0.0, 100.0), 0.0);
    assertEquals(1000000, Clamp.run(1000001, 1000, 1000000));
  }

  @Test
  public void value() {
    assertEquals(50, Clamp.run(50, 0, 100));
    assertEquals(80, Clamp.run(80, 50, 100));
    assertEquals(64, Clamp.run(64, 0, 100));
    assertEquals(777777, Clamp.run(777777, 1000, 1000000));
  }


}