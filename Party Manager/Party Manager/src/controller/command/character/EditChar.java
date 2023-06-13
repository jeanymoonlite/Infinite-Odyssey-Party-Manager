package controller.command.character;

import controller.Controller;
import controller.command.ACommand;
import controller.input.validation.CharacterValid;
import java.io.IOException;
import java.util.Scanner;
import model.Character;
import model.Manager;
import view.TextView;

public class EditChar extends ACommand {

  private final Scanner sc;
  private boolean running;
  private boolean tryingToQuit;
  private boolean saving;
  private Character character;
  private Character newCharacter;
  private EditAttribute edit;
  public static final String separator = Controller.separator.replace("~", "-");

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

  @Override
  public void run() {
    String name = this.sc.nextLine().trim();

    if (!new CharacterValid(this.model, this.view, this.sc).isValid(name)) {
      return;
    }

    this.character = this.model.findCharByName(name);
    this.newCharacter = this.model.findCharByName(name);
    this.startMessage();
    this.running = true;
    this.tryingToQuit = false;
    this.saving = false;
    this.edit = new EditAttribute(this.model, this.view, this.sc, this.character,
        this.newCharacter);

    while (this.running) {
      try {
        this.isTryingToQuit();

        if (this.tryingToQuit) {
          break;
        }

        this.view.display(EditChar.separator);
        this.view.display(this.newCharacter.toStringAll() + "\n");
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
          case "edit":
            this.edit = new EditAttribute(this.model, this.view, this.sc, this.character,
                this.newCharacter);
            this.edit.run();
            this.newCharacter = this.edit.getUpdatedCharacter();
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
      this.view.display("You are now in character editing mode.\n");
      this.view.display("Editing: " + this.character.getName() +
          " (" + this.character.getPlayerName() + ")" + "\n");
    }
    catch (IOException io) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }

  private void helpMessage() {
    try {
      this.view.display(this.edit.getSignature() + ":\n");
      this.view.display("\t" + this.edit.getDescription().replace("\n", "\n\t"));
      this.view.display("\n");
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
      this.view.display(this.newCharacter.toStringAll());
      this.view.display("Are you sure you want to exit Character editing mode?\n");
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
      this.view.display(this.newCharacter.toStringAll());
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
              "\nAll changes made to " + this.character.getName() + " have been undone.\n");
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
          this.view.display("\n" + this.character.getName() + " has been updated!\n");
          this.model.removeCharacter(this.character.getName());
          this.model.addCharacter(this.newCharacter);
//          this.view.displayCharacter(this.newCharacter.getName());
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
}
