package controller.command.dice;

import controller.IOManagerSeedHolder;
import controller.command.ACommand;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import model.Manager;
import view.TextView;

/**
 * This command sets the seed that the {@code Roll} and all the dice commands will use.
 */
public class SetSeed extends ACommand {

  private final Scanner sc;

  /**
   * Constructs a new {@code SetSeed}.
   *
   * @param model the model to use
   * @param view  the view to use to render messages
   */
  public SetSeed(Manager model, TextView view, Scanner sc) {
    super(model, view);
    this.sc = sc;
  }

  @Override
  public void run() {
    try {
      try {
        int seed = this.sc.nextInt();
        IOManagerSeedHolder.getInstance().setSeed(seed);
        this.view.display("The seed has been set to " + seed + ".\n");
        this.view.display("To use the seed, use the use-seed command to enable or disable it.\n");
//        new UseSeed(this.model, this.view, this.sc).run();
      }
      catch (InputMismatchException e) {
        this.sc.next();
        this.view.display("Invalid input: A stat's value cannot be decimal.\n");
      }
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }
}
