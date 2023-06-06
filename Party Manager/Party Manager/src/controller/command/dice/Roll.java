package controller.command.dice;

import controller.command.ACommand;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import model.Manager;
import utils.Clamp;
import view.TextView;

/**
 * This command generates a random number between 1 and the given upper bound.
 * This command does NOT use the controller's seed.
 */
public class Roll extends ACommand {

  private final Scanner sc;

  /**
   * Constructs a new {@code PartyCommand}.
   *
   * @param model the model to use
   * @param view  the view to use to render messages
   * @param sc  the scanner to read input from
   */
  public Roll(Manager model, TextView view, Scanner sc) {
    super(model, view);
    this.sc = sc;
  }

  @Override
  public void run() {
    try {
      try {
        int upperBound = this.sc.nextInt();

        if (upperBound < 2) {
          this.view.display("Invalid input: The upper bound must be greater than 1.\n");
          return;
        }

        int roll = Clamp.run(new Random().nextInt(upperBound + 1), 1, upperBound);
        this.view.display("The roll is [" + roll + "]\n");
      }
      catch (InputMismatchException e) {
        this.sc.next();
        this.view.display("Invalid input: The upper bound for the roll cannot be a decimal.\n");
      }
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }
}
