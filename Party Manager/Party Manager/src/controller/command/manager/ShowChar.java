package controller.command.manager;

import controller.command.ACommand;
import controller.input.validation.CharacterValid;
import java.io.IOException;
import java.util.Scanner;
import model.Manager;
import view.TextView;

/**
 * A command that displays a character in the given {@code Manager}.
 */
public final class ShowChar extends ACommand {

  private final Scanner sc;

  /**
   * Constructs a new {@code ShowChar}.
   *
   * @param model the model to use
   * @param view  the view to use to render messages
   * @param sc    the scanner to read input from
   */
  public ShowChar(Manager model, TextView view, Scanner sc) {
    super(model, view);
    this.sc = sc;
    this.signature = "show-char (name)";
    this.description = "Displays the name, class, class specification, and stats \n"
        + "of the specified character.";
  }

  @Override
  public void run() {
    try {
      String name = this.sc.nextLine().trim();

      if (!new CharacterValid(this.model, this.view, this.sc).isValid(name)) {
        return;
      }

      this.view.displayCharacter(name);
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }
}
