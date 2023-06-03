package controller;

import controller.command.ACommand;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import model.Manager;
import view.TextView;

/**
 * A implementation {@code Controller} for the {@code IOManager}, using the {@code IOManagerTextView}, to render
 * messages to a given {@code Appendable}
 */
public class IOManagerController implements Controller {

  private boolean running;
  private final Manager model;
  private final TextView view;
  private final Scanner sc;
  private HashMap<String, ACommand> commands;

  /**
   * Constructs a new {@code IOManager}.
   *
   * @param model the model to control
   * @param view  the view of the model
   * @param input the Readable from which to read input from
   * @throws IllegalArgumentException if any of the given arguments are null
   */
  public IOManagerController(Manager model, TextView view, Readable input)
      throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("The Model for the Controller cannot be null.");
    }

    if (view == null) {
      throw new IllegalArgumentException("The View for the Controller cannot be null.");
    }

    if (input == null) {
      throw new IllegalArgumentException("The Readable for the Controller cannot be null.");
    }

    this.model = model;
    this.view = view;
    this.sc = new Scanner(input);
    this.initCommands();

  }

  private void initCommands() {
    this.commands = new HashMap<String, ACommand>();

//    this.commands.put("help", new Help(this.model, this.view));

  }

  @Override
  public void start() throws IllegalStateException {

    this.running = true;
    boolean tryingToQuit = false;

    try {
      this.view.display("Welcome to the Infinite Odyssey's Party Manager.\n\n");
      this.view.displayManagerRules();
    } catch (IOException io) {
      //pass
    }

    // begin command input loop
    while (this.running) {

      if (!tryingToQuit) {
        try {
          this.view.display("Awaiting command:\n");
        } catch (IOException io) {
          //pass
        }
      }

      // Make sure there is input to read
      if (!this.sc.hasNext()) {
        throw new IllegalStateException("No input detected.");
      }

      String currCommand = this.sc.next();

      if (tryingToQuit) {
        if (currCommand.equalsIgnoreCase("y")) {
          this.running = false;
          try {
            this.view.display("Bye Bye!");
          } catch (IOException e) {
            //ignore
          }
          break;
        } else if (currCommand.equalsIgnoreCase("n")) {
          tryingToQuit = false;
          continue;
        }
      }

      //quitting
      if (currCommand.equalsIgnoreCase("quit")
          || (currCommand.equalsIgnoreCase("q"))) {

        try {
          this.view.display("WARNING: Quitting will delete any unsaved progress. "
              + "Confirm? (y/n)\n");
        } catch (IOException e) {
          //ignore
        }
        tryingToQuit = true;
        continue;
      }

      if (this.commands.containsKey(currCommand)) {
        this.commands.get(currCommand).run();
      } else {
        try {
          this.view.display("Invalid Command\n");
        } catch (IOException io) {
          //pass
        }
      }
    }
  }

}
