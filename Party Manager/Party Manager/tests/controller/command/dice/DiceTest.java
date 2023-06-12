package controller.command.dice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import controller.Controller;
import controller.IOManagerController;
import controller.IOManagerControllerTest;
import controller.IOManagerSeedHolder;
import java.io.StringReader;
import model.infiniteodysseys.IOManager;
import org.junit.Test;
import view.IOManagerTextView;

public class DiceTest extends IOManagerControllerTest {

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
  public void cf() {
    String rolls = "";
    for (int i = 0; i < 1000; i++) {
      rolls = rolls.concat("cf\n");
    }

    Readable input = new StringReader(rolls + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);

    this.controller.start();

    for (int i = 0; i < 1000; i++) {
      String out = output.toString().split(Controller.separator)[i + 1]
          .split("The coin landed on ")[1];
      assertTrue(out.equalsIgnoreCase("Heads.\n")
          || out.equalsIgnoreCase("Tails.\n"));
    }
  }

  @Test
  public void d4() {
    int n = 4;

    String rolls = "";
    for (int i = 0; i < 1000; i++) {
      rolls = rolls.concat("d" + n + "\n");
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
  public void d6() {
    int n = 6;

    String rolls = "";
    for (int i = 0; i < 1000; i++) {
      rolls = rolls.concat("d" + n + "\n");
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
  public void d8() {
    int n = 8;

    String rolls = "";
    for (int i = 0; i < 1000; i++) {
      rolls = rolls.concat("d" + n + "\n");
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
  public void d10() {
    int n = 10;

    String rolls = "";
    for (int i = 0; i < 1000; i++) {
      rolls = rolls.concat("d" + n + "\n");
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
  public void d12() {
    int n = 12;

    String rolls = "";
    for (int i = 0; i < 1000; i++) {
      rolls = rolls.concat("d" + n + "\n");
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
  public void d20() {
    int n = 20;

    String rolls = "";
    for (int i = 0; i < 1000; i++) {
      rolls = rolls.concat("d" + n + "\n");
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
  public void d100() {
    int n = 100;

    String rolls = "";
    for (int i = 0; i < 1000; i++) {
      rolls = rolls.concat("d" + n + "\n");
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