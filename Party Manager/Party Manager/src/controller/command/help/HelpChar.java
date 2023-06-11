package controller.command.help;

import controller.command.ACommand;
import controller.command.character.CreateChar;
import controller.command.character.EditChar;
import controller.command.character.RemoveChar;
import java.io.IOException;
import model.Manager;
import view.TextView;

/**
 * A command object that displays all the commands relating to characters.
 */
public final class HelpChar extends ACommand {

  /**
   * Constructs a new {@code HelpChar}.
   *
   * @param model the model to use
   * @param view  the view to use to render messages
   */
  public HelpChar(Manager model, TextView view) {
    super(model, view);
    this.signature = "help-char";
    this.description = "Displays every character-related command.";
  }

  @Override
  public void run() {
    try {
      this.view.display(new CreateChar(this.model, null, null).getSignature() + ": ");
      this.view.display("\n\t");
      this.view.display(new CreateChar(this.model, null, null)
          .getDescription().replace("\n", "\n\t"));
      this.view.display("\n");
      this.view.display("\n");

      this.view.display(new EditChar(null, null, null).getSignature() + ": ");
      this.view.display("\n\t");
      this.view.display(new EditChar(null, null, null)
          .getDescription().replace("\n", "\n\t"));
      this.view.display("\n");
      this.view.display("\n");

      this.view.display(new RemoveChar(null, null, null).getSignature() + ": ");
      this.view.display("\n\t");
      this.view.display(new RemoveChar(null, null, null)
          .getDescription().replace("\n", "\n\t"));
      this.view.display("\n");
      this.view.display("\n");
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }
}
