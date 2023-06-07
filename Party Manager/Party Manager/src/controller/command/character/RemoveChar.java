package controller.command.character;

import controller.command.ACommand;
import java.io.IOException;
import java.util.Scanner;
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
        return;
      }

      try {

      }
      catch (IllegalStateException e) {

      }

      this.view.display("");
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }

    this.sc.close();
  }
}
