package controller.command;

import model.Manager;
import view.IOManagerTextView;
import view.TextView;

/**
 * An abstract command object that contains a {@code IOManager} and a {@code IOManagerTextView}.
 */
public abstract class ACommand implements Command {

  protected final Manager model;
  protected final TextView view;

  /**
   * Constructs a new {@code ACommand}.
   *
   * @param model the model to use
   * @param view  the view to use to render messages
   */
  public ACommand(Manager model, TextView view) {
    this.model = model;
    this.view = view;
  }

  @Override
  public abstract void run();
}
