package controller.command.dice;

import controller.command.ACommand;
import java.io.IOException;
import java.util.Scanner;
import model.Manager;
import view.TextView;

/**
 * A command object that generates a random number from 1 to the given upperBound.
 * This class, unlike the {@code Roll} class, will use the controller's seed if it has/ is using one.
 */
public class Dice extends ACommand {

  /**
   * Constructs a new {@code Dice}.
   *
   * @param model the model to use
   * @param view  the view to use to render messages
   */
  public Dice(Manager model, TextView view, int upperBound) {
    super(model, view);
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
