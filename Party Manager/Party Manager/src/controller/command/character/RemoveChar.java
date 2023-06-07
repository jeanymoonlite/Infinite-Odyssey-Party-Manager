package controller.command.character;

import controller.command.ACommand;
import java.io.IOException;
import java.util.Scanner;
import model.Character;
import model.Manager;
import view.TextView;

/**
 * A command that remove the given character from the given Manager.
 */
public final class RemoveChar extends ACommand {

  private final Scanner sc;

  /**
   * Constructs a new {@code RemoveChar}.
   *
   * @param model the model to use
   * @param view  the view to use to render messages
   * @param sc  the scanner to read input from
   */
  public RemoveChar(Manager model, TextView view, Scanner sc) {
    super(model, view);
    this.sc = sc;
  }

  @Override
  public void run() {
    try {
      if (this.model.hasStartedACampaign()) {
        this.view.display("Invalid state: This command can't be used during a campaign.\n");
        this.view.display("Use the quit command to end the current campaign.\n");
        this.sc.nextLine();
        return;
      }

      if (!this.model.hasCharacters()) {
        this.view.display("The Manager doesn't have any Characters!\n");
        this.view.display("Add Characters using the create-char command.\n");
        return;
      }

      String name = this.sc.nextLine().trim();

      if (!this.model.doesCharacterExist(name)) {
        this.view.display("Invalid input: The Character " + name + " doesn't exist in this Manager.\n");
        return;
      }

      String playerName = this.model.findCharByName(name).getPlayerName();

      while (true) {
        this.view.display("The following action cannot be undone.\n");
        this.view.display("Remove the following Character? (Confirm y or n): \n");
        this.view.displayCharacter(name);

        String answer = this.sc.next();

        if (answer.equalsIgnoreCase("y")) {
          this.view.display(name + " (" + playerName + ") was removed from the Manager.\n");
          this.model.removeCharacter(name);
          break;
        }

        else if (answer.equalsIgnoreCase("n")) {
          this.view.display(name + " (" + playerName + ") will not be removed.\n");
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
