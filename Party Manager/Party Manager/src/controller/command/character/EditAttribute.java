package controller.command.character;

import controller.command.ACommand;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import model.Character;
import model.Manager;
import model.infiniteodysseys.IOCharacter;
import model.infiniteodysseys.IORoles;
import view.TextView;

final class EditAttribute extends ACommand {

  private final Scanner sc;
  private Character character;
  private Character newCharacter;

  /**
   * Constructs a new {@code PartyCommand}.
   *
   * @param model the model to use
   * @param view  the view to use to render messages
   * @param sc    the scanner to read input from
   */
  public EditAttribute(Manager model, TextView view, Scanner sc,
      Character character, Character newCharacter) {
    super(model, view);
    this.sc = sc;
    this.character = character;
    this.newCharacter = newCharacter;

    this.signature = "edit (attribute)";
    this.description = "Change the value of one of the following attributes:\n"
        + "\ti. Name\n"
        + "\tii. Player Name\n"
        + "\tiii. Class\n"
        + "\tiv. Class Spec/Class Specification\n"
        + "\tv. Strength\n"
        + "\tvi. Intelligence\n"
        + "\tvii. Creativity\n"
        + "\tviii. Charisma\n"
        + "\tix. Stealth\n"
        + "\tx. Intimidation";
  }

  @Override
  public void run() {
    try {
      String attribute = this.sc.nextLine().trim();

      String name = this.newCharacter.getName();
      String playerName = this.newCharacter.getPlayerName();
      IORoles role = IORoles.valueOf(this.newCharacter.getRole().toUpperCase());
      String roleSpec = this.newCharacter.getSpecification();

      int[] statVals = new int[this.model.getStats().length - 2];

      for (int i = 0; i < statVals.length; i++) {
        statVals[i] = this.newCharacter.getValueOf(this.model.getStats()[i + 2].toLowerCase());
      }

      switch (attribute.toLowerCase()) {
        case "name":
          name = this.getName();
          break;
        case "player name":
          playerName = this.getPlayerName();
          break;
        case "class":
          role = this.getRole();
          break;
        case "class specification":
        case "class spec":
          roleSpec = this.getRoleSpec();
          break;
        default:
          boolean validStat = false;
          for (int i = 2; i < this.model.getStats().length; i++) {
            if (attribute.equalsIgnoreCase(this.model.getStats()[i])) {
              validStat = true;
              break;
            }
          }

          if (validStat) break;

          this.view.display("Invalid input: The attribute " + attribute + " does not exist for "
              + this.character.getName() + "\n"
              + "or cannot be edited.\n");
          return;
      }

      for (int i = 0; i < statVals.length; i++) {
        if (attribute.equalsIgnoreCase(this.model.getStats()[i + 2].toLowerCase())) {
          statVals[i] = this.getStat(i, statVals);
        }
      }

      this.newCharacter = new IOCharacter(name, playerName, role, roleSpec,
          statVals[0], statVals[1],
          statVals[2], statVals[3],
          statVals[4], statVals[5]);
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }

  public Character getUpdatedCharacter() {
    return this.newCharacter;
  }

  private String getName() {
    try {
      this.view.display("Current Character name: " + this.newCharacter.getName() + "\n");
      this.view.display("New Character name: ");

      String name = this.sc.nextLine();

      if (name.isEmpty() || name.isBlank()) {
        return this.newCharacter.getName();
      }

      if ((!this.character.getName().equalsIgnoreCase(name))
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
        return this.newCharacter.getPlayerName();
      }

      return name;
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }

  private IORoles getRole() {
    try {
      this.view.display("Current Class: " + this.newCharacter.getRole() + "\n");
      this.view.display("New Class: ");

      IORoles validRole;
      String role = this.sc.nextLine();

      if (role.isEmpty() || role.isBlank()) {
        return IORoles.valueOf(this.newCharacter.getRole().toUpperCase());
      }

      try {
        validRole = IORoles.valueOf(role.toUpperCase());
      }
      catch (IllegalArgumentException e) {
        validRole = null;
      }

      if (validRole == null) {
        this.view.display("\nInvalid input: " + role + " is not a valid Class.\n");
        this.view.display("Please try again.\n");
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
          "Current Class Specification: " + this.newCharacter.getSpecification() + "\n");
      this.view.display("New Class Specification: ");
      String roleSpec = this.sc.nextLine();

      if (roleSpec.isBlank() || roleSpec.isEmpty()) {
        return this.newCharacter.getSpecification();
      }

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
          this.view.display("\nInvalid input: A stat's value must be a non-negative integer.\n");
          this.sc.next();
          return this.getStat(i, stats);
        }
      }

      //obtains the new stat total
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