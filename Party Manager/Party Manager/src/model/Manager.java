package model;

/**
 * The model for this program. This interface contains methods that can add, remove, and retrieve
 * {@code Party}s and {@code Character}s.
 */
public interface Manager {

  /**
   * Sets the party the manager is going to use.
   * @param name the name of the party to use
   * @throws IllegalStateException if the manager has no parties
   * @throws IllegalArgumentException if the given String is null
   *                                  OR there's no party with the given name
   */
  void setActiveParty(String name) throws IllegalStateException, IllegalArgumentException;

  /**
   * Returns the current active party that this {@code Manager} is using.
   * @return the current active party that this {@code Manager} is using
   * @throws IllegalStateException if the manager has no parties
   */
  Party getActiveParty() throws IllegalStateException;

  /**
   * Returns every {@code Party} this {@code Manager} has.
   * @return every {@code Party} this {@code Manager} has.
   * @throws IllegalStateException if the manager has no parties
   */
  Party[] getAllParties() throws IllegalStateException;

  /**
   * Returns every {@code Character} this {@code Manager} has.
   * @return every {@code Character} this {@code Manager} has.
   * @throws IllegalStateException if the manager has no characters
   */
  Character[] getAllCharacters() throws IllegalStateException;

  /**
   * Determines whether this {@code Manager} contains a {@code Character} with the given
   * name.
   * @param name the name to look for
   * @return true if there's a character with the given name, otherwise false
   */
  boolean doesCharacterExist(String name);

  /**
   * Determines whether this {@code Manager} contains a {@code Party} with the given
   * name.
   * @param name the name to look for
   * @return true if there's a party with the given name, otherwise false
   */
  boolean doesPartyExist(String name);

  /**
   * Adds the given {@code Character} to this {@code Manager}.
   * @param c the character to add
   * @throws IllegalArgumentException if the given character is null
   */
  void addCharacter(Character c) throws IllegalArgumentException;

  /**
   * Adds a {@code Party} to this {@code Manager} with the given name. The {@code Party} is
   * made up of the given {@code Character}s. If any of the given {@code Character}s are NOT
   * in this {@code Manager}, they get added to this {@code Manager}.
   * @param name the name to index the party as
   * @param c the characters to put into the party
   * @throws IllegalArgumentException if the given String is null
   *                                  OR if the given String is whitespace
   *                                  OR if any of the characters are null.
   */
  void addParty(String name, Character... c) throws IllegalArgumentException;

  /**
   * Removes the {@code Character} from this {@code Manager} whose name matches the given String.
   * This method should also go through every {@code Party} where the character being removed
   * appears in, and remove them. This means editing all of those Parties.
   * <p>
   * So for example, if a Party, p1, contains characters c1, c2, c3;
   * and another Party, p2, contains c2, c3, c4, c5, c6;
   * and lastly p3, contains just c2. If c2 is removed, p1 becomes c1, c3; p2 becomes c3, c4, c5, c6,
   * and p3 is removed since a {@code Party} cannot contain 0 {@code Character}s.
   * @param name the name of the character to remove
   * @throws IllegalArgumentException if the given String is null
   * @throws IllegalStateException if the manager has no characters
   */
  void removeCharacter(String name) throws IllegalArgumentException, IllegalStateException;

  /**
   * Removes the {@code Party} from this {@code Manager} whose name matches the given String.
   * This method should NOT remove the {@code Character}s from the manager.
   * @param name the name of the party to remove
   * @throws IllegalArgumentException if the given String is null
   * @throws IllegalStateException if the manager has no parties
   */
  void removeParty(String name) throws IllegalArgumentException, IllegalStateException;

}
