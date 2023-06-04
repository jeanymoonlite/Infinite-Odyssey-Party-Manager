package controller.command.help;

import controller.command.ACommand;
import java.io.IOException;
import model.Manager;
import view.TextView;

/**
 * A command object that displays all the commands relating to characters.
 */
public final class HelpChar extends ACommand {

  /**
   * Constructs a new {@code HelpChar}.
   *
   * @param model the model to use
   * @param view  the view to use to render messages
   */
  public HelpChar(Manager model, TextView view) {
    super(model, view);
  }

  @Override
  public void run() {
    try {
      this.view.display("create-char (name playerName role roleSpecification "
          + "strength intelligence creativity charisma stealth intimidation\n");
      this.view.display("\tCreates a new character with the given information.\n");
      this.view.display("\n");

      this.view.display("edit-char (name)\n");
      this.view.display("\tEdits a character with the given name.\n"
          + "\tThis will put the program into character editing mode.\n"
          + "\tA new set of commands will become available in editing mode.\n");
      this.view.display("\n");

      this.view.display("remove-char (name)\n");
      this.view.display("\tRemoves a character with the given name\n");
      this.view.display("\n");
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }
}
