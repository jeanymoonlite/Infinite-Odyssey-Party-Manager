package controller.command.party;

import controller.command.ACommand;
import controller.input.validation.PartyValid;
import java.io.IOException;
import java.util.Scanner;
import model.Manager;
import view.TextView;

/**
 * A command that removes a given party from the given Manager.
 */
public final class RemoveParty extends ACommand {

  private final Scanner sc;

  /**
   * Constructs a new {@code RemoveParty}.
   *
   * @param model the model to use
   * @param view  the view to use to render messages
   * @param sc    the scanner to read input from
   */
  public RemoveParty(Manager model, TextView view, Scanner sc) {
    super(model, view);
    this.sc = sc;
    this.signature = "remove-party (name)";
    this.description = "Removes a party with the given name.";
  }

  @Override
  public void run() {
    try {

      String name = this.sc.nextLine().trim();

      if (!new PartyValid(this.model, this.view, this.sc).isValid(name)) {
        return;
      }

      while (true) {
        this.view.display("The following action cannot be undone.\n");
        this.view.display("Remove the following Party? (Confirm y or n): \n");
        this.view.displayParty(name);

        String answer = this.sc.next();

        if (answer.equalsIgnoreCase("y")) {
          this.view.display("The Party " + name + " was removed from the Manager.\n");
          this.model.removeParty(name);
          break;
        }

        else if (answer.equalsIgnoreCase("n")) {
          this.view.display("The Party " + name + " will not be removed.\n");
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
