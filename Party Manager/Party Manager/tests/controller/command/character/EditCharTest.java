package controller.command.character;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
    assertEquals("Edits a Character with the given name.\n"
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
    assertEquals("You are now in character editing mode.\n"
            + "Editing: Onion (Steven)\n"
            + "Onion (Steven)\n"
            + "Class: Human (New Yorker)\n"
            + "Hp: 100/100\n"
            + "Strength: 1 (+1)\n"
            + "Intelligence: 1 (+1)\n"
            + "Creativity: 1 (+1)\n"
            + "Charisma: 1 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 1 (+1)\n"
            + "Awaiting edit command:\n"
            + "Current Character name: Onion\n"
            + "New Character name: \n"
            + "Esteban (Steven)\n"
            + "Class: Human (New Yorker)\n"
            + "Hp: 100/100\n"
            + "Strength: 1 (+1)\n"
            + "Intelligence: 1 (+1)\n"
            + "Creativity: 1 (+1)\n"
            + "Charisma: 1 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 1 (+1)\n"
            + "Awaiting edit command:\n",
        output.toString().split("Awaiting command:\n")[2].split("WARNING")[0]);
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
        + "Rebecca\n"
        + "save\n"
        + "y quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertTrue(this.model.doesCharacterExist("Onion"));
    assertEquals("Rebecca", this.model.findCharByName("Onion").getPlayerName());
    assertEquals("You are now in character editing mode.\n"
            + "Editing: Onion (Steven)\n"
            + "Onion (Steven)\n"
            + "Class: Human (New Yorker)\n"
            + "Hp: 100/100\n"
            + "Strength: 1 (+1)\n"
            + "Intelligence: 1 (+1)\n"
            + "Creativity: 1 (+1)\n"
            + "Charisma: 1 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 1 (+1)\n"
            + "Awaiting edit command:\n"
            + "Current Player name: Steven\n"
            + "New Player name: \n"
            + "Onion (Rebecca)\n"
            + "Class: Human (New Yorker)\n"
            + "Hp: 100/100\n"
            + "Strength: 1 (+1)\n"
            + "Intelligence: 1 (+1)\n"
            + "Creativity: 1 (+1)\n"
            + "Charisma: 1 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 1 (+1)\n"
            + "Awaiting edit command:\n",
        output.toString().split("Awaiting command:\n")[2].split("WARNING")[0]);
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
        + "edit role\n"
        + "Wizard\n"
        + "save\n"
        + "y quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertTrue(this.model.doesCharacterExist("Onion"));
    assertEquals("Wizard", this.model.findCharByName("Onion").getClass());
    assertEquals("You are now in character editing mode.\n"
            + "Editing: Onion (Steven)\n"
            + "Onion (Steven)\n"
            + "Class: Human (New Yorker)\n"
            + "Hp: 100/100\n"
            + "Strength: 1 (+1)\n"
            + "Intelligence: 1 (+1)\n"
            + "Creativity: 1 (+1)\n"
            + "Charisma: 1 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 1 (+1)\n"
            + "Awaiting edit command:\n"
            + "Current Class: Human\n"
            + "New Class: \n"
            + "Onion (Steven)\n"
            + "Class: Wizard (New Yorker)\n"
            + "Hp: 100/100\n"
            + "Strength: 1\n"
            + "Intelligence: 1\n"
            + "Creativity: 1 (+5)\n"
            + "Charisma: 1\n"
            + "Stealth: 1\n"
            + "Intimidation: 1\n"
            + "Awaiting edit command:\n",
        output.toString().split("Awaiting command:\n")[2].split("WARNING")[0]);
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
        + "edit role spec\n"
        + "Salvadorian\n"
        + "save\n"
        + "y quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertTrue(this.model.doesCharacterExist("Onion"));
    assertEquals("Salvadorian", this.model.findCharByName("Onion").getSpecification());

    assertEquals("You are now in character editing mode.\n"
            + "Editing: Onion (Steven)\n"
            + "Onion (Steven)\n"
            + "Class: Human (New Yorker)\n"
            + "Hp: 100/100\n"
            + "Strength: 1 (+1)\n"
            + "Intelligence: 1 (+1)\n"
            + "Creativity: 1 (+1)\n"
            + "Charisma: 1 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 1 (+1)\n"
            + "Awaiting edit command:\n"
            + "Current Class Specification: New Yorker\n"
            + "New Class Specification (Type enter to leave blank): \n"
            + "Onion (Steven)\n"
            + "Class: Human (Salvadorian)\n"
            + "Hp: 100/100\n"
            + "Strength: 1 (+1)\n"
            + "Intelligence: 1 (+1)\n"
            + "Creativity: 1 (+1)\n"
            + "Charisma: 1 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 1 (+1)\n"
            + "Awaiting edit command:\n",
        output.toString().split("Awaiting command:\n")[2].split("WARNING")[0]);
  }

  @Test
  public void successfulEditCharStrength() {
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
        + "2\n"
        + "save\n"
        + "y quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertTrue(this.model.doesCharacterExist("Onion"));
//    assertEquals(2, this.model.findCharByName("Onion").getValueOf("Strength"));
    assertEquals("You are now in character editing mode.\n"
            + "Editing: Onion (Steven)\n"
            + "Onion (Steven)\n"
            + "Class: Human (New Yorker)\n"
            + "Hp: 100/100\n"
            + "Strength: 1 (+1)\n"
            + "Intelligence: 1 (+1)\n"
            + "Creativity: 1 (+1)\n"
            + "Charisma: 1 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 1 (+1)\n"
            + "Awaiting edit command:\n"
            + "Current Strength: 1\n"
            + "New Strength: \n"
            + "Onion (Steven)\n"
            + "Class: Human (New Yorker)\n"
            + "Hp: 100/100\n"
            + "Strength: 2 (+1)\n"
            + "Intelligence: 1 (+1)\n"
            + "Creativity: 1 (+1)\n"
            + "Charisma: 1 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 1 (+1)\n"
            + "Awaiting edit command:\n",
        output.toString().split("Awaiting command:\n")[2].split("WARNING")[0]);
  }

  @Test
  public void successfulEditCharIntelligence() {
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
        + "edit intelligence\n"
        + "2\n"
        + "save\n"
        + "y quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertTrue(this.model.doesCharacterExist("Onion"));
    assertEquals(2, this.model.findCharByName("Onion").getValueOf("intelligence"));
    assertEquals("You are now in character editing mode.\n"
            + "Editing: Onion (Steven)\n"
            + "Onion (Steven)\n"
            + "Class: Human (New Yorker)\n"
            + "Hp: 100/100\n"
            + "Strength: 1 (+1)\n"
            + "Intelligence: 1 (+1)\n"
            + "Creativity: 1 (+1)\n"
            + "Charisma: 1 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 1 (+1)\n"
            + "Awaiting edit command:\n"
            + "Current Intelligence: 1\n"
            + "New Intelligence: \n"
            + "Onion (Steven)\n"
            + "Class: Human (New Yorker)\n"
            + "Hp: 100/100\n"
            + "Strength: 1 (+1)\n"
            + "Intelligence: 2 (+1)\n"
            + "Creativity: 1 (+1)\n"
            + "Charisma: 1 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 1 (+1)\n"
            + "Awaiting edit command:\n",
        output.toString().split("Awaiting command:\n")[2].split("WARNING")[0]);
  }

  @Test
  public void successfulEditCharCreativity() {
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
        + "edit creativity\n"
        + "2\n"
        + "save\n"
        + "y quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertTrue(this.model.doesCharacterExist("Onion"));
    assertEquals(2, this.model.findCharByName("Onion").getValueOf("creativity"));
    assertEquals("You are now in character editing mode.\n"
            + "Editing: Onion (Steven)\n"
            + "Onion (Steven)\n"
            + "Class: Human (New Yorker)\n"
            + "Hp: 100/100\n"
            + "Strength: 1 (+1)\n"
            + "Intelligence: 1 (+1)\n"
            + "Creativity: 1 (+1)\n"
            + "Charisma: 1 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 1 (+1)\n"
            + "Awaiting edit command:\n"
            + "Current Creativity: 1\n"
            + "New Creativity: \n"
            + "Onion (Steven)\n"
            + "Class: Human (New Yorker)\n"
            + "Hp: 100/100\n"
            + "Strength: 1 (+1)\n"
            + "Intelligence: 1 (+1)\n"
            + "Creativity: 2 (+1)\n"
            + "Charisma: 1 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 1 (+1)\n"
            + "Awaiting edit command:\n",
        output.toString().split("Awaiting command:\n")[2].split("WARNING")[0]);
  }

  @Test
  public void successfulEditCharCharisma() {
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
        + "edit Charisma\n"
        + "2\n"
        + "save\n"
        + "y quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertTrue(this.model.doesCharacterExist("Onion"));
    assertEquals(2, this.model.findCharByName("Onion").getValueOf("Charisma"));
    assertEquals("You are now in character editing mode.\n"
            + "Editing: Onion (Steven)\n"
            + "Onion (Steven)\n"
            + "Class: Human (New Yorker)\n"
            + "Hp: 100/100\n"
            + "Strength: 1 (+1)\n"
            + "Intelligence: 1 (+1)\n"
            + "Creativity: 1 (+1)\n"
            + "Charisma: 1 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 1 (+1)\n"
            + "Awaiting edit command:\n"
            + "Current Charisma: 1\n"
            + "New Charisma: \n"
            + "Onion (Steven)\n"
            + "Class: Human (New Yorker)\n"
            + "Hp: 100/100\n"
            + "Strength: 1 (+1)\n"
            + "Intelligence: 1 (+1)\n"
            + "Creativity: 1 (+1)\n"
            + "Charisma: 2 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 1 (+1)\n"
            + "Awaiting edit command:\n",
        output.toString().split("Awaiting command:\n")[2].split("WARNING")[0]);
  }

  @Test
  public void successfulEditCharStealth() {
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
        + "edit Stealth\n"
        + "2\n"
        + "save\n"
        + "y quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertTrue(this.model.doesCharacterExist("Onion"));
    assertEquals(2, this.model.findCharByName("Onion").getValueOf("Stealth"));
    assertEquals("You are now in character editing mode.\n"
            + "Editing: Onion (Steven)\n"
            + "Onion (Steven)\n"
            + "Class: Human (New Yorker)\n"
            + "Hp: 100/100\n"
            + "Strength: 1 (+1)\n"
            + "Intelligence: 1 (+1)\n"
            + "Creativity: 1 (+1)\n"
            + "Charisma: 1 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 1 (+1)\n"
            + "Awaiting edit command:\n"
            + "Current Stealth: 1\n"
            + "New Stealth: \n"
            + "Onion (Steven)\n"
            + "Class: Human (New Yorker)\n"
            + "Hp: 100/100\n"
            + "Strength: 1 (+1)\n"
            + "Intelligence: 1 (+1)\n"
            + "Creativity: 1 (+1)\n"
            + "Charisma: 1 (+1)\n"
            + "Stealth: 2 (+1)\n"
            + "Intimidation: 1 (+1)\n"
            + "Awaiting edit command:\n",
        output.toString().split("Awaiting command:\n")[2].split("WARNING")[0]);
  }

  @Test
  public void successfulEditCharIntimidation() {
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
        + "2\n"
        + "save\n"
        + "y quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertTrue(this.model.doesCharacterExist("Onion"));
    assertEquals(2, this.model.findCharByName("Onion").getValueOf("Intimidation"));
    assertEquals("You are now in character editing mode.\n"
            + "Editing: Onion (Steven)\n"
            + "Onion (Steven)\n"
            + "Class: Human (New Yorker)\n"
            + "Hp: 100/100\n"
            + "Strength: 1 (+1)\n"
            + "Intelligence: 1 (+1)\n"
            + "Creativity: 1 (+1)\n"
            + "Charisma: 1 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 1 (+1)\n"
            + "Awaiting edit command:\n"
            + "Current Intimidation: 1\n"
            + "New Intimidation: \n"
            + "Onion (Steven)\n"
            + "Class: Human (New Yorker)\n"
            + "Hp: 100/100\n"
            + "Strength: 1 (+1)\n"
            + "Intelligence: 1 (+1)\n"
            + "Creativity: 1 (+1)\n"
            + "Charisma: 1 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 2 (+1)\n"
            + "Awaiting edit command:\n",
        output.toString().split("Awaiting command:\n")[2].split("WARNING")[0]);
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
    assertEquals("WARNING: Quitting will remove any changes made.\n"
            + "Are you sure you want to exit Character editing mode?"
            + " Confirm (y or n): \n"
            + "All changes made to Onion have been undone.\n"
            + "Now exiting Character editing mode.\n"
            + "Awaiting command:\n",
        output.toString().split("Awaiting edit command:\n")[2]
            .split("WARNING: Quitting will delete")[0]);
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
        + "quit\n"
        + "n\n"
        + "save\n"
        + "y\n"
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
            + "Are you sure you want to exit Character editing mode? "
            + "Confirm (y or n): \n"
            + "Onion (Steven)\n"
            + "Class: Human (New Yorker)\n"
            + "Hp: 100/100\n"
            + "Strength: 1 (+1)\n"
            + "Intelligence: 1 (+1)\n"
            + "Creativity: 1 (+1)\n"
            + "Charisma: 1 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 25 (+1)\n",
        output.toString().split("Awaiting edit command:\n")[2]
            .split("WARNING: Quitting will delete")[0]);

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
            + "Onion (Steven)\n"
            + "Class: Human (New Yorker)\n"
            + "Hp: 100/100\n"
            + "Strength: 1 (+1)\n"
            + "Intelligence: 1 (+1)\n"
            + "Creativity: 1 (+1)\n"
            + "Charisma: 1 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 25 (+1)\n"
            + "Now exiting Character editing mode.\n"
            + "Awaiting command:",
        output.toString().split("Awaiting edit command:\n")[3]
            .split("WARNING: Quitting will delete")[0]);
  }

  @Test
  public void editCharDuringCampaign() {
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
        + "create-party\n"
        + "S\n"
        + "Onion\n"
        + "y\n"
        + "start S\n"
        + "y\n"
        + "edit-char Onion\n"
        + "edit Intimidation\n"
        + "2\n"
        + "save\n"
        + "y quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertTrue(this.model.doesCharacterExist("Onion"));
    assertEquals("Invalid state: This command can't be used during a campaign.\n"
            + "Use the quit command to end the current campaign.\n",
        output.toString().split("Awaiting command:\n")[4].split("WARNING")[0]);
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
    assertEquals("The Manager doesn't have any Characters!\n"
            + "Add Characters using the create-char command.\n",
        output.toString().split("Awaiting command:\n")[1].split("WARNING")[0]);
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
    assertEquals("Invalid input: The Character Steve doesn't exist in this Manager.\n",
        output.toString().split("Awaiting command:\n")[2].split("WARNING")[0]);
  }

}