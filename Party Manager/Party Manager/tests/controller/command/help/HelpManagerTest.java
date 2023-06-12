package controller.command.help;

import static org.junit.Assert.assertEquals;

import controller.Controller;
import controller.IOManagerController;
import controller.IOManagerControllerTest;
import java.io.StringReader;
import model.infiniteodysseys.IOManager;
import org.junit.Test;
import view.IOManagerTextView;

public class HelpManagerTest extends IOManagerControllerTest {

  @Test
  public void getSignature() {
    this.model = new IOManager();
    assertEquals("help-manager",
        new HelpManager(null, null).getSignature());
  }

  @Test
  public void getDescription() {
    this.model = new IOManager();
    assertEquals("Displays commands for viewing characters and parties.",
        new HelpManager(null, null).getDescription());
  }

  @Test
  public void helpManager() {
    Readable input = new StringReader("help-manager quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertEquals("Awaiting command: party: \n"
            + "\tDisplays the active party if there is one.\n"
            + "\n"
            + "show-all-chars: \n"
            + "\tDisplays a list of every character with their name and player name.\n"
            + "\n"
            + "show-all-parties: \n"
            + "\tDisplays a list of every party with their name, followed \n"
            + "\tby the characters within them.\n"
            + "\n"
            + "show-char (name): \n"
            + "\tDisplays the name, class, class specification, and stats \n"
            + "\tof the specified character.\n"
            + "\n"
            + "show-party (name): \n"
            + "\tDisplays the name, class, class specification, and stats of \n"
            + "\tthe every character in the specified party.\n"
            + "\n",
        output.toString().split(Controller.separator)[1]);
  }

}