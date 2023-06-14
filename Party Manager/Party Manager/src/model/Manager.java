package model;

/**
 * The model for this program. This interface contains methods that can add, remove, and retrieve
 * {@code Party}s and {@code Character}s.
 */
public interface Manager {

  /**
   * Returns whether a campaign has been started with this {@code Manager}.
   *
   * @return true if a campaign has been started, otherwise false.
   */
  boolean hasStartedACampaign();

  /**
   * Starts or ends a campaign with this {@code Manager}, using the active party.
   *
   * @param start flag to either start or end a campaign
   * @throws IllegalStateException if the active party is null.
   */
  void startCampaign(boolean start) throws IllegalStateException;

  /**
   * Sets the party the manager is going to use.
   *
   * @param name the name of the party to use
   * @throws IllegalStateException    if the manager has no parties OR if a campaign has been
   *                                  started
   * @throws IllegalArgumentException if the given String is null OR there's no party with the given
   *                                  name
   */
  void setActiveParty(String name) throws IllegalStateException, IllegalArgumentException;

  /**
   * Returns the current active party that this {@code Manager} is using.
   *
   * @return the current active party that this {@code Manager} is using
   * @throws IllegalStateException if the manager has no parties
   */
  Party getActiveParty() throws IllegalStateException;

  /**
   * Returns every {@code Party} this {@code Manager} has.
   *
   * @return every {@code Party} this {@code Manager} has.
   * @throws IllegalStateException if the manager has no parties OR if a campaign has been started
   */
  Party[] getAllParties() throws IllegalStateException;

  /**
   * Returns every {@code Character} this {@code Manager} has.
   *
   * @return every {@code Character} this {@code Manager} has.
   * @throws IllegalStateException if the manager has no characters OR if a campaign has been
   *                               started
   */
  Character[] getAllCharacters() throws IllegalStateException;

  /**
   * Returns a String array featuring every stat that the {@code Characters} in this {@code Manager}
   * has.
   *
   * @return a String array with the name of every stat.
   */
  String[] getStats();

  /**
   * Returns a String array featuring every role that the {@code Characters} in this {@code Manager}
   * has.
   *
   * @return a String array with the name of every role.
   */
  String[] getRoles();

  /**
   * Reduces the hp of the {@code Character} in the party that has the given name.
   *
   * @param name   the name of the character to damage
   * @param amount the amount of hp to reduce
   * @throws IllegalArgumentException if the amount is less than 0 OR if the given String is null
   */
  void damage(String name, int amount) throws IllegalArgumentException;

  /**
   * Adds to the hp value of a the {@code Character} in the party that has the given name.
   *
   * @param name   the name of the character to damage
   * @param amount the amount of hp to reduce
   * @throws IllegalArgumentException if the amount is less than 0 OR if the given String is null
   */
  void heal(String name, int amount) throws IllegalArgumentException;

  /**
   * Determines whether this {@code Manager} contains a {@code Character} with the given name.
   *
   * @param name the name to look for
   * @return true if there's a character with the given name, otherwise false
   * @throws IllegalArgumentException if the given String is null
   */
  boolean doesCharacterExist(String name) throws IllegalArgumentException;

  /**
   * Determines whether this {@code Manager} contains a {@code Party} with the given name.
   *
   * @param name the name to look for
   * @return true if there's a party with the given name, otherwise false
   * @throws IllegalArgumentException if the given String is null
   */
  boolean doesPartyExist(String name) throws IllegalArgumentException;

  /**
   * Returns whether this {@code Manager} has any {@code Character}s in it.
   *
   * @return true if this {@code Manager} has any {@code Character}s in it, otherwise false.
   */
  boolean hasCharacters();

  /**
   * Returns whether this {@code Manager} has any {@code Party}s in it.
   *
   * @return true if this {@code Manager} has any {@code Party}s in it, otherwise false.
   */
  boolean hasParties();

  /**
   * Returns the {@code Character} in this manage with the given name.
   *
   * @param name the name of the character to find.
   * @return the character with the given name
   * @throws IllegalArgumentException if the given String is null OR if there's no character with
   *                                  the given name.
   * @throws IllegalStateException    if the manager has no characters
   */
  Character findCharByName(String name) throws IllegalArgumentException, IllegalStateException;

  /**
   * Returns the {@code Party} in this manage with the given name.
   *
   * @param name the name of the party to find.
   * @return the party with the given name
   * @throws IllegalArgumentException if the given String is null OR if there's no party with the
   *                                  given name.
   * @throws IllegalStateException    if the manager has no parties
   */
  Party findPartyByName(String name) throws IllegalArgumentException, IllegalStateException;

  /**
   * Adds the given {@code Character} to this {@code Manager}.
   *
   * @param c the character to add
   * @throws IllegalArgumentException if the given character is null
   * @throws IllegalStateException    if there's a character in this manager with the given name
   */
  void addCharacter(Character c) throws IllegalArgumentException, IllegalStateException;

  /**
   * Adds a {@code Party} to this {@code Manager} with the given name. The {@code Party} is made up
   * of the given {@code Character}s.
   *
   * @param name the name to index the party as
   * @param c    the characters to put into the party
   * @throws IllegalArgumentException if the given String is null
   *                                  OR if the given String is whitespace
   *                                  OR if any of the characters are null
   *                                  OR if any of the characters are not present in the manager
   * @throws IllegalStateException    if there's a party in this manager with the given name
   */
  void addParty(String name, Character... c) throws IllegalArgumentException, IllegalStateException;

  /**
   * Removes the {@code Character} from this {@code Manager} whose name matches the given String.
   * This method should also go through every {@code Party} where the character being removed
   * appears in, and remove them. This means editing all of those Parties.
   * <p>
   * So for example, if a Party, p1, contains characters c1, c2, c3; and another Party, p2, contains
   * c2, c3, c4, c5, c6; and lastly p3, contains just c2. If c2 is removed, p1 becomes c1, c3; p2
   * becomes c3, c4, c5, c6, and p3 is removed since a {@code Party} cannot contain 0
   * {@code Character}s.
   * <p>
   * In the case that a party is removed via this method, and said party was the active party, this
   * method should set the active party to the first party this {@code Manager} has. If there are no
   * parties after this method removes a party, the active party should be set to null.
   *
   * @param name the name of the character to remove
   * @throws IllegalArgumentException if the given String is null
   * @throws IllegalStateException    if the manager has no characters
   */
  void removeCharacter(String name) throws IllegalArgumentException, IllegalStateException;

  /**
   * Removes the {@code Party} from this {@code Manager} whose name matches the given String. This
   * method should NOT remove the {@code Character}s from the manager.
   * <p>
   * If the party that was removed was the active party, this method should set the active party to
   * the first party this {@code Manager} has. If there are no parties after this method removes a
   * party, the active party should be set to null.
   *
   * @param name the name of the party to remove
   * @throws IllegalArgumentException if the given String is null
   * @throws IllegalStateException    if the manager has no parties
   */
  void removeParty(String name) throws IllegalArgumentException, IllegalStateException;

}
