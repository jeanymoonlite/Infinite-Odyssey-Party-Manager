package controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.StringReader;
import model.Manager;
import model.infiniteodysseys.IOManager;
import org.junit.Test;
import view.IOManagerTextView;
import view.TextView;

public class IOManagerControllerTest {

  protected IOManagerController controller;
  protected TextView view;
  protected Manager model;

  private final String startMessage = "Welcome to the Infinite Odyssey's Party Manager.\n\n"
      + "This Manager is made to be used for Infinite Odysseys.\n"
      + "This means that the Manager has the following rules when it\n"
      + "comes to creating Characters:\n"
      + "1. All Characters have the following stats:\n"
      + "\ti. Hp (integer between 0-100)\n"
      + "\tii. Defense (which is dictated by Role)\n"
      + "\tiii. Strength\n"
      + "\tiv. Intelligence\n"
      + "\tv. Creativity\n"
      + "\tvi. Charisma\n"
      + "\tvii. Stealth\n"
      + "\tviii. Intimidation\n"
      + "\n2. The sum of every stat's value must NOT exceed 30.\n"
      + "\n3. No stat can have a value less than 0.\n"
      + "\n4. All Characters must have one of the Roles listed below.\n"
      + "\ti. Warrior\n"
      + "\tii. Wizard\n"
      + "\tiii. Bard\n"
      + "\tiv. Engineer\n"
      + "\tv. Rogue\n"
      + "\tvi. Monk\n"
      + "\tvii. Human\n";

  @Test
  public void invalidConstructor() {

    try {
      this.controller = new IOManagerController(null, null, null);
      fail("Model is null");
    } catch (IllegalArgumentException e) {
      assertEquals("The Model for the Controller cannot be null.",
          e.getMessage());
    }

    try {
      this.model = new IOManager();
      this.controller = new IOManagerController(this.model, null, null);
      fail("View is null");
    } catch (IllegalArgumentException e) {
      assertEquals("The View for the Controller cannot be null.",
          e.getMessage());
    }

    try {
      this.model = new IOManager();
      this.view = new IOManagerTextView(this.model, System.out);
      this.controller = new IOManagerController(this.model, this.view, null);
      fail("Output is null");
    } catch (IllegalArgumentException e) {
      assertEquals("The Readable for the Controller cannot be null.",
          e.getMessage());
    }
  }

  @Test
  public void welcomeMessage() {
    Readable input = new StringReader("quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertEquals(this.startMessage
            + "Awaiting command:\n",
        output.toString().split("WARNING")[0]);
  }

  @Test
  public void quitMessageYes() {
    Readable input = new StringReader("quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertEquals(this.startMessage
            + "Awaiting command:\n"
            + "WARNING: Quitting will delete any unsaved progress. "
            + "Confirm? (y/n)\n"
            + "Bye Bye!",
        output.toString());
  }

  @Test
  public void quitMessageNo() {
    Readable input = new StringReader("quit n quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertEquals(this.startMessage
            + "Awaiting command:\n"
            + "WARNING: Quitting will delete any unsaved progress. "
            + "Confirm? (y/n)\n"
            + "Awaiting command:\n"
            + "WARNING: Quitting will delete any unsaved progress. "
            + "Confirm? (y/n)\n"
            + "Bye Bye!",
        output.toString());
  }

  @Test
  public void badInput() {
    Readable input = new StringReader("huh what quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertEquals(this.startMessage
            + "Awaiting command:\n"
            + "Invalid command\n"
            + "Awaiting command:\n"
            + "Invalid command\n"
            + "Awaiting command:\n"
            + "WARNING: Quitting will delete any unsaved progress. "
            + "Confirm? (y/n)\n"
            + "Bye Bye!",
        output.toString());
  }

  @Test
  public void noInput() {
    Readable input = new StringReader("");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);

    try {
      this.controller.start();
    }
    catch (IllegalStateException e) {
      assertEquals("No input detected.", e.getMessage());
    }
  }

  @Test
  public void badInputTryingToQuit() {
    Readable input = new StringReader("quit b y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();


    assertEquals(this.startMessage
            + "Awaiting command:\n"
            + "WARNING: Quitting will delete any unsaved progress. "
            + "Confirm? (y/n)\n"
            + "Invalid command\n"
            + "WARNING: Quitting will delete any unsaved progress. "
            + "Confirm? (y/n)\n"
            + "Bye Bye!",
        output.toString());
  }

  @Test
  public void badInputTryingToQuit2() {
    Readable input = new StringReader("quit b quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();


    assertEquals(this.startMessage
            + "Awaiting command:\n"
            + "WARNING: Quitting will delete any unsaved progress. "
            + "Confirm? (y/n)\n"
            + "Invalid command\n"
            + "WARNING: Quitting will delete any unsaved progress. "
            + "Confirm? (y/n)\n"
            + "Invalid command\n"
            + "WARNING: Quitting will delete any unsaved progress. "
            + "Confirm? (y/n)\n"
            + "Bye Bye!",
        output.toString());
  }

}