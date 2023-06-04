package controller.command.help;

import static org.junit.Assert.assertEquals;

import controller.IOManagerController;
import controller.IOManagerControllerTest;
import java.io.StringReader;
import model.infiniteodysseys.IOManager;
import org.junit.Test;
import view.IOManagerTextView;

public class HelpPartyTest extends IOManagerControllerTest {

  @Test
  public void helpParty() {
    Readable input = new StringReader("help-party quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertEquals("create-party (name characters...)\n"
            + "\tCreates a new party with the given name and list of characters.\n"
            + "\tEach character's name must be seperated by a space and they should all be on one line.\n"
            + "\tDuplicate characters cannot be used.\n"
            + "\n"
            + "edit-party (name)\n"
            + "\tEdits a Party with the given name.\n"
            + "\tThis will put the program into party editing mode, "
            + "where a new set of commands will be available.\n"
            + "\n"
            + "remove-party (name)\n"
            + "\tRemoves a party with the given name.\n"
            + "\n",
        output.toString().split("Awaiting command:\n")[1].split("WARNING")[0]);
  }

}