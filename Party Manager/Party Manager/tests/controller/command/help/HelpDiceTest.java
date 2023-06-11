package controller.command.help;

import static org.junit.Assert.assertEquals;

import controller.IOManagerController;
import controller.IOManagerControllerTest;
import controller.command.dice.Dice;
import controller.command.dice.Roll;
import java.io.StringReader;
import model.infiniteodysseys.IOManager;
import org.junit.Test;
import view.IOManagerTextView;

public class HelpDiceTest extends IOManagerControllerTest {

  @Test
  public void getSignature() {
    this.model = new IOManager();
    assertEquals("cf",
        new Dice(this.model, null, 2).getSignature());
    assertEquals("d20",
        new Dice(this.model, null, 20).getSignature());
    assertEquals("d50",
        new Dice(this.model, null, 50).getSignature());
  }

  @Test
  public void getDescription() {
    this.model = new IOManager();
    assertEquals("Flips a coin.",
        new Dice(this.model, null, 2).getDescription());
    assertEquals("Rolls a random number between 1 and 20.",
        new Dice(this.model, null, 20).getDescription());
    assertEquals("Rolls a random number between 1 and 50.",
        new Dice(this.model, null, 50).getDescription());
  }

  @Test
  public void helpDice() {
    Readable input = new StringReader("help-dice quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertEquals("roll (upperBound): \n"
            + "\tReturns a random number between 1 and the given upper bound.\n"
            + "\tThe upper bound cannot be less than 2.\n"
            + "\n"
            + "cf: Flips a coin.\n"
            + "\n"
            + "d4: Rolls a random number between 1 and 4.\n"
            + "\n"
            + "d6: Rolls a random number between 1 and 6.\n"
            + "\n"
            + "d8: Rolls a random number between 1 and 8.\n"
            + "\n"
            + "d10: Rolls a random number between 1 and 10.\n"
            + "\n"
            + "d12: Rolls a random number between 1 and 12.\n"
            + "\n"
            + "d20: Rolls a random number between 1 and 20.\n"
            + "\n"
            + "d100: Rolls a random number between 1 and 100.\n"
            + "\n",
        output.toString().split("Awaiting command:\n")[1].split("WARNING")[0]);
  }

}