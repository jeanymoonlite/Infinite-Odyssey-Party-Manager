package controller.command.help;

import controller.command.ACommand;
import java.io.IOException;
import model.Manager;
import view.TextView;

/**
 * A command object that displays all possible commands for this program.
 */
public final class Help extends ACommand {

  /**
   * Constructs a new {@code Help}.
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
      this.view.display("Character Related Commands:\n");
      new HelpChar(this.model, this.view).run();

      this.view.display("Party Related Commands:\n");
      new HelpParty(this.model, this.view).run();

      this.view.display("Manager Related Commands:\n");
      new HelpManager(this.model, this.view).run();

      this.view.display("Stats Related Commands:\n");
      new HelpStats(this.model, this.view).run();

      this.view.display("Dice Related Commands:\n");
      new HelpDice(this.model, this.view).run();
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }
}
