package controller.command.dice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import controller.Controller;
import controller.IOManagerController;
import controller.IOManagerControllerTest;
import controller.command.character.CreateChar;
import java.io.StringReader;
import model.infiniteodysseys.IOManager;
import org.junit.Test;
import view.IOManagerTextView;

public class RollTest extends IOManagerControllerTest {

  @Test
  public void getSignature() {
    this.model = new IOManager();
    assertEquals("roll (upperBound)",
        new Roll(this.model, null, null).getSignature());
  }

  @Test
  public void getDescription() {
    this.model = new IOManager();
    assertEquals("Returns a random number between 1 and the given upper bound.\n"
            + "The upper bound cannot be less than 2.",
        new Roll(this.model, null, null).getDescription());
  }

  @Test
  public void roll20() {
    String rolls = "";
    for (int i = 0; i < 1000; i++) {
      rolls = rolls.concat("roll 20\n");
    }

    Readable input = new StringReader(rolls
        + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);

    this.controller.start();

    for (int i = 0; i < 1000; i++) {
      String out = output.toString().split(Controller.separator)[i + 1]
          .split("The roll is ")[1];
      out = out.replace("[", "");
      out = out.replace("]", "");
      out = out.trim();
      assertTrue(Integer.parseInt(out) > 0 && Integer.parseInt(out) <= 20);
    }
  }

  @Test
  public void roll2() {
    int n = 2;

    String rolls = "";
    for (int i = 0; i < 1000; i++) {
      rolls = rolls.concat("roll " + n + "\n");
    }

    Readable input = new StringReader(rolls + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);

    this.controller.start();

    for (int i = 0; i < 1000; i++) {
      String out = output.toString().split(Controller.separator)[i + 1]
          .split("The roll is ")[1];
      out = out.replace("[", "");
      out = out.replace("]", "");
      out = out.trim();
      assertTrue(Integer.parseInt(out) > 0 && Integer.parseInt(out) <= n);
    }
  }

  @Test
  public void roll4() {
    int n = 4;

    String rolls = "";
    for (int i = 0; i < 1000; i++) {
      rolls = rolls.concat("roll " + n + "\n");
    }

    Readable input = new StringReader(rolls + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);

    this.controller.start();

    for (int i = 0; i < 1000; i++) {
      String out = output.toString().split(Controller.separator)[i + 1]
          .split("The roll is ")[1];
      out = out.replace("[", "");
      out = out.replace("]", "");
      out = out.trim();
      assertTrue(Integer.parseInt(out) > 0 && Integer.parseInt(out) <= n);
    }
  }

  @Test
  public void roll6() {
    int n = 6;

    String rolls = "";
    for (int i = 0; i < 1000; i++) {
      rolls = rolls.concat("roll " + n + "\n");
    }

    Readable input = new StringReader(rolls + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);

    this.controller.start();

    for (int i = 0; i < 1000; i++) {
      String out = output.toString().split(Controller.separator)[i + 1]
          .split("The roll is ")[1];
      out = out.replace("[", "");
      out = out.replace("]", "");
      out = out.trim();
      assertTrue(Integer.parseInt(out) > 0 && Integer.parseInt(out) <= n);
    }
  }

  @Test
  public void roll100() {
    int n = 100;

    String rolls = "";
    for (int i = 0; i < 1000; i++) {
      rolls = rolls.concat("roll " + n + "\n");
    }

    Readable input = new StringReader(rolls + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);

    this.controller.start();

    for (int i = 0; i < 1000; i++) {
      String out = output.toString().split(Controller.separator)[i + 1]
          .split("The roll is ")[1];
      out = out.replace("[", "");
      out = out.replace("]", "");
      out = out.trim();
      assertTrue(Integer.parseInt(out) > 0 && Integer.parseInt(out) <= n);
    }
  }

  @Test
  public void roll1000() {
    int n = 1000;

    String rolls = "";
    for (int i = 0; i < 1000; i++) {
      rolls = rolls.concat("roll " + n + "\n");
    }

    Readable input = new StringReader(rolls + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);

    this.controller.start();

    for (int i = 0; i < 1000; i++) {
      String out = output.toString().split(Controller.separator)[i + 1]
          .split("The roll is ")[1];
      out = out.replace("[", "");
      out = out.replace("]", "");
      out = out.trim();
      assertTrue(Integer.parseInt(out) > 0 && Integer.parseInt(out) <= n);
    }
  }
}