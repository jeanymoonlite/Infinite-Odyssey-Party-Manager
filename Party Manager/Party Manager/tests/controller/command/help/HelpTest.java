package controller.command.help;

import static org.junit.Assert.assertEquals;

import controller.IOManagerController;
import controller.IOManagerControllerTest;
import java.io.StringReader;
import model.infiniteodysseys.IOManager;
import org.junit.Test;
import view.IOManagerTextView;

public class HelpTest extends IOManagerControllerTest {

  @Test
  public void getSignature() {
    this.model = new IOManager();
    assertEquals("help",
        new Help(null, null).getSignature());
  }

  @Test
  public void getDescription() {
    this.model = new IOManager();
    assertEquals("Displays the various help commands.\n"
        + "When in Character or Party editing mode, this command displays the edit specific commands.",
        new Help(null, null).getDescription());
  }

  @Test
  public void help() {
    Readable input = new StringReader("help quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertEquals("All Help Commands:\n"
            + "help: \n"
            + "\tDisplays the various help commands.\n"
            + "\tWhen in Character or Party editing mode, this command displays the edit specific commands.\n"
            + "\n"
            + "help-all\n"
            + "\tDisplays every command in the program organized by categories.\n"
            + "\n"
            + "help-char\n"
            + "\tDisplays every character-related command.\n"
            + "\n"
            + "help-party\n"
            + "\tDisplays every party-related command.\n"
            + "\n"
            + "help-manager\n"
            + "\tDisplays commands for viewing characters and parties.\n"
            + "\n"
            + "help-stats\n"
            + "\tDisplays every command for healing and damaging characters.\n"
            + "\n"
            + "help-dice\n"
            + "\tDisplays every dice-related command.\n"
            + "\n",
        output.toString().split("Awaiting command:\n")[1].split("WARNING")[0]);
  }

}