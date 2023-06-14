package controller.command.party;

import controller.Controller;
import controller.command.ACommand;
import controller.input.validation.CharacterValid;
import controller.input.validation.PartyValid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import model.Character;
import model.Manager;
import model.Party;
import model.infiniteodysseys.IOParty;
import view.TextView;

public class EditParty extends ACommand {

  private final Scanner sc;
  public static final String separator = Controller.separator.replace("~", "-");
  private boolean running;
  private boolean tryingToQuit;
  private boolean saving;
  private Party party;
  private Party newParty;

  /**
   * Constructs a new {@code PartyCommand}.
   *
   * @param model the model to use
   * @param view  the view to use to render messages
   * @param sc    the scanner to read input from
   */
  public EditParty(Manager model, TextView view, Scanner sc) {
    super(model, view);
    this.sc = sc;
    this.signature = "edit-party (name)";
    this.description = "Edits the Party with the given name.\n"
        + "This will put the program into Party Editing mode.\n"
        + "A new set of commands will become available in editing mode.";
  }

  @Override
  public void run() {
    String name = this.sc.nextLine().trim();

    if (!new PartyValid(this.model, this.view, this.sc).isValid(name)) {
      return;
    }

    this.party = this.model.findPartyByName(name);
    this.newParty = this.model.findPartyByName(name);
    this.startMessage();
    this.running = true;
    this.tryingToQuit = false;
    this.saving = false;

    while (this.running) {
      try {
        this.isTryingToQuit();

        if (this.tryingToQuit) {
          break;
        }

        this.view.display(EditParty.separator);
        this.view.display(this.getPartyNameAndMembers(false));
        this.view.display("Awaiting edit command: ");

        String currCommand = this.sc.next();

        switch (currCommand.toLowerCase()) {
          case "quit":
            this.quitMessage();
            break;
          case "save":
            this.saveMessage();
            break;
          case "help":
            this.helpMessage();
            break;
          case "show-party":
            this.view.display(this.getPartyNameAndMembers(true));
            break;
          case "edit-name":
            this.sc.nextLine();
            this.newParty = this.changeName();
            break;
          case "add-char":
            this.newParty = this.addChar();
            break;
          case "remove-char":
            this.newParty = this.removeChar();
            break;
          default:
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
      this.view.display("You are now in party editing mode.\n");
      this.view.display("Editing: " + this.party.getName() + "\n");
    }
    catch (IOException io) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }

  private void helpMessage() {
    try {
      this.view.display("show-party:\n");
      this.view.display("\tShows what the Party that's being edited looks like.\n");
      this.view.display("\n");

      this.view.display("edit-name:\n");
      this.view.display("\tChanges the name of this Party.\n");
      this.view.display("\n");

      this.view.display("add-char (name):\n");
      this.view.display("\tAdds the Character with the given name to this Party.\n");
      this.view.display("\n");

      this.view.display("remove-char (name):\n");
      this.view.display("\tRemoves the Character with the given name from this Party.\n");
      this.view.display("\n");

      this.view.display("save:\n");
      this.view.display("\tSaves the changes made to the Character being edited.\n");
      this.view.display("\n");

      this.view.display("quit:\n");
      this.view.display("\tRemoves any changes made to the Character being edited.\n");
      this.view.display("\n");
    }
    catch (IOException io) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }

  private void quitMessage() {
    try {
      this.view.display("WARNING: Quitting will remove the following changes:\n");
      this.view.display(this.getPartyNameAndMembers(true));

      this.view.display("Are you sure you want to exit Party editing mode?\n");
      this.view.display("Confirm (y or n): ");
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
      this.view.display(this.getPartyNameAndMembers(true));

      this.view.display("Confirm (y or n): ");
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
          this.view.display(
              "\nAll changes made to " + this.party.getName() + " have been undone.\n");
          this.view.display("Now exiting Party editing mode.\n");
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
          this.view.display("\n" + this.party.getName() + " has been updated!\n");
          this.model.removeParty(this.party.getName());
          this.model.addParty(this.newParty.getName(), this.newParty.getParty());
          this.view.display("Now exiting Party editing mode.\n");
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

  private String getPartyNameAndMembers(boolean showAll) {
    String result = "Party name: " + this.newParty.getName() + "\n";
    result = result.concat((showAll) ? "" : "Party members: ");

    for (int i = 0; i < this.newParty.getParty().length; i++) {
      Character c = this.newParty.getParty()[i];

      result = (showAll) ? result.concat(c.toStringAll() + "\n")
          : result.concat(c.toString() +
              ((i != this.newParty.getParty().length - 1) ? ", " : "\n"));
    }

    return result;
  }

  private Party changeName() {
    try {
      this.view.display("Current Party name: " + this.newParty.getName() + "\n");
      this.view.display("New Party name: ");

      String name = this.sc.nextLine();

      if (name.isEmpty() || name.isBlank()) {
        return this.newParty;
      }

      if ((!this.party.getName().equalsIgnoreCase(name))
          && (this.model.doesPartyExist(name))) {
        this.view.display("\nInvalid input: There is already a Party named " + name + ".\n");
        this.view.display("Please input a different name.\n");
        return this.changeName();
      }

      return new IOParty(name, this.newParty.getParty());
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }

  private Party addChar() {
    try {
      String name = this.sc.nextLine().trim();

      if (!new CharacterValid(this.model, this.view, this.sc).isValid(name)) {
        return this.newParty;
      }

      if (this.newParty.hasCharacter(name)) {
        this.view.display(
            "\nInvalid input: The Character " + name + " is already in this Party.\n");
        return this.newParty;
      }

      Character[] c = new Character[this.newParty.getParty().length + 1];
      System.arraycopy(this.newParty.getParty(), 0, c, 0, this.newParty.getParty().length);
      c[c.length - 1] = this.model.findCharByName(name);

      this.view.display("\n" + c[c.length - 1].toString() + " was added to " + this.newParty.getName() + ".\n");
      return new IOParty(this.newParty.getName(), c);
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }

  private Party removeChar() {
    try {
      String name = this.sc.nextLine().trim();

      if (!new CharacterValid(this.model, this.view, this.sc).isValid(name)) {
        return this.newParty;
      }

      if (!this.newParty.hasCharacter(name)) {
        this.view.display(
            "\nInvalid input: The Character " + name + " doesn't exist in this Party.\n");
        return this.newParty;
      }

      if (this.newParty.size() == 1) {
        while (true) {
          this.view.display("\nRemoving " + name + " will delete this Party as a Party needs\n");
          this.view.display("at least one Character in it.\n");
          this.view.display("Are you sure you want to remove " + name + "?\n");
          this.view.display("Confirm (y or n): ");

          String answer = this.sc.next();
          if (answer.equalsIgnoreCase("y")) {
            this.running = false;
            this.tryingToQuit = true;
            this.view.display(
                "The Party " + this.party.getName() + " was removed from the Manager.\n");
            this.model.removeParty(this.party.getName());
            this.view.display("Now exiting Party editing mode.\n");
            return null;
          }
          else if (answer.equalsIgnoreCase("n")) {
            this.tryingToQuit = false;
            this.saving = false;
            return this.newParty;
          }
          else {
            this.view.display("\nInvalid input.\n");
          }
        }
      }

      List<Character> temp = new ArrayList<Character>();

      for (int i = 0; i < this.newParty.getParty().length; i++) {
        if (!this.newParty.getParty()[i].getName().equalsIgnoreCase(name)) {
          temp.add(this.newParty.getParty()[i]);
        }
      }

      Character[] c = new Character[temp.size()];
      for (int i = 0; i < temp.size(); i++) {
        c[i] = temp.get(i);
      }

      this.view.display("\n" + this.newParty.getPartyMember(name).toString());
      this.view.display(" was removed from " + this.newParty.getName() + ".\n");

      return new IOParty(this.newParty.getName(), c);
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }
}
