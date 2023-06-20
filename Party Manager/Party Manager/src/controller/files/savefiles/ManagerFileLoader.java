package controller.files.savefiles;

import controller.input.validation.InputValidation;
import model.Manager;

/**
 * An interface that can load a .iom file.
 */
public interface ManagerFileLoader extends InputValidation {

  /**
   * Returns back a {@code Manager} object constructed by the file found at the given
   * String.
   * @param filePath the file to read from.
   * @return a {@code Manager} object constructed by the given file.
   * @throws IllegalArgumentException if the given String is null
   *                                  OR if {@code isValid()} was not called first.
   */
  Manager load(String filePath) throws IllegalArgumentException;

}
