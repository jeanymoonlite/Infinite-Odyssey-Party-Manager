package controller.command.manager;

import controller.command.ACommand;
import java.io.IOException;
import model.Manager;
import view.TextView;

/**
 * A command that displays all the parties in the given {@code Manager}.
 */
public final class ShowAllParties extends ACommand {

  /**
   * Constructs a new {@code ShowAllParties}.
   *
   * @param model the model to use
   * @param view  the view to use to render messages
   */
  public ShowAllParties(Manager model, TextView view) {
    super(model, view);
    this.signature = "show-all-parties";
    this.description = "Displays a list of every party with their name, followed \n"
        + "by the characters within them.";
  }

  @Override
  public void run() {
    try {
      if (!this.model.hasParties()) {
        this.view.display("Invalid state: The Manager doesn't have any Parties!\n");
        this.view.display("Add Parties using the create-party command.\n");
        return;
      }

      this.view.displayAllParties();
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }
}
