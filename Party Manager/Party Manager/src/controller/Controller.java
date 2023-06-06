package controller;

/**
 * An interface for controlling and interacting with a {@code Manager}.
 */
public interface Controller {

  /**
   * Starts the Controller.
   */
  void start();

  /**
   * Initializes the commands that this {@code Controller} supports.
   */
  void initCommands();

}
