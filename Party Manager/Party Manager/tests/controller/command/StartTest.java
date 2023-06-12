package controller.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import controller.Controller;
import controller.IOManagerController;
import controller.IOManagerControllerTest;
import java.io.StringReader;
import java.util.NoSuchElementException;
import model.infiniteodysseys.IOManager;
import org.junit.Test;
import view.IOManagerTextView;

public class StartTest extends IOManagerControllerTest {

  @Test
  public void startY() {
    Readable input = new StringReader("create-char\n"
        + "Danny Sexbang\n"
        + "Dan\n"
        + "Bard\n"
        + "Lover\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "y\n"
        + "create-char\n"
        + "Ninja Brian\n"
        + "Brian\n"
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
        + "Ninja Sex Party\n"
        + "Danny Sexbang, Ninja Brian\n"
        + "y\n"
        + "start Ninja Sex Party\n"
        + "y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);

    try {
      this.controller.start();
    }
    catch (NoSuchElementException | IllegalStateException e) {
      assertTrue(this.model.doesCharacterExist("Danny Sexbang"));
      assertTrue(this.model.doesCharacterExist("Ninja Brian"));
      assertTrue(this.model.doesPartyExist("Ninja Sex Party"));
      assertEquals("Awaiting command: \n"
              + "Start a Campaign with the Ninja Sex Party Party?\n"
              + "Confirm (y or n): "
              + "A Campaign has been started with Ninja Sex Party:\n\n"
              + "Active Party: Ninja Sex Party\n\n"
              + "Danny Sexbang (Dan)\n"
              + "Class: Bard (Lover)\n"
              + "Hp: 100/100 (+1 Def)\n"
              + "Strength: 1\n"
              + "Intelligence: 1\n"
              + "Creativity: 1\n"
              + "Charisma: 1 (+4)\n"
              + "Stealth: 1\n"
              + "Intimidation: 1\n"
              + "\n"
              + "Ninja Brian (Brian)\n"
              + "Class: Rogue (Ninja)\n"
              + "Hp: 100/100 (-5 Def)\n"
              + "Strength: 1 (+2)\n"
              + "Intelligence: 1\n"
              + "Creativity: 1\n"
              + "Charisma: 1\n"
              + "Stealth: 1 (+3)\n"
              + "Intimidation: 1\n",
          output.toString().split(Controller.separator)[4]);
    }
  }

  @Test
  public void startN() {
    Readable input = new StringReader("create-char\n"
        + "Danny Sexbang\n"
        + "Dan\n"
        + "Bard\n"
        + "Lover\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "y\n"
        + "create-char\n"
        + "Ninja Brian\n"
        + "Brian\n"
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
        + "Ninja Sex Party\n"
        + "Danny Sexbang, Ninja Brian\n"
        + "y\n"
        + "start Ninja Sex Party\n"
        + "n");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);

    try {
      this.controller.start();
    }
    catch (NoSuchElementException | IllegalStateException e) {
      assertTrue(this.model.doesCharacterExist("Danny Sexbang"));
      assertTrue(this.model.doesCharacterExist("Ninja Brian"));
      assertTrue(this.model.doesPartyExist("Ninja Sex Party"));
      assertEquals("Awaiting command: \n"
              + "Start a Campaign with the Ninja Sex Party Party?\n"
              + "Confirm (y or n): ",
          output.toString().split(Controller.separator)[4]);
    }
  }

  @Test
  public void startBadInput() {
    Readable input = new StringReader("create-char\n"
        + "Danny Sexbang\n"
        + "Dan\n"
        + "Bard\n"
        + "Lover\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "y\n"
        + "create-char\n"
        + "Ninja Brian\n"
        + "Brian\n"
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
        + "Ninja Sex Party\n"
        + "Danny Sexbang, Ninja Brian\n"
        + "y\n"
        + "start Ninja Sex Party\n"
        + "aadad\n"
        + "aa\n"
        + "n");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);

    try {
      this.controller.start();
    }
    catch (NoSuchElementException | IllegalStateException e) {
      assertTrue(this.model.doesCharacterExist("Danny Sexbang"));
      assertTrue(this.model.doesCharacterExist("Ninja Brian"));
      assertTrue(this.model.doesPartyExist("Ninja Sex Party"));
      assertEquals("Awaiting command: \n"
              + "Start a Campaign with the Ninja Sex Party Party?\n"
              + "Confirm (y or n): "
              + "\nInvalid input.\n"
              + "Start a Campaign with the Ninja Sex Party Party?\n"
              + "Confirm (y or n): "
              + "\nInvalid input.\n"
              + "Start a Campaign with the Ninja Sex Party Party?\n"
              + "Confirm (y or n): ",
          output.toString().split(Controller.separator)[4]);
    }
  }

  @Test
  public void startNoExistParty() {
    Readable input = new StringReader("create-char\n"
        + "Danny Sexbang\n"
        + "Dan\n"
        + "Bard\n"
        + "Lover\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "y\n"
        + "create-char\n"
        + "Ninja Brian\n"
        + "Brian\n"
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
        + "Ninja Sex Party\n"
        + "Danny Sexbang, Ninja Brian\n"
        + "y\n"
        + "start Ninja Party\n"
    );
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);

    try {
      this.controller.start();
    }
    catch (NoSuchElementException | IllegalStateException e) {
      assertTrue(this.model.doesCharacterExist("Danny Sexbang"));
      assertTrue(this.model.doesCharacterExist("Ninja Brian"));
      assertTrue(this.model.doesPartyExist("Ninja Sex Party"));
      assertEquals("Awaiting command: \n"
              + "Invalid input: The Party Ninja Party doesn't exist in this Manager.\n",
          output.toString().split(Controller.separator)[4]);
    }
  }

  @Test
  public void startNoParties() {
    Readable input = new StringReader("create-char\n"
        + "Danny Sexbang\n"
        + "Dan\n"
        + "Bard\n"
        + "Lover\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "y\n"
        + "create-char\n"
        + "Ninja Brian\n"
        + "Brian\n"
        + "Rogue\n"
        + "Ninja\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "y\n"
        + "start Ninja Party\n"
    );
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);

    try {
      this.controller.start();
    }
    catch (NoSuchElementException | IllegalStateException e) {
      assertTrue(this.model.doesCharacterExist("Danny Sexbang"));
      assertTrue(this.model.doesCharacterExist("Ninja Brian"));
      assertFalse(this.model.doesPartyExist("Ninja Sex Party"));
      assertEquals("Awaiting command: \n"
              + "Invalid state: The Manager doesn't have any Parties!\n"
              + "Add Parties using the create-party command.\n",
          output.toString().split(Controller.separator)[3]);
    }
  }

  @Test
  public void startThenQuitY() {
    Readable input = new StringReader("create-char\n"
        + "Danny Sexbang\n"
        + "Dan\n"
        + "Bard\n"
        + "Lover\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "y\n"
        + "create-char\n"
        + "Ninja Brian\n"
        + "Brian\n"
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
        + "Ninja Sex Party\n"
        + "Danny Sexbang, Ninja Brian\n"
        + "y\n"
        + "start Ninja Sex Party\n"
        + "y quit y quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);

    this.controller.start();
    assertTrue(this.model.doesCharacterExist("Danny Sexbang"));
    assertTrue(this.model.doesCharacterExist("Ninja Brian"));
    assertTrue(this.model.doesPartyExist("Ninja Sex Party"));
    assertEquals("Awaiting command: \n"
            + "Start a Campaign with the Ninja Sex Party Party?\n"
            + "Confirm (y or n): "
            + "A Campaign has been started with Ninja Sex Party:\n\n"
            + "Active Party: Ninja Sex Party\n\n"
            + "Danny Sexbang (Dan)\n"
            + "Class: Bard (Lover)\n"
            + "Hp: 100/100 (+1 Def)\n"
            + "Strength: 1\n"
            + "Intelligence: 1\n"
            + "Creativity: 1\n"
            + "Charisma: 1 (+4)\n"
            + "Stealth: 1\n"
            + "Intimidation: 1\n"
            + "\n"
            + "Ninja Brian (Brian)\n"
            + "Class: Rogue (Ninja)\n"
            + "Hp: 100/100 (-5 Def)\n"
            + "Strength: 1 (+2)\n"
            + "Intelligence: 1\n"
            + "Creativity: 1\n"
            + "Charisma: 1\n"
            + "Stealth: 1 (+3)\n"
            + "Intimidation: 1\n",
        output.toString().split(Controller.separator)[4]);

    assertEquals("Awaiting command: "
            + "Are you sure you want to end the current campaign?\n"
            + "Confirm (y or n): "
            + "\nThe campaign with Ninja Sex Party has ended.\n",
        output.toString().split(Controller.separator)[5]);
  }

  @Test
  public void startThenQuitN() {
    Readable input = new StringReader("create-char\n"
        + "Danny Sexbang\n"
        + "Dan\n"
        + "Bard\n"
        + "Lover\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "y\n"
        + "create-char\n"
        + "Ninja Brian\n"
        + "Brian\n"
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
        + "Ninja Sex Party\n"
        + "Danny Sexbang, Ninja Brian\n"
        + "y\n"
        + "start Ninja Sex Party\n"
        + "y quit n quit y quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);

    this.controller.start();
    assertTrue(this.model.doesCharacterExist("Danny Sexbang"));
    assertTrue(this.model.doesCharacterExist("Ninja Brian"));
    assertTrue(this.model.doesPartyExist("Ninja Sex Party"));
    assertEquals("Awaiting command: \n"
            + "Start a Campaign with the Ninja Sex Party Party?\n"
            + "Confirm (y or n): "
            + "A Campaign has been started with Ninja Sex Party:\n\n"
            + "Active Party: Ninja Sex Party\n\n"
            + "Danny Sexbang (Dan)\n"
            + "Class: Bard (Lover)\n"
            + "Hp: 100/100 (+1 Def)\n"
            + "Strength: 1\n"
            + "Intelligence: 1\n"
            + "Creativity: 1\n"
            + "Charisma: 1 (+4)\n"
            + "Stealth: 1\n"
            + "Intimidation: 1\n"
            + "\n"
            + "Ninja Brian (Brian)\n"
            + "Class: Rogue (Ninja)\n"
            + "Hp: 100/100 (-5 Def)\n"
            + "Strength: 1 (+2)\n"
            + "Intelligence: 1\n"
            + "Creativity: 1\n"
            + "Charisma: 1\n"
            + "Stealth: 1 (+3)\n"
            + "Intimidation: 1\n",
        output.toString().split(Controller.separator)[4]);

    assertEquals("Awaiting command: "
            + "Are you sure you want to end the current campaign?\n"
            + "Confirm (y or n): ",
        output.toString().split(Controller.separator)[5]);

    assertEquals("Awaiting command: "
            + "Are you sure you want to end the current campaign?\n"
            + "Confirm (y or n): "
            + "\nThe campaign with Ninja Sex Party has ended.\n",
        output.toString().split(Controller.separator)[6]);
  }

  @Test
  public void startThenQuitBadInput() {
    Readable input = new StringReader("create-char\n"
        + "Danny Sexbang\n"
        + "Dan\n"
        + "Bard\n"
        + "Lover\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "y\n"
        + "create-char\n"
        + "Ninja Brian\n"
        + "Brian\n"
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
        + "Ninja Sex Party\n"
        + "Danny Sexbang, Ninja Brian\n"
        + "y\n"
        + "start Ninja Sex Party\n"
        + "y quit ggg sn a b y quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);

    this.controller.start();
    assertTrue(this.model.doesCharacterExist("Danny Sexbang"));
    assertTrue(this.model.doesCharacterExist("Ninja Brian"));
    assertTrue(this.model.doesPartyExist("Ninja Sex Party"));
    assertEquals("Awaiting command: \n"
            + "Start a Campaign with the Ninja Sex Party Party?\n"
            + "Confirm (y or n): "
            + "A Campaign has been started with Ninja Sex Party:\n\n"
            + "Active Party: Ninja Sex Party\n\n"
            + "Danny Sexbang (Dan)\n"
            + "Class: Bard (Lover)\n"
            + "Hp: 100/100 (+1 Def)\n"
            + "Strength: 1\n"
            + "Intelligence: 1\n"
            + "Creativity: 1\n"
            + "Charisma: 1 (+4)\n"
            + "Stealth: 1\n"
            + "Intimidation: 1\n"
            + "\n"
            + "Ninja Brian (Brian)\n"
            + "Class: Rogue (Ninja)\n"
            + "Hp: 100/100 (-5 Def)\n"
            + "Strength: 1 (+2)\n"
            + "Intelligence: 1\n"
            + "Creativity: 1\n"
            + "Charisma: 1\n"
            + "Stealth: 1 (+3)\n"
            + "Intimidation: 1\n",
        output.toString().split(Controller.separator)[4]);

    assertEquals("Awaiting command: Are you sure you want to end the current campaign?\n"
            + "Confirm (y or n): \n"
            + "Invalid input.\n"
            + "Are you sure you want to end the current campaign?\n"
            + "Confirm (y or n): \n"
            + "Invalid input.\n"
            + "Are you sure you want to end the current campaign?\n"
            + "Confirm (y or n): \n"
            + "Invalid input.\n"
            + "Are you sure you want to end the current campaign?\n"
            + "Confirm (y or n): \n"
            + "Invalid input.\n"
            + "Are you sure you want to end the current campaign?\n"
            + "Confirm (y or n): \n"
            + "The campaign with Ninja Sex Party has ended.\n",
        output.toString().split(Controller.separator)[5]);
  }
}