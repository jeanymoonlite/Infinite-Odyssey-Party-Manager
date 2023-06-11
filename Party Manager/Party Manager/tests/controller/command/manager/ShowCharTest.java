package controller.command.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import controller.IOManagerController;
import controller.IOManagerControllerTest;
import java.io.StringReader;
import model.infiniteodysseys.IOManager;
import org.junit.Test;
import view.IOManagerTextView;

public class ShowCharTest extends IOManagerControllerTest {

  @Test
  public void getSignature() {
    this.model = new IOManager();
    assertEquals("show-char (name)",
        new ShowChar(this.model, null, null).getSignature());
  }

  @Test
  public void getDescription() {
    this.model = new IOManager();
    assertEquals("Displays the name, class, class specification, and stats \n"
            + "of the specified character.",
        new ShowChar(this.model, null, null).getDescription());
  }

  @Test
  public void successfulShowChar() {
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
        + "show-char Onion\n"
        + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertTrue(this.model.doesCharacterExist("Onion"));
    assertTrue(this.model.doesCharacterExist("Lunarose"));
    assertEquals("Onion (Steven)\n"
            + "Class: Human\n"
            + "Hp: 100/100\n"
            + "Strength: 1 (+1)\n"
            + "Intelligence: 1 (+1)\n"
            + "Creativity: 1 (+1)\n"
            + "Charisma: 1 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 1 (+1)\n",
        output.toString()
            .split("WARNING")[0]
            .split("Awaiting command:\n")[3]);
  }

  @Test
  public void noCharsShowChar() {
    Readable input = new StringReader("show-char Onion\n"
        + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertEquals("The Manager doesn't have any Characters!\n"
            + "Add Characters using the create-char command.\n",
        output.toString()
            .split("WARNING")[0]
            .split("Awaiting command:\n")[1]);
  }

  @Test
  public void doesntExistShowChar() {
    Readable input = new StringReader("create-char\n"
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
        + "show-char Onion\n"
        + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertTrue(this.model.doesCharacterExist("Lunarose"));
    assertEquals("Invalid input: The Character Onion doesn't exist in this Manager.\n",
        output.toString()
            .split("WARNING")[0]
            .split("Awaiting command:\n")[2]);
  }
}