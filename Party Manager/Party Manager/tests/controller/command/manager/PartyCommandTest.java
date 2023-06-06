package controller.command.manager;

import static org.junit.Assert.*;

import controller.IOManagerController;
import controller.IOManagerControllerTest;
import java.io.StringReader;
import model.infiniteodysseys.IOCharacter;
import model.Character;
import model.infiniteodysseys.IOManager;
import model.infiniteodysseys.IORoles;
import org.junit.Test;
import view.IOManagerTextView;

public class PartyCommandTest extends IOManagerControllerTest {

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
    Readable input = new StringReader("party "
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

    assertEquals("Awaiting command:\n"
            + "Active Party: Game Grumps\n\n"
            + "Danny Sexbang (Dan)\n"
            + "Role: Bard (Lover)\n"
            + "Hp: 100/100 (+1 Def)\n"
            + "Strength: 1\n"
            + "Intelligence: 1\n"
            + "Creativity: 1\n"
            + "Charisma: 1 (+4)\n"
            + "Stealth: 1\n"
            + "Intimidation: 1\n"
            + "\n"
            + "Egoraptor (Arin)\n"
            + "Role: Warrior (Knight)\n"
            + "Hp: 100/100 (+5 Def)\n"
            + "Strength: 1\n"
            + "Intelligence: 1\n"
            + "Creativity: 1\n"
            + "Charisma: 1\n"
            + "Stealth: 1\n"
            + "Intimidation: 1\n"
            + "Awaiting command:\n",
        output.toString().split("\tvii. Human\n")[1].split("WARNING")[0]);
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

    assertEquals("Awaiting command:\n"
            + "The Manager doesn't have any Parties!\n"
            + "Add Parties using the create-party command.\n"
            + "Awaiting command:\n",
        output.toString().split("\tvii. Human\n")[1].split("WARNING")[0]);
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

    assertEquals("Awaiting command:\n"
            + "Active Party: None\n"
            + "Set the active party using the start command.\n"
            + "Awaiting command:\n",
        output.toString().split("\tvii. Human\n")[1].split("WARNING")[0]);
  }
}