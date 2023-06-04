package controller.command.help;

import static org.junit.Assert.assertEquals;

import controller.IOManagerController;
import controller.IOManagerControllerTest;
import java.io.StringReader;
import model.infiniteodysseys.IOManager;
import org.junit.Test;
import view.IOManagerTextView;

public class HelpCharTest extends IOManagerControllerTest {

  @Test
  public void helpChar() {
    Readable input = new StringReader("help-char quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertEquals("create-char (name playerName role roleSpecification "
            + "strength intelligence creativity charisma stealth intimidation\n"
            + "\tCreates a new Character with the given information.\n"
            + "\n"
            + "edit-char (name)\n"
            + "\tEdits a Character with the given name.\n"
            + "\tThis will put the program into Character Editing mode.\n"
            + "\tA new set of commands will become available in editing mode.\n"
            + "\n"
            + "remove-char (name)\n"
            + "\tRemoves a Character with the given name\n"
            + "\n",
        output.toString().split("Awaiting command:\n")[1].split("WARNING")[0]);
  }

}