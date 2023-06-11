package controller.command.help;

import controller.command.ACommand;
import controller.command.dice.Roll;
import controller.command.manager.PartyCommand;
import controller.command.manager.ShowAllChars;
import controller.command.manager.ShowAllParties;
import controller.command.manager.ShowChar;
import controller.command.manager.ShowParty;
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
    this.signature = "help-manager";
    this.description = "Displays commands for viewing characters and parties.";
  }

  @Override
  public void run() {
    try {
      this.view.display(new PartyCommand(null, null, null).getSignature());
      this.view.display(": ");
      this.view.display("\n\t");
      this.view.display(new PartyCommand(null, null, null)
          .getDescription().replace("\n", "\n\t"));
      this.view.display("\n");
      this.view.display("\n");

      this.view.display(new ShowAllChars(null, null).getSignature());
      this.view.display(": ");
      this.view.display("\n\t");
      this.view.display(new ShowAllChars(null, null)
          .getDescription().replace("\n", "\n\t"));
      this.view.display("\n");
      this.view.display("\n");

      this.view.display(new ShowAllParties(null, null).getSignature());
      this.view.display(": ");
      this.view.display("\n\t");
      this.view.display(new ShowAllParties(null, null)
          .getDescription().replace("\n", "\n\t"));
      this.view.display("\n");
      this.view.display("\n");

      this.view.display(new ShowChar(null, null, null).getSignature());
      this.view.display(": ");
      this.view.display("\n\t");
      this.view.display(new ShowChar(null, null, null)
          .getDescription().replace("\n", "\n\t"));
      this.view.display("\n");
      this.view.display("\n");

      this.view.display(new ShowParty(null, null, null).getSignature());
      this.view.display(": ");
      this.view.display("\n\t");
      this.view.display(new ShowParty(null, null, null)
          .getDescription().replace("\n", "\n\t"));
      this.view.display("\n");
      this.view.display("\n");
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }
}
