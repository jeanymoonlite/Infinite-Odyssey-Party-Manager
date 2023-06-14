package controller.command.help;

import controller.command.ACommand;
import controller.command.dice.Dice;
import controller.command.dice.Roll;
import java.io.IOException;
import model.Manager;
import view.TextView;

/**
 * A command object that displays all the commands relating to dice.
 */
public final class HelpDice extends ACommand {

  /**
   * Constructs a new {@code HelpAll}.
   *
   * @param model the model to use
   * @param view  the view to use to render messages
   */
  public HelpDice(Manager model, TextView view) {
    super(model, view);
    this.signature = "help-dice";
    this.description = "Displays every dice-related command.";
  }

  @Override
  public void run() {
    try {
      this.view.display(new Roll(null, null, null).getSignature());
      this.view.display(": ");
      this.view.display("\n\t");
      this.view.display(new Roll(null, null, null)
          .getDescription().replace("\n", "\n\t"));
      this.view.display("\n");
      this.view.display("\n");

      int[] dices = new int[] {2, 4, 6, 8, 10, 12, 20, 100};

      for (int i : dices) {
        this.view.display(new Dice(null, null, i).getSignature());
        this.view.display(": ");
        this.view.display(new Dice(null, null, i)
            .getDescription().replace("\n", "\n\t"));
        this.view.display("\n");
        this.view.display("\n");
      }
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }
}
