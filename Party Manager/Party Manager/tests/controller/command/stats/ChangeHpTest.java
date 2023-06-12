package controller.command.stats;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import controller.Controller;
import controller.IOManagerController;
import controller.IOManagerControllerTest;
import controller.command.manager.PartyCommand;
import java.io.StringReader;
import java.util.NoSuchElementException;
import model.infiniteodysseys.IOManager;
import org.junit.Test;
import view.IOManagerTextView;

public class ChangeHpTest extends IOManagerControllerTest {

  @Test
  public void getSignature() {
    assertEquals("heal (amount name)",
        new ChangeHp(null, null, null, true, false).getSignature());
    assertEquals("damage (amount name)",
        new ChangeHp(null, null, null, false, false).getSignature());
    assertEquals("heal-all (amount)",
        new ChangeHp(null, null, null, true, true).getSignature());
    assertEquals("damage-all (amount)",
        new ChangeHp(null, null, null, false, true).getSignature());
  }

  @Test
  public void getDescription() {
    assertEquals("Adds the amount to the character's hp with the given name."
            + "\nThis command rejects any non-integers/negative numbers.",
        new ChangeHp(null, null, null, true, false).getDescription());
    assertEquals("Subtracts the amount to the character's hp with the given name."
            + "\nThis command rejects any non-integers/negative numbers.",
        new ChangeHp(null, null, null, false, false).getDescription());
    assertEquals("Adds the amount to every character's hp in the active party."
            + "\nThis command rejects any non-integers/negative numbers.",
        new ChangeHp(null, null, null, true, true).getDescription());
    assertEquals("Subtracts the amount to every character's hp in the active party."
            + "\nThis command rejects any non-integers/negative numbers.",
        new ChangeHp(null, null, null, false, true).getDescription());
  }

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
    assertEquals("Awaiting command: Onion lost 5 hp.\n"
            + "Onion (Steven) Hp: 95/100\n",
        output.toString().split(Controller.separator)[5]);

    assertEquals("Awaiting command: Steven Universe lost 4 hp.\n"
            + "Steven Universe (Steven) Hp: 96/100\n",
        output.toString().split(Controller.separator)[6]);

    assertEquals("Awaiting command: Jake Walker lost 1 hp.\n"
            + "Jake Walker (Jake) Hp: 99/100\n",
        output.toString().split(Controller.separator)[7]);

    assertEquals("Awaiting command: Randy lost 10 hp.\n"
            + "Randy (Randy) Hp: 90/100\n",
        output.toString().split(Controller.separator)[8]);
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
    assertEquals("Awaiting command: Onion had 10 hp restored.\n"
            + "Onion (Steven) Hp: 90/100\n",
        output.toString().split(Controller.separator)[9]);

    assertEquals("Awaiting command: Steven Universe had 10 hp restored.\n"
            + "Steven Universe (Steven) Hp: 91/100\n",
        output.toString().split(Controller.separator)[10]);

    assertEquals("Awaiting command: Jake Walker had 5 hp restored.\n"
            + "Jake Walker (Jake) Hp: 90/100\n",
        output.toString().split(Controller.separator)[11]);

    assertEquals("Awaiting command: Randy had 15 hp restored.\n"
            + "Randy (Randy) Hp: 90/100\n",
        output.toString().split(Controller.separator)[12]);
  }

  @Test
  public void successfulHealAll() {
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
        + "create-party\n"
        + "The Boys\n"
        + "Onion, Steven Universe, Jake Walker, Randy\n"
        + "y\n"
        + "start The Boys\n"
        + "y\n"
        + "damage 20 Onion\n"
        + "damage 20 Steven Universe\n"
        + "damage 20 Jake Walker\n"
        + "damage 20 Randy\n"
        + "heal-all 100\n"
    );
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    try {
      this.controller.start();
      fail();
    }
    catch (NoSuchElementException | IllegalStateException e) {
      assertTrue(this.model.doesCharacterExist("Onion"));
      assertTrue(this.model.doesCharacterExist("Steven Universe"));
      assertTrue(this.model.doesCharacterExist("Jake Walker"));
      assertTrue(this.model.doesCharacterExist("Randy"));
      assertTrue(this.model.doesPartyExist("The Boys"));

      assertEquals("Awaiting command: Onion had 100 hp restored.\n"
              + "Onion (Steven) Hp: 100/100\n"
              + "Steven Universe had 100 hp restored.\n"
              + "Steven Universe (Steven) Hp: 100/100\n"
              + "Jake Walker had 100 hp restored.\n"
              + "Jake Walker (Jake) Hp: 100/100\n"
              + "Randy had 100 hp restored.\n"
              + "Randy (Randy) Hp: 100/100\n",
          output.toString().split(Controller.separator)[11]);
    }
  }

  @Test
  public void successfulDamageAll() {
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
        + "create-party\n"
        + "The Boys\n"
        + "Onion, Steven Universe, Jake Walker, Randy\n"
        + "y\n"
        + "start The Boys\n"
        + "y\n"
        + "damage-all 100\n"
        + "heal-all 1\n"
    );
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    try {
      this.controller.start();
      fail();
    }
    catch (NoSuchElementException | IllegalStateException e) {
      assertTrue(this.model.doesCharacterExist("Onion"));
      assertTrue(this.model.doesCharacterExist("Steven Universe"));
      assertTrue(this.model.doesCharacterExist("Jake Walker"));
      assertTrue(this.model.doesCharacterExist("Randy"));
      assertTrue(this.model.doesPartyExist("The Boys"));

      assertEquals("Awaiting command: Onion lost 100 hp.\n"
              + "Onion (Steven) Hp: 0/100\n"
              + "Steven Universe lost 99 hp.\n"
              + "Steven Universe (Steven) Hp: 1/100\n"
              + "Jake Walker lost 95 hp.\n"
              + "Jake Walker (Jake) Hp: 5/100\n"
              + "Randy lost 100 hp.\n"
              + "Randy (Randy) Hp: 0/100\n",
          output.toString().split(Controller.separator)[7]);
    }
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
    assertEquals("Awaiting command: Invalid input: Amount cannot be less than 1.\n",
        output.toString().split(Controller.separator)[2]);

    assertEquals("Awaiting command: Invalid input: Amount cannot be less than 1.\n",
        output.toString().split(Controller.separator)[3]);

    assertEquals("Awaiting command: Invalid input: Amount cannot be less than 1.\n",
        output.toString().split(Controller.separator)[4]);

    assertEquals("Awaiting command: Invalid input: Amount cannot be less than 1.\n",
        output.toString().split(Controller.separator)[5]);
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
    assertEquals("Awaiting command: \nInvalid state: The Manager doesn't have any Characters!\n"
            + "Add Characters using the create-char command.\n",
        output.toString().split(Controller.separator)[1]);

    assertEquals("Awaiting command: \nInvalid state: The Manager doesn't have any Characters!\n"
            + "Add Characters using the create-char command.\n",
        output.toString().split(Controller.separator)[2]);
  }

  @Test
  public void noStartHealAndDamageAll() {
    Readable input = new StringReader("damage-all 20\n"
        + "heal-all 10\n"
        + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertFalse(this.model.doesCharacterExist("Onion"));
    assertEquals("Awaiting command: Invalid state: This command can only be used during a campaign.\n"
            + "Use the start command to start a campaign.\n",
        output.toString().split(Controller.separator)[1]);

    assertEquals("Awaiting command: Invalid state: This command can only be used during a campaign.\n"
            + "Use the start command to start a campaign.\n",
        output.toString().split(Controller.separator)[2]);
  }

  @Test
  public void nonExistCharHealAndDamage() {
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
    assertEquals("Awaiting command: \nInvalid input: The Character Luna doesn't exist in this Manager.\n",
        output.toString().split(Controller.separator)[2]);

    assertEquals("Awaiting command: \nInvalid input: The Character Luna doesn't exist in this Manager.\n",
        output.toString().split(Controller.separator)[3]);
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
        + "damage-all 20.5\n"
        + "heal-all 10.223\n"
        + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertTrue(this.model.doesCharacterExist("Onion"));
    assertEquals("Awaiting command: Invalid input: Amount must be an integer (whole number).\n",
        output.toString().split(Controller.separator)[2]);

    assertEquals("Awaiting command: Invalid input: Amount must be an integer (whole number).\n",
        output.toString().split(Controller.separator)[3]);

    assertEquals("Awaiting command: Invalid input: Amount must be an integer (whole number).\n",
        output.toString().split(Controller.separator)[4]);

    assertEquals("Awaiting command: Invalid input: Amount must be an integer (whole number).\n",
        output.toString().split(Controller.separator)[5]);
  }
}