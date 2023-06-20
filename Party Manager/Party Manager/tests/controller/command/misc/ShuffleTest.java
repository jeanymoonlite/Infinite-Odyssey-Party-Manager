package controller.command.misc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import controller.Controller;
import controller.IOManagerController;
import controller.IOManagerControllerTest;
import controller.command.manager.PartyCommand;
import java.io.StringReader;
import model.infiniteodysseys.IOManager;
import org.junit.Test;
import view.IOManagerTextView;

public class ShuffleTest extends IOManagerControllerTest {

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

  @Test
  public void successfulShuffle() {
    Readable input = new StringReader(
        "load C:\\Users\\Jr641\\Desktop\\Programming\\Java Programs\\Infinite-Odyssey-Party-Manager\\Party Manager\\Party Manager\\res\\grumps.iom"
            + "\nstart Star Bomb\n"
            + "y\n"
            + "shuffle\n"
            + "quit y\n"
            + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);

    this.controller.start();

    assertEquals("Awaiting command: \n"
            + "Start a Campaign with the Star Bomb Party?\n"
            + "Confirm (y or n): A Campaign has been started with Star Bomb:\n"
            + "\n"
            + "Active Party: Star Bomb\n\n"
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
            + "Intimidation: 1\n",
        output.toString().split(Controller.separator)[2]);

    assertTrue(output.toString().split(Controller.separator)[3].contains("Danny Sexbang"));
    assertTrue(output.toString().split(Controller.separator)[3].contains("Egoraptor"));
    assertTrue(output.toString().split(Controller.separator)[3].contains("Ninja Brian"));
  }

  @Test
  public void badShuffle() {
    Readable input = new StringReader(
        "load C:\\Users\\Jr641\\Desktop\\Programming\\Java Programs\\Infinite-Odyssey-Party-Manager\\Party Manager\\Party Manager\\res\\grumps.iom"
            + "\nstart Star Bomb\n"
            + "y\n"
            + "shuffle\n"
            + "quit y\n"
            + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);

    this.controller.start();

    assertEquals("Awaiting command: \n"
            + "Start a Campaign with the Star Bomb Party?\n"
            + "Confirm (y or n): A Campaign has been started with Star Bomb:\n"
            + "\n"
            + "Active Party: Star Bomb\n\n"
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
            + "Intimidation: 1\n",
        output.toString().split(Controller.separator)[2]);

    assertTrue(output.toString().split(Controller.separator)[3].contains("Danny Sexbang"));
    assertTrue(output.toString().split(Controller.separator)[3].contains("Egoraptor"));
    assertTrue(output.toString().split(Controller.separator)[3].contains("Ninja Brian"));
  }
}