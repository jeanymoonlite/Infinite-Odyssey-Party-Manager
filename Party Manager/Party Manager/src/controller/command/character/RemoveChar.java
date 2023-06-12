package controller.command.character;

import controller.command.ACommand;
import controller.input.validation.CharacterValid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import model.Manager;
import view.TextView;

/**
 * A command that remove the given character from the given Manager.
 */
public final class RemoveChar extends ACommand {

  private final Scanner sc;

  /**
   * Constructs a new {@code RemoveChar}.
   *
   * @param model the model to use
   * @param view  the view to use to render messages
   * @param sc    the scanner to read input from
   */
  public RemoveChar(Manager model, TextView view, Scanner sc) {
    super(model, view);
    this.sc = sc;
    this.signature = "remove-char (name)";
    this.description = "Removes a Character with the given name.";
  }

  @Override
  public void run() {
    try {
      String name = this.sc.nextLine().trim();

      if (!new CharacterValid(this.model, this.view, this.sc).isValid(name)) {
        return;
      }

      String playerName = this.model.findCharByName(name).getPlayerName();

      String[] parties = null;

      if (this.model.hasParties()) {
        parties = new String[this.model.getAllParties().length];
        for (int i = 0; i < parties.length; i++) {
          parties[i] = this.model.getAllParties()[i].getName();
        }
      }

      while (true) {
        this.view.display("The following action cannot be undone.\n");
        this.view.display("Remove the following Character?\n");
        this.view.displayCharacter(name);
        this.view.display("Confirm (y or n): ");

        String answer = this.sc.next();

        if (answer.equalsIgnoreCase("y")) {
          this.view.display(name + " (" + playerName + ") was removed from the Manager.\n");
          this.model.removeCharacter(name);
          this.displayRemovedParties(parties);
          break;
        }

        else if (answer.equalsIgnoreCase("n")) {
          this.view.display(name + " (" + playerName + ") will not be removed.\n");
          break;
        }

        else {
          this.view.display("Invalid input.\n");
        }
      }
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }

  private void displayRemovedParties(String[] parties) {
    if (parties == null) {
      return;
    }

    try {
      if (!this.model.hasParties()) {
        for (String s : parties) {
          this.view.display("The Party " + s + " was removed from the Manager.\n");
        }
        return;
      }

      String[] newParties = new String[this.model.getAllParties().length];

      for (int i = 0; i < newParties.length; i++) {
        newParties[i] = this.model.getAllParties()[i].getName();
      }

      String[] removedParties = this.getDifference(parties, newParties);

      if (removedParties.length == 0) {
        return;
      }

      for (String s : removedParties) {
        this.view.display("The Party " + s + " was removed from the Manager.\n");
      }
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }

  private String[] getDifference(String[] array1, String[] array2) {
    List<String> differenceList = new ArrayList<>();

    // Convert array2 to a List for easy lookup
    List<String> array2List = new ArrayList<>(Arrays.asList(array2));

    // Iterate over array1 and check if each element is present in array2
    for (String element : array1) {
      if (!array2List.contains(element)) {
        differenceList.add(element);
      }
    }

    // Convert the difference List back to an array
    String[] differenceArray = new String[differenceList.size()];
    differenceArray = differenceList.toArray(differenceArray);

    return differenceArray;
  }
}
