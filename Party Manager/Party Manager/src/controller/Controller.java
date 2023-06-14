package controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * An interface for controlling and interacting with a {@code Manager}.
 */
public interface Controller {

  /**
   * Starts the Controller.
   */
  void start();

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
