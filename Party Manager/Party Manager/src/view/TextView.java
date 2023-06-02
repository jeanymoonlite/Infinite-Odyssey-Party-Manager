package view;


import java.io.IOException;
import model.Character;

/**
 * The interface for a client's perspective of the model of Bodega Dreams.
 */
public interface TextView {

  /**
   * Renders a given message to the data output in the implementation.
   * @param message the message to be printed
   * @throws IOException if the transmission of the message to the data output fails
   * @throws IllegalStateException if the given message is null
   */
  void display(String message) throws IOException, IllegalArgumentException;

  /**
   * Renders every {@code Character} that the {@code Manager} contains. This includes just their
   * name and the player's name.
   * @throws IOException if the transmission of the message to the data output fails
   * @throws IllegalStateException if the manager has no characters
   */
  void displayAllCharacters() throws IOException, IllegalStateException;

  /**
   * Renders every {@code Party} that the {@code Manager} contains. This includes the name of the
   * {@code Party}s and the names and player names of the {@code Character}s in them.
   * @throws IOException if the transmission of the message to the data output fails
   * @throws IllegalStateException if the manager has no parties
   */
  void displayAllParties() throws IOException, IllegalStateException;

  /**
   * Renders the stats of a specified character found in the {@code Manager}. This method renders
   * the character's name, player name, the class (if applicable), class specification
   * (if applicable), health points, and the name of each stat and their values.
   * @throws IOException if the transmission of the message to the data output fails
   * @throws IllegalArgumentException if there's no character with the given name in the manager.
   *                                  OR if the given String is null
   * @throws IllegalStateException if the manager has no characters
   */
  void displayCharacter(String name) throws IOException, IllegalArgumentException, IllegalStateException;

  /**
   * Renders every {@code Character} found in {@code Party} with the given name. This method renders
   * each character the same way that {@code displayCharacter()} does.
   * @param name the name of the {@code Party} to
   * @throws IllegalArgumentException if there's no party with the given name in the manager.
   *                                  OR if the given String is null
   * @throws IllegalStateException if the manager has no parties
   */
  void displayParty(String name) throws IOException, IllegalArgumentException, IllegalStateException;

  /**
   * Renders every {@code Character} found in the {@code Manager}'s active party. This method renders
   * each character the same way that {@code displaysStatsOf()} does.
   * @throws IOException if the transmission of the message to the data output fails
   * @throws IllegalStateException if the manager has no parties
   */
  void displayActiveParty() throws IOException, IllegalStateException;

}
