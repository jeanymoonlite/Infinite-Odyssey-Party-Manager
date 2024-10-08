package controller.command.dice;

import controller.IOManagerSeedHolder;
import controller.command.ACommand;
import java.io.IOException;
import java.util.Random;
import model.Manager;
import utils.Clamp;
import view.TextView;

/**
 * A command object that generates a random number from 1 to the given upperBound. This class,
 * unlike the {@code Roll} class, will use the controller's seed if it has/ is using one.
 */
public class Dice extends ACommand {

  private final int upperBound;
  private final Random seeded;
  private final Random rand;

  /**
   * Constructs a new {@code Dice}.
   *
   * @param model      the model to use
   * @param view       the view to use to render messages
   * @param upperBound the upper limit of the dice
   */
  public Dice(Manager model, TextView view, int upperBound) {
    super(model, view);
    this.upperBound = upperBound;
    this.seeded = new Random(IOManagerSeedHolder.getInstance().getSeed());
    this.rand = new Random();

    this.signature = (this.upperBound == 2)? "cf" : "d" + this.upperBound;
    this.description = (this.upperBound == 2)?
        "Flips a coin." : "Rolls a random number between 1 and " + this.upperBound + ".";
  }

  @Override
  public void run() {
    try {
      if (this.upperBound == 2) {
        int roll = Clamp.run(this.rand.nextInt(100), 1, 100);
        String[] coin = new String[] {"Heads", "Tails"};
        this.view.display("The coin landed on " + coin[(roll > 50)? 1 : 0] + ".\n");
      }
      else {
        int roll = Clamp.run(this.rand.nextInt(this.upperBound + 1), 1, this.upperBound);
        this.view.display("The roll is [" + roll + "]\n");
      }
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }
}
