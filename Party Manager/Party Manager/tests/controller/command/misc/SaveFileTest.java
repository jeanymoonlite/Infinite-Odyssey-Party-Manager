package controller.command.misc;

import static org.junit.Assert.*;

import controller.Controller;
import controller.IOManagerController;
import controller.IOManagerControllerTest;
import controller.files.savefiles.Ver100FileValid;
import java.io.File;
import java.io.StringReader;
import model.infiniteodysseys.IOManager;
import org.junit.After;
import org.junit.Test;
import view.IOManagerTextView;

public class SaveFileTest extends IOManagerControllerTest {

  @Test
  public void getSignature() {
    this.model = new IOManager();
    assertEquals("save-file (fileName)",
        new SaveFile(null, null, null).getSignature());
  }

  @Test
  public void getDescription() {
    this.model = new IOManager();
    assertEquals("Saves all of the Characters and Parties in the Manager \n"
            + "to a file. The file will be in the same folder as this program.",
        new SaveFile(null, null, null).getDescription());
  }

  @After
  public void cleanUp() {
    File f = new File("C:\\Users\\Jr641\\Desktop\\Programming\\Java Programs\\Infinite-Odyssey-Party-Manager\\Party Manager\\Party Manager\\out\\production\\grumps.iom");
    f.delete();
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

//    File f = new File(filePath);
//    if (!f.delete()) fail();
  }
}