package controller.command.help;

import controller.command.ACommand;
import model.Manager;
import view.IOManagerTextView;
import view.TextView;

/**
 * A command object that displays all the commands relating to the manager.
 */
public final class HelpManager extends ACommand {

  /**
   * Constructs a new {@code Help}.
   *
   * @param model the model to use
   * @param view  the view to use to render messages
   */
  public HelpManager(Manager model, TextView view) {
    super(model, view);
  }

  @Override
  public void run() {

  }
}
