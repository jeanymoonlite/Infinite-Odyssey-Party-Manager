package controller.command.help;

import controller.command.ACommand;
import model.Manager;
import view.IOManagerTextView;
import view.TextView;

/**
 * A command object that displays all possible commands for this program.
 */
public final class Help extends ACommand {

  /**
   * Constructs a new {@code Help}.
   *
   * @param model the model to use
   * @param view  the view to use to render messages
   */
  public Help(Manager model, TextView view) {
    super(model, view);
  }

  @Override
  public void run() {

  }
}
