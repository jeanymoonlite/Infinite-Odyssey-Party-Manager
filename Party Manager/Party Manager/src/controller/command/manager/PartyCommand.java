package controller.command.manager;

import controller.command.ACommand;
import java.io.IOException;
import java.util.Scanner;
import model.Manager;
import view.TextView;

public final class PartyCommand extends ACommand {

  private final Scanner sc;

  /**
   * Constructs a new {@code PartyCommand}.
   *
   * @param model the model to use
   * @param view  the view to use to render messages
   * @param sc    the scanner to read input from
   */
  public PartyCommand(Manager model, TextView view, Scanner sc) {
    super(model, view);
    this.sc = sc;
    this.signature = "party";
    this.description = "Displays the active party if there is one.";
  }

  @Override
  public void run() {
    try {
      if (!this.model.hasParties()) {
        this.view.display("The Manager doesn't have any Parties!\n");
        this.view.display("Add Parties using the create-party command.\n");
        return;
      }

      if (this.model.getActiveParty() == null) {
        this.view.display("Invalid state: This command can only be used during a campaign.\n");
        this.view.display("Use the start command to start a campaign.\n");
        return;
      }

      this.view.displayActiveParty();
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }
}
