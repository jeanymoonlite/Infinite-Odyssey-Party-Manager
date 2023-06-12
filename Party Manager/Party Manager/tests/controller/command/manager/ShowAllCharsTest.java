package controller.command.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import controller.Controller;
import controller.IOManagerController;
import controller.IOManagerControllerTest;
import java.io.StringReader;
import model.infiniteodysseys.IOManager;
import org.junit.Test;
import view.IOManagerTextView;

public class ShowAllCharsTest extends IOManagerControllerTest {

  @Test
  public void getSignature() {
    this.model = new IOManager();
    assertEquals("show-all-chars",
        new ShowAllChars(null, null).getSignature());
  }

  @Test
  public void getDescription() {
    this.model = new IOManager();
    assertEquals("Displays a list of every character with their name and player name.",
        new ShowAllChars(null, null).getDescription());
  }

  @Test
  public void successfulShowAllChars() {
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
        + "Lunarose\n"
        + "Luna\n"
        + "Wizard\n"
        + "Fire Mage\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "y\n"
        + "show-all-chars\n"
        + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertTrue(this.model.doesCharacterExist("Onion"));
    assertEquals("Awaiting command: Onion (Steven)\n"
            + "Lunarose (Luna)\n"
            + "Total Characters: 2\n",
        output.toString().split(Controller.separator)[3]);
  }

  @Test
  public void noCharsShowAllChars() {
    Readable input = new StringReader("show-all-chars\n"
        + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertEquals("Awaiting command: Invalid state: The Manager doesn't have any Characters!\n"
            + "Add Characters using the create-char command.\n",
        output.toString().split(Controller.separator)[1]);
  }

}