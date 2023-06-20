package controller.command.misc;

import static org.junit.Assert.*;

import controller.Controller;
import controller.IOManagerController;
import controller.files.savefiles.Ver100FileValid;
import java.io.StringReader;
import model.Manager;
import model.infiniteodysseys.IOManager;
import org.junit.Test;
import view.IOManagerTextView;
import view.TextView;

public class LoadFileTest {

  protected IOManagerController controller;
  protected TextView view;
  protected Manager model;

  @Test
  public void getSignature() {
    this.model = new IOManager();
    assertEquals("load (fileName)",
        new LoadFile(null, null, null, null).getSignature());
  }

  @Test
  public void getDescription() {
    this.model = new IOManager();
    assertEquals("Loads all of the Characters and Parties in an .iom file.",
        new LoadFile(null, null, null, null).getDescription());
  }

  @Test
  public void fileDoesntExist() {
    Readable input = new StringReader("load grumps\n"
        + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertEquals("Awaiting command: "
        + "Invalid input: The given file does not exist.", output.toString().split(
        Controller.separator)[1]);
  }

  @Test
  public void fileIsDirectory() {
    Readable input = new StringReader("load C:\\Users\\Jr641\\"
        + "\n"
        + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertEquals("Awaiting command: "
        + "Invalid input: The given file is a folder.", output.toString().split(
        Controller.separator)[1]);
  }

  @Test
  public void invalidFile() {
    Readable input = new StringReader("load C:\\Users\\Jr641\\Desktop\\Programming\\Java Programs\\Infinite-Odyssey-Party-Manager\\Party Manager\\Party Manager\\res\\grumps.txt\n"
        + "quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertEquals("Awaiting command: "
        + "Invalid file: The given file is not a valid .iom file.", output.toString().split(
        Controller.separator)[1]);
  }
}