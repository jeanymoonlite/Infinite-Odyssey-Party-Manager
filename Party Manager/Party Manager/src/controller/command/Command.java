package controller.command;

/**
 * An interface for Commands to be used by the {@code IOManagerController}.
 */
public interface Command {

  /**
   * The run method allows each command object to collect input from the user, then call a
   * corresponding method in the model.
   */
  void run();

  /**
   * Returns the name of the command in the program, along with the name of its arguments
   * (if there are any).
   * @return the name of the command and its arguments
   */
  String getSignature();


  /**
   * Returns a description of what this command does.
   * @return a description of what this command does.
   */
  String getDescription();

}
