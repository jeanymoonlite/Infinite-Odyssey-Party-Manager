package controller.command.party;

import controller.command.ACommand;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import model.Character;
import model.Manager;
import model.Party;
import model.infiniteodysseys.IOCharacter;
import model.infiniteodysseys.IOParty;
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
   */
  public CreateParty(Manager model, TextView view, Scanner sc) {
    super(model, view);
    this.sc = sc;
  }

  @Override
  public void run() {
    try {
      this.sc.nextLine();
      this.view.display("Party name: ");
      String name = this.sc.nextLine();

      if (this.model.doesPartyExist(name)) {
        this.view.display("\nInvalid input: There is already a Party named " + name + ".\n");
        this.view.display("Please input a different name.\n");
        this.run();
        return;
      }

      this.view.display("Characters (Each by a comma): ");
      String characters = this.sc.nextLine();

      while (characters.isEmpty() || characters.isBlank()) {
        this.view.display("\nInvalid input: A Party needs at least one Character.\n");
        characters = this.sc.nextLine();
      }

      String[] characterList = characters.split(",\\s*");

//      while (Arrays.stream(characterList).allMatch((s) -> s.isEmpty() || s.isBlank())) {
//        this.view.display("\nInvalid input: A Party needs at least one Character.\n");
//        characters = this.sc.nextLine();
//        characterList = characters.split(",\\s*");
//      }

      List<String> charsAdd = new ArrayList<String>();
      List<String> charsRemoved = new ArrayList<String>();

      for (String s : characterList) {
        if (this.model.doesCharacterExist(s.trim())) {
          charsAdd.add(s);
        }
        else {
          charsRemoved.add(s);
        }
      }

      this.view.display("\n");

      for (String s : charsRemoved) {
        if (!s.isBlank() || !s.isEmpty()) {
          this.view.display(s + " is not a Character in this Manager, "
              + "therefore they won't be added.\n");
        }
      }

      Character[] c = new Character[charsAdd.size()];

      for (int i = 0; i < charsAdd.size(); i++) {
        c[i] = this.findByName(charsAdd.get(i));
      }

      this.model.addParty(name, c);

      while (true) {
        this.view.display("Create the following Party? (Confirm y or n): \n");
        this.view.display("Party name: " + name + "\n");
        this.view.display("Party members: ");

        for (int i = 0; i < charsAdd.size(); i ++) {
          this.view.display(charsAdd.get(i));

          if (i != charsAdd.size() - 1) {
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

  private Character findByName(String name) {
    for (Character c : this.model.getAllCharacters()) {
      if (c.getName().equalsIgnoreCase(name)) {
        return c;
      }
    }
    return null;
  }

}
