package controller.command.party;

import static org.junit.Assert.*;

import controller.IOManagerController;
import controller.IOManagerControllerTest;
import java.io.StringReader;
import model.infiniteodysseys.IOManager;
import org.junit.Test;
import view.IOManagerTextView;

public class CreatePartyTest extends IOManagerControllerTest {

  @Test
  public void successfulCreateParty() {
    Readable input = new StringReader("create-char\n"
        + "Onion\n"
        + "Steven\n"
        + "Human\n"
        + "New Yorker\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "y quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertTrue(this.model.doesCharacterExist("Onion"));
    assertEquals("Character name: "
            + "Player name: "
            + "Role: "
            + "Role Specification (Type n/a to leave blank): "
            + "Strength: "
            + "Intelligence: "
            + "Creativity: "
            + "Charisma: "
            + "Stealth: "
            + "Intimidation: \n"
            + "Create the following Character? (Confirm y or n): y\n"
            + "Onion (Steven)\n"
            + "Role: Human (New Yorker)\n"
            + "Hp: 100/100\n"
            + "Strength: 1 (+1)\n"
            + "Intelligence: 1 (+1)\n"
            + "Creativity: 1 (+1)\n"
            + "Charisma: 1 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 1 (+1)\n"
            + "Onion (Steven) was added to the Manager.\n",
        output.toString().split("Awaiting command:\n")[1].split("WARNING")[0]);
  }
}