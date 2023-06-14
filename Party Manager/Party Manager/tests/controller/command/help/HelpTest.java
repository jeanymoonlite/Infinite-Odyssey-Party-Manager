package controller.command.help;

import static org.junit.Assert.assertEquals;

import controller.Controller;
import controller.IOManagerController;
import controller.IOManagerControllerTest;
import java.io.StringReader;
import model.infiniteodysseys.IOManager;
import org.junit.Test;
import view.IOManagerTextView;

public class HelpTest extends IOManagerControllerTest {

  @Test
  public void getSignature() {
    this.model = new IOManager();
    assertEquals("help",
        new Help(null, null).getSignature());
  }

  @Test
  public void getDescription() {
    this.model = new IOManager();
    assertEquals("Displays the various help commands.\n"
        + "When in Character or Party editing mode, this command displays the edit specific commands.",
        new Help(null, null).getDescription());
  }

  @Test
  public void help() {
    Readable input = new StringReader("help quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertEquals("Awaiting command: All Help Commands:\n"
            + "help: \n"
            + "\tDisplays the various help commands.\n"
            + "\tWhen in Character or Party editing mode, this command displays the edit specific commands.\n"
            + "\n"
            + "help-all: \n"
            + "\tDisplays every command in the program organized by categories.\n"
            + "\n"
            + "help-char: \n"
            + "\tDisplays every character-related command.\n"
            + "\n"
            + "help-party: \n"
            + "\tDisplays every party-related command.\n"
            + "\n"
            + "help-manager: \n"
            + "\tDisplays commands for viewing characters and parties.\n"
            + "\n"
            + "help-stats: \n"
            + "\tDisplays every command for healing and damaging characters.\n"
            + "\n"
            + "help-dice: \n"
            + "\tDisplays every dice-related command.\n"
            + "\n",
        output.toString().split(Controller.separator)[1]);
  }

  @Test
  public void helpInCampaign() {
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
        + "Star Bomb\n"
        + "Danny Sexbang, Egoraptor, Ninja Brian\n"
        + "y\n"
        + "start Star Bomb\n"
        + "y\n"
        + "help\n"
        + "quit y\n"
        + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertEquals("Awaiting command: All Campaign Commands:\n"
            + "help: \n"
            + "\tDisplays the various help commands.\n"
            + "\tWhen in Character or Party editing mode, this command displays the edit specific commands.\n"
            + "\n"
            + "party: \n"
            + "\tDisplays the active party if there is one.\n"
            + "\n"
            + "heal (amount name):\n"
            + "\tAdds the amount to the character's hp with the given name.\n"
            + "\tThis command rejects any non-integers/negative numbers.\n"
            + "\n"
            + "damage (amount name):\n"
            + "\tSubtracts the amount to the character's hp with the given name.\n"
            + "\tThis command rejects any non-integers/negative numbers.\n"
            + "\n"
            + "heal-all (amount):\n"
            + "\tAdds the amount to every character in the active party.\n"
            + "\n"
            + "damage-all (amount):\n"
            + "\tSubtracts the amount to every character in the active party\n"
            + "\n"
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
            + "quit:\n"
            + "\tEnds the current campaign.\n"
            + "\n"
            + "clear: \n"
            + "\tClears the screen.\n",
        output.toString().split(Controller.separator)[6]);
  }

}