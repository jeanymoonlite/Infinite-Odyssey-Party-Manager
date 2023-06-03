package controller.command;

import model.Manager;
import view.IOManagerTextView;

public final class Help extends ACommand {

  /**
   * Constructs a new {@code Help}.
   *
   * @param model the model to use
   * @param view  the view to use to render messages
   */
  public Help(Manager model, IOManagerTextView view) {
    super(model, view);
  }

  @Override
  public void run() {

  }
}
