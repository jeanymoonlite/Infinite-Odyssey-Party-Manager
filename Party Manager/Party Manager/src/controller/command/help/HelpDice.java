package controller.command.help;

import controller.command.ACommand;
import java.io.IOException;
import model.Manager;
import view.TextView;

/**
 * A command object that displays all the commands relating to dice.
 */
public final class HelpDice extends ACommand {

  /**
   * Constructs a new {@code Help}.
   *
   * @param model the model to use
   * @param view  the view to use to render messages
   */
  public HelpDice(Manager model, TextView view) {
    super(model, view);
  }

  @Override
  public void run() {
    try {
      this.view.display("set-seed (seed)\n"
          + "\tSets a seed to use for seeded-rolls.\n");

      this.view.display("\n");

      this.view.display("use-seed (y or n)\n"
          + "\tEnables or disables the seed for dice rolls. If a seed has NOT been set, the "
          + "command will request one.\n"
          + "\tThe command will ONLY accept y or n; y enables the seed, n disables the seed.\n"
          + "\tSeeds cannot be set for specific dice, they're either enabled for all or disabled for all.\n");

      this.view.display("\n");

      this.view.display("roll (upperBound)\n"
          + "\tReturns a random number between 1 and the given upper bound.\n"
          + "\tThe upper bound cannot be less than 2; if it is, the user will be informed.\n"
          + "\tThe number generator does NOT use a seed.\n");

      this.view.display("\n");

      this.view.display("d2 : Randomly picks 1 or 2.\n");
      this.view.display("\n");
      this.view.display("d4 : Rolls a random number between 1 and 4.\n");
      this.view.display("\n");
      this.view.display("d6 : Rolls a random number between 1 and 6.\n");
      this.view.display("\n");
      this.view.display("d8 : Rolls a random number between 1 and 8.\n");
      this.view.display("\n");
      this.view.display("d10 : Rolls a random number between 1 and 10.\n");
      this.view.display("\n");
      this.view.display("d12 : Rolls a random number between 1 and 12.\n");
      this.view.display("\n");
      this.view.display("d20 : Rolls a random number between 1 and 20.\n");
      this.view.display("\n");
      this.view.display("d100 : Rolls a random number between 1 and 100.\n");
      this.view.display("\n");

    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }
}
