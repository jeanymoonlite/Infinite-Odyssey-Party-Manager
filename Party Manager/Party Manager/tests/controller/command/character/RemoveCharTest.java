package controller.command.character;

import static org.junit.Assert.*;

import controller.IOManagerController;
import controller.IOManagerControllerTest;
import java.io.StringReader;
import model.infiniteodysseys.IOManager;
import org.junit.Test;
import view.IOManagerTextView;

public class RemoveCharTest extends IOManagerControllerTest {

  @Test
  public void getSignature() {
    assertEquals("remove-char (name)",
        new RemoveChar(null, null, null).getSignature());
  }

  @Test
  public void getDescription() {
    assertEquals("Removes a Character with the given name.",
        new RemoveChar(null, null, null).getDescription());
  }

  @Test
  public void successfulRemoveChar() {
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
        + "remove-char Lunarose\ny\n"
        + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertTrue(this.model.doesCharacterExist("Onion"));
    assertFalse(this.model.doesCharacterExist("Lunarose"));
    assertEquals("Character name: "
            + "Player name: Role: Role Specification (Type n/a to leave blank): "
            + "Strength: Intelligence: Creativity: Charisma: Stealth: Intimidation: \n"
            + "Create the following Character? (Confirm y or n): \n"
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

    assertEquals("Character name: "
            + "Player name: Role: Role Specification (Type n/a to leave blank): "
            + "Strength: Intelligence: Creativity: Charisma: Stealth: Intimidation: \n"
            + "Create the following Character? (Confirm y or n): \n"
            + "Lunarose (Luna)\n"
            + "Role: Wizard (Mage)\n"
            + "Hp: 100/100\n"
            + "Strength: 1\n"
            + "Intelligence: 1\n"
            + "Creativity: 1 (+5)\n"
            + "Charisma: 1\n"
            + "Stealth: 1\n"
            + "Intimidation: 1\n"
            + "Lunarose (Luna) was added to the Manager.\n",
        output.toString().split("Awaiting command:\n")[2].split("WARNING")[0]);

    assertEquals("The following action cannot be undone.\n"
            + "Remove the following Character? (Confirm y or n): \n"
            + "Lunarose (Luna)\n"
            + "Role: Wizard (Mage)\n"
            + "Hp: 100/100\n"
            + "Strength: 1\n"
            + "Intelligence: 1\n"
            + "Creativity: 1 (+5)\n"
            + "Charisma: 1\n"
            + "Stealth: 1\n"
            + "Intimidation: 1\n"
            + "Lunarose (Luna) was removed from the Manager.\n",
        output.toString().split("Awaiting command:\n")[3].split("WARNING")[0]);
  }

  @Test
  public void cancellingRemoveChar() {
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
        + "remove-char Lunarose\n"
        + "n quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertTrue(this.model.doesCharacterExist("Onion"));
    assertTrue(this.model.doesCharacterExist("Lunarose"));

    assertEquals("The following action cannot be undone.\n"
            + "Remove the following Character? (Confirm y or n): \n"
            + "Lunarose (Luna)\n"
            + "Role: Wizard (Mage)\n"
            + "Hp: 100/100\n"
            + "Strength: 1\n"
            + "Intelligence: 1\n"
            + "Creativity: 1 (+5)\n"
            + "Charisma: 1\n"
            + "Stealth: 1\n"
            + "Intimidation: 1\n"
            + "Lunarose (Luna) will not be removed.\n",
        output.toString().split("Awaiting command:\n")[3].split("WARNING")[0]);
  }

  @Test
  public void noExistCharRemoveChar() {
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
        + "remove-char Luna\n"
        + "n quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertTrue(this.model.doesCharacterExist("Onion"));
    assertTrue(this.model.doesCharacterExist("Lunarose"));

    assertEquals("Invalid input: The Character Luna doesn't exist in this Manager.\n",
        output.toString().split("Awaiting command:\n")[3].split("WARNING")[0]);
  }

  @Test
  public void noCharsRemoveChar() {
    Readable input = new StringReader("remove-char Luna\n"
        + "n quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertFalse(this.model.doesCharacterExist("Onion"));
    assertFalse(this.model.doesCharacterExist("Lunarose"));

    assertEquals("The Manager doesn't have any Characters!\n"
            + "Add Characters using the create-char command.\n",
        output.toString().split("Awaiting command:\n")[1].split("WARNING")[0]);
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
        + "remove-char Luna\n"
        + "n quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertTrue(this.model.doesCharacterExist("Onion"));
    assertTrue(this.model.doesCharacterExist("Lunarose"));

    assertEquals("Invalid state: This command can't be used during a campaign.\n"
            + "Use the quit command to end the current campaign.\n",
        output.toString().split("Awaiting command:\n")[5].split("WARNING")[0]);
  }

  @Test
  public void removedFromParty() {
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
        + "show-party Boys\n"
        + "remove-char Lunarose\ny\n"
        + "show-party Boys\n"
        + "n quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertTrue(this.model.doesCharacterExist("Onion"));
    assertFalse(this.model.doesCharacterExist("Lunarose"));

    assertEquals("Onion (Steven)\n"
            + "Role: Human (New Yorker)\n"
            + "Hp: 100/100\n"
            + "Strength: 1 (+1)\n"
            + "Intelligence: 1 (+1)\n"
            + "Creativity: 1 (+1)\n"
            + "Charisma: 1 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 1 (+1)\n"
            + "\n"
            + "Lunarose (Luna)\n"
            + "Role: Wizard (Mage)\n"
            + "Hp: 100/100\n"
            + "Strength: 1\n"
            + "Intelligence: 1\n"
            + "Creativity: 1 (+5)\n"
            + "Charisma: 1\n"
            + "Stealth: 1\n"
            + "Intimidation: 1\n",
        output.toString().split("Awaiting command:\n")[4].split("WARNING")[0]);

    assertEquals("Onion (Steven)\n"
            + "Role: Human (New Yorker)\n"
            + "Hp: 100/100\n"
            + "Strength: 1 (+1)\n"
            + "Intelligence: 1 (+1)\n"
            + "Creativity: 1 (+1)\n"
            + "Charisma: 1 (+1)\n"
            + "Stealth: 1 (+1)\n"
            + "Intimidation: 1 (+1)\n",
        output.toString().split("Awaiting command:\n")[6].split("WARNING")[0]);
  }

  @Test
  public void removedCharAndAParty() {
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
        + "Luna\n"
        + "Lunarose\n"
        + "y\n"
        + "show-party Luna\n"
        + "remove-char Lunarose\ny\n"
        + "show-all-parties\n"
        + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertTrue(this.model.doesCharacterExist("Onion"));
    assertFalse(this.model.doesCharacterExist("Lunarose"));

    assertEquals("Lunarose (Luna)\n"
            + "Role: Wizard (Mage)\n"
            + "Hp: 100/100\n"
            + "Strength: 1\n"
            + "Intelligence: 1\n"
            + "Creativity: 1 (+5)\n"
            + "Charisma: 1\n"
            + "Stealth: 1\n"
            + "Intimidation: 1\n",
        output.toString().split("Awaiting command:\n")[4].split("WARNING")[0]);

    assertEquals("The following action cannot be undone.\n"
            + "Remove the following Character? (Confirm y or n): \n"
            + "Lunarose (Luna)\n"
            + "Role: Wizard (Mage)\n"
            + "Hp: 100/100\n"
            + "Strength: 1\n"
            + "Intelligence: 1\n"
            + "Creativity: 1 (+5)\n"
            + "Charisma: 1\n"
            + "Stealth: 1\n"
            + "Intimidation: 1\n"
            + "Lunarose (Luna) was removed from the Manager.\n"
            + "The Party Luna was removed from the Manager.\n",
        output.toString().split("Awaiting command:\n")[5].split("WARNING")[0]);

    assertEquals("The Manager doesn't have any Parties!\n"
            + "Add Parties using the create-party command.\n",
        output.toString().split("Awaiting command:\n")[6].split("WARNING")[0]);
  }

  @Test
  public void removedCharAndAParty2() {
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
        + "Luna\n"
        + "Lunarose\n"
        + "y\n"
        + "create-party\n"
        + "Boys\n"
        + "Onion, Lunarose\n"
        + "y\n"
        + "show-party Luna\n"
        + "remove-char Lunarose\ny\n"
        + "show-all-parties\n"
        + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertTrue(this.model.doesCharacterExist("Onion"));
    assertFalse(this.model.doesCharacterExist("Lunarose"));

    assertEquals("Lunarose (Luna)\n"
            + "Role: Wizard (Mage)\n"
            + "Hp: 100/100\n"
            + "Strength: 1\n"
            + "Intelligence: 1\n"
            + "Creativity: 1 (+5)\n"
            + "Charisma: 1\n"
            + "Stealth: 1\n"
            + "Intimidation: 1\n",
        output.toString().split("Awaiting command:\n")[5].split("WARNING")[0]);

    assertEquals("The following action cannot be undone.\n"
            + "Remove the following Character? (Confirm y or n): \n"
            + "Lunarose (Luna)\n"
            + "Role: Wizard (Mage)\n"
            + "Hp: 100/100\n"
            + "Strength: 1\n"
            + "Intelligence: 1\n"
            + "Creativity: 1 (+5)\n"
            + "Charisma: 1\n"
            + "Stealth: 1\n"
            + "Intimidation: 1\n"
            + "Lunarose (Luna) was removed from the Manager.\n"
            + "The Party Luna was removed from the Manager.\n",
        output.toString().split("Awaiting command:\n")[6].split("WARNING")[0]);

    assertEquals("Boys: Onion (Steven)\n"
            + "Total Parties: 1\n",
        output.toString().split("Awaiting command:\n")[7].split("WARNING")[0]);
  }

  @Test
  public void badInputRemoveChar() {
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
        + "remove-char Lunarose\n"
        + "a\n"
        + "afs\n"
        + "yyyyy\n"
        + "n\n"
        + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertTrue(this.model.doesCharacterExist("Onion"));
    assertTrue(this.model.doesCharacterExist("Lunarose"));

    assertEquals("The following action cannot be undone.\n"
            + "Remove the following Character? (Confirm y or n): \n"
            + "Lunarose (Luna)\n"
            + "Role: Wizard (Mage)\n"
            + "Hp: 100/100\n"
            + "Strength: 1\n"
            + "Intelligence: 1\n"
            + "Creativity: 1 (+5)\n"
            + "Charisma: 1\n"
            + "Stealth: 1\n"
            + "Intimidation: 1\n"
            + "Invalid input.\n"
            + "The following action cannot be undone.\n"
            + "Remove the following Character? (Confirm y or n): \n"
            + "Lunarose (Luna)\n"
            + "Role: Wizard (Mage)\n"
            + "Hp: 100/100\n"
            + "Strength: 1\n"
            + "Intelligence: 1\n"
            + "Creativity: 1 (+5)\n"
            + "Charisma: 1\n"
            + "Stealth: 1\n"
            + "Intimidation: 1\n"
            + "Invalid input.\n"
            + "The following action cannot be undone.\n"
            + "Remove the following Character? (Confirm y or n): \n"
            + "Lunarose (Luna)\n"
            + "Role: Wizard (Mage)\n"
            + "Hp: 100/100\n"
            + "Strength: 1\n"
            + "Intelligence: 1\n"
            + "Creativity: 1 (+5)\n"
            + "Charisma: 1\n"
            + "Stealth: 1\n"
            + "Intimidation: 1\n"
            + "Invalid input.\n"
            + "The following action cannot be undone.\n"
            + "Remove the following Character? (Confirm y or n): \n"
            + "Lunarose (Luna)\n"
            + "Role: Wizard (Mage)\n"
            + "Hp: 100/100\n"
            + "Strength: 1\n"
            + "Intelligence: 1\n"
            + "Creativity: 1 (+5)\n"
            + "Charisma: 1\n"
            + "Stealth: 1\n"
            + "Intimidation: 1\n"
            + "Lunarose (Luna) will not be removed.\n",
        output.toString().split("Awaiting command:\n")[3].split("WARNING")[0]);
  }
}