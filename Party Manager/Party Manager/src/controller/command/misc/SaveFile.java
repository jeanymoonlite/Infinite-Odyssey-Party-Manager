package controller.command.misc;

import controller.Controller;
import controller.command.ACommand;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.util.Scanner;
import model.Character;
import model.Manager;
import model.Party;
import view.TextView;

/**
 * A command that saves all the {@code Manager}'s {@code Character}s and {@code Party}s.
 */
public class SaveFile extends ACommand {

  private final Scanner sc;

  /**
   * Constructs a new {@code SaveFile}.
   *
   * @param model the model to use
   * @param view  the view to use to render messages
   * @param sc    the scanner to read input from
   */
  public SaveFile(Manager model, TextView view, Scanner sc) {
    super(model, view);
    this.sc = sc;
    this.signature = "save (fileName)";
    this.description = "Saves all of the Characters and Parties in the Manager \n"
        + "to a file. The file will be in the same folder as this program.";
  }

  @Override
  public void run() {
    String header = "Infinite Odysseys Party Manager";
    String version = "Ver. " + Controller.getVersion();

    String characters = this.getAllCharacters();
    if (characters == null) {
      return;
    }

    String parties = this.getAllParties();

    String fileName = this.getFileName();
    if (fileName == null) {
      return;
    }

    String jarFileDirectory = this.getJarDirectory();

    try {
      String contents = header + "\n";
      contents = contents.concat(version + "\n");
      contents = contents.concat(
          "Total Characters: " + this.model.getAllCharacters().length + "\n");
      try {
        contents = contents.concat("Total Parties: " + this.model.getAllParties().length + "\n");
      }
      catch (IllegalStateException e) {
        contents = contents.concat("Total Parties: 0\n");
      }
      contents = contents.concat("\n");
      contents = contents.concat(characters);
      contents = contents.concat(parties);
      contents = contents.concat("\n");

      File file = new File(jarFileDirectory, fileName + ".iom");
      if (file.createNewFile()) {
        this.saveFile(file, contents);
      }
      else {
        this.overwriteFile(file, contents);
      }
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }

  private String getFileName() {
    String name = this.sc.nextLine().trim();

    try {
      if (name.isBlank() || name.isEmpty()) {
        this.view.display("Invalid input: File name cannot be whitespace.\n");
        return null;
      }

      if (name.contains(".")) {
        this.view.display("Invalid input: File name cannot contain a period.\n");
        return null;
      }
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }

    return name;
  }

  private String getJarDirectory() {
    ProtectionDomain protectionDomain = SaveFile.class.getProtectionDomain();
    CodeSource codeSource = protectionDomain.getCodeSource();
    String jarFilePath = codeSource.getLocation().getPath();

    File file = new File(jarFilePath);
    return file.getParent().replace("%20", " ");
  }

  private String getAllCharacters() {
    String characters = "";

    try {
      if (!this.model.hasCharacters()) {
        this.view.display("\nInvalid state: The Manager doesn't have any Characters!\n");
        this.view.display("Add Characters using the create-char command.\n");
        return null;
      }

      for (int i = 0; i < this.model.getAllCharacters().length; i++) {
        Character curCharacter = this.model.getAllCharacters()[i];

        characters = characters.concat("Name: " + curCharacter.getName() + "\n");
        characters = characters.concat("Player: " + curCharacter.getPlayerName() + "\n");

        characters = characters.concat("Class: " + curCharacter.getRole() + "\n");
        characters = characters.concat(
            "Class Specification: " + curCharacter.getSpecification() + "\n");

        characters = characters.concat("Hp: " + curCharacter.getHP() + "/");
        characters = characters.concat(curCharacter.getMaxHP() + "\n");

        characters = characters.concat("Strength: " + curCharacter.getValueOf("Strength") + "\n");
        characters = characters.concat(
            "Intelligence: " + curCharacter.getValueOf("Strength") + "\n");
        characters = characters.concat("Creativity: " + curCharacter.getValueOf("Strength") + "\n");
        characters = characters.concat("Charisma: " + curCharacter.getValueOf("Strength") + "\n");
        characters = characters.concat("Stealth: " + curCharacter.getValueOf("Strength") + "\n");
        characters = characters.concat(
            "Intimidation: " + curCharacter.getValueOf("Strength") + "\n");
        characters = characters.concat("\n");
      }
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
    return characters;
  }

  private String getAllParties() {
    String parties = "";

    if (!this.model.hasParties()) {
      return parties;
    }

    for (int i = 0; i < this.model.getAllParties().length; i++) {
      Party curParty = this.model.getAllParties()[i];
      parties = parties.concat(curParty.getName() + " | ");

      for (int j = 0; j < curParty.size(); j++) {
        parties = parties.concat(curParty.getParty()[j].toString());

        if (j != curParty.size() - 1) {
          parties = parties.concat(" | ");
        }
      }

      parties = parties.concat("\n");
    }
    return parties;
  }

  private void saveFile(File file, String contents) {
    try {
      this.view.display("File created at " + file.getPath());

      FileWriter writer;
      writer = new FileWriter(file);
      writer.write(contents);
      writer.close();
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }

  private void overwriteFile(File file, String contents) {
    try {
      while (true) {
        this.view.display("\nThere exists a file named " + file.getName() + ".\n");
        this.view.display("Do you want to overwrite " + file.getPath() + "?\n");
        this.view.display("Confirm (y or n): ");

        String answer = this.sc.next();

        if (answer.equalsIgnoreCase("y")) {
          this.view.display("The file " + file.getPath() + " has been overwritten.\n");

          FileWriter writer;
          writer = new FileWriter(file);
          writer.write(contents);
          writer.close();
          break;
        }

        else if (answer.equalsIgnoreCase("n")) {
          break;
        }

        else {
          this.view.display("\nInvalid input.");
        }
      }
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }
}
