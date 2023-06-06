package controller.command.character;

import controller.command.ACommand;
import java.io.IOException;
import java.util.Scanner;
import model.Manager;
import view.TextView;

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
      this.view.display("");
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }

    this.sc.close();
  }
}
