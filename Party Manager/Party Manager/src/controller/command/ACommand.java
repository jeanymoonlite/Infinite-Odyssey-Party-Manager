package controller.command;

import model.Manager;
import view.IOManagerTextView;

/**
 * An abstract command object that contains a {@code IOManager} and a {@code IOManagerTextView}.
 */
public abstract class ACommand implements Command {

  protected final Manager model;
  protected final IOManagerTextView view;

  /**
   * Constructs a new {@code ACommand}.
   *
   * @param model the model to use
   * @param view  the view to use to render messages
   */
  public ACommand(Manager model, IOManagerTextView view) {
    this.model = model;
    this.view = view;
  }

  @Override
  public abstract void run();
}
