package controller.input.validation;

import controller.command.ACommand;
import java.io.IOException;
import java.util.Scanner;
import model.Manager;
import view.TextView;

/**
 * A functional object that takes in the name of a {@code Character} and confirms whether the
 * character is valid.
 */
public final class CharacterValid extends ACommand implements InputValidation {

  private final Scanner sc;

  /**
   * Constructs a new {@code CharacterValid}.
   *
   * @param model the model to use
   * @param view  the view to use to render messages
   * @param sc    the scanner to read input from
   */
  public CharacterValid(Manager model, TextView view, Scanner sc) {
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

      if (!this.model.hasCharacters()) {
        this.view.display("The Manager doesn't have any Characters!\n");
        this.view.display("Add Characters using the create-char command.\n");
        return false;
      }

      if (!this.model.doesCharacterExist(input)) {
        this.view.display(
            "Invalid input: The Character " + input + " doesn't exist in this Manager.\n");
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
