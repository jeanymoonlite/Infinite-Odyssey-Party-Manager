package controller.command.help;

import static org.junit.Assert.assertEquals;

import controller.IOManagerController;
import controller.IOManagerControllerTest;
import java.io.StringReader;
import model.infiniteodysseys.IOManager;
import org.junit.Test;
import view.IOManagerTextView;

public class HelpStatsTest extends IOManagerControllerTest {

  @Test
  public void getSignature() {
    this.model = new IOManager();
    assertEquals("help-stats",
        new HelpStats(null, null).getSignature());
  }

  @Test
  public void getDescription() {
    this.model = new IOManager();
    assertEquals("Displays every command for healing and damaging characters.",
        new HelpStats(null, null).getDescription());
  }

  @Test
  public void helpStats() {
    Readable input = new StringReader("help-stats quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertEquals("heal (amount name)\n"
            + "\tAdds the amount to the character's hp with the given name.\n"
            + "\tThis command rejects any non-integers/negative numbers.\n"
            + "\n"
            + "damage (amount name)\n"
            + "\tSubtracts the amount to the character's hp with the given name.\n"
            + "\tThis command rejects any non-integers/negative numbers.\n"
            + "\n"
            + "heal-all (amount)\n"
            + "\tAdds the amount to every character in the active party.\n"
            + "\n"
            + "damage-all (amount)\n"
            + "\tSubtracts the amount to every character in the active party\n"
            + "\n",
        output.toString().split("Awaiting command:\n")[1].split("WARNING")[0]);
  }

}