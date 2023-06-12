package controller;

import controller.command.ACommand;
import controller.command.character.CreateChar;
import controller.command.character.EditChar;
import controller.command.character.RemoveChar;
import controller.command.dice.Dice;
import controller.command.dice.Roll;
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
import controller.command.misc.Clear;
import controller.command.misc.Start;
import controller.command.party.CreateParty;
import controller.command.party.RemoveParty;
import controller.command.stats.ChangeHp;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import model.Manager;
import view.TextView;

/**
 * An implementation {@code Controller} for the {@code IOManager}, using the
 * {@code IOManagerTextView}, to render messages to a given {@code Appendable}.
 */
public class IOManagerController implements Controller {

  private boolean running;
  private boolean tryingToQuit;
  private final Manager model;
  private final TextView view;
  private final Scanner sc;
  private HashMap<String, ACommand> commands;
  private HashMap<String, ACommand> campaignCommands;

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

    this.model = model;
    this.view = view;
    this.sc = new Scanner(input);
    this.tryingToQuit = false;
    this.initCommands();
    this.initCampaignCommands();

  }

  @Override
  public void initCommands() {
    this.commands = new HashMap<String, ACommand>();

    this.commands.put("start", new Start(this.model, this.view, this.sc));

    this.commands.put("clear", new Clear(this.model, this.view, 100));

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
    this.commands.put("remove-char", new RemoveChar(this.model, this.view, this.sc));
    this.commands.put("edit-char", new EditChar(this.model, this.view, this.sc));

    //Party
    this.commands.put("create-party", new CreateParty(this.model, this.view, this.sc));
    this.commands.put("remove-party", new RemoveParty(this.model, this.view, this.sc));

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
//    this.commands.put("set-seed", new SetSeed(this.model, this.view, this.sc, this));
//    this.commands.put("use-seed", new UseSeed(this.model, this.view, this.sc));

    this.commands.put("roll", new Roll(this.model, this.view, this.sc));

    this.commands.put("cf", new Dice(this.model, this.view, 2));

    this.commands.put("d4", new Dice(this.model, this.view, 4));
    this.commands.put("d6", new Dice(this.model, this.view, 6));
    this.commands.put("d8", new Dice(this.model, this.view, 8));
    this.commands.put("d10", new Dice(this.model, this.view, 10));
    this.commands.put("d12", new Dice(this.model, this.view, 12));
    this.commands.put("d20", new Dice(this.model, this.view, 20));
    this.commands.put("d100", new Dice(this.model, this.view, 100));
  }

  private void initCampaignCommands() {
    this.campaignCommands = new HashMap<String, ACommand>();

    this.campaignCommands.put("clear", new Clear(this.model, this.view, 100));

    //Help
    this.campaignCommands.put("help", new Help(this.model, this.view));

    //Stat
    this.campaignCommands.put("heal", new ChangeHp(this.model, this.view, this.sc, true, false));
    this.campaignCommands.put("damage", new ChangeHp(this.model, this.view, this.sc, false, false));

    this.campaignCommands.put("heal-all", new ChangeHp(this.model, this.view, this.sc, true, true));
    this.campaignCommands.put("damage-all", new ChangeHp(this.model, this.view, this.sc, false, true));

    //Manager
    this.campaignCommands.put("party", new PartyCommand(this.model, this.view, this.sc));

    this.campaignCommands.put("show-char", new ShowChar(this.model, this.view, this.sc));
    this.campaignCommands.put("show-party", new ShowParty(this.model, this.view, this.sc));

    //Dice
    this.campaignCommands.put("roll", new Roll(this.model, this.view, this.sc));

    this.campaignCommands.put("cf", new Dice(this.model, this.view, 2));

    this.campaignCommands.put("d4", new Dice(this.model, this.view, 4));
    this.campaignCommands.put("d6", new Dice(this.model, this.view, 6));
    this.campaignCommands.put("d8", new Dice(this.model, this.view, 8));
    this.campaignCommands.put("d10", new Dice(this.model, this.view, 10));
    this.campaignCommands.put("d12", new Dice(this.model, this.view, 12));
    this.campaignCommands.put("d20", new Dice(this.model, this.view, 20));
    this.campaignCommands.put("d100", new Dice(this.model, this.view, 100));
  }

  @Override
  public void start() throws IllegalStateException {

    this.running = true;
    this.startMessage();

    // begin command input loop
    while (this.running) {
      try {
        // Make sure there is input to read
//        if (!this.sc.hasNext()) {
//          throw new IllegalStateException("No input detected.");
//        }
        this.isTryingToQuit();

        if (this.tryingToQuit) break;

        this.view.display(Controller.separator);
        this.view.display("Awaiting command: ");

        String currCommand = this.sc.next();

        if (this.model.hasStartedACampaign()) {
          this.campaignController(currCommand);
        }
        else {
          this.regularController(currCommand);
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
      this.view.display("Welcome to the Infinite Odysseys Party Manager.\n\n");
      this.view.displayManagerRules();
    }
    catch (IOException io) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }

  private void quitMessage() {
    try {
      this.view.display("WARNING: Quitting will delete any unsaved data.\n");
      this.view.display("Confirm (y or n): ");
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
    this.tryingToQuit = true;
  }

  private void quitCampaignMessage() {
    try {
      this.view.display("Are you sure you want to end the current campaign?\n");
      this.view.display("Confirm (y or n): ");
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
    this.tryingToQuit = true;
  }

  private void isTryingToQuit() {
    if (this.model.hasStartedACampaign()) {
      this.isTryingToQuitCampaign();
    }
    else {
      this.isTryingToQuitProgram();
    }
  }

  private void isTryingToQuitProgram() {
    try {
      while (this.tryingToQuit) {
        String answer = this.sc.next();
        if (answer.equalsIgnoreCase("y")) {
          this.running = false;
          this.view.display("\nThank you for using the Infinite Odysseys Party Manager.");
          break;
        }
        else if (answer.equalsIgnoreCase("n")) {
          tryingToQuit = false;
          break;
        }
        else {
          this.view.display("\nInvalid input.\n");
          this.quitMessage();
        }
      }
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }

  private void isTryingToQuitCampaign() {
    try {
      while (this.tryingToQuit) {
        String answer = this.sc.next();
        if (answer.equalsIgnoreCase("y")) {
          this.running = false;
          this.view.display("\nThe campaign with " + this.model.getActiveParty().getName()
              + " has ended.\n");
          this.model.startCampaign(false);
          tryingToQuit = false;
          break;
        }
        else if (answer.equalsIgnoreCase("n")) {
          tryingToQuit = false;
          break;
        }
        else {
          this.view.display("\nInvalid input.\n");
          this.quitCampaignMessage();
        }
      }
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }

  private void regularController(String currCommand) {
    try {
      if (currCommand.equalsIgnoreCase("quit")) {
        this.view.display(Controller.separator);
        this.quitMessage();
      }

      else if (this.commands.containsKey(currCommand)) {
        this.commands.get(currCommand).run();
      }

      else {
        this.view.display("\nInvalid command.\n");
      }
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }

  private void campaignController(String currCommand) {
    try {
      if (currCommand.equalsIgnoreCase("quit")) {
        this.quitCampaignMessage();
      }

      else if (this.campaignCommands.containsKey(currCommand)) {
        this.campaignCommands.get(currCommand).run();
      }

      else if (this.commands.containsKey(currCommand)) {
        this.view.display("\nInvalid state: This command can't be used during a campaign.\n");
        this.view.display("Use the quit command to end the current campaign.\n");
      }

      else {
        this.view.display("\nInvalid command.\n");
      }
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }
}
