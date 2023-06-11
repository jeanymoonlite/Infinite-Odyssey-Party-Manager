package controller.command.help;

import controller.command.ACommand;
import controller.command.manager.PartyCommand;
import controller.command.party.CreateParty;
import controller.command.party.EditParty;
import controller.command.party.RemoveParty;
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
      this.view.display(new CreateParty(null, null, null).getSignature());
      this.view.display(": ");
      this.view.display("\n\t");
      this.view.display(new CreateParty(null, null, null)
          .getDescription().replace("\n", "\n\t"));
      this.view.display("\n");
      this.view.display("\n");

      this.view.display(new EditParty(null, null, null).getSignature());
      this.view.display(": ");
      this.view.display("\n\t");
      this.view.display(new EditParty(null, null, null)
          .getDescription().replace("\n", "\n\t"));
      this.view.display("\n");
      this.view.display("\n");

      this.view.display(new RemoveParty(null, null, null).getSignature());
      this.view.display(": ");
      this.view.display("\n\t");
      this.view.display(new RemoveParty(null, null, null)
          .getDescription().replace("\n", "\n\t"));
      this.view.display("\n");
      this.view.display("\n");
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }
}
