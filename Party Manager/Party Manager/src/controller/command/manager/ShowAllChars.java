package controller.command.manager;

import controller.command.ACommand;
import java.io.IOException;
import model.Manager;
import view.TextView;

/**
 * A command that displays all the characters in the given {@code Manager}.
 */
public final class ShowAllChars extends ACommand {

  /**
   * Constructs a new {@code ShowAllChars}.
   *
   * @param model the model to use
   * @param view  the view to use to render messages
   */
  public ShowAllChars(Manager model, TextView view) {
    super(model, view);
  }

  @Override
  public void run() {
    try {
      try {
        this.view.displayAllCharacters();
      }
      catch (IllegalStateException e) {
        this.view.display("The Manager doesn't have any Characters!\n");
        this.view.display("Add Characters using the create-char command.\n");
      }
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }
}
