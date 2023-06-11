package controller.command.help;

import controller.command.ACommand;
import java.io.IOException;
import model.Manager;
import view.TextView;

/**
 * A command object that displays all the commands relating to the manager.
 */
public final class HelpManager extends ACommand {

  /**
   * Constructs a new {@code HelpAll}.
   *
   * @param model the model to use
   * @param view  the view to use to render messages
   */
  public HelpManager(Manager model, TextView view) {
    super(model, view);
  }

  @Override
  public void run() {
    try {
      this.view.display("party\n");
      this.view.display("\tDisplays the active party if there is one.\n");
      this.view.display("\n");

      this.view.display(
          "Note: The following 4 commands are disabled when a campaign is started.\n\n");

      this.view.display("show-all-chars\n");
      this.view.display("\tDisplays a list of every character with their name and player name.\n");
      this.view.display("\n");

      this.view.display("show-all-parties\n");
      this.view.display(
          "\tDisplays a list of every party with their name, followed by the characters within them.\n");
      this.view.display("\n");

      this.view.display("show-char (name)\n");
      this.view.display(
          "\tDisplays the name, role, role specification, and stats of the specified character.\n");
      this.view.display("\n");

      this.view.display("show-party (name)\n");
      this.view.display(
          "\tDisplays the name, role, role specification, and stats of the every character in the specified party.\n");
      this.view.display("\n");
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }
}
