package controller.input.validation;

import controller.command.ACommand;
import java.io.IOException;
import java.util.Scanner;
import model.Manager;
import view.TextView;

/**
 * A functional object that takes in the name of a {@code Party} and confirms whether the party is
 * valid.
 */
public final class PartyValid extends ACommand implements InputValidation {

  private final Scanner sc;

  /**
   * Constructs a new {@code ACommand}.
   *
   * @param model the model to use
   * @param view  the view to use to render messages
   * @param sc    the scanner to read input from
   */
  public PartyValid(Manager model, TextView view, Scanner sc) {
    super(model, view);
    this.sc = sc;
  }

  @Override
  public boolean isValid(String input) {
    try {
      if (this.model.hasStartedACampaign()) {
        this.view.display("Invalid state: This command can't be used during a campaign.\n");
        this.view.display("Use the quit command to end the current campaign.\n");
        return false;
      }

      if (!this.model.hasParties()) {
        this.view.display("The Manager doesn't have any Parties!\n");
        this.view.display("Add Parties using the create-party command.\n");
        return false;
      }

      if (!this.model.doesPartyExist(input)) {
        this.view.display(
            "Invalid input: The Party " + input + " doesn't exist in this Manager.\n");
        return false;
      }

      return true;
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }


  @Override
  public void run() {
  }
}
