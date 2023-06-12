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
    this.signature = "show-all-chars";
    this.description = "Displays a list of every character with their name and player name.";
  }

  @Override
  public void run() {
    try {
      if (!this.model.hasCharacters()) {
        this.view.display("Invalid state: The Manager doesn't have any Characters!\n");
        this.view.display("Add Characters using the create-char command.\n");
        return;
      }

      this.view.displayAllCharacters();
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }
}
