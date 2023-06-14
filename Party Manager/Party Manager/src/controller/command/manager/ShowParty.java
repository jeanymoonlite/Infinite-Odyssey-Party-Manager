package controller.command.manager;

import controller.command.ACommand;
import controller.input.validation.PartyValid;
import java.io.IOException;
import java.util.Scanner;
import model.Manager;
import view.TextView;

/**
 * A command that displays a party in the given {@code Manager}.
 */
public final class ShowParty extends ACommand {

  private final Scanner sc;

  /**
   * Constructs a new {@code ShowParty}.
   *
   * @param model the model to use
   * @param view  the view to use to render messages
   * @param sc    the scanner to read input from
   */
  public ShowParty(Manager model, TextView view, Scanner sc) {
    super(model, view);
    this.sc = sc;
    this.signature = "show-party (name)";
    this.description = "Displays the name, class, class specification, and stats of \n"
        + "the every character in the specified party.";
  }

  @Override
  public void run() {
    try {
      String name = this.sc.nextLine().trim();

      if (!new PartyValid(this.model, this.view, this.sc).isValid(name)) {
        return;
      }

      this.view.displayParty(name);
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }
}
