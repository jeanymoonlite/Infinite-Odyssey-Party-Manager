package controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import model.Manager;
import org.w3c.dom.Text;
import view.TextView;

/**
 * An interface for controlling and interacting with a {@code Manager}.
 */
public interface Controller {

  /**
   * Starts the Controller.
   */
  void start();

  /**
   * Starts the Controller with the given {@code Manager} and {@code TextView}.
   * This method should be used in cases where either the TextView or Manager have been
   * updated by a command.
   * @param model the Manager to use.
   * @param view the TextView to use.
   */
  void start(Manager model, TextView view);

  /**
   * Initializes the commands that this {@code Controller} supports.
   */
  void initCommands();

  /**
   * Returns the version number of the program.
   *
   * @return the version number of the program.
   */
  static String getVersion() {
    Properties prop = new Properties();

    try (InputStream inputStream = ClassLoader.getSystemResourceAsStream("config.properties")) {
      prop.load(inputStream);
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }

    return prop.getProperty("version");
  }

  String separator = "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
}
