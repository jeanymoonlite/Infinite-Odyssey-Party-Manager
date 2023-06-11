package controller.command.character;

import controller.command.ACommand;
import controller.input.validation.CharacterValid;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import model.Character;
import model.Manager;
import model.infiniteodysseys.IOCharacter;
import model.infiniteodysseys.IORoles;
import view.TextView;

public class EditChar extends ACommand {

  private final Scanner sc;
  private boolean tryingToQuit;
  private boolean saving;
  private Character character;
  private Character newCharacter;
  private final String[] commands;

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
    this.commands = new String[]{"edit", "back", "quit", "save"};

    this.signature = "edit-char (name)";
    this.description = "Edits the Character with the given name.\n"
        + "This will put the program into Character Editing mode.\n"
        + "A new set of commands will become available in editing mode.";
  }

  @Override
  public void run() {
    try {
      boolean running = true;
      this.tryingToQuit = false;
      this.saving = false;

      String name = this.sc.nextLine().trim();

      if (!new CharacterValid(this.model, this.view, this.sc).isValid(name)) {
        return;
      }

      this.startMessage(name);

      while (running) {
        if (!this.tryingToQuit) {
          this.view.display(this.newCharacter.toStringAll());
          this.view.display("Awaiting edit command:\n");
        }

        // Make sure there is input to read
        if (!this.sc.hasNext()) {
          throw new IllegalStateException("No input detected.");
        }

        String currCommand = this.sc.next();

        if (this.tryingToQuit) {
          if (currCommand.equalsIgnoreCase("y")) {
            running = false;

            if (this.saving) {
              this.view.display(name + " has been updated!\n");
              this.model.removeCharacter(name);
              this.model.addCharacter(this.newCharacter);
              this.view.displayCharacter(this.newCharacter.getName());
            }
            else {
              this.view.display("All changes made to " + name + " have been undone.\n");
            }

            this.view.display("Now exiting Character editing mode.\n");
            break;
          }
          else if (currCommand.equalsIgnoreCase("n")) {
            this.tryingToQuit = false;
            this.saving = false;
            continue;
          }
          else {
            this.view.display("Invalid command\n");
            this.quitMessage();
            continue;
          }
        }

        switch (currCommand) {
          case "back":
          case "quit":
            this.quitMessage();
            break;
          case "save":
            this.saveMessage();
            break;
          case "edit":
            this.edit();
            break;
          default:
            this.view.display("Invalid command\n");
            break;
        }
      }
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }

  private void startMessage(String name) {
    try {
      this.view.display("You are now in character editing mode.\n");
      this.character = this.model.findCharByName(name);
      this.newCharacter = this.model.findCharByName(name);
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

  private void edit() {
    try {
      String attribute = this.sc.nextLine().trim();

      String[] attributes = new String[]{"Name", "Player Name", "Role", "Role Spec",
          "Role Specification"};

      String[] statNames = new String[this.model.getStats().length - 2];
      System.arraycopy(this.model.getStats(), 2, statNames, 0, statNames.length);

      String[] validAttribute = new String[attributes.length + statNames.length];
      System.arraycopy(attributes, 0, validAttribute, 0, attributes.length);
      System.arraycopy(statNames, 0, validAttribute, attributes.length, statNames.length);

      if (Arrays.stream(validAttribute).noneMatch((s) -> s.equalsIgnoreCase(attribute))) {
        this.view.display("Invalid input: The attribute " + attribute + " does not exist for "
            + this.character.getName() + "\n"
            + "or cannot be edited.\n");
        return;
      }

      String name = this.newCharacter.getName();
      String playerName = this.newCharacter.getPlayerName();
      IORoles role = IORoles.valueOf(this.newCharacter.getRole().toUpperCase());
      String roleSpec = this.newCharacter.getSpecification();

      int[] statVals = new int[statNames.length];

      for (int i = 0; i < statVals.length; i++) {
        statVals[i] = this.newCharacter.getValueOf(statNames[i]);
      }

      switch (attribute.toLowerCase()) {
        case "name":
          name = this.getName();
          break;
        case "player name":
          playerName = this.getPlayerName();
          break;
        case "role":
          role = this.getRole();
          break;
        case "role specification":
        case "role spec":
          roleSpec = this.getRoleSpec();
          break;
      }

      for (int i = 0; i < statNames.length; i++) {
        if (attribute.equalsIgnoreCase(statNames[i])) {
          statVals[i] = this.getStat(i, statVals);
        }
      }

      this.newCharacter = new IOCharacter(name, playerName, role, roleSpec,
          statVals[0], statVals[1],
          statVals[2], statVals[3],
          statVals[4], statVals[5]);
      this.view.display("\n");
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }

  private String getName() {
    try {
      this.view.display("Current Character name: " + this.newCharacter.getName() + "\n");
      this.view.display("New Character name: ");

      String name = this.sc.nextLine();

      if (name.isEmpty() || name.isBlank()) {
        this.view.display("\nInvalid input: Character name cannot be whitespace. ");
        this.view.display("Please try again.\n");
        return this.getName();
      }

      if ((!this.character.getName().equalsIgnoreCase(this.newCharacter.getName()))
          && (this.model.doesCharacterExist(name))) {
        this.view.display("\nInvalid input: There is already a Character named " + name + ".\n");
        this.view.display("Please input a different name.\n");
        return this.getName();
      }

      return name;
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
