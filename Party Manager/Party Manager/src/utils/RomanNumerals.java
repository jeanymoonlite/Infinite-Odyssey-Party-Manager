package utils;

/**
 * A utility method that takes in an integer and returns back it's roman numeral representation.
 * This class goes up to 100.
 */
public final class RomanNumerals {

  private static final String single = "i";
  private static final String five = "v";
  private static final String ten = "x";
  private static final String fifty = "l";
  private static final String hundred = "c";

  public static String get(int number) {

    if (number < 1 || number > 100) {
      throw new IllegalArgumentException("This class can only convert integers 1-100.");
    }

    String[] romanSymbols = {
        "i", "iv", "v", "ix", "x", "xl", "l", "xc", "c"
    };

    int[] arabicValues = {
        1, 4, 5, 9, 10, 40, 50, 90, 100
    };

    StringBuilder romanNumeral = new StringBuilder();

    for (int i = arabicValues.length - 1; i >= 0; i--) {
      while (number >= arabicValues[i]) {
        romanNumeral.append(romanSymbols[i]);
        number -= arabicValues[i];
      }
    }

    return romanNumeral.toString();
  }

}
