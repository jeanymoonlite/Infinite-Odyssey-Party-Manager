package controller.command.character;

import controller.command.ACommand;
import java.io.IOException;
import java.util.Scanner;
import model.Manager;
import model.infiniteodysseys.IORoles;
import view.TextView;

/**
 * A command object that creates a {@code Character} and adds it to the given
 * model.
 */
public class CreateChar extends ACommand {

  private final Scanner sc;

  /**
   * Constructs a new {@code CreateChar}.
   *
   * @param model the model to use
   * @param view  the view to use to render messages
   */
  public CreateChar(Manager model, TextView view, Scanner sc) {
    super(model, view);
    this.sc = sc;
  }

  @Override
  public void run() {
    try {
      String name = this.getName();

      String playerName = this.getPlayerName();

      IORoles role = this.getRole();

      String roleSpecification = this.getRoleSpec();



      this.view.display("");

      int[] stats = new int[6];

      for (int i = 0; i < stats.length; i++) {
        stats[i] = this.sc.nextInt();
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
        this.view.display("\nInvalid input: Character name cannot be whitespace.\n");
        this.view.display("Please try again.\n");
        this.getName();
      }

      if (this.model.doesPartyExist(name)) {
        this.view.display("Invalid input: There is already a Character named " + name + ".\n");
        this.view.display("Please input a different name.\n");
        this.getName();
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
        this.view.display("Invalid input: Player name cannot be whitespace.\n");
        this.view.display("Please try again.\n");
        this.getPlayerName();
      }

      return name;
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }

  private IORoles getRole() {
    try {
      this.view.display("Role: ");
      IORoles validRole = null;
      String role = this.sc.next();


      for (IORoles r : IORoles.values()) {
        if (r.toString().equalsIgnoreCase(role)) {
          validRole = r;
          break;
        }
      }

      if (validRole == null) {
        this.view.display(role + " is not a valid role.\n"
            + "Please try again.\n");
        this.getRole();
      }

      return validRole;
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }

  private String getRoleSpec() {
    try {
      this.view.display("Role Specification: ");
      return this.sc.nextLine();
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }
}
