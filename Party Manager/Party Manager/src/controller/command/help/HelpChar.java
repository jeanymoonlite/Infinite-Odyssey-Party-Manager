package controller.command.help;

import controller.command.ACommand;
import java.io.IOException;
import model.Manager;
import view.IOManagerTextView;
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

      this.view.display("\n");

      this.view.display("edit-char (name)\n");

      this.view.display("\n");

      this.view.display("remove-char (name)\n");
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }
}
