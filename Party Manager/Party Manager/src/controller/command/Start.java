package controller.command;

import java.io.IOException;
import java.util.Scanner;
import model.Manager;
import view.TextView;

public class Start extends ACommand {

  private final Scanner sc;

  /**
   * Constructs a new {@code Start}.
   *
   * @param model the model to use
   * @param view  the view to use to render messages
   * @param sc    the scanner to read input from
   */
  public Start(Manager model, TextView view, Scanner sc) {
    super(model, view);
    this.sc = sc;
    this.signature = "start (partyName)";
    this.description = "Starts a campaign with the party that has the given name.";
  }

  @Override
  public void run() {
    try {
      String name = this.sc.nextLine().trim();

      if (!this.model.hasParties()) {
        this.view.display("The Manager doesn't have any Parties!\n");
        this.view.display("Add Parties using the create-party command.\n");
        return;
      }

      if (!this.model.doesPartyExist(name)) {
        this.view.display("The Party " + name + " doesn't exist in this Manager.\n");
        return;
      }

      while (true) {
        this.view.display("Start a Campaign with the " + name + " Party?\n");
        this.view.display("Confirm (y or n): ");

        String answer = this.sc.next();

        if (answer.equalsIgnoreCase("y")) {
          this.view.display("A Campaign has been started with " + name + ":\n\n");
          this.model.setActiveParty(name);
          this.model.startCampaign(true);
          this.view.displayActiveParty();
          break;
        }

        else if (answer.equalsIgnoreCase("n")) {
          break;
        }

        else {
          this.view.display("Invalid input.\n");
        }
      }
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }
}
