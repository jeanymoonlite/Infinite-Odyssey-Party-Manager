package controller.command.party;

import static org.junit.Assert.*;

import controller.Controller;
import controller.IOManagerController;
import controller.IOManagerControllerTest;
import java.io.StringReader;
import model.infiniteodysseys.IOManager;
import org.junit.Test;
import view.IOManagerTextView;

public class EditPartyTest extends IOManagerControllerTest  {

  @Test
  public void getSignature() {
    assertEquals("edit-party (name)",
        new EditParty(null, null, null).getSignature());
  }

  @Test
  public void getDescription() {
    assertEquals("Edits the Party with the given name.\n"
            + "This will put the program into Party Editing mode.\n"
            + "A new set of commands will become available in editing mode.",
        new EditParty(null, null, null).getDescription());
  }

  @Test
  public void successfulEditPartyName() {
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
        + "edit-party Ninja Sex Party\n"
        + "edit-name\n"
        + "Star Bomb\n"
        + "save\n"
        + "y quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertTrue(this.model.doesPartyExist("Star Bomb"));
    assertEquals("Awaiting command: You are now in party editing mode.\n"
            + "Editing: Ninja Sex Party\n",
        output.toString().split(Controller.separator)[5].split(EditParty.separator)[0]);

    assertEquals("Party name: Ninja Sex Party\n"
            + "Party members: Danny Sexbang (Dan), Ninja Brian (Brian)\n"
            + "Awaiting edit command: "
            + "Current Party name: Ninja Sex Party\n"
            + "New Party name: ",
        output.toString().split(Controller.separator)[5].split(EditParty.separator)[1]);

    assertEquals("Party name: Star Bomb\n"
            + "Party members: Danny Sexbang (Dan), Ninja Brian (Brian)\n"
            + "Awaiting edit command: "
            + "Save the following changes?\n"
            + "Party name: Star Bomb\n"
            + "Danny Sexbang (Dan)\n"
            + "Class: Bard (Lover)\n"
            + "Hp: 100/100 (+1 Def)\n"
            + "Strength: 1\n"
            + "Intelligence: 1\n"
            + "Creativity: 1\n"
            + "Charisma: 1 (+4)\n"
            + "Stealth: 1\n"
            + "Intimidation: 1\n"
            + "\n"
            + "Ninja Brian (Brian)\n"
            + "Class: Rogue (Ninja)\n"
            + "Hp: 100/100 (-5 Def)\n"
            + "Strength: 1 (+2)\n"
            + "Intelligence: 1\n"
            + "Creativity: 1\n"
            + "Charisma: 1\n"
            + "Stealth: 1 (+3)\n"
            + "Intimidation: 1\n\n"
            + "Confirm (y or n): \n"
            + "Ninja Sex Party has been updated!\n"
            + "Now exiting Party editing mode.\n",
        output.toString().split(Controller.separator)[5].split(EditParty.separator)[2]);
  }

  @Test
  public void keepingEditPartyName() {
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
        + "edit-party Ninja Sex Party\n"
        + "edit-name\n"
        + "\n"
        + "save\n"
        + "y quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertTrue(this.model.doesPartyExist("Ninja Sex Party"));
    assertEquals("Awaiting command: You are now in party editing mode.\n"
            + "Editing: Ninja Sex Party\n",
        output.toString().split(Controller.separator)[5].split(EditParty.separator)[0]);

    assertEquals("Party name: Ninja Sex Party\n"
            + "Party members: Danny Sexbang (Dan), Ninja Brian (Brian)\n"
            + "Awaiting edit command: "
            + "Current Party name: Ninja Sex Party\n"
            + "New Party name: ",
        output.toString().split(Controller.separator)[5].split(EditParty.separator)[1]);

    assertEquals("Party name: Ninja Sex Party\n"
            + "Party members: Danny Sexbang (Dan), Ninja Brian (Brian)\n"
            + "Awaiting edit command: "
            + "Save the following changes?\n"
            + "Party name: Ninja Sex Party\n"
            + "Danny Sexbang (Dan)\n"
            + "Class: Bard (Lover)\n"
            + "Hp: 100/100 (+1 Def)\n"
            + "Strength: 1\n"
            + "Intelligence: 1\n"
            + "Creativity: 1\n"
            + "Charisma: 1 (+4)\n"
            + "Stealth: 1\n"
            + "Intimidation: 1\n"
            + "\n"
            + "Ninja Brian (Brian)\n"
            + "Class: Rogue (Ninja)\n"
            + "Hp: 100/100 (-5 Def)\n"
            + "Strength: 1 (+2)\n"
            + "Intelligence: 1\n"
            + "Creativity: 1\n"
            + "Charisma: 1\n"
            + "Stealth: 1 (+3)\n"
            + "Intimidation: 1\n\n"
            + "Confirm (y or n): \n"
            + "Ninja Sex Party has been updated!\n"
            + "Now exiting Party editing mode.\n",
        output.toString().split(Controller.separator)[5].split(EditParty.separator)[2]);
  }

  @Test
  public void changingNameToExistingPartyEditParty() {
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
        + "edit-party Ninja Sex Party\n"
        + "edit-name\n"
        + "Game Grumps\n"
        + "Star Bomb\n"
        + "save\n"
        + "y quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertTrue(this.model.doesPartyExist("Star Bomb"));
    assertTrue(this.model.doesPartyExist("Game Grumps"));
    assertEquals("Awaiting command: You are now in party editing mode.\n"
            + "Editing: Ninja Sex Party\n",
        output.toString().split(Controller.separator)[6].split(EditParty.separator)[0]);

    assertEquals("Party name: Ninja Sex Party\n"
            + "Party members: Danny Sexbang (Dan), Ninja Brian (Brian)\n"
            + "Awaiting edit command: "
            + "Current Party name: Ninja Sex Party\n"
            + "New Party name: \n"
            + "Invalid input: There is already a Party named Game Grumps.\n"
            + "Please input a different name.\n"
            + "Current Party name: Ninja Sex Party\n"
            + "New Party name: ",
        output.toString().split(Controller.separator)[6].split(EditParty.separator)[1]);

    assertEquals("Party name: Star Bomb\n"
            + "Party members: Danny Sexbang (Dan), Ninja Brian (Brian)\n"
            + "Awaiting edit command: "
            + "Save the following changes?\n"
            + "Party name: Star Bomb\n"
            + "Danny Sexbang (Dan)\n"
            + "Class: Bard (Lover)\n"
            + "Hp: 100/100 (+1 Def)\n"
            + "Strength: 1\n"
            + "Intelligence: 1\n"
            + "Creativity: 1\n"
            + "Charisma: 1 (+4)\n"
            + "Stealth: 1\n"
            + "Intimidation: 1\n"
            + "\n"
            + "Ninja Brian (Brian)\n"
            + "Class: Rogue (Ninja)\n"
            + "Hp: 100/100 (-5 Def)\n"
            + "Strength: 1 (+2)\n"
            + "Intelligence: 1\n"
            + "Creativity: 1\n"
            + "Charisma: 1\n"
            + "Stealth: 1 (+3)\n"
            + "Intimidation: 1\n\n"
            + "Confirm (y or n): \n"
            + "Ninja Sex Party has been updated!\n"
            + "Now exiting Party editing mode.\n",
        output.toString().split(Controller.separator)[6].split(EditParty.separator)[2]);
  }

  @Test
  public void helpEditParty() {
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
        + "edit-party Ninja Sex Party\n"
        + "help\n"
        + "save\n"
        + "y quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertTrue(this.model.doesPartyExist("Ninja Sex Party"));
    assertEquals("Awaiting command: You are now in party editing mode.\n"
            + "Editing: Ninja Sex Party\n",
        output.toString().split(Controller.separator)[4].split(EditParty.separator)[0]);

    assertEquals("edit-name:\n"
            + "\tChanges the name of this Party.\n"
            + "\n"
            + "add-char (name):\n"
            + "\tAdds the Character with the given name to this Party.\n"
            + "\n"
            + "remove-char (name):\n"
            + "\tRemoves the Character with the given name from this Party.\n"
            + "\n"
            + "save:\n"
            + "\tSaves the changes made to the Character being edited.\n"
            + "\n"
            + "quit:\n"
            + "\tRemoves any changes made to the Character being edited.\n"
            + "\n",
        output.toString().split(Controller.separator)[4].split(EditParty.separator)[1].split(
            "Awaiting edit command: ")[1]);
  }

}