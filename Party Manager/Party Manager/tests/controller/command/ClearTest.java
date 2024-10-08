package controller.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import controller.Controller;
import controller.IOManagerController;
import controller.IOManagerControllerTest;
import controller.command.misc.Clear;
import java.io.StringReader;
import model.infiniteodysseys.IOManager;
import org.junit.Test;
import view.IOManagerTextView;

public class ClearTest extends IOManagerControllerTest {

  @Test
  public void constructor() {
    for (int i = -1; i > -1000; i--) {
      try {
        new Clear(this.model, this.view, -1);
        fail();
      }
      catch (IllegalArgumentException e) {
        assertEquals("Amount must be greater than 0.", e.getMessage());
      }
    }
  }

  @Test
  public void clear() {
    Readable input = new StringReader("clear quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    String newlines = "";

    for (int i = 0; i < 100; i++) {
      newlines = newlines.concat("\n");
    }

    assertEquals("Awaiting command: " + newlines,
        output.toString().split(Controller.separator)[1]);
  }
}