package controller.command.character;

import static org.junit.Assert.*;

import controller.IOManagerController;
import controller.IOManagerControllerTest;
import java.io.StringReader;
import model.infiniteodysseys.IOManager;
import org.junit.Test;
import view.IOManagerTextView;

public class CreateCharTest extends IOManagerControllerTest {

  @Test
  public void successfulCreateChar() {
    Readable input = new StringReader("create-char Onion Steven Human New Yorker 1 1 1 1 1 1 y quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertEquals("Character name: Onion\n"
            + "Player name: Steven\n"
            + "Role: Human\n"
            + "Role Specification: \n"
            + "Strength: 1\n"
            + "Intelligence: 1\n"
            + "Creativity: 1\n"
            + "Charisma: 1\n"
            + "Stealth: 1\n"
            + "Intimidation: 1\n"
            + "Is this okay? (Confirm y or n): y\n"
            + "Onion (Steven) was added to the Manager.\n"
            + "\n",
        output.toString().split("Awaiting command:\n")[1].split("WARNING")[0]);
  }

}