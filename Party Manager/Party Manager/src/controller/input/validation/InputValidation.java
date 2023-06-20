package controller.input.validation;

/**
 * An interface that takes in some input, and returns whether the given input is valid.
 */
public interface InputValidation {

  /**
   * Determines if the given input is valid.
   * @param input the input to check
   * @return true if the input is valid, otherwise false.
   * @throws IllegalArgumentException if the given input is null.
   */
  boolean isValid(String input) throws IllegalArgumentException;

}
