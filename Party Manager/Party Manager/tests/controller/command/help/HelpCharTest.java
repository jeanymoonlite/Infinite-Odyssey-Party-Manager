package controller.command.help;

import static org.junit.Assert.assertEquals;

import controller.Controller;
import controller.IOManagerController;
import controller.IOManagerControllerTest;
import java.io.StringReader;
import model.infiniteodysseys.IOManager;
import org.junit.Test;
import view.IOManagerTextView;

public class HelpCharTest extends IOManagerControllerTest {

  @Test
  public void getSignature() {
    this.model = new IOManager();
    assertEquals("help-char",
        new HelpChar(null, null).getSignature());
  }

  @Test
  public void getDescription() {
    this.model = new IOManager();
    assertEquals("Displays every character-related command.",
        new HelpChar(null, null).getDescription());
  }

  @Test
  public void helpChar() {
    Readable input = new StringReader("help-char quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertEquals("Awaiting command: create-char (name playerName class classSpecification\n"
            + "             strength intelligence creativity\n"
            + "             charisma stealth intimidation): \n"
            + "\tCreates a new Character with the given information.\n"
            + "\n"
            + "edit-char (name): \n"
            + "\tEdits the Character with the given name.\n"
            + "\tThis will put the program into Character Editing mode.\n"
            + "\tA new set of commands will become available in editing mode.\n"
            + "\n"
            + "remove-char (name): \n"
            + "\tRemoves a Character with the given name.\n"
            + "\n",
        output.toString().split(Controller.separator)[1]);
  }

}