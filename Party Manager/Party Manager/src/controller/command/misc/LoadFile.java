package controller.command.misc;

import controller.Controller;
import controller.IOManagerController;
import controller.command.ACommand;
import controller.files.savefiles.Ver100FileValid;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import model.Manager;
import model.infiniteodysseys.IOManager;
import view.IOManagerTextView;
import view.TextView;

/**
 * A template for {@code ACommand} objects.
 */
public class LoadFile extends ACommand {

  private final Scanner sc;
  private final Controller con;

  /**
   * Constructs a new {@code PartyCommand}.
   *
   * @param model the model to use
   * @param view  the view to use to render messages
   * @param sc    the scanner to read input from
   */
  public LoadFile(Manager model, TextView view, Scanner sc, Controller con) {
    super(model, view);
    this.sc = sc;
    this.con = con;
  }

  @Override
  public void run() {
    try {
      String filePath = this.sc.nextLine().trim();

      File file = new File(filePath);

      if (!file.exists()) {
        this.view.display("Invalid input: The given file does not exist.");
        return;
      }

      if (file.isDirectory()) {
        this.view.display("Invalid input: The given file is a folder.");
      }

      Ver100FileValid temp = new Ver100FileValid();

      if (temp.isValid(filePath)) {
        this.view.display("The file " + file.getName() + " was loaded.\n");
        this.con.start(temp.load(filePath), this.view);
      }
      else {
        this.view.display("Invalid file: The given file is not a valid .iom file.");
      }
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }
}
