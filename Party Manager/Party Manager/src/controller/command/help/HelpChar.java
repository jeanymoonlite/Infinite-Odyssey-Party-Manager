package controller.command.help;

import controller.command.ACommand;
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
  }

  @Override
  public void run() {
    String[] stats = new String[this.model.getStats().length];

    for (int i = 2; i < stats.length; i++) {
      stats[i] = this.model.getStats()[i].toLowerCase();
    }

    try {
      this.view.display("create-char (name playerName role roleSpecification ");

      for (int i = 2; i < stats.length; i++) {
        this.view.display(stats[i]);

        if (i != stats.length - 1) this.view.display(" ");
      }

      this.view.display("\n");
      this.view.display("\tCreates a new Character with the given information.\n");
      this.view.display("\n");

      this.view.display("edit-char (name)\n");
      this.view.display("\tEdits a Character with the given name.\n"
          + "\tThis will put the program into Character Editing mode.\n"
          + "\tA new set of commands will become available in editing mode.\n");
      this.view.display("\n");

      this.view.display("remove-char (name)\n");
      this.view.display("\tRemoves a Character with the given name\n");
      this.view.display("\n");
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }
}
