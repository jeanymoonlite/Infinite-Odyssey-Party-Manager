package controller.command.character;

import controller.command.ACommand;
import controller.command.character.subcommands.EditAttribute;
import controller.input.validation.CharacterValid;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;
import model.Character;
import model.Manager;
import model.infiniteodysseys.IOCharacter;
import model.infiniteodysseys.IORoles;
import view.TextView;

public class EditChar extends ACommand {

  private final Scanner sc;
  private boolean running;
  private boolean tryingToQuit;
  private boolean saving;
  private Character character;
  private Character newCharacter;
  private HashMap<String, ACommand> commands;

  /**
   * Constructs a new {@code PartyCommand}.
   *
   * @param model the model to use
   * @param view  the view to use to render messages
   * @param sc    the scanner to read input from
   */
  public EditChar(Manager model, TextView view, Scanner sc) {
    super(model, view);
    this.sc = sc;
    this.tryingToQuit = false;

    this.signature = "edit-char (name)";
    this.description = "Edits the Character with the given name.\n"
        + "This will put the program into Character Editing mode.\n"
        + "A new set of commands will become available in editing mode.";
  }

  private void init() {
    this.commands = new HashMap<String, ACommand>();

    this.commands.put("edit", new EditAttribute(this.model, this.view, this.sc,
        this.character, this.newCharacter));

//    this.commands.put("quit", new EditAttribute(this.model, this.view, this.sc));
//    this.commands.put("back", new EditAttribute(this.model, this.view, this.sc));

    this.tryingToQuit = false;
    this.saving = false;
  }

  @Override
  public void run() {
    String name = this.sc.nextLine().trim();

    if (!new CharacterValid(this.model, this.view, this.sc).isValid(name)) {
      return;
    }

    this.character = this.model.findCharByName(name);
    this.newCharacter = this.model.findCharByName(name);
    this.init();
    this.startMessage();

    while (this.running) {
      try {
        this.isTryingToQuit();

        if (this.tryingToQuit) break;

        this.view.display(this.newCharacter.toStringAll());
        this.view.display("Awaiting edit command:\n");

        String currCommand = this.sc.next();

        if (currCommand.equalsIgnoreCase("quit")) {
          this.quitMessage();
        }

        else if (currCommand.equalsIgnoreCase("save")) {
          this.saveMessage();
        }

        else if (this.commands.containsKey(currCommand.toLowerCase())) {
          this.commands.get(currCommand).run();
        }
        else {
          this.view.display("\nInvalid edit command.\n");
        }
      }
      catch (IOException e) {
        throw new RuntimeException("Fatal Error: IOException occurred.");
      }
    }
  }

  private void startMessage() {
    try {
      this.view.display("You are now in character editing mode.\n");
      this.view.display("Editing: " + this.character.getName() +
          " (" + this.character.getPlayerName() + ")" + "\n");
    }
    catch (IOException io) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }

  private void quitMessage() {
    try {
      this.view.display("WARNING: Quitting will remove the following changes:\n");
      this.view.display(this.newCharacter.toStringAll());
      this.view.display("Are you sure you want to exit Character editing mode?");
      this.view.display(" Confirm (y or n): \n");
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
    this.tryingToQuit = true;
    this.saving = false;
  }

  private void saveMessage() {
    try {
      this.view.display("Save the following changes?\n");
      this.view.display(this.newCharacter.toStringAll());
      this.view.display("Confirm (y or n): \n");
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
    this.tryingToQuit = true;
    this.saving = true;
  }

  private void isTryingToQuit() {
    if (this.saving) {
      this.isTryingToSave();
    }
    else {
      this.isTryingToQuitUnsaved();
    }
  }

  private void isTryingToQuitUnsaved() {
    try {
      while (this.tryingToQuit) {
        String answer = this.sc.next();
        if (answer.equalsIgnoreCase("y")) {
          this.running = false;
          this.view.display("All changes made to " + this.character.getName() + " have been undone.\n");
          this.view.display("Now exiting Character editing mode.\n");
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

  private void isTryingToSave() {
    try {
      while (this.tryingToQuit) {
        String answer = this.sc.next();
        if (answer.equalsIgnoreCase("y")) {
          this.running = false;
          this.view.display(this.character.getName() + " has been updated!\n");
          this.model.removeCharacter(this.character.getName());
          this.model.addCharacter(this.newCharacter);
          this.view.displayCharacter(this.newCharacter.getName());
          this.view.display("Now exiting Character editing mode.\n");
          break;
        }
        else if (answer.equalsIgnoreCase("n")) {
          this.tryingToQuit = false;
          this.saving = false;
          break;
        }
        else {
          this.view.display("\nInvalid input.\n");
          this.saveMessage();
        }
      }
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }

  private String getPlayerName() {
    try {
      this.view.display("Current Player name: " + this.newCharacter.getPlayerName() + "\n");
      this.view.display("New Player name: ");
      String name = this.sc.nextLine();

      if (name.isEmpty() || name.isBlank()) {
        this.view.display("\nInvalid input: Player name cannot be whitespace. Please try again.\n");
        return this.getPlayerName();
      }

      return name;
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }

  private IORoles getRole() {
    try {
      this.view.display("Current Role: " + this.newCharacter.getRole() + "\n");
      this.view.display("New Role: ");

      IORoles validRole = null;
      String role = this.sc.nextLine();

      for (IORoles r : IORoles.values()) {
        if (r.toString().equalsIgnoreCase(role)) {
          validRole = r;
          break;
        }
      }

      if (validRole == null) {
        this.view.display("\nInvalid input: " + role + " is not a valid role."
            + " Please try again.\n");
        return this.getRole();
      }

      return validRole;
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }

  private String getRoleSpec() {
    try {
      this.view.display(
          "Current Role Specification: " + this.newCharacter.getSpecification() + "\n");
      this.view.display("New Role Specification (Type enter to leave blank): ");
      String roleSpec = this.sc.nextLine();

      return roleSpec;
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }

  private int getStat(int i, int[] stats) {
    int result = 0;

    try {
      boolean isValid = false;

      while (!isValid) {
        this.view.display("Current " + this.model.getStats()[2 + i]);
        this.view.display(": " + this.newCharacter.getValueOf(this.model.getStats()[2 + i]) + "\n");
        this.view.display("New " + this.model.getStats()[2 + i] + ": ");

        try {
          int input = this.sc.nextInt();

          if (input < 0) {
            this.view.display("\nInvalid input: A stat's value cannot be negative.\n");
          }

          else {
            result = input;
            isValid = true;
          }
        }
        catch (InputMismatchException e) {
          this.view.display("\nInvalid input: A stat's value cannot be a decimal.\n");
          this.sc.next(); // Clear the input buffer
        }
      }

      int[] newStats = new int[stats.length];

      System.arraycopy(stats, 0, newStats, 0, stats.length);

      newStats[i] = result;

      boolean lessThan30 = Arrays.stream(newStats).sum() <= 30;
      boolean noNegative = Arrays.stream(newStats).anyMatch(n -> n >= 0);

      if (lessThan30 && noNegative) {
        return result;
      }
      else {
        this.view.display("\nInvalid input: The sum of all the stats exceeds 30.\n");
        this.view.display("Please try again.\n");
        return this.getStat(i, stats);
      }
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }
}
