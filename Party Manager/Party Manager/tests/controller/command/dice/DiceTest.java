package controller.command.dice;

import static org.junit.Assert.*;

import controller.IOManagerController;
import controller.IOManagerControllerTest;
import controller.IOManagerSeedHolder;
import java.io.StringReader;
import model.infiniteodysseys.IOManager;
import org.junit.Test;
import view.IOManagerTextView;

public class DiceTest extends IOManagerControllerTest {
  @Test
  public void d2() {
    int n = 2;

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
      String out = output.toString()
          .split("Awaiting command:\n")[i + 1]
          .split("The roll is ")[1];
      out = out.replace("[", "");
      out = out.replace("]", "");
      out = out.trim();
      assertTrue(Integer.parseInt(out) > 0 && Integer.parseInt(out) <= n);
    }
  }

  @Test
  public void d2Seeded() {
    int n = 2;

    String rolls = "";
    for (int i = 0; i < 5; i++) {
      rolls = rolls.concat("d" + n + "\n");
    }

    Readable input = new StringReader("set-seed 1 use-seed y " + rolls + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);

    this.controller.start();

    for (int i = 0; i < 5; i++) {
      String out = output.toString()
          .split("Awaiting command:\n")[i + 3]
          .split("The roll is ")[1];
      out = out.replace("[", "");
      out = out.replace("]", "");
      out = out.trim();
      assertTrue(Integer.parseInt(out) > 0 && Integer.parseInt(out) <= n);
    }

    assertEquals(1, IOManagerSeedHolder.getInstance().getSeed());
    assertTrue(IOManagerSeedHolder.getInstance().isUsingSeed());
    assertEquals("Awaiting command:\n"
            + "The roll is [1]\n"
            + "Awaiting command:\n"
            + "The roll is [1]\n"
            + "Awaiting command:\n"
            + "The roll is [1]\n"
            + "Awaiting command:\n"
            + "The roll is [1]\n"
            + "Awaiting command:\n"
            + "The roll is [2]\n"
            + "Awaiting command:\n",
        output.toString().split("The Manager is now using the seed 1.\n")[1].split("WARNING")[0]);
  }

  @Test
  public void d2Seeded2() {
    int n = 2;

    String rolls = "";
    for (int i = 0; i < 5; i++) {
      rolls = rolls.concat("d" + n + "\n");
    }

    Readable input = new StringReader("set-seed 2 use-seed y " + rolls + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);

    this.controller.start();

    for (int i = 0; i < 5; i++) {
      String out = output.toString()
          .split("Awaiting command:\n")[i + 3]
          .split("The roll is ")[1];
      out = out.replace("[", "");
      out = out.replace("]", "");
      out = out.trim();
      assertTrue(Integer.parseInt(out) > 0 && Integer.parseInt(out) <= n);
    }

    assertEquals(2, IOManagerSeedHolder.getInstance().getSeed());
    assertTrue(IOManagerSeedHolder.getInstance().isUsingSeed());
    assertEquals("Awaiting command:\n"
            + "The roll is [1]\n"
            + "Awaiting command:\n"
            + "The roll is [1]\n"
            + "Awaiting command:\n"
            + "The roll is [2]\n"
            + "Awaiting command:\n"
            + "The roll is [1]\n"
            + "Awaiting command:\n"
            + "The roll is [1]\n"
            + "Awaiting command:\n",
        output.toString().split("The Manager is now using the seed 2.\n")[1].split("WARNING")[0]);
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
      String out = output.toString()
          .split("Awaiting command:\n")[i + 1]
          .split("The roll is ")[1];
      out = out.replace("[", "");
      out = out.replace("]", "");
      out = out.trim();
      assertTrue(Integer.parseInt(out) > 0 && Integer.parseInt(out) <= n);
    }
  }

  @Test
  public void d4Seeded() {
    int n = 4;

    String rolls = "";
    for (int i = 0; i < 5; i++) {
      rolls = rolls.concat("d" + n + "\n");
    }

    Readable input = new StringReader("set-seed 1 use-seed y " + rolls + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);

    this.controller.start();

    for (int i = 0; i < 5; i++) {
      String out = output.toString()
          .split("Awaiting command:\n")[i + 3]
          .split("The roll is ")[1];
      out = out.replace("[", "");
      out = out.replace("]", "");
      out = out.trim();
      assertTrue(Integer.parseInt(out) > 0 && Integer.parseInt(out) <= n);
    }

    assertEquals(1, IOManagerSeedHolder.getInstance().getSeed());
    assertTrue(IOManagerSeedHolder.getInstance().isUsingSeed());
    assertEquals("Awaiting command:\n"
            + "The roll is [1]\n"
            + "Awaiting command:\n"
            + "The roll is [3]\n"
            + "Awaiting command:\n"
            + "The roll is [2]\n"
            + "Awaiting command:\n"
            + "The roll is [3]\n"
            + "Awaiting command:\n"
            + "The roll is [4]\n"
            + "Awaiting command:\n",
        output.toString().split("The Manager is now using the seed 1.\n")[1].split("WARNING")[0]);
  }

  @Test
  public void d4Seeded2() {
    int n = 4;

    String rolls = "";
    for (int i = 0; i < 5; i++) {
      rolls = rolls.concat("d" + n + "\n");
    }

    Readable input = new StringReader("set-seed 2 use-seed y " + rolls + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);

    this.controller.start();

    for (int i = 0; i < 5; i++) {
      String out = output.toString()
          .split("Awaiting command:\n")[i + 3]
          .split("The roll is ")[1];
      out = out.replace("[", "");
      out = out.replace("]", "");
      out = out.trim();
      assertTrue(Integer.parseInt(out) > 0 && Integer.parseInt(out) <= n);
    }

    assertEquals(2, IOManagerSeedHolder.getInstance().getSeed());
    assertTrue(IOManagerSeedHolder.getInstance().isUsingSeed());
    assertEquals("Awaiting command:\n"
            + "The roll is [3]\n"
            + "Awaiting command:\n"
            + "The roll is [2]\n"
            + "Awaiting command:\n"
            + "The roll is [1]\n"
            + "Awaiting command:\n"
            + "The roll is [2]\n"
            + "Awaiting command:\n"
            + "The roll is [4]\n"
            + "Awaiting command:\n",
        output.toString().split("The Manager is now using the seed 2.\n")[1].split("WARNING")[0]);
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
      String out = output.toString()
          .split("Awaiting command:\n")[i + 1]
          .split("The roll is ")[1];
      out = out.replace("[", "");
      out = out.replace("]", "");
      out = out.trim();
      assertTrue(Integer.parseInt(out) > 0 && Integer.parseInt(out) <= n);
    }
  }

  @Test
  public void d6Seeded() {
    int n = 6;

    String rolls = "";
    for (int i = 0; i < 5; i++) {
      rolls = rolls.concat("d" + n + "\n");
    }

    Readable input = new StringReader("set-seed 1 use-seed y " + rolls + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);

    this.controller.start();

    for (int i = 0; i < 5; i++) {
      String out = output.toString()
          .split("Awaiting command:\n")[i + 3]
          .split("The roll is ")[1];
      out = out.replace("[", "");
      out = out.replace("]", "");
      out = out.trim();
      assertTrue(Integer.parseInt(out) > 0 && Integer.parseInt(out) <= n);
    }

    assertEquals(1, IOManagerSeedHolder.getInstance().getSeed());
    assertTrue(IOManagerSeedHolder.getInstance().isUsingSeed());
    assertEquals("Awaiting command:\n"
            + "The roll is [4]\n"
            + "Awaiting command:\n"
            + "The roll is [4]\n"
            + "Awaiting command:\n"
            + "The roll is [1]\n"
            + "Awaiting command:\n"
            + "The roll is [1]\n"
            + "Awaiting command:\n"
            + "The roll is [6]\n"
            + "Awaiting command:\n",
        output.toString().split("The Manager is now using the seed 1.\n")[1].split("WARNING")[0]);
  }

  @Test
  public void d6Seeded2() {
    int n = 6;

    String rolls = "";
    for (int i = 0; i < 5; i++) {
      rolls = rolls.concat("d" + n + "\n");
    }

    Readable input = new StringReader("set-seed 2 use-seed y " + rolls + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);

    this.controller.start();

    for (int i = 0; i < 5; i++) {
      String out = output.toString()
          .split("Awaiting command:\n")[i + 3]
          .split("The roll is ")[1];
      out = out.replace("[", "");
      out = out.replace("]", "");
      out = out.trim();
      assertTrue(Integer.parseInt(out) > 0 && Integer.parseInt(out) <= n);
    }

    assertEquals(2, IOManagerSeedHolder.getInstance().getSeed());
    assertTrue(IOManagerSeedHolder.getInstance().isUsingSeed());
    assertEquals("Awaiting command:\n"
            + "The roll is [5]\n"
            + "Awaiting command:\n"
            + "The roll is [1]\n"
            + "Awaiting command:\n"
            + "The roll is [4]\n"
            + "Awaiting command:\n"
            + "The roll is [4]\n"
            + "Awaiting command:\n"
            + "The roll is [5]\n"
            + "Awaiting command:\n",
        output.toString().split("The Manager is now using the seed 2.\n")[1].split("WARNING")[0]);
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
      String out = output.toString()
          .split("Awaiting command:\n")[i + 1]
          .split("The roll is ")[1];
      out = out.replace("[", "");
      out = out.replace("]", "");
      out = out.trim();
      assertTrue(Integer.parseInt(out) > 0 && Integer.parseInt(out) <= n);
    }
  }

  @Test
  public void d8Seeded() {
    int n = 8;

    String rolls = "";
    for (int i = 0; i < 5; i++) {
      rolls = rolls.concat("d" + n + "\n");
    }

    Readable input = new StringReader("set-seed 1 use-seed y " + rolls + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);

    this.controller.start();

    for (int i = 0; i < 5; i++) {
      String out = output.toString()
          .split("Awaiting command:\n")[i + 3]
          .split("The roll is ")[1];
      out = out.replace("[", "");
      out = out.replace("]", "");
      out = out.trim();
      assertTrue(Integer.parseInt(out) > 0 && Integer.parseInt(out) <= n);
    }

    assertEquals(1, IOManagerSeedHolder.getInstance().getSeed());
    assertTrue(IOManagerSeedHolder.getInstance().isUsingSeed());
    assertEquals("Awaiting command:\n"
            + "The roll is [6]\n"
            + "Awaiting command:\n"
            + "The roll is [1]\n"
            + "Awaiting command:\n"
            + "The roll is [1]\n"
            + "Awaiting command:\n"
            + "The roll is [6]\n"
            + "Awaiting command:\n"
            + "The roll is [8]\n"
            + "Awaiting command:\n",
        output.toString().split("The Manager is now using the seed 1.\n")[1].split("WARNING")[0]);
  }

  @Test
  public void d8Seeded2() {
    int n = 8;

    String rolls = "";
    for (int i = 0; i < 5; i++) {
      rolls = rolls.concat("d" + n + "\n");
    }

    Readable input = new StringReader("set-seed 2 use-seed y " + rolls + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);

    this.controller.start();

    for (int i = 0; i < 5; i++) {
      String out = output.toString()
          .split("Awaiting command:\n")[i + 3]
          .split("The roll is ")[1];
      out = out.replace("[", "");
      out = out.replace("]", "");
      out = out.trim();
      assertTrue(Integer.parseInt(out) > 0 && Integer.parseInt(out) <= n);
    }

    assertEquals(2, IOManagerSeedHolder.getInstance().getSeed());
    assertTrue(IOManagerSeedHolder.getInstance().isUsingSeed());
    assertEquals("Awaiting command:\n"
            + "The roll is [4]\n"
            + "Awaiting command:\n"
            + "The roll is [6]\n"
            + "Awaiting command:\n"
            + "The roll is [2]\n"
            + "Awaiting command:\n"
            + "The roll is [1]\n"
            + "Awaiting command:\n"
            + "The roll is [6]\n"
            + "Awaiting command:\n",
        output.toString().split("The Manager is now using the seed 2.\n")[1].split("WARNING")[0]);
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
      String out = output.toString()
          .split("Awaiting command:\n")[i + 1]
          .split("The roll is ")[1];
      out = out.replace("[", "");
      out = out.replace("]", "");
      out = out.trim();
      assertTrue(Integer.parseInt(out) > 0 && Integer.parseInt(out) <= n);
    }
  }

  @Test
  public void d10Seeded() {
    int n = 10;

    String rolls = "";
    for (int i = 0; i < 5; i++) {
      rolls = rolls.concat("d" + n + "\n");
    }

    Readable input = new StringReader("set-seed 1 use-seed y " + rolls + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);

    this.controller.start();

    for (int i = 0; i < 5; i++) {
      String out = output.toString()
          .split("Awaiting command:\n")[i + 3]
          .split("The roll is ")[1];
      out = out.replace("[", "");
      out = out.replace("]", "");
      out = out.trim();
      assertTrue(Integer.parseInt(out) > 0 && Integer.parseInt(out) <= n);
    }

    assertEquals(1, IOManagerSeedHolder.getInstance().getSeed());
    assertTrue(IOManagerSeedHolder.getInstance().isUsingSeed());
    assertEquals("Awaiting command:\n"
            + "The roll is [4]\n"
            + "Awaiting command:\n"
            + "The roll is [6]\n"
            + "Awaiting command:\n"
            + "The roll is [8]\n"
            + "Awaiting command:\n"
            + "The roll is [8]\n"
            + "Awaiting command:\n"
            + "The roll is [9]\n"
            + "Awaiting command:\n",
        output.toString().split("The Manager is now using the seed 1.\n")[1].split("WARNING")[0]);
  }

  @Test
  public void d10Seeded2() {
    int n = 10;

    String rolls = "";
    for (int i = 0; i < 5; i++) {
      rolls = rolls.concat("d" + n + "\n");
    }

    Readable input = new StringReader("set-seed 2 use-seed y " + rolls + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);

    this.controller.start();

    for (int i = 0; i < 5; i++) {
      String out = output.toString()
          .split("Awaiting command:\n")[i + 3]
          .split("The roll is ")[1];
      out = out.replace("[", "");
      out = out.replace("]", "");
      out = out.trim();
      assertTrue(Integer.parseInt(out) > 0 && Integer.parseInt(out) <= n);
    }

    assertEquals(2, IOManagerSeedHolder.getInstance().getSeed());
    assertTrue(IOManagerSeedHolder.getInstance().isUsingSeed());
    assertEquals("Awaiting command:\n"
            + "The roll is [1]\n"
            + "Awaiting command:\n"
            + "The roll is [2]\n"
            + "Awaiting command:\n"
            + "The roll is [9]\n"
            + "Awaiting command:\n"
            + "The roll is [8]\n"
            + "Awaiting command:\n"
            + "The roll is [9]\n"
            + "Awaiting command:\n",
        output.toString().split("The Manager is now using the seed 2.\n")[1].split("WARNING")[0]);
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
      String out = output.toString()
          .split("Awaiting command:\n")[i + 1]
          .split("The roll is ")[1];
      out = out.replace("[", "");
      out = out.replace("]", "");
      out = out.trim();
      assertTrue(Integer.parseInt(out) > 0 && Integer.parseInt(out) <= n);
    }
  }

  @Test
  public void d12Seeded() {
    int n = 12;

    String rolls = "";
    for (int i = 0; i < 5; i++) {
      rolls = rolls.concat("d" + n + "\n");
    }

    Readable input = new StringReader("set-seed 1 use-seed y " + rolls + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);

    this.controller.start();

    for (int i = 0; i < 5; i++) {
      String out = output.toString()
          .split("Awaiting command:\n")[i + 3]
          .split("The roll is ")[1];
      out = out.replace("[", "");
      out = out.replace("]", "");
      out = out.trim();
      assertTrue(Integer.parseInt(out) > 0 && Integer.parseInt(out) <= n);
    }

    assertEquals(1, IOManagerSeedHolder.getInstance().getSeed());
    assertTrue(IOManagerSeedHolder.getInstance().isUsingSeed());
    assertEquals("Awaiting command:\n"
            + "The roll is [4]\n"
            + "Awaiting command:\n"
            + "The roll is [1]\n"
            + "Awaiting command:\n"
            + "The roll is [7]\n"
            + "Awaiting command:\n"
            + "The roll is [9]\n"
            + "Awaiting command:\n"
            + "The roll is [12]\n"
            + "Awaiting command:\n",
        output.toString().split("The Manager is now using the seed 1.\n")[1].split("WARNING")[0]);
  }

  @Test
  public void d12Seeded2() {
    int n = 12;

    String rolls = "";
    for (int i = 0; i < 5; i++) {
      rolls = rolls.concat("d" + n + "\n");
    }

    Readable input = new StringReader("set-seed 2 use-seed y " + rolls + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);

    this.controller.start();

    for (int i = 0; i < 5; i++) {
      String out = output.toString()
          .split("Awaiting command:\n")[i + 3]
          .split("The roll is ")[1];
      out = out.replace("[", "");
      out = out.replace("]", "");
      out = out.trim();
      assertTrue(Integer.parseInt(out) > 0 && Integer.parseInt(out) <= n);
    }

    assertEquals(2, IOManagerSeedHolder.getInstance().getSeed());
    assertTrue(IOManagerSeedHolder.getInstance().isUsingSeed());
    assertEquals("Awaiting command:\n"
            + "The roll is [5]\n"
            + "Awaiting command:\n"
            + "The roll is [6]\n"
            + "Awaiting command:\n"
            + "The roll is [1]\n"
            + "Awaiting command:\n"
            + "The roll is [8]\n"
            + "Awaiting command:\n"
            + "The roll is [4]\n"
            + "Awaiting command:\n",
        output.toString().split("The Manager is now using the seed 2.\n")[1].split("WARNING")[0]);
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
      String out = output.toString()
          .split("Awaiting command:\n")[i + 1]
          .split("The roll is ")[1];
      out = out.replace("[", "");
      out = out.replace("]", "");
      out = out.trim();
      assertTrue(Integer.parseInt(out) > 0 && Integer.parseInt(out) <= n);
    }
  }

  @Test
  public void d20Seeded() {
    int n = 20;

    String rolls = "";
    for (int i = 0; i < 5; i++) {
      rolls = rolls.concat("d" + n + "\n");
    }

    Readable input = new StringReader("set-seed 1 use-seed y " + rolls + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);

    this.controller.start();

    for (int i = 0; i < 5; i++) {
      String out = output.toString()
          .split("Awaiting command:\n")[i + 3]
          .split("The roll is ")[1];
      out = out.replace("[", "");
      out = out.replace("]", "");
      out = out.trim();
      assertTrue(Integer.parseInt(out) > 0 && Integer.parseInt(out) <= n);
    }

    assertEquals(1, IOManagerSeedHolder.getInstance().getSeed());
    assertTrue(IOManagerSeedHolder.getInstance().isUsingSeed());
    assertEquals("Awaiting command:\n"
            + "The roll is [18]\n"
            + "Awaiting command:\n"
            + "The roll is [4]\n"
            + "Awaiting command:\n"
            + "The roll is [1]\n"
            + "Awaiting command:\n"
            + "The roll is [1]\n"
            + "Awaiting command:\n"
            + "The roll is [20]\n"
            + "Awaiting command:\n",
        output.toString().split("The Manager is now using the seed 1.\n")[1].split("WARNING")[0]);
  }

  @Test
  public void d20Seeded2() {
    int n = 20;

    String rolls = "";
    for (int i = 0; i < 5; i++) {
      rolls = rolls.concat("d" + n + "\n");
    }

    Readable input = new StringReader("set-seed 2 use-seed y " + rolls + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);

    this.controller.start();

    for (int i = 0; i < 5; i++) {
      String out = output.toString()
          .split("Awaiting command:\n")[i + 3]
          .split("The roll is ")[1];
      out = out.replace("[", "");
      out = out.replace("]", "");
      out = out.trim();
      assertTrue(Integer.parseInt(out) > 0 && Integer.parseInt(out) <= n);
    }

    assertEquals(2, IOManagerSeedHolder.getInstance().getSeed());
    assertTrue(IOManagerSeedHolder.getInstance().isUsingSeed());
    assertEquals("Awaiting command:\n"
            + "The roll is [19]\n"
            + "Awaiting command:\n"
            + "The roll is [15]\n"
            + "Awaiting command:\n"
            + "The roll is [11]\n"
            + "Awaiting command:\n"
            + "The roll is [4]\n"
            + "Awaiting command:\n"
            + "The roll is [12]\n"
            + "Awaiting command:\n",
        output.toString().split("The Manager is now using the seed 2.\n")[1].split("WARNING")[0]);
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
      String out = output.toString()
          .split("Awaiting command:\n")[i + 1]
          .split("The roll is ")[1];
      out = out.replace("[", "");
      out = out.replace("]", "");
      out = out.trim();
      assertTrue(Integer.parseInt(out) > 0 && Integer.parseInt(out) <= n);
    }
  }

  @Test
  public void d100Seeded() {
    int n = 100;

    String rolls = "";
    for (int i = 0; i < 5; i++) {
      rolls = rolls.concat("d" + n + "\n");
    }

    Readable input = new StringReader("set-seed 1 use-seed y " + rolls + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);

    this.controller.start();

    for (int i = 0; i < 5; i++) {
      String out = output.toString()
          .split("Awaiting command:\n")[i + 3]
          .split("The roll is ")[1];
      out = out.replace("[", "");
      out = out.replace("]", "");
      out = out.trim();
      assertTrue(Integer.parseInt(out) > 0 && Integer.parseInt(out) <= n);
    }

    assertEquals(1, IOManagerSeedHolder.getInstance().getSeed());
    assertTrue(IOManagerSeedHolder.getInstance().isUsingSeed());
    assertEquals("Awaiting command:\n"
            + "The roll is [97]\n"
            + "Awaiting command:\n"
            + "The roll is [5]\n"
            + "Awaiting command:\n"
            + "The roll is [21]\n"
            + "Awaiting command:\n"
            + "The roll is [41]\n"
            + "Awaiting command:\n"
            + "The roll is [77]\n"
            + "Awaiting command:\n",
        output.toString().split("The Manager is now using the seed 1.\n")[1].split("WARNING")[0]);
  }

  @Test
  public void d100Seeded2() {
    int n = 100;

    String rolls = "";
    for (int i = 0; i < 5; i++) {
      rolls = rolls.concat("d" + n + "\n");
    }

    Readable input = new StringReader("set-seed 2 use-seed y " + rolls + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);

    this.controller.start();

    for (int i = 0; i < 5; i++) {
      String out = output.toString()
          .split("Awaiting command:\n")[i + 3]
          .split("The roll is ")[1];
      out = out.replace("[", "");
      out = out.replace("]", "");
      out = out.trim();
      assertTrue(Integer.parseInt(out) > 0 && Integer.parseInt(out) <= n);
    }

    assertEquals(2, IOManagerSeedHolder.getInstance().getSeed());
    assertTrue(IOManagerSeedHolder.getInstance().isUsingSeed());
    assertEquals("Awaiting command:\n"
            + "The roll is [5]\n"
            + "Awaiting command:\n"
            + "The roll is [37]\n"
            + "Awaiting command:\n"
            + "The roll is [68]\n"
            + "Awaiting command:\n"
            + "The roll is [1]\n"
            + "Awaiting command:\n"
            + "The roll is [41]\n"
            + "Awaiting command:\n",
        output.toString().split("The Manager is now using the seed 2.\n")[1].split("WARNING")[0]);
  }
}