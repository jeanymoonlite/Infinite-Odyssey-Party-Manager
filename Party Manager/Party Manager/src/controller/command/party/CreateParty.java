package controller.command.party;

import controller.command.ACommand;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.Character;
import model.Manager;
import view.TextView;

/**
 * A command that creates a new {@code Party} and adds it to the given {@code Manager}.
 */
public final class CreateParty extends ACommand {

  private final Scanner sc;

  /**
   * Constructs a new {@code CreateParty}.
   *
   * @param model the model to use
   * @param view  the view to use to render messages
   * @param sc    the scanner to read input from
   */
  public CreateParty(Manager model, TextView view, Scanner sc) {
    super(model, view);
    this.sc = sc;
    this.signature = "create-party (name characters...)";
    this.description = "Creates a new party with the given name and list of characters.\n"
        + "Each character's name must be separated by a space and \n"
        + "they should all be on one line.";
  }

  @Override
  public void run() {
    try {
      if (!this.model.hasCharacters()) {
        this.view.display("The Manager doesn't have any Characters!\n");
        this.view.display("Add Characters using the create-char command.\n");
        return;
      }

      this.sc.nextLine();
      String name = this.getName();
      Character[] c = this.getCharacters();

      this.model.addParty(name, c);

      while (true) {
        this.view.display("Create the following Party? (Confirm y or n): \n");
        this.view.display("Party name: " + name + "\n");
        this.view.display("Party members: ");

        for (int i = 0; i < c.length; i++) {
          this.view.display(c[i].getName());

          if (i != c.length - 1) {
            this.view.display(", ");
          }
        }

        this.view.display("\n");
        String answer = this.sc.next();

        if (answer.equalsIgnoreCase("y")) {
          this.view.display("The Party " + name + " was added to the Manager.\n");
          break;
        }

        else if (answer.equalsIgnoreCase("n")) {
          this.view.display("The Party " + name + " will not be created.\n");
          this.model.removeParty(name);
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

  private String getName() {
    try {
      this.view.display("Party name: ");
      String name = this.sc.nextLine();

      if (this.model.doesPartyExist(name)) {
        this.view.display("\nInvalid input: There is already a Party named " + name + ".\n");
        this.view.display("Please input a different name.\n");
        return this.getName();
      }

      return name;
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }

  private Character[] getCharacters() {
    try {
      this.view.display("Characters (Each by a comma): ");

      List<String> charsAdd = new ArrayList<String>();
      List<String> charsRemoved;

      String characters;
      String[] characterList;

      while (charsAdd.size() == 0) {
        characters = this.sc.nextLine();
        characterList = characters.split(",\\s*");
        charsAdd = new ArrayList<String>();
        charsRemoved = new ArrayList<String>();

        if (characters.isEmpty() || characters.isBlank()) {
          this.view.display("\nInvalid input: A Party needs at least one Character.\n");
          continue;
        }

        for (String s : characterList) {
          if (this.model.doesCharacterExist(s.trim())) {
            charsAdd.add(s);
          }
          else {
            charsRemoved.add(s);
          }
        }

        this.view.display("\n");

        if (charsAdd.size() > 0) {
          this.displayNotAddedChars(charsRemoved);
          break;
        }

        this.view.display("Invalid input: A Party needs at least one Character.\n");

        this.displayNotAddedChars(charsRemoved);
      }

      Character[] result = new Character[charsAdd.size()];

      for (int i = 0; i < charsAdd.size(); i++) {
        result[i] = this.model.findCharByName(charsAdd.get(i));
      }

      return result;
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }

  private void displayNotAddedChars(List<String> charsRemoved) {
    try {
      for (String s : charsRemoved) {
        if (!s.isBlank() || !s.isEmpty()) {
          this.view.display(s + " is not a Character in this Manager, "
              + "therefore they won't be added.\n");
        }
      }
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }
}
