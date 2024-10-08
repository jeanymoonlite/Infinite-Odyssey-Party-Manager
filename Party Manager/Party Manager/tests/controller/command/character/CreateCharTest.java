package controller.command.character;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import controller.Controller;
import controller.IOManagerController;
import controller.IOManagerControllerTest;
import java.io.StringReader;
import java.util.NoSuchElementException;
import model.infiniteodysseys.IOManager;
import org.junit.Test;
import view.IOManagerTextView;

public class CreateCharTest extends IOManagerControllerTest {

  @Test
  public void getSignature() {
    this.model = new IOManager();
    assertEquals("create-char (name playerName class classSpecification\n"
            + "             strength intelligence creativity\n"
            + "             charisma stealth intimidation)",
        new CreateChar(this.model, null, null).getSignature());
  }

  @Test
  public void getDescription() {
    this.model = new IOManager();
    assertEquals("Creates a new Character with the given information.",
        new CreateChar(this.model, null, null).getDescription());
  }

  @Test
  public void successfulCreateChar() {
    Readable input = new StringReader("create-char\n"
        + "Onion\n"
        + "Steven\n"
        + "Human\n"
        + "New Yorker\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "y quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertTrue(this.model.doesCharacterExist("Onion"));
    assertEquals("Awaiting command: "
            + "Character name: "
            + "Player name: "
            + "Class: "
            + "Class Specification (Type enter to leave blank): "
            + "Strength: "
            + "Intelligence: "
            + "Creativity: "
            + "Charisma: "
            + "Stealth: "
            + "Intimidation: \n"
            + "Create the following Character?\n"
            + "Onion (Steven)\n"
            + "Class: Human (New Yorker)\n"
            + "Hp: 100/100\n"
            + "Strength: 1 (+1)\n"
            + "Intelligence: 1 (+1)\n"
            + "Creativity: 1 (+1)\n"
            + "Charisma: 1 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 1 (+1)\n"
            + "Confirm (y or n): \n"
            + "Onion (Steven) was added to the Manager.\n",
        output.toString().split(Controller.separator)[1]);
  }

  @Test
  public void cancellingCreateChar() {
    Readable input = new StringReader("create-char\n"
        + "Onion\n"
        + "Steven\n"
        + "Human\n"
        + "\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "n quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertFalse(this.model.doesCharacterExist("Onion"));
    assertEquals("Awaiting command: "
            + "Character name: "
            + "Player name: "
            + "Class: "
            + "Class Specification (Type enter to leave blank): "
            + "Strength: "
            + "Intelligence: "
            + "Creativity: "
            + "Charisma: "
            + "Stealth: "
            + "Intimidation: \n"
            + "Create the following Character?\n"
            + "Onion (Steven)\n"
            + "Class: Human\n"
            + "Hp: 100/100\n"
            + "Strength: 1 (+1)\n"
            + "Intelligence: 1 (+1)\n"
            + "Creativity: 1 (+1)\n"
            + "Charisma: 1 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 1 (+1)\n"
            + "Confirm (y or n): \n"
            + "Onion (Steven) will not be created.\n",
        output.toString().split(Controller.separator)[1]);
  }

  @Test
  public void badCancellingCreateChar() {
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
        + "avcasfvsad y quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertTrue(this.model.doesCharacterExist("Onion"));
    assertEquals("Awaiting command: "
            + "Character name: "
            + "Player name: "
            + "Class: "
            + "Class Specification (Type enter to leave blank): "
            + "Strength: "
            + "Intelligence: "
            + "Creativity: "
            + "Charisma: "
            + "Stealth: "
            + "Intimidation: \n"
            + "Create the following Character?\n"
            + "Onion (Steven)\n"
            + "Class: Human\n"
            + "Hp: 100/100\n"
            + "Strength: 1 (+1)\n"
            + "Intelligence: 1 (+1)\n"
            + "Creativity: 1 (+1)\n"
            + "Charisma: 1 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 1 (+1)\n"
            + "Confirm (y or n): \n"
            + "Invalid input.\n"
            + "Create the following Character?\n"
            + "Onion (Steven)\n"
            + "Class: Human\n"
            + "Hp: 100/100\n"
            + "Strength: 1 (+1)\n"
            + "Intelligence: 1 (+1)\n"
            + "Creativity: 1 (+1)\n"
            + "Charisma: 1 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 1 (+1)\n"
            + "Confirm (y or n): \n"
            + "Onion (Steven) was added to the Manager.\n",
        output.toString().split(Controller.separator)[1]);
  }

  @Test
  public void whitespaceNameAndPlayerName() {
    Readable input = new StringReader("create-char\n"
        + " \n"
        + "Avaline\n"
        + " \n"
        + "Day\n");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);

    try {
      this.controller.start();
      fail();
    }
    catch (NoSuchElementException e) {
      assertEquals("Awaiting command: "
              + "Character name: "
              + "\nInvalid input: Character name cannot be whitespace. Please try again.\n"
              + "Character name: Player name: "
              + "\nInvalid input: Player name cannot be whitespace. Please try again.\n"
              + "Player name: Class: ",
          output.toString().split(Controller.separator)[1]);
    }
  }

  @Test
  public void roles() {
    Readable input = new StringReader("create-char\n"
        + "Onion\n"
        + "Steven\n"
        + "h\n"
        + "quit\n"
        + "a\n"
        + "Tank\n");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);

    try {
      this.controller.start();
      fail();
    }
    catch (NoSuchElementException e) {
      assertEquals("Awaiting command: "
              + "Character name: Player name: Class: "
              + "\nInvalid input: h is not a valid role. Please try again.\n"
              + "Class: "
              + "\nInvalid input: quit is not a valid role. Please try again.\n"
              + "Class: "
              + "\nInvalid input: a is not a valid role. Please try again.\n"
              + "Class: "
              + "\nInvalid input: Tank is not a valid role. Please try again.\n"
              + "Class: ",
          output.toString().split(Controller.separator)[1]);
    }
  }

  @Test
  public void emptyClassSpec() {
    Readable input = new StringReader("create-char\n"
        + "Onion\n"
        + "Steven\n"
        + "human\n"
        + "\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "y\n");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);

    try {
      this.controller.start();
      fail();
    }
    catch (NoSuchElementException e) {
      assertTrue(this.model.doesCharacterExist("Onion"));
      assertEquals("", this.model.findCharByName("Onion").getSpecification());
      assertEquals("Awaiting command: "
              + "Character name: "
              + "Player name: "
              + "Class: "
              + "Class Specification (Type enter to leave blank): "
              + "Strength: "
              + "Intelligence: "
              + "Creativity: "
              + "Charisma: "
              + "Stealth: "
              + "Intimidation: \n"
              + "Create the following Character?\n"
              + "Onion (Steven)\n"
              + "Class: Human\n"
              + "Hp: 100/100\n"
              + "Strength: 1 (+1)\n"
              + "Intelligence: 1 (+1)\n"
              + "Creativity: 1 (+1)\n"
              + "Charisma: 1 (+1)\n"
              + "Stealth: 1 (+1)\n"
              + "Intimidation: 1 (+1)\n"
              + "Confirm (y or n): \n"
              + "Onion (Steven) was added to the Manager.\n",
          output.toString().split(Controller.separator)[1]);
    }
  }

  @Test
  public void invalidStat() {
    Readable input = new StringReader("create-char\n"
        + "Onion\n"
        + "Steven\n"
        + "human\n"
        + "n/a\n"
        + "-1\n"
        + "0.4");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);

    try {
      this.controller.start();
      fail();
    }
    catch (NoSuchElementException e) {
      assertEquals("Awaiting command: "
              + "Character name: "
              + "Player name: "
              + "Class: "
              + "Class Specification (Type enter to leave blank): "
              + "Strength: "
              + "\nInvalid input: A stat's value cannot be negative.\n"
              + "Strength: "
              + "\nInvalid input: A stat's value must be a non-negative integer.\n"
              + "Strength: ",
          output.toString().split(Controller.separator)[1]);
    }
  }

  @Test
  public void exceeding30() {
    Readable input = new StringReader("create-char\n"
        + "Onion\n"
        + "Steven\n"
        + "human\n"
        + "n/a\n"
        + "10\n"
        + "6\n"
        + "3\n"
        + "4\n"
        + "5\n"
        + "5\n");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);

    try {
      this.controller.start();
      fail();
    }
    catch (NoSuchElementException e) {
      assertEquals("Awaiting command: "
              + "Character name: "
              + "Player name: "
              + "Class: "
              + "Class Specification (Type enter to leave blank): "
              + "Strength: "
              + "Intelligence: "
              + "Creativity: "
              + "Charisma: "
              + "Stealth: "
              + "Intimidation: "
              + "\nInvalid input: The sum of all the stats exceeds 30.\n"
              + "Please try again.\n"
              + "Strength: ",
          output.toString().split(Controller.separator)[1]);
    }
  }

  @Test
  public void duplicateName() {
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
        + "Onion\n");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);

    try {
      this.controller.start();
      fail();
    }
    catch (NoSuchElementException e) {
      assertTrue(this.model.doesCharacterExist("Onion"));
      assertEquals("Awaiting command: "
              + "Character name: "
              + "\nInvalid input: There is already a Character named Onion.\n"
              + "Please input a different name.\n"
              + "Character name: ",
          output.toString().split(Controller.separator)[2]);
    }


  }

}