package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import utils.RomanNumerals;

public class RomanNumeralsTest {

  @Test
  public void numberOver100() {
   try {
      RomanNumerals.get(101);
      fail();
   }
   catch (IllegalArgumentException e) {
     assertEquals("This class can only convert integers 1-100.", e.getMessage());
   }

    try {
      RomanNumerals.get(-10);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("This class can only convert integers 1-100.", e.getMessage());
    }
  }

  @Test
  public void numbers1to10() {
    assertEquals("i", RomanNumerals.get(1));
    assertEquals("ii", RomanNumerals.get(2));
    assertEquals("iii", RomanNumerals.get(3));
    assertEquals("iv", RomanNumerals.get(4));
    assertEquals("v", RomanNumerals.get(5));
    assertEquals("vi", RomanNumerals.get(6));
    assertEquals("vii", RomanNumerals.get(7));
    assertEquals("viii", RomanNumerals.get(8));
    assertEquals("ix", RomanNumerals.get(9));
    assertEquals("x", RomanNumerals.get(10));
  }

  @Test
  public void numbers11to20() {
    assertEquals("xi", RomanNumerals.get(11));
    assertEquals("xii", RomanNumerals.get(12));
    assertEquals("xiii", RomanNumerals.get(13));
    assertEquals("xiv", RomanNumerals.get(14));
    assertEquals("xv", RomanNumerals.get(15));
    assertEquals("xvi", RomanNumerals.get(16));
    assertEquals("xvii", RomanNumerals.get(17));
    assertEquals("xviii", RomanNumerals.get(18));
    assertEquals("xix", RomanNumerals.get(19));
    assertEquals("xx", RomanNumerals.get(20));
  }

  @Test
  public void numbers21to30() {
    assertEquals("xxi", RomanNumerals.get(21));
    assertEquals("xxii", RomanNumerals.get(22));
    assertEquals("xxiii", RomanNumerals.get(23));
    assertEquals("xxiv", RomanNumerals.get(24));
    assertEquals("xxv", RomanNumerals.get(25));
    assertEquals("xxvi", RomanNumerals.get(26));
    assertEquals("xxvii", RomanNumerals.get(27));
    assertEquals("xxviii", RomanNumerals.get(28));
    assertEquals("xxix", RomanNumerals.get(29));
    assertEquals("xxx", RomanNumerals.get(30));
  }

  // Additional test cases can be added to cover a wider range of numbers.
}
