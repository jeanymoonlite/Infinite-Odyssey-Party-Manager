package controller.command.help;

import controller.command.ACommand;
import controller.command.character.CreateChar;
import controller.command.manager.PartyCommand;
import controller.command.misc.Clear;
import controller.command.misc.Start;
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
    this.signature = "help";
    this.description = "Displays the various help commands.\n"
        + "When in Character or Party editing mode, this command displays the edit specific commands.";
  }

  @Override
  public void run() {
    try {
      if (this.model.hasStartedACampaign()) {
        this.campaignHelp();
        return;
      }

      this.view.display("All Help Commands:\n");

      this.view.display(this.getSignature() + ": ");
      this.view.display("\n\t");
      this.view.display(this.getDescription().replace("\n", "\n\t"));
      this.view.display("\n");
      this.view.display("\n");

      this.view.display(new HelpAll(null, null).getSignature() + ": ");
      this.view.display("\n\t");
      this.view.display(new HelpAll(null, null)
          .getDescription().replace("\n", "\n\t"));
      this.view.display("\n");
      this.view.display("\n");

      this.view.display(new HelpChar(null, null).getSignature() + ": ");
      this.view.display("\n\t");
      this.view.display(new HelpChar(null, null)
          .getDescription().replace("\n", "\n\t"));
      this.view.display("\n");
      this.view.display("\n");

      this.view.display(new HelpParty(null, null).getSignature() + ": ");
      this.view.display("\n\t");
      this.view.display(new HelpParty(null, null)
          .getDescription().replace("\n", "\n\t"));
      this.view.display("\n");
      this.view.display("\n");

      this.view.display(new HelpManager(null, null).getSignature() + ": ");
      this.view.display("\n\t");
      this.view.display(new HelpManager(null, null)
          .getDescription().replace("\n", "\n\t"));
      this.view.display("\n");
      this.view.display("\n");

      this.view.display(new HelpStats(null, null).getSignature() + ": ");
      this.view.display("\n\t");
      this.view.display(new HelpStats(null, null)
          .getDescription().replace("\n", "\n\t"));
      this.view.display("\n");
      this.view.display("\n");

      this.view.display(new HelpDice(null, null).getSignature() + ": ");
      this.view.display("\n\t");
      this.view.display(new HelpDice(null, null)
          .getDescription().replace("\n", "\n\t"));
      this.view.display("\n");
      this.view.display("\n");
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }

  private void campaignHelp() {
    try {
      this.view.display("All Campaign Commands:\n");

      this.view.display(this.getSignature() + ": ");
      this.view.display("\n\t");
      this.view.display(this.getDescription().replace("\n", "\n\t"));
      this.view.display("\n");
      this.view.display("\n");

      this.view.display(new PartyCommand(null, null, null).getSignature());
      this.view.display(": ");
      this.view.display("\n\t");
      this.view.display(new PartyCommand(null, null, null)
          .getDescription().replace("\n", "\n\t"));
      this.view.display("\n");
      this.view.display("\n");

      new HelpStats(this.model, this.view).run();
      new HelpDice(this.model, this.view).run();

      this.view.display("quit:");
      this.view.display("\n\tEnds the current campaign.\n");
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
