package controller.command.dice;

import controller.IOManagerSeedHolder;
import controller.command.ACommand;
import java.io.IOException;
import java.util.Scanner;
import model.Manager;
import view.TextView;

/**
 * A command that enables the seed given seed to be used. This only works if a seed
 * has been set.
 */
public class UseSeed extends ACommand {

  private final Scanner sc;

  /**
   * Constructs a new {@code PartyCommand}.
   *
   * @param model the model to use
   * @param view  the view to use to render messages
   */
  public UseSeed(Manager model, TextView view, Scanner sc) {
    super(model, view);
    this.sc = sc;
  }

  @Override
  public void run() {
    try {
      if (!IOManagerSeedHolder.getInstance().hasSetSeed()) {
        this.view.display("A seed has not been set for this Manager.\n");
        this.view.display("Use the set-seed command to set a seed.\n");
        return;
      }

      while (true) {
        this.view.display("Use the seed "
            + IOManagerSeedHolder.getInstance().getSeed()
            + " for all dice commands? (Confirm y or n): \n");

        String answer = this.sc.next();

        if (answer.equalsIgnoreCase("y")) {
          this.view.display("The Manager is now using the seed "
              + IOManagerSeedHolder.getInstance().getSeed() + ".\n");
          IOManagerSeedHolder.getInstance().usingSeed(true);
          break;
        }

        else if (answer.equalsIgnoreCase("n")) {
          this.view.display("The Manager will not use a seed.\n");
          IOManagerSeedHolder.getInstance().usingSeed(false);
          break;
        }

        else {
          this.view.display("Invalid input.\n");
        }
      }
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }
}
