package controller.command.misc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import controller.Controller;
import controller.IOManagerController;
import controller.IOManagerControllerTest;
import controller.files.savefiles.Ver100FileValid;
import java.io.File;
import java.io.StringReader;
import model.Manager;
import model.infiniteodysseys.IOManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import view.IOManagerTextView;
import view.TextView;

public class SaveFileTest {

  protected IOManagerController controller;
  protected TextView view;
  protected Manager model;

  @Test
  public void getSignature() {
    this.model = new IOManager();
    assertEquals("save (fileName)",
        new SaveFile(null, null, null).getSignature());
  }

  @Test
  public void getDescription() {
    this.model = new IOManager();
    assertEquals("Saves all of the Characters and Parties in the Manager \n"
            + "to a file. The file will be in the same folder as this program.",
        new SaveFile(null, null, null).getDescription());
  }

  private final String dir = "C:\\Users\\Jr641\\Desktop\\Programming\\Java Programs\\Infinite-Odyssey-Party-Manager\\Party Manager\\Party Manager\\out\\production\\";
  private final String[] fileNames = new String[] {"grumps.iom", "grumper.iom", "grumping.iom"};

  @Before
  public void setUp() {
    File file = new File(dir);
    if (!file.isDirectory()) {
      fail();
    }

    for (String s : fileNames) {
      file = new File(dir,s);
      if (file.exists()) {
        file.delete();
      }
    }
  }

  @After
  public void cleanUp() {
    File file = new File(dir);
    if (!file.isDirectory()) {
      fail();
    }

    for (String s : fileNames) {
      file = new File(dir,s);
      if (file.exists()) {
        file.delete();
      }
    }
  }

  @Test
  public void successfulSaveFile() {
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
        + "create-char\n"
        + "Egoraptor\n"
        + "Arin\n"
        + "Warrior\n"
        + "Knight\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "y\n"
        + "create-party\n"
        + "Star Bomb\n"
        + "Danny Sexbang, Egoraptor, Ninja Brian\n"
        + "y\n"
        + "create-party\n"
        + "Game Grumps\n"
        + "Danny Sexbang, Egoraptor\n"
        + "y\n"
        + "create-party\n"
        + "Ninja Sex Party\n"
        + "Danny Sexbang, Ninja Brian\n"
        + "y\n"
        + "save grumps\n"
        + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertTrue(this.model.doesCharacterExist("Danny Sexbang"));
    assertTrue(this.model.doesCharacterExist("Ninja Brian"));
    assertTrue(this.model.doesPartyExist("Ninja Sex Party"));
    assertTrue(this.model.doesPartyExist("Game Grumps"));
    assertTrue(this.model.doesPartyExist("Star Bomb"));

    String filePath = "C:\\Users\\Jr641\\Desktop\\Programming\\Java Programs\\Infinite-Odyssey-Party-Manager\\Party Manager\\Party Manager\\out\\production\\grumps.iom";
    assertEquals("Awaiting command: File created at " + filePath, output.toString().split(Controller.separator)[7]);

    assertTrue(new Ver100FileValid().isValid(filePath));
  }

  @Test
  public void badNamesSaveFile() {
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
        + "create-char\n"
        + "Egoraptor\n"
        + "Arin\n"
        + "Warrior\n"
        + "Knight\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "y\n"
        + "create-party\n"
        + "Star Bomb\n"
        + "Danny Sexbang, Egoraptor, Ninja Brian\n"
        + "y\n"
        + "create-party\n"
        + "Game Grumps\n"
        + "Danny Sexbang, Egoraptor\n"
        + "y\n"
        + "create-party\n"
        + "Ninja Sex Party\n"
        + "Danny Sexbang, Ninja Brian\n"
        + "y\n"
        + "save \n"
        + "save file.txt\n"
        + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertTrue(this.model.doesCharacterExist("Danny Sexbang"));
    assertTrue(this.model.doesCharacterExist("Ninja Brian"));
    assertTrue(this.model.doesPartyExist("Ninja Sex Party"));
    assertTrue(this.model.doesPartyExist("Game Grumps"));
    assertTrue(this.model.doesPartyExist("Star Bomb"));

    String filePath = "C:\\Users\\Jr641\\Desktop\\Programming\\Java Programs\\Infinite-Odyssey-Party-Manager\\Party Manager\\Party Manager\\out\\production\\grumps.iom";
    assertEquals("Awaiting command: Invalid input: File name cannot be whitespace.\n",
        output.toString().split(Controller.separator)[7]);
    assertEquals("Awaiting command: Invalid input: File name cannot contain a period.\n",
        output.toString().split(Controller.separator)[8]);

    assertTrue(new Ver100FileValid().isValid(filePath));
  }

  @Test
  public void noCharsSaveFile() {
    Readable input = new StringReader("save file\n"
        + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertFalse(this.model.hasCharacters());
    assertFalse(this.model.hasParties());

    String filePath = "C:\\Users\\Jr641\\Desktop\\Programming\\Java Programs\\Infinite-Odyssey-Party-Manager\\Party Manager\\Party Manager\\out\\production\\grumps.iom";
    assertEquals("Awaiting command: \n"
            + "Invalid state: The Manager doesn't have any Characters!\n"
            + "Add Characters using the create-char command.\n",
        output.toString().split(Controller.separator)[1]);
  }

  @Test
  public void noPartiesSaveFile() {
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
        + "create-char\n"
        + "Egoraptor\n"
        + "Arin\n"
        + "Warrior\n"
        + "Knight\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "y\n"
        + "save grumping\n"
        + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertTrue(this.model.doesCharacterExist("Danny Sexbang"));
    assertTrue(this.model.doesCharacterExist("Ninja Brian"));
    assertTrue(this.model.doesCharacterExist("Egoraptor"));
    assertFalse(this.model.doesPartyExist("Ninja Sex Party"));
    assertFalse(this.model.doesPartyExist("Game Grumps"));
    assertFalse(this.model.doesPartyExist("Star Bomb"));

    String filePath = "C:\\Users\\Jr641\\Desktop\\Programming\\Java Programs\\Infinite-Odyssey-Party-Manager\\Party Manager\\Party Manager\\out\\production\\grumping.iom";
    assertEquals("Awaiting command: File created at " + filePath,
        output.toString().split(Controller.separator)[4]);

    assertTrue(new Ver100FileValid().isValid(filePath));
  }

  @Test
  public void overwriteFile() {
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
        + "create-char\n"
        + "Egoraptor\n"
        + "Arin\n"
        + "Warrior\n"
        + "Knight\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "y\n"
        + "create-party\n"
        + "Star Bomb\n"
        + "Danny Sexbang, Egoraptor, Ninja Brian\n"
        + "y\n"
        + "create-party\n"
        + "Game Grumps\n"
        + "Danny Sexbang, Egoraptor\n"
        + "y\n"
        + "create-party\n"
        + "Ninja Sex Party\n"
        + "Danny Sexbang, Ninja Brian\n"
        + "y\n"
        + "save grumper\n"
        + "remove-party Ninja Sex Party\n"
        + "y\n"
        + "save grumper\n"
        + "a\n"
        + "n\n"
        + "save grumper\n"
        + "y\n"
        + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertTrue(this.model.doesCharacterExist("Danny Sexbang"));
    assertTrue(this.model.doesCharacterExist("Ninja Brian"));
    assertFalse(this.model.doesPartyExist("Ninja Sex Party"));
    assertTrue(this.model.doesPartyExist("Game Grumps"));
    assertTrue(this.model.doesPartyExist("Star Bomb"));

    String filePath = dir + "grumper.iom";
    assertEquals("Awaiting command: File created at " + filePath, output.toString().split(Controller.separator)[7]);
    assertTrue(new Ver100FileValid().isValid(filePath));
    assertEquals("Awaiting command: "
            + "\nThere exists a file named grumper.iom.\n"
            + "Do you want to overwrite " + filePath + "?\n"
            + "Confirm (y or n): "
            + "\nInvalid input.\n"
            + "There exists a file named grumper.iom.\n"
            + "Do you want to overwrite " + filePath + "?\n"
            + "Confirm (y or n): "
        , output.toString().split(Controller.separator)[9]);

    assertEquals("Awaiting command: "
            + "\nThere exists a file named grumper.iom.\n"
            + "Do you want to overwrite " + filePath + "?\n"
            + "Confirm (y or n): "
            + "The file " + filePath + " has been overwritten.\n"
        , output.toString().split(Controller.separator)[10]);
  }
}