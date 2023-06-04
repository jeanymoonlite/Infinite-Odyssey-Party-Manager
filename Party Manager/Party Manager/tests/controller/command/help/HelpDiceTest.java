package controller.command.help;

import static org.junit.Assert.*;

import controller.IOManagerController;
import controller.IOManagerControllerTest;
import java.io.StringReader;
import model.infiniteodysseys.IOManager;
import org.junit.Test;
import view.IOManagerTextView;

public class HelpDiceTest extends IOManagerControllerTest {

  @Test
  public void helpDice() {
    Readable input = new StringReader("help-dice quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertEquals("set-seed (seed)\n"
            + "\tSets a seed to use for seeded-rolls.\n"
            + "\n"
            + "use-seed (y or n)\n"
            + "\tEnables or disables the seed for dice rolls. If a seed has NOT been set, the "
            + "command will request one.\n"
            + "\tThe command will ONLY accept y or n; y enables the seed, n disables the seed.\n"
            + "\tSeeds cannot be set for specific dice, they're either enabled for all or disabled for all.\n"
            + "\n"
            + "roll (upperBound)\n"
            + "\tReturns a random number between 1 and the given upper bound.\n"
            + "\tThe upper bound cannot be less than 2; if it is, the user will be informed.\n"
            + "\tThe number generator does NOT use a seed.\n"
            + "\n"
            + "d2 : Randomly picks 1 or 2.\n"
            + "\n"
            + "d4 : Rolls a random number between 1 and 4.\n"
            + "\n"
            + "d6 : Rolls a random number between 1 and 6.\n"
            + "\n"
            + "d8 : Rolls a random number between 1 and 8.\n"
            + "\n"
            + "d10 : Rolls a random number between 1 and 10.\n"
            + "\n"
            + "d12 : Rolls a random number between 1 and 12.\n"
            + "\n"
            + "d20 : Rolls a random number between 1 and 20.\n"
            + "\n"
            + "d100 : Rolls a random number between 1 and 100.\n"
            + "\n",
        output.toString().split("Awaiting command:\n")[1].split("WARNING")[0]);
  }

}