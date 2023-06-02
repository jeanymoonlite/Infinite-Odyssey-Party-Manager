package view;

import static org.junit.Assert.*;

import java.io.IOException;
import model.Manager;
import model.infiniteodysseys.IOManager;
import model.infiniteodysseys.IOManagerTest;
import org.junit.Before;
import org.junit.Test;

public class IOManagerTextViewTest extends IOManagerTest {

  private IOManagerTextView view;
  private Manager model;
  private Appendable output;

  @Before
  public void initView() {
    this.initManager();
    this.model = new IOManager();
    this.output = new StringBuilder();
    this.view = new IOManagerTextView(this.model, this.output);
  }

  @Test
  public void invalidConstructor() {
    try {
      new IOManagerTextView(null, null);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("Manager cannot be null.", e.getMessage());
    }

    try {
      new IOManagerTextView(new IOManager(), null);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("Appendable cannot be null.", e.getMessage());
    }
  }

  @Test
  public void display() {
    try {
      try { this.view.display(null); }
      catch (IOException ignored) {}
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("Message cannot be null.", e.getMessage());
    }

    try {
      this.view.display("Hi! This is an example text.");
      assertEquals("Hi! This is an example text.", this.output.toString());
      this.view.display("\nThis is a second line!");
      assertEquals("Hi! This is an example text.\nThis is a second line!", this.output.toString());
    }
    catch (IOException e) {
      fail();
    }

  }

  @Test
  public void displayAllCharacters() {
    try {
      this.view.displayAllCharacters();
      fail();
    }
    catch (IllegalStateException | IOException e) {
      assertEquals("This Manager doesn't have any Characters!", e.getMessage());
    }

    this.addCharsAndParties(this.model);

    try {
      this.view.displayAllCharacters();
      assertEquals("Lunarose (Luna)",
          this.output.toString().split("\n")[0]);
      assertEquals("Jake Walker (Jacob)",
          this.output.toString().split("\n")[1]);
      assertEquals("Manuel (Bryan)",
          this.output.toString().split("\n")[2]);
      assertEquals("Rose Walker (Day)",
          this.output.toString().split("\n")[3]);
      assertEquals("Onion (Steven)",
          this.output.toString().split("\n")[4]);
      assertEquals("Dre (Andre)",
          this.output.toString().split("\n")[5]);
      assertEquals("Total Characters: 6",
          this.output.toString().split("\n")[6]);
    }
    catch (IOException e) {
      fail();
    }
  }

  @Test
  public void displayAllParties() {
    try {
      this.view.displayAllParties();
      fail();
    }
    catch (IllegalStateException | IOException e) {
      assertEquals("This Manager doesn't have any Parties!", e.getMessage());
    }

    this.addCharsAndParties(this.model);

    try {
      this.view.displayAllParties();
      assertEquals("The Boys: Lunarose (Luna), Jake Walker (Jacob), Manuel (Bryan), Onion (Steven)",
          this.output.toString().split("\n")[0]);
      assertEquals("The Infinite Odyssey: Lunarose (Luna), Rose Walker (Day)",
          this.output.toString().split("\n")[1]);
      assertEquals("Future: Lunarose (Luna), Dre (Andre), Onion (Steven), Rose Walker (Day)",
          this.output.toString().split("\n")[2]);
      assertEquals("Total Parties: 3",
          this.output.toString().split("\n")[3]);
    }
    catch (IOException e) {
      fail();
    }
  }

  @Test
  public void displayCharacter() {
    try {
      this.view.displayCharacter(null);
      fail();
    }
    catch (IllegalStateException | IOException e) {
      assertEquals("This Manager doesn't have any Characters!", e.getMessage());
    }

    this.addCharsAndParties(this.model);

    try {
      this.view.displayCharacter(null);
      fail();
    }
    catch (IllegalArgumentException | IOException e) {
      assertEquals("The given String cannot be null.", e.getMessage());
    }

    try {
      this.view.displayCharacter("Poopy");
      fail();
    }
    catch (IllegalArgumentException | IOException e) {
      assertEquals("This Manager doesn't have a Character named Poopy.", e.getMessage());
    }

    try {
      this.view.displayCharacter("Lunarose");
      assertEquals("Lunarose (Luna)\n"
              + "Class: Wizard (Fire Mage)\n"
              + "Hp: 100/100\n"
              + "Strength: 5\n"
              + "Intelligence: 5\n"
              + "Creativity: 10 (+5)\n"
              + "Charisma: 5\n"
              + "Stealth: 0\n"
              + "Intimidation: 0\n",
          this.output.toString());

      this.output = new StringBuilder();
      this.view = new IOManagerTextView(this.model, this.output);

      this.view.displayCharacter("Onion");
      assertEquals("Onion (Steven)\n"
              + "Class: Human\n"
              + "Hp: 100/100\n"
              + "Strength: 1 (+1)\n"
              + "Intelligence: 1 (+1)\n"
              + "Creativity: 1 (+1)\n"
              + "Charisma: 1 (+1)\n"
              + "Stealth: 1 (+1)\n"
              + "Intimidation: 1 (+1)\n",
          this.output.toString());
    }
    catch (IOException e) {
      fail();
    }
  }

  @Test
  public void displayParty() {
  }

  @Test
  public void displayActiveParty() {
  }
}