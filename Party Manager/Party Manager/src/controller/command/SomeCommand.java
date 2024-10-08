package controller.command;

import java.io.IOException;
import java.util.Scanner;
import model.Manager;
import view.TextView;

/**
 * A template for {@code ACommand} objects.
 */
public class SomeCommand extends ACommand {

  private final Scanner sc;

  /**
   * Constructs a new {@code PartyCommand}.
   *
   * @param model the model to use
   * @param view  the view to use to render messages
   * @param sc    the scanner to read input from
   */
  public SomeCommand(Manager model, TextView view, Scanner sc) {
    super(model, view);
    this.sc = sc;
  }

  @Override
  public void run() {
    try {

      this.view.display("");
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }
}
