package controller.command.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import controller.Controller;
import controller.IOManagerController;
import controller.IOManagerControllerTest;
import java.io.StringReader;
import model.infiniteodysseys.IOManager;
import org.junit.Test;
import view.IOManagerTextView;

public class ShowAllPartiesTest extends IOManagerControllerTest {

  @Test
  public void getSignature() {
    this.model = new IOManager();
    assertEquals("show-all-parties",
        new ShowAllParties(this.model, null).getSignature());
  }

  @Test
  public void getDescription() {
    this.model = new IOManager();
    assertEquals("Displays a list of every party with their name, followed \n"
            + "by the characters within them.",
        new ShowAllParties(this.model, null).getDescription());
  }

  @Test
  public void successfulShowAllParties() {
    Readable input = new StringReader("create-char\n"
        + "Danny Sexbang\n"
        + "Dan\n"
        + "Bard\n"
        + "Lover\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "y\n"
        + "create-char\n"
        + "Ninja Brian\n"
        + "Brian\n"
        + "Rogue\n"
        + "Ninja\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "y\n"
        + "create-char\n"
        + "Egoraptor\n"
        + "Arin\n"
        + "Warrior\n"
        + "Knight\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "y\n"
        + "create-party\n"
        + "Ninja Sex Party\n"
        + "Danny Sexbang, Ninja Brian\n"
        + "y\n"
        + "create-party\n"
        + "Game Grumps\n"
        + "Danny Sexbang, Egoraptor\n"
        + "y\n"
        + "show-all-parties\n"
        + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertTrue(this.model.doesCharacterExist("Danny Sexbang"));
    assertTrue(this.model.doesCharacterExist("Ninja Brian"));
    assertTrue(this.model.doesCharacterExist("Egoraptor"));
    assertTrue(this.model.doesPartyExist("Ninja Sex Party"));
    assertTrue(this.model.doesPartyExist("Game Grumps"));
    assertEquals("Awaiting command: "
            + "Ninja Sex Party: Danny Sexbang (Dan), Ninja Brian (Brian)\n"
            + "Game Grumps: Danny Sexbang (Dan), Egoraptor (Arin)\n"
            + "Total Parties: 2\n",
        output.toString().split(Controller.separator)[6]);
  }

  @Test
  public void noPartyShowAllParties() {
    Readable input = new StringReader("show-all-parties\n"
        + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertEquals("Awaiting command: Invalid state: The Manager doesn't have any Parties!\n"
            + "Add Parties using the create-party command.\n",
        output.toString().split(Controller.separator)[1]);
  }
}