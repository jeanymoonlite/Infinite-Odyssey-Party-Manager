package controller.command;

import static org.junit.Assert.*;

import controller.IOManagerController;
import controller.IOManagerControllerTest;
import java.io.StringReader;
import model.infiniteodysseys.IOManager;
import org.junit.Before;
import org.junit.Test;
import view.IOManagerTextView;

public class HelpTest extends IOManagerControllerTest {

  @Test
  public void helpQuit() {
    Readable input = new StringReader("help quit y");
    Appendable output = new StringBuilder();

    this.model = new IOManager();
    this.view = new IOManagerTextView(this.model, output);
    this.controller = new IOManagerController(this.model, this.view, input);
    this.controller.start();

    assertEquals("\n"
            + "",
        output.toString().split("Awaiting command:\n")[1]);
  }

}