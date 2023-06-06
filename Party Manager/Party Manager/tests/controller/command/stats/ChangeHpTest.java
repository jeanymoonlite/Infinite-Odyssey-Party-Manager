package controller.command.stats;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import controller.IOManagerController;
import controller.IOManagerControllerTest;
import java.io.StringReader;
import model.infiniteodysseys.IOManager;
import org.junit.Test;
import view.IOManagerTextView;

public class ChangeHpTest extends IOManagerControllerTest {

  @Test
  public void successfulDamage() {
    Readable input = new StringReader("create-char\n"
        + "Onion\n"
        + "Steven\n"
        + "Human\n"
        + "n/a\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "y\n"
        + "create-char\n"
        + "Jake Walker\n"
        + "Jake\n"
        + "Warrior\n"
        + "Soldier\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "y\n"
        + "create-char\n"
        + "Steven Universe\n"
        + "Steven\n"
        + "Bard\n"
        + "Musician\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "y\n"
        + "create-char\n"
        + "Randy\n"
        + "Randy\n"
        + "Rogue\n"
        + "Ninja\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "y\n"
        + "damage 5 Onion\n"
        + "damage 5 Steven Universe\n"
        + "damage 5 Jake Walker\n"
        + "damage 5 Randy\n"
        + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertTrue(this.model.doesCharacterExist("Onion"));
    assertTrue(this.model.doesCharacterExist("Steven Universe"));
    assertTrue(this.model.doesCharacterExist("Jake Walker"));
    assertTrue(this.model.doesCharacterExist("Randy"));
    assertEquals("Onion lost 5 hp.\n"
            + "Onion (Steven) Hp: 95/100\n",
        output.toString()
            .split("WARNING")[0]
            .split("Awaiting command:\n")[5]);

    assertEquals("Steven Universe lost 4 hp.\n"
            + "Steven Universe (Steven) Hp: 96/100\n",
        output.toString()
            .split("WARNING")[0]
            .split("Awaiting command:\n")[6]);

    assertEquals("Jake Walker lost 1 hp.\n"
            + "Jake Walker (Jake) Hp: 99/100\n",
        output.toString()
            .split("WARNING")[0]
            .split("Awaiting command:\n")[7]);

    assertEquals("Randy lost 10 hp.\n"
            + "Randy (Randy) Hp: 90/100\n",
        output.toString()
            .split("WARNING")[0]
            .split("Awaiting command:\n")[8]);
  }

  @Test
  public void successfulHeal() {
    Readable input = new StringReader("create-char\n"
        + "Onion\n"
        + "Steven\n"
        + "Human\n"
        + "n/a\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "y\n"
        + "create-char\n"
        + "Jake Walker\n"
        + "Jake\n"
        + "Warrior\n"
        + "Soldier\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "y\n"
        + "create-char\n"
        + "Steven Universe\n"
        + "Steven\n"
        + "Bard\n"
        + "Musician\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "y\n"
        + "create-char\n"
        + "Randy\n"
        + "Randy\n"
        + "Rogue\n"
        + "Ninja\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "y\n"
        + "damage 20 Onion\n"
        + "damage 20 Steven Universe\n"
        + "damage 20 Jake Walker\n"
        + "damage 20 Randy\n"
        + "heal 10 Onion\n"
        + "heal 10 Steven Universe\n"
        + "heal 5 Jake Walker\n"
        + "heal 15 Randy\n"
        + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertTrue(this.model.doesCharacterExist("Onion"));
    assertTrue(this.model.doesCharacterExist("Steven Universe"));
    assertTrue(this.model.doesCharacterExist("Jake Walker"));
    assertTrue(this.model.doesCharacterExist("Randy"));
    assertEquals("Onion had 10 hp restored.\n"
            + "Onion (Steven) Hp: 90/100\n",
        output.toString()
            .split("WARNING")[0]
            .split("Awaiting command:\n")[9]);

    assertEquals("Steven Universe had 10 hp restored.\n"
            + "Steven Universe (Steven) Hp: 91/100\n",
        output.toString()
            .split("WARNING")[0]
            .split("Awaiting command:\n")[10]);

    assertEquals("Jake Walker had 5 hp restored.\n"
            + "Jake Walker (Jake) Hp: 90/100\n",
        output.toString()
            .split("WARNING")[0]
            .split("Awaiting command:\n")[11]);

    assertEquals("Randy had 15 hp restored.\n"
            + "Randy (Randy) Hp: 90/100\n",
        output.toString()
            .split("WARNING")[0]
            .split("Awaiting command:\n")[12]);
  }

  @Test
  public void amount0() {
    Readable input = new StringReader("create-char\n"
        + "Onion\n"
        + "Steven\n"
        + "Human\n"
        + "n/a\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "y\n"
        + "damage 0 Onion\n"
        + "heal 0 Onion\n"
        + "damage-all 0 Onion\n"
        + "heal-all 0 Onion\n"
        + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertTrue(this.model.doesCharacterExist("Onion"));
    assertEquals("Invalid input: Amount cannot be less than 1.\n",
        output.toString()
            .split("WARNING")[0]
            .split("Awaiting command:\n")[2]);

    assertEquals("Invalid input: Amount cannot be less than 1.\n",
        output.toString()
            .split("WARNING")[0]
            .split("Awaiting command:\n")[3]);

    assertEquals("Invalid input: Amount cannot be less than 1.\n",
        output.toString()
            .split("WARNING")[0]
            .split("Awaiting command:\n")[4]);

    assertEquals("Invalid input: Amount cannot be less than 1.\n",
        output.toString()
            .split("WARNING")[0]
            .split("Awaiting command:\n")[5]);
  }

  @Test
  public void noCharsHealAndDamage() {
    Readable input = new StringReader("damage 20 Luna\n"
        + "heal 10 Luna\n"
        + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertFalse(this.model.doesCharacterExist("Onion"));
    assertEquals("The Manager doesn't have any Characters!\n"
            + "Add Characters using the create-char command.\n",
        output.toString()
            .split("WARNING")[0]
            .split("Awaiting command:\n")[1]);

    assertEquals("The Manager doesn't have any Characters!\n"
            + "Add Characters using the create-char command.\n",
        output.toString()
            .split("WARNING")[0]
            .split("Awaiting command:\n")[2]);
  }

  @Test
  public void nonExistHealAndDamage() {
    Readable input = new StringReader("create-char\n"
        + "Onion\n"
        + "Steven\n"
        + "Human\n"
        + "n/a\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "y\n"
        + "damage 20 Luna\n"
        + "heal 10 Luna\n"
        + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertTrue(this.model.doesCharacterExist("Onion"));
    assertEquals("The Character Luna doesn't exist in this Manager.\n",
        output.toString()
            .split("WARNING")[0]
            .split("Awaiting command:\n")[2]);

    assertEquals("The Character Luna doesn't exist in this Manager.\n",
        output.toString()
            .split("WARNING")[0]
            .split("Awaiting command:\n")[3]);
  }

  @Test
  public void decimalHealAndDamage() {
    Readable input = new StringReader("create-char\n"
        + "Onion\n"
        + "Steven\n"
        + "Human\n"
        + "n/a\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "y\n"
        + "damage 20.5 Onion\n"
        + "heal 10.223 Onion\n"
        + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertTrue(this.model.doesCharacterExist("Onion"));
    assertEquals("Invalid input: Amount cannot be a decimal.\n",
        output.toString()
            .split("WARNING")[0]
            .split("Awaiting command:\n")[2]);

    assertEquals("Invalid input: Amount cannot be a decimal.\n",
        output.toString()
            .split("WARNING")[0]
            .split("Awaiting command:\n")[3]);
  }

  @Test
  public void decimalHealAndDamage2() {
    Readable input = new StringReader("create-char\n"
        + "Onion\n"
        + "Steven\n"
        + "Human\n"
        + "n/a\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "y\n"
        + "damage 20.5\n"
        + "heal 10.223\n"
        + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertTrue(this.model.doesCharacterExist("Onion"));
    assertEquals("Invalid input: Amount cannot be a decimal.\n",
        output.toString()
            .split("WARNING")[0]
            .split("Awaiting command:\n")[2]);

    assertEquals("Invalid input: Amount cannot be a decimal.\n",
        output.toString()
            .split("WARNING")[0]
            .split("Awaiting command:\n")[3]);
  }
}