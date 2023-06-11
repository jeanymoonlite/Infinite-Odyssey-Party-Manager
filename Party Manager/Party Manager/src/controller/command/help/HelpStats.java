package controller.command.help;

import controller.command.ACommand;
import java.io.IOException;
import model.Manager;
import view.TextView;

/**
 * A command object that displays all the commands relating to stats.
 */
public final class HelpStats extends ACommand {

  /**
   * Constructs a new {@code HelpChar}.
   *
   * @param model the model to use
   * @param view  the view to use to render messages
   */
  public HelpStats(Manager model, TextView view) {
    super(model, view);
    this.signature = "help-stats";
    this.description = "Displays every command for healing and damaging characters.";
  }

  @Override
  public void run() {
    try {
      this.view.display("heal (amount name)\n");
      this.view.display("\tAdds the amount to the character's hp with the given name.\n");
      this.view.display("\tThis command rejects any non-integers/negative numbers.\n");
      this.view.display("\n");

      this.view.display("damage (amount name)\n");
      this.view.display("\tSubtracts the amount to the character's hp with the given name.\n");
      this.view.display("\tThis command rejects any non-integers/negative numbers.\n");
      this.view.display("\n");

      this.view.display("heal-all (amount)\n");
      this.view.display("\tAdds the amount to every character in the active party.\n");
      this.view.display("\n");

      this.view.display("damage-all (amount)\n");
      this.view.display("\tSubtracts the amount to every character in the active party\n");
      this.view.display("\n");
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }
}
