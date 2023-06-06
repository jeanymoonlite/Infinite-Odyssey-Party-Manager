package controller.command.dice;

import static org.junit.Assert.*;

import controller.IOManagerController;
import controller.IOManagerControllerTest;
import controller.IOManagerSeedHolder;
import java.io.StringReader;
import model.infiniteodysseys.IOManager;
import org.junit.Test;
import view.IOManagerTextView;

public class UseSeedTest extends IOManagerControllerTest {

  @Test
  public void useSeedYes() {
    Readable input = new StringReader("set-seed 2 use-seed y "
        + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);

    this.controller.start();

    assertEquals(2, IOManagerSeedHolder.getInstance().getSeed());
    assertTrue(IOManagerSeedHolder.getInstance().isUsingSeed());
    assertEquals("Awaiting command:\n"
            + "The seed has been set to 2.\n"
            + "To use the seed, use the use-seed command to enable or disable it.\n"
            + "Awaiting command:\n"
            + "Use the seed 2 for all dice commands? (Confirm y or n): \n"
            + "The Manager is now using the seed 2.\n"
            + "Awaiting command:\n",
        output.toString().split("\tvii. Human\n")[1].split("WARNING")[0]);
  }

  @Test
  public void useSeedWithNoSeed() {
    Readable input = new StringReader("use-seed "
        + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);

    this.controller.start();

    assertEquals(0, IOManagerSeedHolder.getInstance().getSeed());
    assertFalse(IOManagerSeedHolder.getInstance().isUsingSeed());
    assertEquals("Awaiting command:\n"
            + "A seed has not been set for this Manager.\n"
            + "Use the set-seed command to set a seed.\n"
            + "Awaiting command:\n",
        output.toString().split("\tvii. Human\n")[1].split("WARNING")[0]);
  }

  @Test
  public void useSeedNo() {
    Readable input = new StringReader("set-seed 2 use-seed n "
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
            + "Awaiting command:\n"
            + "Use the seed 2 for all dice commands? (Confirm y or n): \n"
            + "The Manager will not use a seed.\n"
            + "Awaiting command:\n",
        output.toString().split("\tvii. Human\n")[1].split("WARNING")[0]);
  }

  @Test
  public void useSeedBadInput() {
    Readable input = new StringReader("set-seed 2 use-seed s n "
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
            + "Awaiting command:\n"
            + "Use the seed 2 for all dice commands? (Confirm y or n): \n"
            + "Invalid input.\n"
            + "Use the seed 2 for all dice commands? (Confirm y or n): \n"
            + "The Manager will not use a seed.\n"
            + "Awaiting command:\n",
        output.toString().split("\tvii. Human\n")[1].split("WARNING")[0]);
  }

}