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

  @Test
  public void invalidConstructor() {

    try {
      this.controller = new IOManagerController(null, null, null);
      fail("Model is null");
    }
    catch (IllegalArgumentException e) {
      assertEquals("The Model for the Controller cannot be null.",
          e.getMessage());
    }

    try {
      this.model = new IOManager();
      this.controller = new IOManagerController(this.model, null, null);
      fail("View is null");
    }
    catch (IllegalArgumentException e) {
      assertEquals("The View for the Controller cannot be null.",
          e.getMessage());
    }

    try {
      this.model = new IOManager();
      this.view = new IOManagerTextView(this.model, System.out);
      this.controller = new IOManagerController(this.model, this.view, null);
      fail("Output is null");
    }
    catch (IllegalArgumentException e) {
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

    assertEquals("Awaiting command: ",
        output.toString().split(Controller.separator)[1]);
  }

  @Test
  public void quitMessageYes() {
    Readable input = new StringReader("quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertEquals("WARNING: Quitting will delete any unsaved data.\n"
            + "Confirm (y or n): \n"
            + "Thank you for using the Infinite Odysseys Party Manager.",
        output.toString().split(Controller.separator)[2]);
  }

  @Test
  public void quitMessageNo() {
    Readable input = new StringReader("quit n quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertEquals("WARNING: Quitting will delete any unsaved data.\n"
            + "Confirm (y or n): ",
        output.toString().split(Controller.separator)[2]);

    assertEquals("Awaiting command: ",
        output.toString().split(Controller.separator)[3]);

    assertEquals("WARNING: Quitting will delete any unsaved data.\n"
            + "Confirm (y or n): \n"
            + "Thank you for using the Infinite Odysseys Party Manager.",
        output.toString().split(Controller.separator)[4]);
  }

  @Test
  public void badInput() {
    Readable input = new StringReader("huh what quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertEquals("Awaiting command: \n"
            + "Invalid command.\n",
        output.toString().split(Controller.separator)[1]);

    assertEquals("Awaiting command: \n"
            + "Invalid command.\n",
        output.toString().split(Controller.separator)[2]);

    assertEquals("Awaiting command: ",
        output.toString().split(Controller.separator)[3]);

    assertEquals("WARNING: Quitting will delete any unsaved data.\n"
            + "Confirm (y or n): \n"
            + "Thank you for using the Infinite Odysseys Party Manager.",
        output.toString().split(Controller.separator)[4]);
  }

//  @Test
//  public void noInput() {
//    Readable input = new StringReader("");
//    Appendable output = new StringBuilder();
//
//    this.model = new IOManager();
//    this.view = new IOManagerTextView(this.model, output);
//    this.controller = new IOManagerController(this.model, this.view, input);
//
//    try {
//      this.controller.start();
//    }
//    catch (NoSuchElementException e) {
//      assertEquals("No input detected.", e.getMessage());
//    }
//  }

  @Test
  public void badInputTryingToQuit() {
    Readable input = new StringReader("quit b y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertEquals("WARNING: Quitting will delete any unsaved data.\n"
            + "Confirm (y or n): "
            + "\nInvalid input.\n"
            + "WARNING: Quitting will delete any unsaved data.\n"
            + "Confirm (y or n): "
            + "\nThank you for using the Infinite Odysseys Party Manager.",
        output.toString().split(Controller.separator)[2]);
  }

  @Test
  public void unavailableCommandsDuringCampaign() {
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
        + "create-party\n"
        + "Ninja Sex Party\n"
        + "Danny Sexbang, Ninja Brian\n"
        + "y\n"
        + "start Ninja Sex Party\n"
        + "y\n"
        + "create-party\n"
        + "create-char\n"
        + "remove-char\n"
        + "remove-party\n"
        + "start\n"
        + "quit y quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertEquals("Awaiting command: \n"
            + "Invalid state: This command can't be used during a campaign.\n"
            + "Use the quit command to end the current campaign.\n",
        output.toString().split(Controller.separator)[5]);

    assertEquals("Awaiting command: \n"
            + "Invalid state: This command can't be used during a campaign.\n"
            + "Use the quit command to end the current campaign.\n",
        output.toString().split(Controller.separator)[6]);

    assertEquals("Awaiting command: \n"
            + "Invalid state: This command can't be used during a campaign.\n"
            + "Use the quit command to end the current campaign.\n",
        output.toString().split(Controller.separator)[8]);

    assertEquals("Awaiting command: \n"
            + "Invalid state: This command can't be used during a campaign.\n"
            + "Use the quit command to end the current campaign.\n",
        output.toString().split(Controller.separator)[9]);
  }

}