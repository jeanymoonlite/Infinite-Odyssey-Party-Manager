package controller.command.help;

import controller.command.ACommand;
import java.io.IOException;
import model.Manager;
import view.TextView;

/**
 * A command object that displays all help commands for this program.
 */
public final class Help extends ACommand {

  /**
   * Constructs a new {@code HelpAll}.
   *
   * @param model the model to use
   * @param view  the view to use to render messages
   */
  public Help(Manager model, TextView view) {
    super(model, view);
  }

  @Override
  public void run() {
    try {
      this.view.display("All Help Commands:\n");

      this.view.display("help\n");
      this.view.display(
          "\tWhen in Character or Party editing mode, this command displays the edit specific commands.\n");
      this.view.display("\n");

      this.view.display("help-all\n");
      this.view.display("\tDisplays every command in the program organized by categories.\n");
      this.view.display("\n");

      this.view.display("help-char\n");
      this.view.display("\tDisplays every character-related command.\n");
      this.view.display("\n");

      this.view.display("help-party\n");
      this.view.display("\tDisplays every party-related command.\n");
      this.view.display("\n");

      this.view.display("help-manager\n");
      this.view.display("\tDisplays commands for viewing characters and parties.\n");
      this.view.display("\n");

      this.view.display("help-stats\n");
      this.view.display("\tDisplays every command for healing and damaging characters.\n");
      this.view.display("\n");

      this.view.display("help-dice\n");
      this.view.display("\tDisplays every dice-related command.\n");
      this.view.display("\n");

    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }
}
