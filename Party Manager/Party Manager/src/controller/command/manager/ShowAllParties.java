package controller.command.manager;

import controller.command.ACommand;
import java.io.IOException;
import java.util.Scanner;
import model.Manager;
import view.TextView;

/**
 * A command that displays all the parties in the given {@code Manager}.
 */
public final class ShowAllParties extends ACommand {

  private final Scanner sc;

  /**
   * Constructs a new {@code ShowAllParties}.
   *
   * @param model the model to use
   * @param view  the view to use to render messages
   */
  public ShowAllParties(Manager model, TextView view, Scanner sc) {
    super(model, view);
    this.sc = sc;
  }

  @Override
  public void run() {
    try {
      this.view.displayAllParties();
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }
}
