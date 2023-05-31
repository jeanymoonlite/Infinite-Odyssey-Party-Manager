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
   * @throws IllegalArgumentException if there's no party in this manager with the given name
   */
  void setActiveParty(String name) throws IllegalStateException, IllegalArgumentException;

  /**
   * Returns the current active party that this {@code Manager} is using.
   * @return the current active party that this {@code Manager} is using
   */
  Party getActiveParty();

  /**
   * Returns every {@code Party} this {@code Manager} has.
   * @return every {@code Party} this {@code Manager} has.
   */
  Party[] getAllParties();

  /**
   * Returns every {@code Character} this {@code Manager} has.
   * @return every {@code Character} this {@code Manager} has.
   */
  Character[] getAllCharacters();

  void addCharacter(Character c) throws IllegalArgumentException;

  void addParty(String name, Character... c) throws IllegalArgumentException;

  void removeCharacter(String name) throws IllegalArgumentException;

  void removeParty(String name) throws IllegalArgumentException;

}
