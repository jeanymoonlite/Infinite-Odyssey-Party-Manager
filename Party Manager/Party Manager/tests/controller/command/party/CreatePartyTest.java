package controller.command.party;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import controller.Controller;
import controller.IOManagerController;
import controller.IOManagerControllerTest;
import java.io.StringReader;
import java.util.NoSuchElementException;
import model.infiniteodysseys.IOManager;
import org.junit.Test;
import view.IOManagerTextView;

public class CreatePartyTest extends IOManagerControllerTest {

  @Test
  public void getSignature() {
    this.model = new IOManager();
    assertEquals("create-party (name characters...)",
        new CreateParty(null, null, null).getSignature());
  }

  @Test
  public void getDescription() {
    this.model = new IOManager();
    assertEquals("Creates a new party with the given name and list of characters.\n"
            + "Each character's name must be separated by a space and \n"
            + "they should all be on one line.",
        new CreateParty(null, null, null).getDescription());
  }

  @Test
  public void successfulCreateParty() {
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
        + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertTrue(this.model.doesCharacterExist("Danny Sexbang"));
    assertTrue(this.model.doesCharacterExist("Ninja Brian"));
    assertTrue(this.model.doesPartyExist("Ninja Sex Party"));
    assertEquals("Awaiting command: "
            + "Party name: "
            + "Characters (Each by a comma): \n"
            + "Create the following Party?\n"
            + "Party name: Ninja Sex Party\n"
            + "Party members: Danny Sexbang, Ninja Brian\n"
            + "Confirm (y or n): "
            + "The Party Ninja Sex Party was added to the Manager.\n",
        output.toString().split(Controller.separator)[3]);
  }

  @Test
  public void cancellingCreateParty() {
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
        + "n\n"
        + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertTrue(this.model.doesCharacterExist("Danny Sexbang"));
    assertTrue(this.model.doesCharacterExist("Ninja Brian"));
    assertFalse(this.model.doesPartyExist("Ninja Sex Party"));
    assertEquals("Awaiting command: "
            + "Party name: "
            + "Characters (Each by a comma): \n"
            + "Create the following Party?\n"
            + "Party name: Ninja Sex Party\n"
            + "Party members: Danny Sexbang, Ninja Brian\n"
            + "Confirm (y or n): "
            + "The Party Ninja Sex Party will not be created.\n",
        output.toString().split(Controller.separator)[3]);
  }

  @Test
  public void badInputCancellingCreateParty() {
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
        + "a\n"
        + "asdbvbd\n"
        + "d\n"
        + "y\n"
        + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertTrue(this.model.doesCharacterExist("Danny Sexbang"));
    assertTrue(this.model.doesCharacterExist("Ninja Brian"));
    assertTrue(this.model.doesPartyExist("Ninja Sex Party"));
    assertEquals("Awaiting command: "
            + "Party name: "
            + "Characters (Each by a comma): \n"
            + "Create the following Party?\n"
            + "Party name: Ninja Sex Party\n"
            + "Party members: Danny Sexbang, Ninja Brian\n"
            + "Confirm (y or n): "
            + "Invalid input.\n"
            + "Create the following Party?\n"
            + "Party name: Ninja Sex Party\n"
            + "Party members: Danny Sexbang, Ninja Brian\n"
            + "Confirm (y or n): "
            + "Invalid input.\n"
            + "Create the following Party?\n"
            + "Party name: Ninja Sex Party\n"
            + "Party members: Danny Sexbang, Ninja Brian\n"
            + "Confirm (y or n): "
            + "Invalid input.\n"
            + "Create the following Party?\n"
            + "Party name: Ninja Sex Party\n"
            + "Party members: Danny Sexbang, Ninja Brian\n"
            + "Confirm (y or n): "
            + "The Party Ninja Sex Party was added to the Manager.\n",
        output.toString().split(Controller.separator)[3]);
  }

  @Test
  public void invalidCharsCreateParty() {
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
        + "Danny Sexbang, Ninja Gaiden\n"
        + "y\n"
        + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertTrue(this.model.doesCharacterExist("Danny Sexbang"));
    assertTrue(this.model.doesCharacterExist("Ninja Brian"));
    assertTrue(this.model.doesPartyExist("Ninja Sex Party"));
    this.model.setActiveParty("Ninja Sex Party");
    assertEquals(1, this.model.getActiveParty().size());
    assertEquals("Awaiting command: "
            + "Party name: "
            + "Characters (Each by a comma): \n"
            + "Ninja Gaiden is not a Character in this Manager, therefore they won't be added.\n"
            + "Create the following Party?\n"
            + "Party name: Ninja Sex Party\n"
            + "Party members: Danny Sexbang\n"
            + "Confirm (y or n): "
            + "The Party Ninja Sex Party was added to the Manager.\n",
        output.toString().split(Controller.separator)[3]);
  }

  @Test
  public void existingPartyCreateParty() {
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
        + "Danny Sexbang\n"
        + "y\n"
        + "create-party\n"
        + "Ninja Sex Party\n"
        + "Ninja\n"
        + "Ninja Brian\n"
        + "y\n"
        + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertTrue(this.model.doesCharacterExist("Danny Sexbang"));
    assertTrue(this.model.doesCharacterExist("Ninja Brian"));
    assertTrue(this.model.doesPartyExist("Ninja Sex Party"));
    this.model.setActiveParty("Ninja Sex Party");
    assertEquals(1, this.model.getActiveParty().size());
    assertEquals("Awaiting command: "
            + "Party name: "
            + "Characters (Each by a comma): \n"
            + "Create the following Party?\n"
            + "Party name: Ninja Sex Party\n"
            + "Party members: Danny Sexbang\n"
            + "Confirm (y or n): "
            + "The Party Ninja Sex Party was added to the Manager.\n",
        output.toString().split(Controller.separator)[3]);

    assertEquals("Awaiting command: "
            + "Party name: \n"
            + "Invalid input: There is already a Party named Ninja Sex Party.\n"
            + "Please input a different name.\n"
            + "Party name: "
            + "Characters (Each by a comma): \n"
            + "Create the following Party?\n"
            + "Party name: Ninja\n"
            + "Party members: Ninja Brian\n"
            + "Confirm (y or n): "
            + "The Party Ninja was added to the Manager.\n",
        output.toString().split(Controller.separator)[4]);
  }

  @Test
  public void noGivenCharsCreateParty() {
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
        + "\n");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    try {
      this.controller.start();
      fail();
    }
    catch (NoSuchElementException e) {
      assertTrue(this.model.doesCharacterExist("Danny Sexbang"));
      assertTrue(this.model.doesCharacterExist("Ninja Brian"));
      assertEquals("Awaiting command: "
              + "Party name: "
              + "Characters (Each by a comma): \n"
              + "Invalid input: A Party needs at least one Character.\n",
          output.toString().split(Controller.separator)[3]);
    }
  }

  @Test
  public void noValidCharsCreateParty() {
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
        + "Dan, Brian, Arin\n");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    try {
      this.controller.start();
      fail();
    }
    catch (NoSuchElementException e) {
      assertTrue(this.model.doesCharacterExist("Danny Sexbang"));
      assertTrue(this.model.doesCharacterExist("Ninja Brian"));
      assertEquals("Awaiting command: "
              + "Party name: "
              + "Characters (Each by a comma): \n"
              + "Invalid input: A Party needs at least one Character.\n"
              + "Dan is not a Character in this Manager, therefore they won't be added.\n"
              + "Brian is not a Character in this Manager, therefore they won't be added.\n"
              + "Arin is not a Character in this Manager, therefore they won't be added.\n",
          output.toString().split(Controller.separator)[3]);
    }
  }

  @Test
  public void noCharsCreateParty() {
    Readable input = new StringReader("create-party\n");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    try {
      this.controller.start();
      fail();
    }
    catch (NoSuchElementException | IllegalStateException e) {
      assertEquals("Awaiting command: "
              + "Invalid state: The Manager doesn't have any Characters!\n"
              + "Add Characters using the create-char command.\n",
          output.toString().split(Controller.separator)[1]);
    }
  }
}