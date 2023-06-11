package controller.command.help;

import static org.junit.Assert.assertEquals;

import controller.IOManagerController;
import controller.IOManagerControllerTest;
import controller.command.dice.Dice;
import java.io.StringReader;
import model.infiniteodysseys.IOManager;
import org.junit.Test;
import view.IOManagerTextView;

public class HelpAllTest extends IOManagerControllerTest {

  @Test
  public void getSignature() {
    this.model = new IOManager();
    assertEquals("help-all",
        new HelpAll(null, null).getSignature());
  }

  @Test
  public void getDescription() {
    this.model = new IOManager();
    assertEquals("Displays every command in the program organized by categories.",
        new HelpAll(null, null).getDescription());
  }

  @Test
  public void helpAll() {
    Readable input = new StringReader("help-all quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertEquals("Character Related Commands:\n"
            + "create-char (name playerName class classSpecification\n"
            + "             strength intelligence creativity\n"
            + "             charisma stealth intimidation): \n"
            + "\tCreates a new Character with the given information.\n"
            + "\n"
            + "edit-char (name): \n"
            + "\tEdits the Character with the given name.\n"
            + "\tThis will put the program into Character Editing mode.\n"
            + "\tA new set of commands will become available in editing mode.\n"
            + "\n"
            + "remove-char (name): \n"
            + "\tRemoves a Character with the given name.\n"
            + "\n"
            + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
            + "Party Related Commands:\n"
            + "create-party (name characters...): \n"
            + "\tCreates a new party with the given name and list of characters.\n"
            + "\tEach character's name must be separated by a space and \n"
            + "\tthey should all be on one line.\n"
            + "\n"
            + ": \n"
            + "\tEdits the Party with the given name.\n"
            + "\tThis will put the program into Party Editing mode.\n"
            + "\tA new set of commands will become available in editing mode.\n"
            + "\n"
            + "remove-party (name): \n"
            + "\tRemoves a party with the given name.\n"
            + "\n"
            + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
            + "Manager Related Commands:\n"
            + "party: \n"
            + "\tDisplays the active party if there is one.\n"
            + "\n"
            + "show-all-chars: \n"
            + "\tDisplays a list of every character with their name and player name.\n"
            + "\n"
            + "show-all-parties: \n"
            + "\tDisplays a list of every party with their name, followed \n"
            + "\tby the characters within them.\n"
            + "\n"
            + "show-char (name): \n"
            + "\tDisplays the name, class, class specification, and stats \n"
            + "\tof the specified character.\n"
            + "\n"
            + "show-party (name): \n"
            + "\tDisplays the name, class, class specification, and stats of \n"
            + "\tthe every character in the specified party.\n"
            + "\n"
            + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
            + "Stats Related Commands:\n"
            + "heal (amount name)\n"
            + "\tAdds the amount to the character's hp with the given name.\n"
            + "\tThis command rejects any non-integers/negative numbers.\n"
            + "\n"
            + "damage (amount name)\n"
            + "\tSubtracts the amount to the character's hp with the given name.\n"
            + "\tThis command rejects any non-integers/negative numbers.\n"
            + "\n"
            + "heal-all (amount)\n"
            + "\tAdds the amount to every character in the active party.\n"
            + "\n"
            + "damage-all (amount)\n"
            + "\tSubtracts the amount to every character in the active party\n"
            + "\n"
            + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
            + "Dice Related Commands:\n"
            + "roll (upperBound): \n"
            + "\tReturns a random number between 1 and the given upper bound.\n"
            + "\tThe upper bound cannot be less than 2.\n"
            + "\n"
            + "cf: Flips a coin.\n"
            + "\n"
            + "d4: Rolls a random number between 1 and 4.\n"
            + "\n"
            + "d6: Rolls a random number between 1 and 6.\n"
            + "\n"
            + "d8: Rolls a random number between 1 and 8.\n"
            + "\n"
            + "d10: Rolls a random number between 1 and 10.\n"
            + "\n"
            + "d12: Rolls a random number between 1 and 12.\n"
            + "\n"
            + "d20: Rolls a random number between 1 and 20.\n"
            + "\n"
            + "d100: Rolls a random number between 1 and 100.\n"
            + "\n"
            + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
            + "Misc Commands:\n"
            + "start (playerName): \n"
            + "\tStarts a campaign with the party that has the given name.\n"
            + "\n"
            + "clear: \n"
            + "\tClears the screen.\n"
            + "\n"
            + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n",
        output.toString().split("Awaiting command:\n")[1].split("WARNING")[0]);
  }

}