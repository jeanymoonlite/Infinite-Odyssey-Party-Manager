package controller.command.help;

import controller.command.ACommand;
import controller.command.misc.Clear;
import controller.command.misc.Start;
import java.io.IOException;
import model.Manager;
import view.TextView;

/**
 * A command object that displays all possible commands for this program.
 */
public final class HelpAll extends ACommand {

  /**
   * Constructs a new {@code HelpAll}.
   *
   * @param model the model to use
   * @param view  the view to use to render messages
   */
  public HelpAll(Manager model, TextView view) {
    super(model, view);
    this.signature = "help-all";
    this.description = "Displays every command in the program organized by categories.";
  }

  @Override
  public void run() {
    try {
      this.view.display("Character Related Commands:\n");
      new HelpChar(this.model, this.view).run();
      this.view.display("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

      this.view.display("Party Related Commands:\n");
      new HelpParty(this.model, this.view).run();
      this.view.display("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

      this.view.display("Manager Related Commands:\n");
      new HelpManager(this.model, this.view).run();
      this.view.display("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

      this.view.display("Stats Related Commands:\n");
      new HelpStats(this.model, this.view).run();
      this.view.display("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

      this.view.display("Dice Related Commands:\n");
      new HelpDice(this.model, this.view).run();
      this.view.display("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

      this.view.display("Misc Commands:\n");
      this.view.display(new Start(null, null, null).getSignature() + ": ");
      this.view.display("\n\t");
      this.view.display(new Start(null, null, null)
          .getDescription().replace("\n", "\n\t"));
      this.view.display("\n");
      this.view.display("\n");

      this.view.display("quit:");
      this.view.display("\n\tExits the program.\n");
      this.view.display("\n");

      this.view.display(new Clear(null, null, 100).getSignature() + ": ");
      this.view.display("\n\t");
      this.view.display(new Clear(null, null, 100)
          .getDescription().replace("\n", "\n\t"));
      this.view.display("\n");
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }
}
