package controller.command.character;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import controller.Controller;
import controller.IOManagerController;
import controller.IOManagerControllerTest;
import java.io.StringReader;
import model.infiniteodysseys.IOManager;
import org.junit.Test;
import view.IOManagerTextView;

public class EditCharTest extends IOManagerControllerTest {

  @Test
  public void getSignature() {
    assertEquals("edit-char (name)",
        new EditChar(null, null, null).getSignature());
  }

  @Test
  public void getDescription() {
    assertEquals("Edits the Character with the given name.\n"
            + "This will put the program into Character Editing mode.\n"
            + "A new set of commands will become available in editing mode.",
        new EditChar(null, null, null).getDescription());
  }

  @Test
  public void successfulEditCharName() {
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
        + "y\n"
        + "edit-char Onion\n"
        + "edit name\n"
        + "Esteban\n"
        + "save\n"
        + "y quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertTrue(this.model.doesCharacterExist("Esteban"));
    assertEquals("Awaiting command: You are now in character editing mode.\n"
            + "Editing: Onion (Steven)\n",
        output.toString().split(Controller.separator)[2].split(EditChar.separator)[0]);

    assertEquals("Onion (Steven)\n"
            + "Class: Human (New Yorker)\n"
            + "Hp: 100/100\n"
            + "Strength: 1 (+1)\n"
            + "Intelligence: 1 (+1)\n"
            + "Creativity: 1 (+1)\n"
            + "Charisma: 1 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 1 (+1)\n\n"
            + "Awaiting edit command: "
            + "Current Character name: Onion\n"
            + "New Character name: ",
        output.toString().split(Controller.separator)[2].split(EditChar.separator)[1]);

    assertEquals("Esteban (Steven)\n"
            + "Class: Human (New Yorker)\n"
            + "Hp: 100/100\n"
            + "Strength: 1 (+1)\n"
            + "Intelligence: 1 (+1)\n"
            + "Creativity: 1 (+1)\n"
            + "Charisma: 1 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 1 (+1)\n\n"
            + "Awaiting edit command: ",
        output.toString().split(Controller.separator)[2].split(EditChar.separator)[2]);
  }

  @Test
  public void keepingEditCharName() {
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
        + "y\n"
        + "edit-char Onion\n"
        + "edit name\n"
        + "\n"
        + "save\n"
        + "y quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertTrue(this.model.doesCharacterExist("Onion"));
    assertEquals("Awaiting command: You are now in character editing mode.\n"
            + "Editing: Onion (Steven)\n",
        output.toString().split(Controller.separator)[2].split(EditChar.separator)[0]);

    assertEquals("Onion (Steven)\n"
            + "Class: Human (New Yorker)\n"
            + "Hp: 100/100\n"
            + "Strength: 1 (+1)\n"
            + "Intelligence: 1 (+1)\n"
            + "Creativity: 1 (+1)\n"
            + "Charisma: 1 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 1 (+1)\n\n"
            + "Awaiting edit command: "
            + "Current Character name: Onion\n"
            + "New Character name: ",
        output.toString().split(Controller.separator)[2].split(EditChar.separator)[1]);

    assertEquals("Onion (Steven)\n"
            + "Class: Human (New Yorker)\n"
            + "Hp: 100/100\n"
            + "Strength: 1 (+1)\n"
            + "Intelligence: 1 (+1)\n"
            + "Creativity: 1 (+1)\n"
            + "Charisma: 1 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 1 (+1)\n\n"
            + "Awaiting edit command: ",
        output.toString().split(Controller.separator)[2].split(EditChar.separator)[2]);
  }

  @Test
  public void changingNameToExistingChar() {
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
        + "y\n"
        + "create-char\n"
        + "Lunarose\n"
        + "Luna\n"
        + "Wizard\n"
        + "Mage\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "y\n"
        + "edit-char Onion\n"
        + "edit name\n"
        + "Lunarose\n"
        + "\n"
        + "save\n"
        + "y quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertTrue(this.model.doesCharacterExist("Onion"));
    assertEquals("Awaiting command: You are now in character editing mode.\n"
            + "Editing: Onion (Steven)\n",
        output.toString().split(Controller.separator)[3].split(EditChar.separator)[0]);

    assertEquals("Onion (Steven)\n"
            + "Class: Human (New Yorker)\n"
            + "Hp: 100/100\n"
            + "Strength: 1 (+1)\n"
            + "Intelligence: 1 (+1)\n"
            + "Creativity: 1 (+1)\n"
            + "Charisma: 1 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 1 (+1)\n\n"
            + "Awaiting edit command: "
            + "Current Character name: Onion\n"
            + "New Character name: \n"
            + "Invalid input: There is already a Character named Lunarose.\n"
            + "Please input a different name.\n"
            + "Current Character name: Onion\n"
            + "New Character name: " ,
        output.toString().split(Controller.separator)[3].split(EditChar.separator)[1]);

    assertEquals("Onion (Steven)\n"
            + "Class: Human (New Yorker)\n"
            + "Hp: 100/100\n"
            + "Strength: 1 (+1)\n"
            + "Intelligence: 1 (+1)\n"
            + "Creativity: 1 (+1)\n"
            + "Charisma: 1 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 1 (+1)\n\n"
            + "Awaiting edit command: ",
        output.toString().split(Controller.separator)[3].split(EditChar.separator)[2]);
  }

  @Test
  public void successfulEditCharPlayerName() {
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
        + "y\n"
        + "edit-char Onion\n"
        + "edit player name\n"
        + "Esteban\n"
        + "save\n"
        + "y quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertEquals("Esteban", this.model.findCharByName("Onion").getPlayerName());
    assertEquals("Awaiting command: You are now in character editing mode.\n"
            + "Editing: Onion (Steven)\n",
        output.toString().split(Controller.separator)[2].split(EditChar.separator)[0]);

    assertEquals("Onion (Steven)\n"
            + "Class: Human (New Yorker)\n"
            + "Hp: 100/100\n"
            + "Strength: 1 (+1)\n"
            + "Intelligence: 1 (+1)\n"
            + "Creativity: 1 (+1)\n"
            + "Charisma: 1 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 1 (+1)\n\n"
            + "Awaiting edit command: "
            + "Current Player name: Steven\n"
            + "New Player name: ",
        output.toString().split(Controller.separator)[2].split(EditChar.separator)[1]);

    assertEquals("Onion (Esteban)\n"
            + "Class: Human (New Yorker)\n"
            + "Hp: 100/100\n"
            + "Strength: 1 (+1)\n"
            + "Intelligence: 1 (+1)\n"
            + "Creativity: 1 (+1)\n"
            + "Charisma: 1 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 1 (+1)\n\n"
            + "Awaiting edit command: ",
        output.toString().split(Controller.separator)[2].split(EditChar.separator)[2]);
  }

  @Test
  public void keepingEditCharPlayerName() {
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
        + "y\n"
        + "edit-char Onion\n"
        + "edit player name\n"
        + "\n"
        + "save\n"
        + "y quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertEquals("Steven", this.model.findCharByName("Onion").getPlayerName());
    assertEquals("Awaiting command: You are now in character editing mode.\n"
            + "Editing: Onion (Steven)\n",
        output.toString().split(Controller.separator)[2].split(EditChar.separator)[0]);

    assertEquals("Onion (Steven)\n"
            + "Class: Human (New Yorker)\n"
            + "Hp: 100/100\n"
            + "Strength: 1 (+1)\n"
            + "Intelligence: 1 (+1)\n"
            + "Creativity: 1 (+1)\n"
            + "Charisma: 1 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 1 (+1)\n\n"
            + "Awaiting edit command: "
            + "Current Player name: Steven\n"
            + "New Player name: ",
        output.toString().split(Controller.separator)[2].split(EditChar.separator)[1]);

    assertEquals("Onion (Steven)\n"
            + "Class: Human (New Yorker)\n"
            + "Hp: 100/100\n"
            + "Strength: 1 (+1)\n"
            + "Intelligence: 1 (+1)\n"
            + "Creativity: 1 (+1)\n"
            + "Charisma: 1 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 1 (+1)\n\n"
            + "Awaiting edit command: ",
        output.toString().split(Controller.separator)[2].split(EditChar.separator)[2]);
  }

  @Test
  public void successfulEditCharClass() {
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
        + "y\n"
        + "edit-char Onion\n"
        + "edit class\n"
        + "Rogue\n"
        + "save\n"
        + "y quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertEquals("Rogue", this.model.findCharByName("Onion").getRole());
    assertEquals("Awaiting command: You are now in character editing mode.\n"
            + "Editing: Onion (Steven)\n",
        output.toString().split(Controller.separator)[2].split(EditChar.separator)[0]);

    assertEquals("Onion (Steven)\n"
            + "Class: Human (New Yorker)\n"
            + "Hp: 100/100\n"
            + "Strength: 1 (+1)\n"
            + "Intelligence: 1 (+1)\n"
            + "Creativity: 1 (+1)\n"
            + "Charisma: 1 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 1 (+1)\n\n"
            + "Awaiting edit command: "
            + "Current Class: Human\n"
            + "New Class: ",
        output.toString().split(Controller.separator)[2].split(EditChar.separator)[1]);

    assertEquals("Onion (Steven)\n"
            + "Class: Rogue (New Yorker)\n"
            + "Hp: 100/100 (-5 Def)\n"
            + "Strength: 1 (+2)\n"
            + "Intelligence: 1\n"
            + "Creativity: 1\n"
            + "Charisma: 1\n"
            + "Stealth: 1 (+3)\n"
            + "Intimidation: 1\n\n"
            + "Awaiting edit command: ",
        output.toString().split(Controller.separator)[2].split(EditChar.separator)[2]);
  }

  @Test
  public void invalidClassEditCharClass() {
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
        + "y\n"
        + "edit-char Onion\n"
        + "edit class\n"
        + "Acrobat\n"
        + "\n"
        + "save\n"
        + "y quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertEquals("Human", this.model.findCharByName("Onion").getRole());
    assertEquals("Awaiting command: You are now in character editing mode.\n"
            + "Editing: Onion (Steven)\n",
        output.toString().split(Controller.separator)[2].split(EditChar.separator)[0]);

    assertEquals("Onion (Steven)\n"
            + "Class: Human (New Yorker)\n"
            + "Hp: 100/100\n"
            + "Strength: 1 (+1)\n"
            + "Intelligence: 1 (+1)\n"
            + "Creativity: 1 (+1)\n"
            + "Charisma: 1 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 1 (+1)\n\n"
            + "Awaiting edit command: "
            + "Current Class: Human\n"
            + "New Class: \n"
            + "Invalid input: Acrobat is not a valid Class.\n"
            + "Please try again.\n"
            + "Current Class: Human\n"
            + "New Class: ",
        output.toString().split(Controller.separator)[2].split(EditChar.separator)[1]);

    assertEquals("Onion (Steven)\n"
            + "Class: Human (New Yorker)\n"
            + "Hp: 100/100\n"
            + "Strength: 1 (+1)\n"
            + "Intelligence: 1 (+1)\n"
            + "Creativity: 1 (+1)\n"
            + "Charisma: 1 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 1 (+1)\n\n"
            + "Awaiting edit command: ",
        output.toString().split(Controller.separator)[2].split(EditChar.separator)[2]);
  }

  @Test
  public void keepingEditCharClass() {
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
        + "y\n"
        + "edit-char Onion\n"
        + "edit class\n"
        + "\n"
        + "save\n"
        + "y quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertEquals("Human", this.model.findCharByName("Onion").getRole());
    assertEquals("Awaiting command: You are now in character editing mode.\n"
            + "Editing: Onion (Steven)\n",
        output.toString().split(Controller.separator)[2].split(EditChar.separator)[0]);

    assertEquals("Onion (Steven)\n"
            + "Class: Human (New Yorker)\n"
            + "Hp: 100/100\n"
            + "Strength: 1 (+1)\n"
            + "Intelligence: 1 (+1)\n"
            + "Creativity: 1 (+1)\n"
            + "Charisma: 1 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 1 (+1)\n\n"
            + "Awaiting edit command: "
            + "Current Class: Human\n"
            + "New Class: ",
        output.toString().split(Controller.separator)[2].split(EditChar.separator)[1]);

    assertEquals("Onion (Steven)\n"
            + "Class: Human (New Yorker)\n"
            + "Hp: 100/100\n"
            + "Strength: 1 (+1)\n"
            + "Intelligence: 1 (+1)\n"
            + "Creativity: 1 (+1)\n"
            + "Charisma: 1 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 1 (+1)\n\n"
            + "Awaiting edit command: ",
        output.toString().split(Controller.separator)[2].split(EditChar.separator)[2]);
  }

  @Test
  public void successfulEditCharClassSpec() {
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
        + "y\n"
        + "edit-char Onion\n"
        + "edit class spec\n"
        + "Salvadorian\n"
        + "save\n"
        + "y quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertEquals("Salvadorian", this.model.findCharByName("Onion").getSpecification());
    assertEquals("Awaiting command: You are now in character editing mode.\n"
            + "Editing: Onion (Steven)\n",
        output.toString().split(Controller.separator)[2].split(EditChar.separator)[0]);

    assertEquals("Onion (Steven)\n"
            + "Class: Human (New Yorker)\n"
            + "Hp: 100/100\n"
            + "Strength: 1 (+1)\n"
            + "Intelligence: 1 (+1)\n"
            + "Creativity: 1 (+1)\n"
            + "Charisma: 1 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 1 (+1)\n\n"
            + "Awaiting edit command: "
            + "Current Class Specification: New Yorker\n"
            + "New Class Specification: ",
        output.toString().split(Controller.separator)[2].split(EditChar.separator)[1]);

    assertEquals("Onion (Steven)\n"
            + "Class: Human (Salvadorian)\n"
            + "Hp: 100/100\n"
            + "Strength: 1 (+1)\n"
            + "Intelligence: 1 (+1)\n"
            + "Creativity: 1 (+1)\n"
            + "Charisma: 1 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 1 (+1)\n\n"
            + "Awaiting edit command: ",
        output.toString().split(Controller.separator)[2].split(EditChar.separator)[2]);
  }

  @Test
  public void keepingEditCharClassSpec() {
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
        + "y\n"
        + "edit-char Onion\n"
        + "edit class spec\n"
        + "\n"
        + "save\n"
        + "y quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertEquals("New Yorker", this.model.findCharByName("Onion").getSpecification());
    assertEquals("Awaiting command: You are now in character editing mode.\n"
            + "Editing: Onion (Steven)\n",
        output.toString().split(Controller.separator)[2].split(EditChar.separator)[0]);

    assertEquals("Onion (Steven)\n"
            + "Class: Human (New Yorker)\n"
            + "Hp: 100/100\n"
            + "Strength: 1 (+1)\n"
            + "Intelligence: 1 (+1)\n"
            + "Creativity: 1 (+1)\n"
            + "Charisma: 1 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 1 (+1)\n\n"
            + "Awaiting edit command: "
            + "Current Class Specification: New Yorker\n"
            + "New Class Specification: ",
        output.toString().split(Controller.separator)[2].split(EditChar.separator)[1]);

    assertEquals("Onion (Steven)\n"
            + "Class: Human (New Yorker)\n"
            + "Hp: 100/100\n"
            + "Strength: 1 (+1)\n"
            + "Intelligence: 1 (+1)\n"
            + "Creativity: 1 (+1)\n"
            + "Charisma: 1 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 1 (+1)\n\n"
            + "Awaiting edit command: ",
        output.toString().split(Controller.separator)[2].split(EditChar.separator)[2]);
  }

  @Test
  public void invalidAttributeEditChar() {
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
        + "y\n"
        + "edit-char Onion\n"
        + "edit weight\n"
        + "quit\n"
        + "y quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertEquals("Awaiting command: You are now in character editing mode.\n"
            + "Editing: Onion (Steven)\n",
        output.toString().split(Controller.separator)[2].split(EditChar.separator)[0]);

    assertEquals("Onion (Steven)\n"
            + "Class: Human (New Yorker)\n"
            + "Hp: 100/100\n"
            + "Strength: 1 (+1)\n"
            + "Intelligence: 1 (+1)\n"
            + "Creativity: 1 (+1)\n"
            + "Charisma: 1 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 1 (+1)\n\n"
            + "Awaiting edit command: Invalid input: The attribute weight does not exist for Onion\n"
            + "or cannot be edited.\n",
        output.toString().split(Controller.separator)[2].split(EditChar.separator)[1]);
  }

  @Test
  public void invalidEditCommand() {
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
        + "y\n"
        + "edit-char Onion\n"
        + "weight\n"
        + "quit\n"
        + "y quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertEquals("Awaiting command: You are now in character editing mode.\n"
            + "Editing: Onion (Steven)\n",
        output.toString().split(Controller.separator)[2].split(EditChar.separator)[0]);

    assertEquals("Onion (Steven)\n"
            + "Class: Human (New Yorker)\n"
            + "Hp: 100/100\n"
            + "Strength: 1 (+1)\n"
            + "Intelligence: 1 (+1)\n"
            + "Creativity: 1 (+1)\n"
            + "Charisma: 1 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 1 (+1)\n\n"
            + "Awaiting edit command: "
            + "\nInvalid edit command.\n",
        output.toString().split(Controller.separator)[2].split(EditChar.separator)[1]);
  }

  @Test
  public void successfulEditCharStats() {
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
        + "y\n"
        + "edit-char Onion\n"
        + "edit strength\n"
        + "5\n"
        + "edit intelligence\n"
        + "5\n"
        + "edit creativity\n"
        + "5\n"
        + "edit charisma\n"
        + "5\n"
        + "edit stealth\n"
        + "5\n"
        + "edit intimidation\n"
        + "5\n"
        + "save\n"
        + "y quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertEquals(5, this.model.findCharByName("Onion").getValueOf("strength"));
    assertEquals("Awaiting command: You are now in character editing mode.\n"
            + "Editing: Onion (Steven)\n",
        output.toString().split(Controller.separator)[2].split(EditChar.separator)[0]);

    assertEquals("Onion (Steven)\n"
            + "Class: Human (New Yorker)\n"
            + "Hp: 100/100\n"
            + "Strength: 1 (+1)\n"
            + "Intelligence: 1 (+1)\n"
            + "Creativity: 1 (+1)\n"
            + "Charisma: 1 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 1 (+1)\n\n"
            + "Awaiting edit command: "
            + "Current Strength: 1\n"
            + "New Strength: ",
        output.toString().split(Controller.separator)[2].split(EditChar.separator)[1]);

    assertEquals("Onion (Steven)\n"
            + "Class: Human (New Yorker)\n"
            + "Hp: 100/100\n"
            + "Strength: 5 (+1)\n"
            + "Intelligence: 1 (+1)\n"
            + "Creativity: 1 (+1)\n"
            + "Charisma: 1 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 1 (+1)\n\n"
            + "Awaiting edit command: "
            + "Current Intelligence: 1\n"
            + "New Intelligence: ",
        output.toString().split(Controller.separator)[2].split(EditChar.separator)[2]);

    assertEquals("Onion (Steven)\n"
            + "Class: Human (New Yorker)\n"
            + "Hp: 100/100\n"
            + "Strength: 5 (+1)\n"
            + "Intelligence: 5 (+1)\n"
            + "Creativity: 1 (+1)\n"
            + "Charisma: 1 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 1 (+1)\n\n"
            + "Awaiting edit command: "
            + "Current Creativity: 1\n"
            + "New Creativity: ",
        output.toString().split(Controller.separator)[2].split(EditChar.separator)[3]);

    assertEquals("Onion (Steven)\n"
            + "Class: Human (New Yorker)\n"
            + "Hp: 100/100\n"
            + "Strength: 5 (+1)\n"
            + "Intelligence: 5 (+1)\n"
            + "Creativity: 5 (+1)\n"
            + "Charisma: 1 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 1 (+1)\n\n"
            + "Awaiting edit command: "
            + "Current Charisma: 1\n"
            + "New Charisma: ",
        output.toString().split(Controller.separator)[2].split(EditChar.separator)[4]);

    assertEquals("Onion (Steven)\n"
            + "Class: Human (New Yorker)\n"
            + "Hp: 100/100\n"
            + "Strength: 5 (+1)\n"
            + "Intelligence: 5 (+1)\n"
            + "Creativity: 5 (+1)\n"
            + "Charisma: 5 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 1 (+1)\n\n"
            + "Awaiting edit command: "
            + "Current Stealth: 1\n"
            + "New Stealth: ",
        output.toString().split(Controller.separator)[2].split(EditChar.separator)[5]);

    assertEquals("Onion (Steven)\n"
            + "Class: Human (New Yorker)\n"
            + "Hp: 100/100\n"
            + "Strength: 5 (+1)\n"
            + "Intelligence: 5 (+1)\n"
            + "Creativity: 5 (+1)\n"
            + "Charisma: 5 (+1)\n"
            + "Stealth: 5 (+1)\n"
            + "Intimidation: 1 (+1)\n\n"
            + "Awaiting edit command: "
            + "Current Intimidation: 1\n"
            + "New Intimidation: ",
        output.toString().split(Controller.separator)[2].split(EditChar.separator)[6]);
  }

  @Test
  public void exceeding30EditCharStats() {
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
        + "y\n"
        + "edit-char Onion\n"
        + "edit strength\n"
        + "50\n"
        + "5\n"
        + "save\n"
        + "y quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertEquals(5, this.model.findCharByName("Onion").getValueOf("strength"));
    assertEquals("Awaiting command: You are now in character editing mode.\n"
            + "Editing: Onion (Steven)\n",
        output.toString().split(Controller.separator)[2].split(EditChar.separator)[0]);

    assertEquals("Onion (Steven)\n"
            + "Class: Human (New Yorker)\n"
            + "Hp: 100/100\n"
            + "Strength: 1 (+1)\n"
            + "Intelligence: 1 (+1)\n"
            + "Creativity: 1 (+1)\n"
            + "Charisma: 1 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 1 (+1)\n\n"
            + "Awaiting edit command: "
            + "Current Strength: 1\n"
            + "New Strength: \n"
            + "Invalid input: The sum of all the stats exceeds 30.\n"
            + "Please try again.\n"
            + "Current Strength: 1\n"
            + "New Strength: ",
        output.toString().split(Controller.separator)[2].split(EditChar.separator)[1]);
  }

  @Test
  public void stringForStatValueEditCharStats() {
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
        + "y\n"
        + "edit-char Onion\n"
        + "edit strength\n"
        + "gap\n"
        + "23\n"
        + "save\n"
        + "y quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertEquals(23, this.model.findCharByName("Onion").getValueOf("strength"));
    assertEquals("Awaiting command: You are now in character editing mode.\n"
            + "Editing: Onion (Steven)\n",
        output.toString().split(Controller.separator)[2].split(EditChar.separator)[0]);

    assertEquals("Onion (Steven)\n"
            + "Class: Human (New Yorker)\n"
            + "Hp: 100/100\n"
            + "Strength: 1 (+1)\n"
            + "Intelligence: 1 (+1)\n"
            + "Creativity: 1 (+1)\n"
            + "Charisma: 1 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 1 (+1)\n\n"
            + "Awaiting edit command: "
            + "Current Strength: 1\n"
            + "New Strength: \n"
            + "Invalid input: A stat's value must be a non-negative integer.\n"
            + "Current Strength: 1\n"
            + "New Strength: ",
        output.toString().split(Controller.separator)[2].split(EditChar.separator)[1]);

    assertEquals("Onion (Steven)\n"
            + "Class: Human (New Yorker)\n"
            + "Hp: 100/100\n"
            + "Strength: 23 (+1)\n"
            + "Intelligence: 1 (+1)\n"
            + "Creativity: 1 (+1)\n"
            + "Charisma: 1 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 1 (+1)\n\n"
            + "Awaiting edit command: ",
        output.toString().split(Controller.separator)[2].split(EditChar.separator)[2]);
  }

  @Test
  public void negativeEditCharStats() {
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
        + "y\n"
        + "edit-char Onion\n"
        + "edit strength\n"
        + "-5\n"
        + "5\n"
        + "save\n"
        + "y quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertEquals(5, this.model.findCharByName("Onion").getValueOf("strength"));
    assertEquals("Awaiting command: You are now in character editing mode.\n"
            + "Editing: Onion (Steven)\n",
        output.toString().split(Controller.separator)[2].split(EditChar.separator)[0]);

    assertEquals("Onion (Steven)\n"
            + "Class: Human (New Yorker)\n"
            + "Hp: 100/100\n"
            + "Strength: 1 (+1)\n"
            + "Intelligence: 1 (+1)\n"
            + "Creativity: 1 (+1)\n"
            + "Charisma: 1 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 1 (+1)\n\n"
            + "Awaiting edit command: "
            + "Current Strength: 1\n"
            + "New Strength: \n"
            + "Invalid input: A stat's value cannot be negative.\n"
            + "Current Strength: 1\n"
            + "New Strength: " ,
        output.toString().split(Controller.separator)[2].split(EditChar.separator)[1]);

    assertEquals("Onion (Steven)\n"
            + "Class: Human (New Yorker)\n"
            + "Hp: 100/100\n"
            + "Strength: 5 (+1)\n"
            + "Intelligence: 1 (+1)\n"
            + "Creativity: 1 (+1)\n"
            + "Charisma: 1 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 1 (+1)\n\n"
            + "Awaiting edit command: ",
        output.toString().split(Controller.separator)[2].split(EditChar.separator)[2]);
  }

  @Test
  public void cancellingEditChar() {
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
        + "y\n"
        + "edit-char Onion\n"
        + "edit Intimidation\n"
        + "25\n"
        + "quit\n"
        + "y quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertTrue(this.model.doesCharacterExist("Onion"));
    assertEquals(1, this.model.findCharByName("Onion").getValueOf("Intimidation"));
    assertEquals("WARNING: Quitting will remove the following changes:\n"
            + "Onion (Steven)\n"
            + "Class: Human (New Yorker)\n"
            + "Hp: 100/100\n"
            + "Strength: 1 (+1)\n"
            + "Intelligence: 1 (+1)\n"
            + "Creativity: 1 (+1)\n"
            + "Charisma: 1 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 25 (+1)\n"
            + "Are you sure you want to exit Character editing mode?\n"
            + "Confirm (y or n): \n"
            + "All changes made to Onion have been undone.\n"
            + "Now exiting Character editing mode.\n",
        output.toString().split(Controller.separator)[2].split(EditChar.separator)[3]);
  }

  @Test
  public void cancellingQuittingEditChar() {
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
        + "y\n"
        + "edit-char Onion\n"
        + "edit Intimidation\n"
        + "25\n"
        + "quit n\n"
        + "save y\n"
        + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertTrue(this.model.doesCharacterExist("Onion"));
    assertEquals(25, this.model.findCharByName("Onion").getValueOf("Intimidation"));
    assertEquals("WARNING: Quitting will remove the following changes:\n"
            + "Onion (Steven)\n"
            + "Class: Human (New Yorker)\n"
            + "Hp: 100/100\n"
            + "Strength: 1 (+1)\n"
            + "Intelligence: 1 (+1)\n"
            + "Creativity: 1 (+1)\n"
            + "Charisma: 1 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 25 (+1)\n"
            + "Are you sure you want to exit Character editing mode?\n"
            + "Confirm (y or n): ",
        output.toString().split(Controller.separator)[2].split(EditChar.separator)[3]);

    assertEquals("Save the following changes?\n"
            + "Onion (Steven)\n"
            + "Class: Human (New Yorker)\n"
            + "Hp: 100/100\n"
            + "Strength: 1 (+1)\n"
            + "Intelligence: 1 (+1)\n"
            + "Creativity: 1 (+1)\n"
            + "Charisma: 1 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 25 (+1)\n"
            + "Confirm (y or n): \n"
            + "Onion has been updated!\n"
            + "Now exiting Character editing mode.\n",
        output.toString().split(Controller.separator)[2].split(EditChar.separator)[5]);
  }

  @Test
  public void cancellingSavingEditChar() {
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
        + "y\n"
        + "edit-char Onion\n"
        + "edit Intimidation\n"
        + "25\n"
        + "save n\n"
        + "quit y\n"
        + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertTrue(this.model.doesCharacterExist("Onion"));
    assertEquals(1, this.model.findCharByName("Onion").getValueOf("Intimidation"));
    assertEquals("Save the following changes?\n"
            + "Onion (Steven)\n"
            + "Class: Human (New Yorker)\n"
            + "Hp: 100/100\n"
            + "Strength: 1 (+1)\n"
            + "Intelligence: 1 (+1)\n"
            + "Creativity: 1 (+1)\n"
            + "Charisma: 1 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 25 (+1)\n"
            + "Confirm (y or n): ",
        output.toString().split(Controller.separator)[2].split(EditChar.separator)[3]);

    assertEquals("WARNING: Quitting will remove the following changes:\n"
            + "Onion (Steven)\n"
            + "Class: Human (New Yorker)\n"
            + "Hp: 100/100\n"
            + "Strength: 1 (+1)\n"
            + "Intelligence: 1 (+1)\n"
            + "Creativity: 1 (+1)\n"
            + "Charisma: 1 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 25 (+1)\n"
            + "Are you sure you want to exit Character editing mode?\n"
            + "Confirm (y or n): \n"
            + "All changes made to Onion have been undone.\n"
            + "Now exiting Character editing mode.\n",
        output.toString().split(Controller.separator)[2].split(EditChar.separator)[5]);
  }

  @Test
  public void badInputQuittingEditChar() {
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
        + "y\n"
        + "edit-char Onion\n"
        + "edit Intimidation\n"
        + "25\n"
        + "quit adas aa afd n\n"
        + "save y\n"
        + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertTrue(this.model.doesCharacterExist("Onion"));
    assertEquals(25, this.model.findCharByName("Onion").getValueOf("Intimidation"));
    assertEquals("WARNING: Quitting will remove the following changes:\n"
            + "Onion (Steven)\n"
            + "Class: Human (New Yorker)\n"
            + "Hp: 100/100\n"
            + "Strength: 1 (+1)\n"
            + "Intelligence: 1 (+1)\n"
            + "Creativity: 1 (+1)\n"
            + "Charisma: 1 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 25 (+1)\n"
            + "Are you sure you want to exit Character editing mode?\n"
            + "Confirm (y or n): "
            + "\nInvalid input.\n"
            + "WARNING: Quitting will remove the following changes:\n"
            + "Onion (Steven)\n"
            + "Class: Human (New Yorker)\n"
            + "Hp: 100/100\n"
            + "Strength: 1 (+1)\n"
            + "Intelligence: 1 (+1)\n"
            + "Creativity: 1 (+1)\n"
            + "Charisma: 1 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 25 (+1)\n"
            + "Are you sure you want to exit Character editing mode?\n"
            + "Confirm (y or n): "
            + "\nInvalid input.\n"
            + "WARNING: Quitting will remove the following changes:\n"
            + "Onion (Steven)\n"
            + "Class: Human (New Yorker)\n"
            + "Hp: 100/100\n"
            + "Strength: 1 (+1)\n"
            + "Intelligence: 1 (+1)\n"
            + "Creativity: 1 (+1)\n"
            + "Charisma: 1 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 25 (+1)\n"
            + "Are you sure you want to exit Character editing mode?\n"
            + "Confirm (y or n): "
            + "\nInvalid input.\n"
            + "WARNING: Quitting will remove the following changes:\n"
            + "Onion (Steven)\n"
            + "Class: Human (New Yorker)\n"
            + "Hp: 100/100\n"
            + "Strength: 1 (+1)\n"
            + "Intelligence: 1 (+1)\n"
            + "Creativity: 1 (+1)\n"
            + "Charisma: 1 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 25 (+1)\n"
            + "Are you sure you want to exit Character editing mode?\n"
            + "Confirm (y or n): ",
        output.toString().split(Controller.separator)[2].split(EditChar.separator)[3]);

    assertEquals("Save the following changes?\n"
            + "Onion (Steven)\n"
            + "Class: Human (New Yorker)\n"
            + "Hp: 100/100\n"
            + "Strength: 1 (+1)\n"
            + "Intelligence: 1 (+1)\n"
            + "Creativity: 1 (+1)\n"
            + "Charisma: 1 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 25 (+1)\n"
            + "Confirm (y or n): \n"
            + "Onion has been updated!\n"
            + "Now exiting Character editing mode.\n",
        output.toString().split(Controller.separator)[2].split(EditChar.separator)[5]);
  }

  @Test
  public void badInputSavingEditChar() {
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
        + "y\n"
        + "edit-char Onion\n"
        + "edit Intimidation\n"
        + "25\n"
        + "save a y\n"
        + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertTrue(this.model.doesCharacterExist("Onion"));
    assertEquals(25, this.model.findCharByName("Onion").getValueOf("Intimidation"));
    assertEquals("Save the following changes?\n"
            + "Onion (Steven)\n"
            + "Class: Human (New Yorker)\n"
            + "Hp: 100/100\n"
            + "Strength: 1 (+1)\n"
            + "Intelligence: 1 (+1)\n"
            + "Creativity: 1 (+1)\n"
            + "Charisma: 1 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 25 (+1)\n"
            + "Confirm (y or n): \n"
            + "Invalid input.\n"
            + "Save the following changes?\n"
            + "Onion (Steven)\n"
            + "Class: Human (New Yorker)\n"
            + "Hp: 100/100\n"
            + "Strength: 1 (+1)\n"
            + "Intelligence: 1 (+1)\n"
            + "Creativity: 1 (+1)\n"
            + "Charisma: 1 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 25 (+1)\n"
            + "Confirm (y or n): \n"
            + "Onion has been updated!\n"
            + "Now exiting Character editing mode.\n",
        output.toString().split(Controller.separator)[2].split(EditChar.separator)[3]);
  }

  @Test
  public void editCharNoChars() {
    Readable input = new StringReader("edit-char Onion\n"
        + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertFalse(this.model.hasCharacters());
    assertEquals("Awaiting command: \n"
            + "Invalid state: The Manager doesn't have any Characters!\n"
            + "Add Characters using the create-char command.\n",
        output.toString().split(Controller.separator)[1]);
  }

  @Test
  public void noExistCharEditChar() {
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
        + "y\n"
        + "edit-char Steve\n"
        + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertTrue(this.model.doesCharacterExist("Onion"));
    assertEquals("Awaiting command: \n"
            + "Invalid input: The Character Steve doesn't exist in this Manager.\n",
        output.toString().split(Controller.separator)[2]);
  }

}