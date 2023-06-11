package controller.command.manager;

import controller.command.ACommand;
import java.io.IOException;
import java.util.Scanner;
import model.Manager;
import view.TextView;

/**
 * A command that displays a character in the given {@code Manager}.
 */
public final class ShowChar extends ACommand {

  private final Scanner sc;

  /**
   * Constructs a new {@code ShowChar}.
   *
   * @param model the model to use
   * @param view  the view to use to render messages
   * @param sc    the scanner to read input from
   */
  public ShowChar(Manager model, TextView view, Scanner sc) {
    super(model, view);
    this.sc = sc;
  }

  @Override
  public void run() {
    try {
      String name = this.sc.nextLine().trim();

      try {
        this.view.displayCharacter(name);
      }
      catch (IllegalArgumentException e) {
        this.view.display("The Character " + name + " doesn't exist in this Manager.\n");
      }
      catch (IllegalStateException e) {
        this.view.display("The Manager doesn't have any Characters!\n");
        this.view.display("Add Characters using the create-char command.\n");
      }
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }
}
