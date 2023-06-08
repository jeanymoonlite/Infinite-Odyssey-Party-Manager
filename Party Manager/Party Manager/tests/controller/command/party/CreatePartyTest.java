package controller.command.party;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import controller.IOManagerController;
import controller.IOManagerControllerTest;
import java.io.StringReader;
import java.util.NoSuchElementException;
import model.infiniteodysseys.IOManager;
import org.junit.Test;
import view.IOManagerTextView;

public class CreatePartyTest extends IOManagerControllerTest {

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
    assertEquals("Awaiting command:\n"
            + "Character name: Player name: Role: Role Specification (Type n/a to leave blank): "
            + "Strength: Intelligence: Creativity: Charisma: Stealth: Intimidation: \n"
            + "Create the following Character? (Confirm y or n): \n"
            + "Danny Sexbang (Dan)\n"
            + "Role: Bard (Lover)\n"
            + "Hp: 100/100 (+1 Def)\n"
            + "Strength: 1\n"
            + "Intelligence: 1\n"
            + "Creativity: 1\n"
            + "Charisma: 1 (+4)\n"
            + "Stealth: 1\n"
            + "Intimidation: 1\n"
            + "Danny Sexbang (Dan) was added to the Manager.\n"
            + "Awaiting command:\n"
            + "Character name: Player name: Role: Role Specification (Type n/a to leave blank): "
            + "Strength: Intelligence: Creativity: Charisma: Stealth: Intimidation: \n"
            + "Create the following Character? (Confirm y or n): \n"
            + "Ninja Brian (Brian)\n"
            + "Role: Rogue (Ninja)\n"
            + "Hp: 100/100 (-5 Def)\n"
            + "Strength: 1 (+2)\n"
            + "Intelligence: 1\n"
            + "Creativity: 1\n"
            + "Charisma: 1\n"
            + "Stealth: 1 (+3)\n"
            + "Intimidation: 1\n"
            + "Ninja Brian (Brian) was added to the Manager.\n"
            + "Awaiting command:\n"
            + "Party name: "
            + "Characters (Each by a comma): "
            + "\nCreate the following Party? (Confirm y or n): \n"
            + "Party name: Ninja Sex Party\n"
            + "Party members: Danny Sexbang, Ninja Brian\n"
            + "The Party Ninja Sex Party was added to the Manager.\n"
            + "Awaiting command:\n",
        output.toString().split("\tvii. Human\n")[1].split("WARNING")[0]);
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
    assertEquals("Awaiting command:\n"
            + "Character name: Player name: Role: Role Specification (Type n/a to leave blank): "
            + "Strength: Intelligence: Creativity: Charisma: Stealth: Intimidation: \n"
            + "Create the following Character? (Confirm y or n): \n"
            + "Danny Sexbang (Dan)\n"
            + "Role: Bard (Lover)\n"
            + "Hp: 100/100 (+1 Def)\n"
            + "Strength: 1\n"
            + "Intelligence: 1\n"
            + "Creativity: 1\n"
            + "Charisma: 1 (+4)\n"
            + "Stealth: 1\n"
            + "Intimidation: 1\n"
            + "Danny Sexbang (Dan) was added to the Manager.\n"
            + "Awaiting command:\n"
            + "Character name: Player name: Role: Role Specification (Type n/a to leave blank): "
            + "Strength: Intelligence: Creativity: Charisma: Stealth: Intimidation: \n"
            + "Create the following Character? (Confirm y or n): \n"
            + "Ninja Brian (Brian)\n"
            + "Role: Rogue (Ninja)\n"
            + "Hp: 100/100 (-5 Def)\n"
            + "Strength: 1 (+2)\n"
            + "Intelligence: 1\n"
            + "Creativity: 1\n"
            + "Charisma: 1\n"
            + "Stealth: 1 (+3)\n"
            + "Intimidation: 1\n"
            + "Ninja Brian (Brian) was added to the Manager.\n"
            + "Awaiting command:\n"
            + "Party name: "
            + "Characters (Each by a comma): \n"
            + "Create the following Party? (Confirm y or n): \n"
            + "Party name: Ninja Sex Party\n"
            + "Party members: Danny Sexbang, Ninja Brian\n"
            + "The Party Ninja Sex Party will not be created.\n"
            + "Awaiting command:\n",
        output.toString().split("\tvii. Human\n")[1].split("WARNING")[0]);
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
    assertEquals("Awaiting command:\n"
            + "Character name: Player name: Role: Role Specification (Type n/a to leave blank): "
            + "Strength: Intelligence: Creativity: Charisma: Stealth: Intimidation: \n"
            + "Create the following Character? (Confirm y or n): \n"
            + "Danny Sexbang (Dan)\n"
            + "Role: Bard (Lover)\n"
            + "Hp: 100/100 (+1 Def)\n"
            + "Strength: 1\n"
            + "Intelligence: 1\n"
            + "Creativity: 1\n"
            + "Charisma: 1 (+4)\n"
            + "Stealth: 1\n"
            + "Intimidation: 1\n"
            + "Danny Sexbang (Dan) was added to the Manager.\n"
            + "Awaiting command:\n"
            + "Character name: Player name: Role: Role Specification (Type n/a to leave blank): "
            + "Strength: Intelligence: Creativity: Charisma: Stealth: Intimidation: \n"
            + "Create the following Character? (Confirm y or n): \n"
            + "Ninja Brian (Brian)\n"
            + "Role: Rogue (Ninja)\n"
            + "Hp: 100/100 (-5 Def)\n"
            + "Strength: 1 (+2)\n"
            + "Intelligence: 1\n"
            + "Creativity: 1\n"
            + "Charisma: 1\n"
            + "Stealth: 1 (+3)\n"
            + "Intimidation: 1\n"
            + "Ninja Brian (Brian) was added to the Manager.\n"
            + "Awaiting command:\n"
            + "Party name: "
            + "Characters (Each by a comma): \n"
            + "Create the following Party? (Confirm y or n): \n"
            + "Party name: Ninja Sex Party\n"
            + "Party members: Danny Sexbang, Ninja Brian\n"
            + "Invalid input.\n"
            + "Create the following Party? (Confirm y or n): \n"
            + "Party name: Ninja Sex Party\n"
            + "Party members: Danny Sexbang, Ninja Brian\n"
            + "Invalid input.\n"
            + "Create the following Party? (Confirm y or n): \n"
            + "Party name: Ninja Sex Party\n"
            + "Party members: Danny Sexbang, Ninja Brian\n"
            + "Invalid input.\n"
            + "Create the following Party? (Confirm y or n): \n"
            + "Party name: Ninja Sex Party\n"
            + "Party members: Danny Sexbang, Ninja Brian\n"
            + "The Party Ninja Sex Party was added to the Manager.\n"
            + "Awaiting command:\n",
        output.toString().split("\tvii. Human\n")[1].split("WARNING")[0]);
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
    assertEquals("Awaiting command:\n"
            + "Character name: Player name: Role: Role Specification (Type n/a to leave blank): "
            + "Strength: Intelligence: Creativity: Charisma: Stealth: Intimidation: \n"
            + "Create the following Character? (Confirm y or n): \n"
            + "Danny Sexbang (Dan)\n"
            + "Role: Bard (Lover)\n"
            + "Hp: 100/100 (+1 Def)\n"
            + "Strength: 1\n"
            + "Intelligence: 1\n"
            + "Creativity: 1\n"
            + "Charisma: 1 (+4)\n"
            + "Stealth: 1\n"
            + "Intimidation: 1\n"
            + "Danny Sexbang (Dan) was added to the Manager.\n"
            + "Awaiting command:\n"
            + "Character name: Player name: Role: Role Specification (Type n/a to leave blank): "
            + "Strength: Intelligence: Creativity: Charisma: Stealth: Intimidation: \n"
            + "Create the following Character? (Confirm y or n): \n"
            + "Ninja Brian (Brian)\n"
            + "Role: Rogue (Ninja)\n"
            + "Hp: 100/100 (-5 Def)\n"
            + "Strength: 1 (+2)\n"
            + "Intelligence: 1\n"
            + "Creativity: 1\n"
            + "Charisma: 1\n"
            + "Stealth: 1 (+3)\n"
            + "Intimidation: 1\n"
            + "Ninja Brian (Brian) was added to the Manager.\n"
            + "Awaiting command:\n"
            + "Party name: "
            + "Characters (Each by a comma): \n"
            + "Ninja Gaiden is not a Character in this Manager, therefore they won't be added.\n"
            + "Create the following Party? (Confirm y or n): \n"
            + "Party name: Ninja Sex Party\n"
            + "Party members: Danny Sexbang\n"
            + "The Party Ninja Sex Party was added to the Manager.\n"
            + "Awaiting command:\n",
        output.toString().split("\tvii. Human\n")[1].split("WARNING")[0]);
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
        + "create-party\n"
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
    assertEquals("Awaiting command:\n"
            + "Character name: Player name: Role: Role Specification (Type n/a to leave blank): "
            + "Strength: Intelligence: Creativity: Charisma: Stealth: Intimidation: \n"
            + "Create the following Character? (Confirm y or n): \n"
            + "Danny Sexbang (Dan)\n"
            + "Role: Bard (Lover)\n"
            + "Hp: 100/100 (+1 Def)\n"
            + "Strength: 1\n"
            + "Intelligence: 1\n"
            + "Creativity: 1\n"
            + "Charisma: 1 (+4)\n"
            + "Stealth: 1\n"
            + "Intimidation: 1\n"
            + "Danny Sexbang (Dan) was added to the Manager.\n"
            + "Awaiting command:\n"
            + "Character name: Player name: Role: Role Specification (Type n/a to leave blank): "
            + "Strength: Intelligence: Creativity: Charisma: Stealth: Intimidation: \n"
            + "Create the following Character? (Confirm y or n): \n"
            + "Ninja Brian (Brian)\n"
            + "Role: Rogue (Ninja)\n"
            + "Hp: 100/100 (-5 Def)\n"
            + "Strength: 1 (+2)\n"
            + "Intelligence: 1\n"
            + "Creativity: 1\n"
            + "Charisma: 1\n"
            + "Stealth: 1 (+3)\n"
            + "Intimidation: 1\n"
            + "Ninja Brian (Brian) was added to the Manager.\n"
            + "Awaiting command:\n"
            + "Party name: "
            + "Characters (Each by a comma): \n"
            + "Create the following Party? (Confirm y or n): \n"
            + "Party name: Ninja Sex Party\n"
            + "Party members: Danny Sexbang\n"
            + "The Party Ninja Sex Party was added to the Manager.\n"
            + "Awaiting command:\n"
            + "Party name: \n"
            + "Invalid input: There is already a Party named Ninja Sex Party.\n"
            + "Please input a different name.\n"
            + "Party name: "
            + "Characters (Each by a comma): \n"
            + "Invalid input: A Party needs at least one Character.\n"
            + "Ninja is not a Character in this Manager, therefore they won't be added.\n"
            + "\nCreate the following Party? (Confirm y or n): \n"
            + "Party name: create-party\n"
            + "Party members: Ninja Brian\n"
            + "The Party create-party was added to the Manager.\n"
            + "Awaiting command:\n",
        output.toString().split("\tvii. Human\n")[1].split("WARNING")[0]);
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
    } catch (NoSuchElementException e) {
      assertTrue(this.model.doesCharacterExist("Danny Sexbang"));
      assertTrue(this.model.doesCharacterExist("Ninja Brian"));
      assertEquals("Awaiting command:\n"
              + "Character name: Player name: Role: Role Specification (Type n/a to leave blank): "
              + "Strength: Intelligence: Creativity: Charisma: Stealth: Intimidation: \n"
              + "Create the following Character? (Confirm y or n): \n"
              + "Danny Sexbang (Dan)\n"
              + "Role: Bard (Lover)\n"
              + "Hp: 100/100 (+1 Def)\n"
              + "Strength: 1\n"
              + "Intelligence: 1\n"
              + "Creativity: 1\n"
              + "Charisma: 1 (+4)\n"
              + "Stealth: 1\n"
              + "Intimidation: 1\n"
              + "Danny Sexbang (Dan) was added to the Manager.\n"
              + "Awaiting command:\n"
              + "Character name: Player name: Role: Role Specification (Type n/a to leave blank): "
              + "Strength: Intelligence: Creativity: Charisma: Stealth: Intimidation: \n"
              + "Create the following Character? (Confirm y or n): \n"
              + "Ninja Brian (Brian)\n"
              + "Role: Rogue (Ninja)\n"
              + "Hp: 100/100 (-5 Def)\n"
              + "Strength: 1 (+2)\n"
              + "Intelligence: 1\n"
              + "Creativity: 1\n"
              + "Charisma: 1\n"
              + "Stealth: 1 (+3)\n"
              + "Intimidation: 1\n"
              + "Ninja Brian (Brian) was added to the Manager.\n"
              + "Awaiting command:\n"
              + "Party name: "
              + "Characters (Each by a comma): "
              + "\nInvalid input: A Party needs at least one Character.\n",
          output.toString().split("\tvii. Human\n")[1]);
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
    } catch (NoSuchElementException | IllegalStateException e) {
      assertEquals("Awaiting command:\n"
              + "The Manager doesn't have any Characters!\n"
              + "Add Characters using the create-char command.\n"
              + "Awaiting command:\n",
          output.toString().split("\tvii. Human\n")[1]);
    }
  }
}