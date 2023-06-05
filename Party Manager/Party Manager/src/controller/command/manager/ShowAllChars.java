package controller.command.manager;

import controller.command.ACommand;
import java.io.IOException;
import java.util.Scanner;
import model.Manager;
import view.TextView;

/**
 * A command that displays all the characters in the given {@code Manager}.
 */
public final class ShowAllChars extends ACommand {

  private final Scanner sc;

  /**
   * Constructs a new {@code ShowAllChars}.
   *
   * @param model the model to use
   * @param view  the view to use to render messages
   */
  public ShowAllChars(Manager model, TextView view, Scanner sc) {
    super(model, view);
    this.sc = sc;
  }

  @Override
  public void run() {
    try {
      this.view.displayAllCharacters();
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }
}
