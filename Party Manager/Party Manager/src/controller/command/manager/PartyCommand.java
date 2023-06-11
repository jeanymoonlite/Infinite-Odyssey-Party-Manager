package controller.command.manager;

import controller.command.ACommand;
import java.io.IOException;
import java.util.Scanner;
import model.Manager;
import view.TextView;

public final class PartyCommand extends ACommand {

  private final Scanner sc;

  /**
   * Constructs a new {@code PartyCommand}.
   *
   * @param model the model to use
   * @param view  the view to use to render messages
   * @param sc    the scanner to read input from
   */
  public PartyCommand(Manager model, TextView view, Scanner sc) {
    super(model, view);
    this.sc = sc;
  }

  @Override
  public void run() {
    try {
      try {
        this.view.displayActiveParty();

        if (this.model.getActiveParty() == null) {
          this.view.display("Set the active party using the start command.\n");
        }
      }
      catch (IllegalStateException e) {
        this.view.display("The Manager doesn't have any Parties!\n");
        this.view.display("Add Parties using the create-party command.\n");
      }
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }
}
