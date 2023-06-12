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

/**
 * A command object that creates a {@code Character} and adds it to the given model.
 */
public final class CreateChar extends ACommand {

  private final Scanner sc;

  /**
   * Constructs a new {@code CreateChar}.
   *
   * @param model the model to use
   * @param view  the view to use to render messages
   * @param sc    the scanner to read input from
   */
  public CreateChar(Manager model, TextView view, Scanner sc) {
    super(model, view);
    this.sc = sc;
    this.initSignature();
    this.description = "Creates a new Character with the given information.";
  }

  private void initSignature() {
    String[] stats = new String[this.model.getStats().length - 2];

    System.arraycopy(this.model.getStats(), 2, stats, 0, stats.length);

    for (int i = 0; i < stats.length; i++) {
      stats[i] = stats[i].toLowerCase();
    }

    this.signature = "create-char (name playerName class classSpecification\n";
    String s = "             ";

    for (int i = 0; i < stats.length; i++) {
      s = s.concat(stats[i]);

      if ((i != stats.length - 1) && ((i + 1) % 3 != 0)) {
        s = s.concat(" ");
      }

      if ((i > 0) && (i != stats.length - 1) && ((i + 1) % 3 == 0)) {
        s = s.concat("\n             ");
      }
    }

    this.signature = this.signature.concat(s + ")");
  }

  @Override
  public void run() {
    try {
      this.sc.nextLine();
      String name = this.getName();

      String playerName = this.getPlayerName();

      IORoles role = this.getRole();

      String roleSpecification = this.getRoleSpec();

      int[] stats = this.getStats();

      Character c = new IOCharacter(name, playerName, role, roleSpecification,
          stats[0], stats[1], stats[2], stats[3], stats[4], stats[5]);
      this.model.addCharacter(c);

      this.view.display("\n");

      while (true) {
        this.view.display("Create the following Character?\n");
        this.view.displayCharacter(name);
        this.view.display("Confirm (y or n): ");

        String answer = this.sc.next();

        if (answer.equalsIgnoreCase("y")) {
          this.view.display("\n");
          this.view.display(name + " (" + playerName + ") was added to the Manager.\n");
          break;
        }

        else if (answer.equalsIgnoreCase("n")) {
          this.view.display("\n");
          this.view.display(name + " (" + playerName + ") will not be created.\n");
          this.model.removeCharacter(name);
          break;
        }

        else {
          this.view.display("\nInvalid input.\n");
        }
      }
    }

    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }

  private String getName() {
    try {
      this.view.display("Character name: ");

      String name = this.sc.nextLine();

      if (name.isEmpty() || name.isBlank()) {
        this.view.display("\nInvalid input: Character name cannot be whitespace. ");
        this.view.display("Please try again.\n");
        return this.getName();
      }

      if (this.model.doesCharacterExist(name)) {
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
      this.view.display("Player name: ");
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
      this.view.display("Class: ");
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
      this.view.display("Class Specification (Type enter to leave blank): ");
      String roleSpec = this.sc.nextLine();

      if (roleSpec.equalsIgnoreCase("n/a")) {
        return "";
      }

      return roleSpec;
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }

  private int[] getStats() {
    try {
      int[] stats = new int[this.model.getStats().length - 2];

      for (int i = 0; i < stats.length; i++) {
        boolean isValid = false;

        while (!isValid) {
          this.view.display(this.model.getStats()[2 + i] + ": ");
          try {
            int input = this.sc.nextInt();

            if (input < 0) {
              this.view.display("\nInvalid input: A stat's value cannot be negative.\n");
            }
            else {
              stats[i] = input;
              isValid = true;
            }
          }

          catch (InputMismatchException e) {
            this.view.display("\nInvalid input: A stat's value cannot be a decimal.\n");
            this.sc.next(); // Clear the input buffer
          }
        }
      }

      boolean lessThan30 = Arrays.stream(stats).sum() <= 30;
      boolean noNegative = Arrays.stream(stats).anyMatch(n -> n >= 0);

      if (lessThan30 && noNegative) {
        return stats;
      }
      else {
        this.view.display("\nInvalid input: The sum of all the stats exceeds 30.\n");
        this.view.display("Please try again.\n");
        return this.getStats();
      }
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }
}
