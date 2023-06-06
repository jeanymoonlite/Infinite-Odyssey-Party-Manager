package controller.command;

import java.io.IOException;
import java.util.Scanner;
import model.Manager;
import view.TextView;

/**
 * A command that appends 100 new lines into the output.
 */
public final class Clear extends ACommand {

  private final int amount;

  /**
   * Constructs a new {@code Clear}.
   *
   * @param model the model to use
   * @param view  the view to use to render messages
   */
  public Clear(Manager model, TextView view, int amount) {
    super(model, view);

    if (amount < 0) {
      throw new IllegalArgumentException("Amount must be greater than 0.");
    }
    this.amount = amount;
  }

  @Override
  public void run() {
    try {
      for (int i = 0; i < this.amount; i++) {
        this.view.display("\n");
      }
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }
}
