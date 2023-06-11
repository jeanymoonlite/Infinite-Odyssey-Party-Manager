package controller.command.party;

import controller.command.ACommand;
import java.io.IOException;
import java.util.Scanner;
import model.Manager;
import view.TextView;

public class EditParty extends ACommand {

  private final Scanner sc;

  /**
   * Constructs a new {@code PartyCommand}.
   *
   * @param model the model to use
   * @param view  the view to use to render messages
   * @param sc    the scanner to read input from
   */
  public EditParty(Manager model, TextView view, Scanner sc) {
    super(model, view);
    this.sc = sc;
    this.signature = "";
    this.description = "Edits the Party with the given name.\n"
        + "This will put the program into Party Editing mode.\n"
        + "A new set of commands will become available in editing mode.";
  }

  @Override
  public void run() {
    try {

      this.view.display("");
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }
}
