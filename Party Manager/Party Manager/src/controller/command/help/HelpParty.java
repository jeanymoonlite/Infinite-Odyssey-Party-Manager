package controller.command.help;

import controller.command.ACommand;
import java.io.IOException;
import model.Manager;
import view.TextView;

/**
 * A command object that displays all the commands relating to parties.
 */
public final class HelpParty extends ACommand {

  /**
   * Constructs a new {@code HelpAll}.
   *
   * @param model the model to use
   * @param view  the view to use to render messages
   */
  public HelpParty(Manager model, TextView view) {
    super(model, view);
  }

  @Override
  public void run() {
    try {
      this.view.display("create-party (name characters...)\n");
      this.view.display("\tCreates a new party with the given name and list of characters.\n");
      this.view.display("\tEach character's name must be seperated by a space and they should all be on one line.\n");
      this.view.display("\tDuplicate characters cannot be used.\n");
      this.view.display("\n");

      this.view.display("edit-party (name)\n");
      this.view.display("\tEdits a Party with the given name.\n"
          + "\tThis will put the program into party editing mode, "
          + "where a new set of commands will be available.\n");
      this.view.display("\n");

      this.view.display("remove-party (name)\n");
      this.view.display("\tRemoves a party with the given name.\n");
      this.view.display("\n");
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }
}
