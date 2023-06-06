package controller.command.dice;

import static org.junit.Assert.*;

import controller.IOManagerController;
import controller.IOManagerControllerTest;
import controller.IOManagerSeedHolder;
import java.io.StringReader;
import model.infiniteodysseys.IOManager;
import org.junit.Test;
import view.IOManagerTextView;

public class RollTest extends IOManagerControllerTest {

  @Test
  public void roll() {
    Readable input = new StringReader("roll 20\n"
        + "roll 20\n"
        + "roll 20\n"
        + "roll 20\n"
        + "roll 20\n"
        + "roll 20\n"
        + "roll 20\n"
        + "roll 20\n"
        + "roll 20\n"
        + "roll 20\n"
        + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);

    this.controller.start();

    for (int i = 0; i < 10; i++) {
      String out = output.toString().split("The roll is ")[i + 1].split("\n")[i];
      assertTrue(Integer.parseInt(out) > 0 && Integer.parseInt(out) <= 20);
    }
  }
}