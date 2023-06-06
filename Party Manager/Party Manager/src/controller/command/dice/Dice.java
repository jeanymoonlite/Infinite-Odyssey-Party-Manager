package controller.command.dice;

import controller.IOManagerSeedHolder;
import controller.command.ACommand;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import model.Manager;
import utils.Clamp;
import view.TextView;

/**
 * A command object that generates a random number from 1 to the given upperBound.
 * This class, unlike the {@code Roll} class, will use the controller's seed if it has/ is using one.
 */
public class Dice extends ACommand {

  private final int upperBound;
  private final Random seeded;
  private final boolean isCoin;

  /**
   * Constructs a new {@code Dice}.
   *
   * @param model the model to use
   * @param view  the view to use to render messages
   * @param upperBound the upper limit of the dice
   */
  public Dice(Manager model, TextView view, int upperBound) {
    super(model, view);
    this.upperBound = upperBound;
    this.seeded = new Random(IOManagerSeedHolder.getInstance().getSeed());
    this.isCoin = false;
  }

  @Override
  public void run() {
    try {
      if (IOManagerSeedHolder.getInstance().isUsingSeed()) {
        int roll = Clamp.run(this.seeded.nextInt(this.upperBound + 1), 1, this.upperBound);
        this.view.display("The roll is [" + roll + "]\n");
      }

      else {
        int roll = Clamp.run(new Random().nextInt(this.upperBound + 1), 1, this.upperBound);
        this.view.display("The roll is [" + roll + "]\n");
      }
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }
}
