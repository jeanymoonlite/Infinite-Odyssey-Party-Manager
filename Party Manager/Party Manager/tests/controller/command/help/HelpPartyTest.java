package controller.command.help;

import static org.junit.Assert.assertEquals;

import controller.Controller;
import controller.IOManagerController;
import controller.IOManagerControllerTest;
import java.io.StringReader;
import model.infiniteodysseys.IOManager;
import org.junit.Test;
import view.IOManagerTextView;

public class HelpPartyTest extends IOManagerControllerTest {

  @Test
  public void getSignature() {
    this.model = new IOManager();
    assertEquals("help-party",
        new HelpParty(null, null).getSignature());
  }

  @Test
  public void getDescription() {
    this.model = new IOManager();
    assertEquals("Displays every party-related command.",
        new HelpParty(null, null).getDescription());
  }

  @Test
  public void helpParty() {
    Readable input = new StringReader("help-party quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertEquals("Awaiting command: create-party (name characters...): \n"
            + "\tCreates a new party with the given name and list of characters.\n"
            + "\tEach character's name must be separated by a space and \n"
            + "\tthey should all be on one line.\n"
            + "\n"
            + "edit-party (name): \n"
            + "\tEdits the Party with the given name.\n"
            + "\tThis will put the program into Party Editing mode.\n"
            + "\tA new set of commands will become available in editing mode.\n"
            + "\n"
            + "remove-party (name): \n"
            + "\tRemoves a party with the given name.\n"
            + "\n",
        output.toString().split(Controller.separator)[1]);
  }

}