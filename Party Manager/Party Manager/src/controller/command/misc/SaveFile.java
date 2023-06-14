package controller.command.misc;

import controller.Controller;
import controller.command.ACommand;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.util.Scanner;
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
    this.signature = "save-file (fileName)";
    this.description = "Saves all of the Characters and Parties in the Manager \n"
        + "to a file. The file will be in the same folder as this program.";
  }

  @Override
  public void run() {
    String header = "Infinite Odysseys Party Manager Ver. " + Controller.getVersion();

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
      File file = new File(jarFileDirectory, fileName + ".txt");
      if (file.createNewFile()) {
        this.view.display("File created at " + file.getPath());
        FileWriter writer;

        try {
          writer = new FileWriter(file);
        }
        catch (IOException io) {
          throw new IOException("File writer error encountered");
        }

        try {
          writer.write(header + "\n");
          writer.write("Total Characters: " + this.model.getAllCharacters().length + "\n");
          writer.write("Total Parties: " + this.model.getAllParties().length + "\n");
          writer.write(characters);
          writer.write(parties);
        }
        catch (IOException io) {
          throw new IOException("File writer error encountered");
        }
        writer.close();
      }
      else {
        this.view.display("There exists a file named " + fileName + ".");
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
        characters = characters.concat(this.model.getAllCharacters()[i].toStringAll() + "\n");
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
      return "No Parties.";
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
}
