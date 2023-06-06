package controller;

import controller.command.ACommand;
import controller.command.Start;
import controller.command.character.CreateChar;
import controller.command.dice.Dice;
import controller.command.dice.Roll;
import controller.command.dice.SetSeed;
import controller.command.dice.UseSeed;
import controller.command.help.Help;
import controller.command.help.HelpAll;
import controller.command.help.HelpChar;
import controller.command.help.HelpDice;
import controller.command.help.HelpManager;
import controller.command.help.HelpParty;
import controller.command.help.HelpStats;
import controller.command.manager.PartyCommand;
import controller.command.manager.ShowAllChars;
import controller.command.manager.ShowAllParties;
import controller.command.manager.ShowChar;
import controller.command.manager.ShowParty;
import controller.command.party.CreateParty;
import controller.command.stats.ChangeHp;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import model.Manager;
import view.TextView;

/**
 * An implementation {@code Controller} for the {@code IOManager}, using the {@code IOManagerTextView}, to render
 * messages to a given {@code Appendable}.
 */
public class IOManagerController implements Controller {

  private boolean running;
  private boolean tryingToQuit;
  private final Manager model;
  private final TextView view;
  private final Scanner sc;
  private HashMap<String, ACommand> commands;

  /**
   * Constructs a new {@code IOManagerController}.
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

    IOManagerSeedHolder.getInstance().reset();
    this.model = model;
    this.view = view;
    this.sc = new Scanner(input);
    this.tryingToQuit = false;
    this.initCommands();

  }

  @Override
  public void initCommands() {
    this.commands = new HashMap<String, ACommand>();

    this.commands.put("start", new Start(this.model, this.view, this.sc));

    //Help
    this.commands.put("help", new Help(this.model, this.view));
    this.commands.put("help-all", new HelpAll(this.model, this.view));

    this.commands.put("help-char", new HelpChar(this.model, this.view));
    this.commands.put("help-party", new HelpParty(this.model, this.view));

    this.commands.put("help-manager", new HelpManager(this.model, this.view));

    this.commands.put("help-dice", new HelpDice(this.model, this.view));
    this.commands.put("help-stats", new HelpStats(this.model, this.view));


    //Character
    this.commands.put("create-char", new CreateChar(this.model, this.view, this.sc));
//    this.commands.put("remove-char", new RemoveChar(this.model, this.view, this.sc));


    //Party
    this.commands.put("create-party", new CreateParty(this.model, this.view, this.sc));

    //Stat
    this.commands.put("heal", new ChangeHp(this.model, this.view, this.sc, true, false));
    this.commands.put("damage", new ChangeHp(this.model, this.view, this.sc, false, false));

    this.commands.put("heal-all", new ChangeHp(this.model, this.view, this.sc, true, true));
    this.commands.put("damage-all", new ChangeHp(this.model, this.view, this.sc, false, true));

    //Manager
    this.commands.put("party", new PartyCommand(this.model, this.view, this.sc));

    this.commands.put("show-all-chars", new ShowAllChars(this.model, this.view));
    this.commands.put("show-all-parties", new ShowAllParties(this.model, this.view));

    this.commands.put("show-char", new ShowChar(this.model, this.view, this.sc));
    this.commands.put("show-party", new ShowParty(this.model, this.view, this.sc));

    //Dice
    this.commands.put("set-seed", new SetSeed(this.model, this.view, this.sc, this));
    this.commands.put("use-seed", new UseSeed(this.model, this.view, this.sc));

    this.commands.put("roll", new Roll(this.model, this.view, this.sc));

    this.commands.put("d2", new Dice(this.model, this.view, 2));
//    this.commands.put("coin-flip", new Dice(this.model, this.view, 2));

    this.commands.put("d4", new Dice(this.model, this.view, 4));
    this.commands.put("d6", new Dice(this.model, this.view, 6));
    this.commands.put("d8", new Dice(this.model, this.view, 8));
    this.commands.put("d10", new Dice(this.model, this.view, 10));
    this.commands.put("d12", new Dice(this.model, this.view, 12));
    this.commands.put("d20", new Dice(this.model, this.view, 20));
    this.commands.put("d100", new Dice(this.model, this.view, 100));
  }

  @Override
  public void start() throws IllegalStateException {

    this.running = true;
    this.startMessage();

    // begin command input loop
    while (this.running) {
      try {
        if (!this.tryingToQuit) {
          this.view.display("Awaiting command:\n");
        }

        // Make sure there is input to read
        if (!this.sc.hasNext()) {
          throw new IllegalStateException("No input detected.");
        }

        String currCommand = this.sc.next();

        if (this.tryingToQuit) {
          if (currCommand.equalsIgnoreCase("y")) {
            this.running = false;
            this.view.display("Bye Bye!");
            break;
          }
          else if (currCommand.equalsIgnoreCase("n")) {
            tryingToQuit = false;
            continue;
          }
          else {
            this.view.display("Invalid command\n");
            this.quitMessage();
            continue;
          }
        }

        //quitting
        if (currCommand.equalsIgnoreCase("quit")
            || (currCommand.equalsIgnoreCase("q"))) {

          this.quitMessage();
          continue;
        }

        if (this.commands.containsKey(currCommand)) {
          this.commands.get(currCommand).run();
        }
        else {
          this.view.display("Invalid command\n");
        }
      }
      catch (IOException io) {
        throw new RuntimeException("Fatal Error: IOException occurred.");
      }
    }
    this.sc.close();
  }

  private void startMessage() {
    try {
      this.view.display("Welcome to the Infinite Odyssey's Party Manager.\n\n");
      this.view.displayManagerRules();
    }
    catch (IOException io) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }

  private void quitMessage() {
    try {
      this.view.display("WARNING: Quitting will delete any unsaved progress. "
          + "Confirm? (y/n)\n");
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
    this.tryingToQuit = true;
  }

}
