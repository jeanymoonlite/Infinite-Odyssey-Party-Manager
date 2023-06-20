package controller.command.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import controller.Controller;
import controller.IOManagerController;
import controller.IOManagerControllerTest;
import java.io.StringReader;
import model.Character;
import model.infiniteodysseys.IOCharacter;
import model.infiniteodysseys.IOManager;
import model.infiniteodysseys.IORoles;
import org.junit.Test;
import view.IOManagerTextView;

public class PartyCommandTest extends IOManagerControllerTest {

  @Test
  public void getSignature() {
    this.model = new IOManager();
    assertEquals("party",
        new PartyCommand(this.model, null, null).getSignature());
  }

  @Test
  public void getDescription() {
    this.model = new IOManager();
    assertEquals("Displays the active party if there is one.",
        new PartyCommand(this.model, null, null).getDescription());
  }

  private void addGrumps() {
    Character dan = new IOCharacter("Danny Sexbang", "Dan", IORoles.BARD, "Lover",
        1, 1, 1, 1, 1, 1);
    Character brian = new IOCharacter("Ninja Brian", "Brian", IORoles.ROGUE, "Ninja",
        1, 1, 1, 1, 1, 1);
    Character arin = new IOCharacter("Egoraptor", "Arin", IORoles.WARRIOR, "Knight",
        1, 1, 1, 1, 1, 1);

    this.model.addCharacter(dan);
    this.model.addCharacter(brian);
    this.model.addCharacter(arin);

    this.model.addParty("Ninja Sex Party", dan, brian);
    this.model.addParty("Game Grumps", dan, arin);
  }

  @Test
  public void successfulShowParty() {
    Readable input = new StringReader("start Game Grumps\n"
        + "y\n"
        + "party\n"
        + "quit y\n"
        + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);

    this.addGrumps();
    this.model.setActiveParty("Game Grumps");

    this.controller.start();

    assertTrue(this.model.doesCharacterExist("Danny Sexbang"));
    assertTrue(this.model.doesCharacterExist("Ninja Brian"));
    assertTrue(this.model.doesCharacterExist("Egoraptor"));
    assertTrue(this.model.doesPartyExist("Ninja Sex Party"));
    assertTrue(this.model.doesPartyExist("Game Grumps"));
    assertEquals("Game Grumps", this.model.getActiveParty().getName());

    assertEquals("Awaiting command: \n"
            + "Start a Campaign with the Game Grumps Party?\n"
            + "Confirm (y or n): A Campaign has been started with Game Grumps:\n"
            + "\n"
            + "Active Party: Game Grumps\n\n"
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
            + "Intimidation: 1\n",
        output.toString().split(Controller.separator)[1]);
  }

  @Test
  public void noPartiesShowParty() {
    Readable input = new StringReader("party "
        + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);

    this.controller.start();

    assertEquals("Awaiting command: "
            + "The Manager doesn't have any Parties!\n"
            + "Add Parties using the create-party command.\n",
        output.toString().split(Controller.separator)[1]);
  }

  @Test
  public void nullActivePartyShowParty() {
    Readable input = new StringReader("party "
        + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);

    this.addGrumps();

    this.controller.start();

    assertEquals("Awaiting command: "
            + "Invalid state: This command can only be used during a campaign.\n"
            + "Use the start command to start a campaign.\n",
        output.toString().split(Controller.separator)[1]);
  }
}