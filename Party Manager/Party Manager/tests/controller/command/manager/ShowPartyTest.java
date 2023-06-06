package controller.command.manager;

import static org.junit.Assert.*;

import controller.IOManagerController;
import controller.IOManagerControllerTest;
import java.io.StringReader;
import model.infiniteodysseys.IOManager;
import org.junit.Test;
import view.IOManagerTextView;

public class ShowPartyTest extends IOManagerControllerTest {

  @Test
  public void successfulShowParty() {
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
        + "create-party\n"
        + "Ninja Sex Party\n"
        + "Danny Sexbang, Ninja Brian\n"
        + "y\n"
        + "show-party Ninja Sex Party\n"
        + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertTrue(this.model.doesCharacterExist("Danny Sexbang"));
    assertTrue(this.model.doesCharacterExist("Ninja Brian"));
    assertEquals("Danny Sexbang (Dan)\n"
            + "Role: Bard (Lover)\n"
            + "Hp: 100/100 (+1 Def)\n"
            + "Strength: 1\n"
            + "Intelligence: 1\n"
            + "Creativity: 1\n"
            + "Charisma: 1 (+4)\n"
            + "Stealth: 1\n"
            + "Intimidation: 1\n"
            + "\n"
            + "Ninja Brian (Brian)\n"
            + "Role: Rogue (Ninja)\n"
            + "Hp: 100/100 (-5 Def)\n"
            + "Strength: 1 (+2)\n"
            + "Intelligence: 1\n"
            + "Creativity: 1\n"
            + "Charisma: 1\n"
            + "Stealth: 1 (+3)\n"
            + "Intimidation: 1\n",
        output.toString()
            .split("WARNING")[0]
            .split("Awaiting command:\n")[4]);
  }

  @Test
  public void noPartyShowParty() {
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
        + "show-party Ninja Sex Party\n"
        + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertTrue(this.model.doesCharacterExist("Danny Sexbang"));
    assertTrue(this.model.doesCharacterExist("Ninja Brian"));
    assertFalse(this.model.doesPartyExist("Ninja Sex Party"));
    assertEquals("The Manager doesn't have any Parties!\n"
            + "Add Parties using the create-party command.\n",
        output.toString()
            .split("WARNING")[0]
            .split("Awaiting command:\n")[3]);
  }

  @Test
  public void doesntExistShowParty() {
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
        + "create-party\n"
        + "Ninja Sex Party\n"
        + "Danny Sexbang, Ninja Brian\n"
        + "y\n"
        + "show-party The Boys\n"
        + ""
        + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertTrue(this.model.doesCharacterExist("Danny Sexbang"));
    assertTrue(this.model.doesCharacterExist("Ninja Brian"));
    assertTrue(this.model.doesPartyExist("Ninja Sex Party"));
    assertEquals("The Party The Boys doesn't exist in this Manager.\n",
        output.toString()
            .split("WARNING")[0]
            .split("Awaiting command:\n")[4]);
  }
}