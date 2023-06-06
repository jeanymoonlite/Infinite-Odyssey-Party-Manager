package controller.command.dice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import controller.IOManagerController;
import controller.IOManagerControllerTest;
import controller.IOManagerSeedHolder;
import java.io.StringReader;
import model.infiniteodysseys.IOManager;
import org.junit.Test;
import view.IOManagerTextView;

public class SetSeedTest extends IOManagerControllerTest {

  @Test
  public void setSeed() {
    Readable input = new StringReader("set-seed 2 "
        + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);

    this.controller.start();

    assertEquals(2, IOManagerSeedHolder.getInstance().getSeed());
    assertFalse(IOManagerSeedHolder.getInstance().isUsingSeed());
    assertEquals("Awaiting command:\n"
            + "The seed has been set to 2.\n"
            + "To use the seed, use the use-seed command to enable or disable it.\n"
            + "Awaiting command:\n",
        output.toString().split("\tvii. Human\n")[1].split("WARNING")[0]);
  }

  @Test
  public void badSetSeed() {
    Readable input = new StringReader("set-seed 2.4 "
        + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);

    this.controller.start();

    assertEquals(0, IOManagerSeedHolder.getInstance().getSeed());
    assertFalse(IOManagerSeedHolder.getInstance().isUsingSeed());
    assertEquals("Awaiting command:\n"
            + "Invalid input: A seed cannot be decimal.\n"
            + "Awaiting command:\n",
        output.toString().split("\tvii. Human\n")[1].split("WARNING")[0]);
  }

}