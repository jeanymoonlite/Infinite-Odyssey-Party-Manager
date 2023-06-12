package controller.command;

import controller.input.validation.PartyValid;
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

      if (!new PartyValid(this.model, this.view, this.sc).isValid(name)) {
        return;
      }

      while (true) {
        this.view.display("\nStart a Campaign with the " + name + " Party?\n");
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
          this.view.display("\nInvalid input.");
        }
      }
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }
}
