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

public class RemovePartyTest extends IOManagerControllerTest {

  @Test
  public void successfulRemoveParty() {
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
        + "y\n"
        + "create-char\n"
        + "Lunarose\n"
        + "Luna\n"
        + "Wizard\n"
        + "Mage\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "y\n"
        + "create-party\n"
        + "Boys\n"
        + "Onion, Lunarose\n"
        + "y\n"
        + "create-party\n"
        + "Luna\n"
        + "Lunarose\n"
        + "y\n"
        + "show-party Luna\n"
        + "remove-party Luna\ny\n"
        + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertTrue(this.model.doesCharacterExist("Onion"));
    assertTrue(this.model.doesCharacterExist("Lunarose"));
    assertTrue(this.model.doesPartyExist("Boys"));
    assertFalse(this.model.doesPartyExist("Luna"));

    assertEquals("Awaiting command: Lunarose (Luna)\n"
            + "Class: Wizard (Mage)\n"
            + "Hp: 100/100\n"
            + "Strength: 1\n"
            + "Intelligence: 1\n"
            + "Creativity: 1 (+5)\n"
            + "Charisma: 1\n"
            + "Stealth: 1\n"
            + "Intimidation: 1\n",
        output.toString().split(Controller.separator)[5]);

    assertEquals("Awaiting command: The following action cannot be undone.\n"
            + "Remove the following Party?\n"
            + "Lunarose (Luna)\n"
            + "Class: Wizard (Mage)\n"
            + "Hp: 100/100\n"
            + "Strength: 1\n"
            + "Intelligence: 1\n"
            + "Creativity: 1 (+5)\n"
            + "Charisma: 1\n"
            + "Stealth: 1\n"
            + "Intimidation: 1\n"
            + "Confirm (y or n): "
            + "The Party Luna was removed from the Manager.\n",
        output.toString().split(Controller.separator)[6]);
  }

  @Test
  public void cancellingRemoveParty() {
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
        + "y\n"
        + "create-char\n"
        + "Lunarose\n"
        + "Luna\n"
        + "Wizard\n"
        + "Mage\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "y\n"
        + "create-party\n"
        + "Boys\n"
        + "Onion, Lunarose\n"
        + "y\n"
        + "create-party\n"
        + "Luna\n"
        + "Lunarose\n"
        + "y\n"
        + "show-party Luna\n"
        + "remove-party Luna\n"
        + "n\n"
        + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertTrue(this.model.doesCharacterExist("Onion"));
    assertTrue(this.model.doesCharacterExist("Lunarose"));
    assertTrue(this.model.doesPartyExist("Boys"));
    assertTrue(this.model.doesPartyExist("Luna"));

    assertEquals("Awaiting command: Lunarose (Luna)\n"
            + "Class: Wizard (Mage)\n"
            + "Hp: 100/100\n"
            + "Strength: 1\n"
            + "Intelligence: 1\n"
            + "Creativity: 1 (+5)\n"
            + "Charisma: 1\n"
            + "Stealth: 1\n"
            + "Intimidation: 1\n",
        output.toString().split(Controller.separator)[5]);

    assertEquals("Awaiting command: The following action cannot be undone.\n"
            + "Remove the following Party?\n"
            + "Lunarose (Luna)\n"
            + "Class: Wizard (Mage)\n"
            + "Hp: 100/100\n"
            + "Strength: 1\n"
            + "Intelligence: 1\n"
            + "Creativity: 1 (+5)\n"
            + "Charisma: 1\n"
            + "Stealth: 1\n"
            + "Intimidation: 1\n"
            + "Confirm (y or n): "
            + "The Party Luna will not be removed.\n",
        output.toString().split(Controller.separator)[6]);
  }

  @Test
  public void noExistPartyRemoveParty() {
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
        + "y\n"
        + "create-char\n"
        + "Lunarose\n"
        + "Luna\n"
        + "Wizard\n"
        + "Mage\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "y\n"
        + "create-party\n"
        + "Boys\n"
        + "Onion, Lunarose\n"
        + "y\n"
        + "create-party\n"
        + "Luna\n"
        + "Lunarose\n"
        + "y\n"
        + "show-party Luna\n"
        + "remove-party The Boys\n"
        + "n\n"
        + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertTrue(this.model.doesCharacterExist("Onion"));
    assertTrue(this.model.doesCharacterExist("Lunarose"));
    assertTrue(this.model.doesPartyExist("Boys"));
    assertTrue(this.model.doesPartyExist("Luna"));

    assertEquals("Awaiting command: \nInvalid input: The Party The Boys doesn't exist in this Manager.\n",
        output.toString().split(Controller.separator)[6]);
  }

  @Test
  public void noPartiesRemoveParty() {
    Readable input = new StringReader("remove-party The Boys\n"
        + "n\n"
        + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertFalse(this.model.doesCharacterExist("Onion"));
    assertFalse(this.model.doesCharacterExist("Lunarose"));
    assertFalse(this.model.doesPartyExist("Boys"));
    assertFalse(this.model.doesPartyExist("Luna"));

    assertEquals("Awaiting command: \nInvalid state: The Manager doesn't have any Parties!\n"
            + "Add Parties using the create-party command.\n",
        output.toString().split(Controller.separator)[1]);
  }

  @Test
  public void inCampaignRemoveChar() {
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
        + "y\n"
        + "create-char\n"
        + "Lunarose\n"
        + "Luna\n"
        + "Wizard\n"
        + "Mage\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "y\n"
        + "create-party\n"
        + "Boys\n"
        + "Onion, Lunarose\n"
        + "y\n"
        + "start Boys\n"
        + "y\n"
        + "remove-party Luna\n"
        + "n quit y quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertTrue(this.model.doesCharacterExist("Onion"));
    assertTrue(this.model.doesCharacterExist("Lunarose"));

    assertEquals("Awaiting command: \nInvalid state: This command can't be used during a campaign.\n"
            + "Use the quit command to end the current campaign.\n",
        output.toString().split(Controller.separator)[5]);
  }

  @Test
  public void badInputRemoveParty() {
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
        + "y\n"
        + "create-char\n"
        + "Lunarose\n"
        + "Luna\n"
        + "Wizard\n"
        + "Mage\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "1\n"
        + "y\n"
        + "create-party\n"
        + "Boys\n"
        + "Onion, Lunarose\n"
        + "y\n"
        + "create-party\n"
        + "Luna\n"
        + "Lunarose\n"
        + "y\n"
        + "show-party Luna\n"
        + "remove-party Luna\n"
        + "ssss\n"
        + "yyy\n"
        + "yyaasaf\n"
        + "n\n"
        + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertTrue(this.model.doesCharacterExist("Onion"));
    assertTrue(this.model.doesCharacterExist("Lunarose"));
    assertTrue(this.model.doesPartyExist("Boys"));
    assertTrue(this.model.doesPartyExist("Luna"));

    assertEquals("Awaiting command: Lunarose (Luna)\n"
            + "Class: Wizard (Mage)\n"
            + "Hp: 100/100\n"
            + "Strength: 1\n"
            + "Intelligence: 1\n"
            + "Creativity: 1 (+5)\n"
            + "Charisma: 1\n"
            + "Stealth: 1\n"
            + "Intimidation: 1\n",
        output.toString().split(Controller.separator)[5]);

    assertEquals("Awaiting command: The following action cannot be undone.\n"
            + "Remove the following Party?\n"
            + "Lunarose (Luna)\n"
            + "Class: Wizard (Mage)\n"
            + "Hp: 100/100\n"
            + "Strength: 1\n"
            + "Intelligence: 1\n"
            + "Creativity: 1 (+5)\n"
            + "Charisma: 1\n"
            + "Stealth: 1\n"
            + "Intimidation: 1\n"
            + "Confirm (y or n): "
            + "Invalid input.\n"
            + "The following action cannot be undone.\n"
            + "Remove the following Party?\n"
            + "Lunarose (Luna)\n"
            + "Class: Wizard (Mage)\n"
            + "Hp: 100/100\n"
            + "Strength: 1\n"
            + "Intelligence: 1\n"
            + "Creativity: 1 (+5)\n"
            + "Charisma: 1\n"
            + "Stealth: 1\n"
            + "Intimidation: 1\n"
            + "Confirm (y or n): "
            + "Invalid input.\n"
            + "The following action cannot be undone.\n"
            + "Remove the following Party?\n"
            + "Lunarose (Luna)\n"
            + "Class: Wizard (Mage)\n"
            + "Hp: 100/100\n"
            + "Strength: 1\n"
            + "Intelligence: 1\n"
            + "Creativity: 1 (+5)\n"
            + "Charisma: 1\n"
            + "Stealth: 1\n"
            + "Intimidation: 1\n"
            + "Confirm (y or n): "
            + "Invalid input.\n"
            + "The following action cannot be undone.\n"
            + "Remove the following Party?\n"
            + "Lunarose (Luna)\n"
            + "Class: Wizard (Mage)\n"
            + "Hp: 100/100\n"
            + "Strength: 1\n"
            + "Intelligence: 1\n"
            + "Creativity: 1 (+5)\n"
            + "Charisma: 1\n"
            + "Stealth: 1\n"
            + "Intimidation: 1\n"
            + "Confirm (y or n): "
            + "The Party Luna will not be removed.\n",
        output.toString().split(Controller.separator)[6]);
  }
}