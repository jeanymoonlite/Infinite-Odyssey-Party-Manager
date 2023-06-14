package controller.command.party;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
  public void badInputEditParty() {
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
        + "stuff\n"
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
            + "Awaiting edit command: \n"
            + "Invalid edit command.\n",
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
  public void cancellingAndBadInputSaveEditParty() {
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
        + "save b\n"
        + "save n\n"
        + "save y\n"
        + "quit y");
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
            + "Invalid input.\n"
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
            + "Invalid input.\n"
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
            + "Intimidation: 1\n"
            + "\n"
            + "Confirm (y or n): ",
        output.toString().split(Controller.separator)[5].split(EditParty.separator)[2]);

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
        output.toString().split(Controller.separator)[5].split(EditParty.separator)[3]);
  }

  @Test
  public void cancellingPartyName() {
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
        + "quit\n"
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
            + "Awaiting edit command: WARNING: Quitting will remove the following changes:\n"
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
            + "Are you sure you want to exit Party editing mode?\n"
            + "Confirm (y or n): \n"
            + "All changes made to Ninja Sex Party have been undone.\n"
            + "Now exiting Party editing mode.\n",
        output.toString().split(Controller.separator)[5].split(EditParty.separator)[1]);
  }

  @Test
  public void cancellingQuitPartyName() {
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
        + "quit n\n"
        + "quit Y\n"
        + "quit y");
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
            + "Awaiting edit command: WARNING: Quitting will remove the following changes:\n"
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
            + "Are you sure you want to exit Party editing mode?\n"
            + "Confirm (y or n): ",
        output.toString().split(Controller.separator)[5].split(EditParty.separator)[1]);

    assertEquals("Party name: Ninja Sex Party\n"
            + "Party members: Danny Sexbang (Dan), Ninja Brian (Brian)\n"
            + "Awaiting edit command: WARNING: Quitting will remove the following changes:\n"
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
            + "Are you sure you want to exit Party editing mode?\n"
            + "Confirm (y or n): \n"
            + "All changes made to Ninja Sex Party have been undone.\n"
            + "Now exiting Party editing mode.\n",
        output.toString().split(Controller.separator)[5].split(EditParty.separator)[2]);
  }

  @Test
  public void cancellingBadInputPartyName() {
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
        + "quit a\n"
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
            + "WARNING: Quitting will remove the following changes:\n"
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
            + "Are you sure you want to exit Party editing mode?\n"
            + "Confirm (y or n): \n"
            + "Invalid input.\n"
            + "WARNING: Quitting will remove the following changes:\n"
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
            + "Are you sure you want to exit Party editing mode?\n"
            + "Confirm (y or n): \n"
            + "All changes made to Ninja Sex Party have been undone.\n"
            + "Now exiting Party editing mode.\n",
        output.toString().split(Controller.separator)[5].split(EditParty.separator)[1]);
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
  public void showPartyEditParty() {
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
        + "show-party\n"
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

    assertEquals("Party name: Ninja Sex Party\n"
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
            + "Intimidation: 1\n\n",
        output.toString().split(Controller.separator)[4].split(EditParty.separator)[1].split(
            "Awaiting edit command: ")[1]);
  }

  @Test
  public void successfulEditPartyAddChar() {
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
        + "add-char Egoraptor\n"
        + "save\n"
        + "y quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertTrue(this.model.doesPartyExist("Star Bomb"));
    assertTrue(this.model.findPartyByName("Star Bomb").hasCharacter("Danny Sexbang"));
    assertTrue(this.model.findPartyByName("Star Bomb").hasCharacter("Ninja Brian"));
    assertTrue(this.model.findPartyByName("Star Bomb").hasCharacter("Egoraptor"));

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
            + "Awaiting edit command: \n"
            + "Egoraptor (Arin) was added to Star Bomb.\n",
        output.toString().split(Controller.separator)[5].split(EditParty.separator)[2]);

    assertEquals("Party name: Star Bomb\n"
            + "Party members: Danny Sexbang (Dan), Ninja Brian (Brian), Egoraptor (Arin)\n"
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
            + "Intimidation: 1\n"
            + "\n"
            + "Egoraptor (Arin)\n"
            + "Class: Warrior (Knight)\n"
            + "Hp: 100/100 (+5 Def)\n"
            + "Strength: 1\n"
            + "Intelligence: 1\n"
            + "Creativity: 1\n"
            + "Charisma: 1\n"
            + "Stealth: 1\n"
            + "Intimidation: 1\n\n"
            + "Confirm (y or n): \n"
            + "Ninja Sex Party has been updated!\n"
            + "Now exiting Party editing mode.\n",
        output.toString().split(Controller.separator)[5].split(EditParty.separator)[3]);
  }

  @Test
  public void addSameCharEditPartyAddChar() {
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
        + "add-char Egoraptor\n"
        + "add-char Ninja Brian\n"
        + "save\n"
        + "y quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertTrue(this.model.doesPartyExist("Star Bomb"));
    assertTrue(this.model.findPartyByName("Star Bomb").hasCharacter("Danny Sexbang"));
    assertTrue(this.model.findPartyByName("Star Bomb").hasCharacter("Ninja Brian"));
    assertTrue(this.model.findPartyByName("Star Bomb").hasCharacter("Egoraptor"));

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
            + "Awaiting edit command: \n"
            + "Egoraptor (Arin) was added to Star Bomb.\n",
        output.toString().split(Controller.separator)[5].split(EditParty.separator)[2]);

    assertEquals("Party name: Star Bomb\n"
            + "Party members: Danny Sexbang (Dan), Ninja Brian (Brian), Egoraptor (Arin)\n"
            + "Awaiting edit command: \n"
            + "Invalid input: The Character Ninja Brian is already in this Party.\n",
        output.toString().split(Controller.separator)[5].split(EditParty.separator)[3]);

    assertEquals("Party name: Star Bomb\n"
            + "Party members: Danny Sexbang (Dan), Ninja Brian (Brian), Egoraptor (Arin)\n"
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
            + "Intimidation: 1\n"
            + "\n"
            + "Egoraptor (Arin)\n"
            + "Class: Warrior (Knight)\n"
            + "Hp: 100/100 (+5 Def)\n"
            + "Strength: 1\n"
            + "Intelligence: 1\n"
            + "Creativity: 1\n"
            + "Charisma: 1\n"
            + "Stealth: 1\n"
            + "Intimidation: 1\n\n"
            + "Confirm (y or n): \n"
            + "Ninja Sex Party has been updated!\n"
            + "Now exiting Party editing mode.\n",
        output.toString().split(Controller.separator)[5].split(EditParty.separator)[4]);
  }

  @Test
  public void nonExistCharEditPartyAddChar() {
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
        + "edit-name\n"
        + "Star Bomb\n"
        + "add-char Egoraptor\n"
        + "save\n"
        + "y quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertTrue(this.model.doesPartyExist("Star Bomb"));
    assertTrue(this.model.findPartyByName("Star Bomb").hasCharacter("Danny Sexbang"));
    assertTrue(this.model.findPartyByName("Star Bomb").hasCharacter("Ninja Brian"));
    assertFalse(this.model.findPartyByName("Star Bomb").hasCharacter("Egoraptor"));

    assertEquals("Awaiting command: You are now in party editing mode.\n"
            + "Editing: Ninja Sex Party\n",
        output.toString().split(Controller.separator)[4].split(EditParty.separator)[0]);

    assertEquals("Party name: Ninja Sex Party\n"
            + "Party members: Danny Sexbang (Dan), Ninja Brian (Brian)\n"
            + "Awaiting edit command: "
            + "Current Party name: Ninja Sex Party\n"
            + "New Party name: ",
        output.toString().split(Controller.separator)[4].split(EditParty.separator)[1]);

    assertEquals("Party name: Star Bomb\n"
            + "Party members: Danny Sexbang (Dan), Ninja Brian (Brian)\n"
            + "Awaiting edit command: \n"
            + "Invalid input: The Character Egoraptor doesn't exist in this Manager.\n",
        output.toString().split(Controller.separator)[4].split(EditParty.separator)[2]);

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
            + "Intimidation: 1\n"
            + "\n"
            + "Confirm (y or n): \n"
            + "Ninja Sex Party has been updated!\n"
            + "Now exiting Party editing mode.\n",
        output.toString().split(Controller.separator)[4].split(EditParty.separator)[3]);
  }

  @Test
  public void successfulEditPartyRemoveChar() {
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
        + "add-char Egoraptor\n"
        + "remove-char Ninja Brian\n"
        + "save\n"
        + "y quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertTrue(this.model.doesPartyExist("Star Bomb"));
    assertTrue(this.model.findPartyByName("Star Bomb").hasCharacter("Danny Sexbang"));
    assertFalse(this.model.findPartyByName("Star Bomb").hasCharacter("Ninja Brian"));
    assertTrue(this.model.findPartyByName("Star Bomb").hasCharacter("Egoraptor"));

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
            + "Awaiting edit command: \n"
            + "Egoraptor (Arin) was added to Star Bomb.\n",
        output.toString().split(Controller.separator)[5].split(EditParty.separator)[2]);

    assertEquals("Party name: Star Bomb\n"
            + "Party members: Danny Sexbang (Dan), Ninja Brian (Brian), Egoraptor (Arin)\n"
            + "Awaiting edit command: \n"
            + "Ninja Brian (Brian) was removed from Star Bomb.\n",
        output.toString().split(Controller.separator)[5].split(EditParty.separator)[3]);

    assertEquals("Party name: Star Bomb\n"
            + "Party members: Danny Sexbang (Dan), Egoraptor (Arin)\n"
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
            + "Egoraptor (Arin)\n"
            + "Class: Warrior (Knight)\n"
            + "Hp: 100/100 (+5 Def)\n"
            + "Strength: 1\n"
            + "Intelligence: 1\n"
            + "Creativity: 1\n"
            + "Charisma: 1\n"
            + "Stealth: 1\n"
            + "Intimidation: 1\n\n"
            + "Confirm (y or n): \n"
            + "Ninja Sex Party has been updated!\n"
            + "Now exiting Party editing mode.\n",
        output.toString().split(Controller.separator)[5].split(EditParty.separator)[4]);
  }

  @Test
  public void nonExistCharEditPartyRemoveChar() {
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
        + "Danny Sexbang, Egoraptor\n"
        + "y\n"
        + "edit-party Ninja Sex Party\n"
        + "edit-name\n"
        + "Game Grumps\n"
        + "remove-char Ninja Brian\n"
        + "save\n"
        + "y quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertTrue(this.model.doesPartyExist("Game Grumps"));
    assertTrue(this.model.findPartyByName("Game Grumps").hasCharacter("Danny Sexbang"));
    assertFalse(this.model.findPartyByName("Game Grumps").hasCharacter("Ninja Brian"));
    assertTrue(this.model.findPartyByName("Game Grumps").hasCharacter("Egoraptor"));

    assertEquals("Awaiting command: You are now in party editing mode.\n"
            + "Editing: Ninja Sex Party\n",
        output.toString().split(Controller.separator)[4].split(EditParty.separator)[0]);

    assertEquals("Party name: Ninja Sex Party\n"
            + "Party members: Danny Sexbang (Dan), Egoraptor (Arin)\n"
            + "Awaiting edit command: "
            + "Current Party name: Ninja Sex Party\n"
            + "New Party name: ",
        output.toString().split(Controller.separator)[4].split(EditParty.separator)[1]);

    assertEquals("Party name: Game Grumps\n"
            + "Party members: Danny Sexbang (Dan), Egoraptor (Arin)\n"
            + "Awaiting edit command: \n"
            + "Invalid input: The Character Ninja Brian doesn't exist in this Manager.\n",
        output.toString().split(Controller.separator)[4].split(EditParty.separator)[2]);

    assertEquals("Party name: Game Grumps\n"
            + "Party members: Danny Sexbang (Dan), Egoraptor (Arin)\n"
            + "Awaiting edit command: "
            + "Save the following changes?\n"
            + "Party name: Game Grumps\n"
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
            + "Egoraptor (Arin)\n"
            + "Class: Warrior (Knight)\n"
            + "Hp: 100/100 (+5 Def)\n"
            + "Strength: 1\n"
            + "Intelligence: 1\n"
            + "Creativity: 1\n"
            + "Charisma: 1\n"
            + "Stealth: 1\n"
            + "Intimidation: 1\n\n"
            + "Confirm (y or n): \n"
            + "Ninja Sex Party has been updated!\n"
            + "Now exiting Party editing mode.\n",
        output.toString().split(Controller.separator)[4].split(EditParty.separator)[3]);
  }

  @Test
  public void charNotInPartyEditPartyRemoveChar() {
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
        + "Danny Sexbang, Egoraptor\n"
        + "y\n"
        + "edit-party Ninja Sex Party\n"
        + "edit-name\n"
        + "Game Grumps\n"
        + "remove-char Ninja Brian\n"
        + "save\n"
        + "y quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertTrue(this.model.doesPartyExist("Game Grumps"));
    assertTrue(this.model.findPartyByName("Game Grumps").hasCharacter("Danny Sexbang"));
    assertTrue(this.model.findPartyByName("Game Grumps").hasCharacter("Egoraptor"));
    assertFalse(this.model.findPartyByName("Game Grumps").hasCharacter("Ninja Brian"));
    assertTrue(this.model.doesCharacterExist("Ninja Brian"));

    assertEquals("Awaiting command: You are now in party editing mode.\n"
            + "Editing: Ninja Sex Party\n",
        output.toString().split(Controller.separator)[5].split(EditParty.separator)[0]);

    assertEquals("Party name: Ninja Sex Party\n"
            + "Party members: Danny Sexbang (Dan), Egoraptor (Arin)\n"
            + "Awaiting edit command: "
            + "Current Party name: Ninja Sex Party\n"
            + "New Party name: ",
        output.toString().split(Controller.separator)[5].split(EditParty.separator)[1]);

    assertEquals("Party name: Game Grumps\n"
            + "Party members: Danny Sexbang (Dan), Egoraptor (Arin)\n"
            + "Awaiting edit command: \n"
            + "Invalid input: The Character Ninja Brian doesn't exist in this Party.\n",
        output.toString().split(Controller.separator)[5].split(EditParty.separator)[2]);

    assertEquals("Party name: Game Grumps\n"
            + "Party members: Danny Sexbang (Dan), Egoraptor (Arin)\n"
            + "Awaiting edit command: "
            + "Save the following changes?\n"
            + "Party name: Game Grumps\n"
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
            + "Egoraptor (Arin)\n"
            + "Class: Warrior (Knight)\n"
            + "Hp: 100/100 (+5 Def)\n"
            + "Strength: 1\n"
            + "Intelligence: 1\n"
            + "Creativity: 1\n"
            + "Charisma: 1\n"
            + "Stealth: 1\n"
            + "Intimidation: 1\n\n"
            + "Confirm (y or n): \n"
            + "Ninja Sex Party has been updated!\n"
            + "Now exiting Party editing mode.\n",
        output.toString().split(Controller.separator)[5].split(EditParty.separator)[3]);
  }

  @Test
  public void removePartyEditPartyRemoveChar() {
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
        + "Dan\n"
        + "Danny Sexbang\n"
        + "y\n"
        + "edit-party Dan\n"
        + "remove-char Danny Sexbang\n"
        + "y\n"
        + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertFalse(this.model.doesPartyExist("Game Grumps"));
    assertFalse(this.model.doesPartyExist("Dan"));

    assertEquals("Awaiting command: You are now in party editing mode.\n"
            + "Editing: Dan\n",
        output.toString().split(Controller.separator)[5].split(EditParty.separator)[0]);

    assertEquals("Party name: Dan\n"
            + "Party members: Danny Sexbang (Dan)\n"
            + "Awaiting edit command: \n"
            + "Removing Danny Sexbang will delete this Party as a Party needs\n"
            + "at least one Character in it.\n"
            + "Are you sure you want to remove Danny Sexbang?\n"
            + "Confirm (y or n): "
            + "The Party Dan was removed from the Manager.\n"
            + "Now exiting Party editing mode.\n",
        output.toString().split(Controller.separator)[5].split(EditParty.separator)[1]);
  }

  @Test
  public void cancellingRemovePartyEditPartyRemoveChar() {
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
        + "Dan\n"
        + "Danny Sexbang\n"
        + "y\n"
        + "edit-party Dan\n"
        + "remove-char Danny Sexbang\n"
        + "n\n"
        + "remove-char Danny Sexbang\n"
        + "y\n"
        + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertFalse(this.model.doesPartyExist("Game Grumps"));
    assertFalse(this.model.doesPartyExist("Dan"));

    assertEquals("Awaiting command: You are now in party editing mode.\n"
            + "Editing: Dan\n",
        output.toString().split(Controller.separator)[5].split(EditParty.separator)[0]);

    assertEquals("Party name: Dan\n"
            + "Party members: Danny Sexbang (Dan)\n"
            + "Awaiting edit command: \n"
            + "Removing Danny Sexbang will delete this Party as a Party needs\n"
            + "at least one Character in it.\n"
            + "Are you sure you want to remove Danny Sexbang?\n"
            + "Confirm (y or n): ",
        output.toString().split(Controller.separator)[5].split(EditParty.separator)[1]);

    assertEquals("Party name: Dan\n"
            + "Party members: Danny Sexbang (Dan)\n"
            + "Awaiting edit command: \n"
            + "Removing Danny Sexbang will delete this Party as a Party needs\n"
            + "at least one Character in it.\n"
            + "Are you sure you want to remove Danny Sexbang?\n"
            + "Confirm (y or n): "
            + "The Party Dan was removed from the Manager.\n"
            + "Now exiting Party editing mode.\n",
        output.toString().split(Controller.separator)[5].split(EditParty.separator)[2]);
  }

  @Test
  public void badInputRemovePartyEditPartyRemoveChar() {
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
        + "Dan\n"
        + "Danny Sexbang\n"
        + "y\n"
        + "edit-party Dan\n"
        + "remove-char Danny Sexbang\n"
        + "aq\n"
        + "y\n"
        + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertFalse(this.model.doesPartyExist("Game Grumps"));
    assertFalse(this.model.doesPartyExist("Dan"));

    assertEquals("Awaiting command: You are now in party editing mode.\n"
            + "Editing: Dan\n",
        output.toString().split(Controller.separator)[5].split(EditParty.separator)[0]);

    assertEquals("Party name: Dan\n"
            + "Party members: Danny Sexbang (Dan)\n"
            + "Awaiting edit command: \n"
            + "Removing Danny Sexbang will delete this Party as a Party needs\n"
            + "at least one Character in it.\n"
            + "Are you sure you want to remove Danny Sexbang?\n"
            + "Confirm (y or n): \n"
            + "Invalid input.\n"
            + "\nRemoving Danny Sexbang will delete this Party as a Party needs\n"
            + "at least one Character in it.\n"
            + "Are you sure you want to remove Danny Sexbang?\n"
            + "Confirm (y or n): "
            + "The Party Dan was removed from the Manager.\n"
            + "Now exiting Party editing mode.\n",
        output.toString().split(Controller.separator)[5].split(EditParty.separator)[1]);
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

    assertEquals("show-party:\n"
            + "\tShows what the Party that's being edited looks like.\n"
            + "\n"
            + "edit-name:\n"
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