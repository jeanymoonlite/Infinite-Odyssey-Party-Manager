package controller.command.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import controller.IOManagerController;
import controller.IOManagerControllerTest;
import java.io.StringReader;
import model.infiniteodysseys.IOManager;
import org.junit.Test;
import view.IOManagerTextView;

public class ShowAllPartiesTest extends IOManagerControllerTest {

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
            + "Character name: Player name: Role: Role Specification (Type n/a to leave blank): "
            + "Strength: Intelligence: Creativity: Charisma: Stealth: Intimidation: \n"
            + "Create the following Character? (Confirm y or n): \n"
            + "Egoraptor (Arin)\n"
            + "Role: Warrior (Knight)\n"
            + "Hp: 100/100 (+5 Def)\n"
            + "Strength: 1\n"
            + "Intelligence: 1\n"
            + "Creativity: 1\n"
            + "Charisma: 1\n"
            + "Stealth: 1\n"
            + "Intimidation: 1\n"
            + "Egoraptor (Arin) was added to the Manager.\n"
            + "Awaiting command:\n"
            + "Party name: "
            + "Characters (Each by a comma): "
            + "\nCreate the following Party? (Confirm y or n): \n"
            + "Party name: Ninja Sex Party\n"
            + "Party members: Danny Sexbang, Ninja Brian\n"
            + "The Party Ninja Sex Party was added to the Manager.\n"
            + "Awaiting command:\n"
            + "Party name: "
            + "Characters (Each by a comma): "
            + "\nCreate the following Party? (Confirm y or n): \n"
            + "Party name: Game Grumps\n"
            + "Party members: Danny Sexbang, Egoraptor\n"
            + "The Party Game Grumps was added to the Manager.\n"
            + "Awaiting command:\n"
            + "Ninja Sex Party: Danny Sexbang (Dan), Ninja Brian (Brian)\n"
            + "Game Grumps: Danny Sexbang (Dan), Egoraptor (Arin)\n"
            + "Total Parties: 2\n"
            + "Awaiting command:\n",
        output.toString().split("\tvii. Human\n")[1].split("WARNING")[0]);
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

    assertEquals("The Manager doesn't have any Parties!\n"
            + "Add Parties using the create-party command.\n",
        output.toString()
            .split("WARNING")[0]
            .split("Awaiting command:\n")[1]);
  }
}