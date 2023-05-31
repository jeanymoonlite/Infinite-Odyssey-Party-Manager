package view;


import java.io.IOException;

/**
 * The interface for a client's perspective of the model of Bodega Dreams.
 */
public interface TextView {

  /**
   * Renders a given message to the data output in the implementation.
   * @param message the message to be printed
   * @throws IOException if the transmission of the message to the data output fails
   * @throws IllegalStateException if the given message is null
   */
  void display(String message) throws IOException, IllegalArgumentException;

}
