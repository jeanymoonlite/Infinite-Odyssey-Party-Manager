package controller.command.help;

import controller.command.ACommand;
import java.io.IOException;
import model.Manager;
import view.TextView;

/**
 * A command object that displays all the commands relating to editing characters.
 */
public final class HelpEditChar extends ACommand {

  /**
   * Constructs a new {@code HelpChar}.
   *
   * @param model the model to use
   * @param view  the view to use to render messages
   */
  public HelpEditChar(Manager model, TextView view) {
    super(model, view);
  }

  @Override
  public void run() {
    try {
      this.view.display("edit (attribute newValue)\n");
      this.view.display("\tattribute must be name, playerName, role, roleSpecification "
          + "strength, intelligence, creativity, charisma, stealth, intimidation\n");
      this.view.display("\tnewValue is the new value to give the specified attribute.\n");
      this.view.display("\t\tname and playerName cannot be whitespace.\n");
      this.view.display(
          "\t\trole must be one of the following: Warrior, Wizard, Bard, Engineer, Rogue, Monk, Human.\n");
      this.view.display("\t\tthe remaining stats must be positive whole numbers.\n");
      this.view.display("\t\tthe sum of the stats must not exceed 30.\n\n");

      this.view.display("save\n\n");
      this.view.display("back\n");
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }
}
