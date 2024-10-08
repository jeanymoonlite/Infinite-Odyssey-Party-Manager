package utils;


/**
 * A functional class that takes in three number (int or double) arguments: a lower-bound,
 * upper-bound, and a value. The function will return back a double or int (based on the types of
 * arguments pass). If value is greater than upper-bound, then the function will return upper-bound.
 * If value is lower than lower-bound, then the function will return lower-bound. If value is in
 * between upper-bound and lower-bound, value will get returned back.
 */
public final class Clamp {

  /**
   * Performs the clamp operation.
   *
   * @param value the value to clamp
   * @param lower the lower-bound value
   * @param upper the upper-bound value
   * @return value if value is between lower and upper, otherwise lower if value is less than lower
   * or upper if value is greater than upper
   */
  public static double run(double value, double lower, double upper) {
    if (value > upper) {
      return upper;
    }
    else if (value < lower) {
      return lower;
    }
    else {
      return value;
    }
  }

  /**
   * Performs the clamp operation.
   *
   * @param value the value to clamp
   * @param lower the lower-bound value
   * @param upper the upper-bound value
   * @return value if value is between lower and upper, otherwise lower if value is less than lower
   * or upper if value is greater than upper
   */
  public static int run(int value, int lower, int upper) {
    if (value > upper) {
      return upper;
    }
    else if (value < lower) {
      return lower;
    }
    else {
      return value;
    }
  }
}

