package controller.command.character.subcommands;

import controller.command.ACommand;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import model.Character;
import model.Manager;
import model.infiniteodysseys.IOCharacter;
import model.infiniteodysseys.IORoles;
import view.TextView;

public class EditAttribute extends ACommand {

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
  }

  @Override
  public void run() {
    try {
      String attribute = this.sc.nextLine().trim();
      String[] validAttributes = this.validAttributes();

      if (Arrays.stream(validAttributes).noneMatch((s) -> s.equalsIgnoreCase(attribute))) {
        this.view.display("Invalid input: The attribute " + attribute + " does not exist for "
            + this.character.getName() + "\n"
            + "or cannot be edited.\n");
        return;
      }

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
      this.view.display("\n");
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }

  private String[] validAttributes() {
    String[] attributes = new String[] {"name", "player name", "class", "class spec",
        "class specification"};

    String[] statNames = new String[this.model.getStats().length - 2];
    System.arraycopy(this.model.getStats(), 2, statNames, 0, statNames.length);

    String[] validAttributes = new String[attributes.length + statNames.length];
    System.arraycopy(attributes, 0, validAttributes, 0, attributes.length);
    System.arraycopy(statNames, 0, validAttributes, attributes.length, statNames.length);
    return validAttributes;
  }

  private String getName() {
    try {
      this.view.display("Current Character name: " + this.newCharacter.getName() + "\n");
      this.view.display("New Character name: ");

      String name = this.sc.nextLine();

      if (name.isEmpty() || name.isBlank()) {
        return this.character.getName();
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
}
