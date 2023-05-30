package model;

/**
 * Represents a group of {@code Character}s that participate in a campaign. This interface
 * contains methods for getting members of the party and damaging/healing members of a party.
 */
public interface Party {

  /**
   * Returns all the {@code Character}s found in this {@code Party}.
   * @return all the {@code Character}s found in this {@code Party}
   */
  Character[] getParty();

  /**
   * Returns the number of {@code Character}s in this {@code Party}.
   * @return the number of {@code Character}s in this {@code Party}
   */
  int size();

  /**
   * Returns the {@code Character} that has the given name (not player name).
   * @param name the name of the character to get
   * @return the {@code Character} that has the given name.
   * @throws IllegalArgumentException if there's no character with the given name
   *                                  OR if the given String is null
   */
  Character getPartyMember(String name) throws IllegalArgumentException;

  /**
   * Reduces the hp of the {@code Character} in the party that has the given name.
   * @param name the name of the character to damage
   * @param amount the amount of hp to reduce
   * @throws IllegalArgumentException if the amount is less than 0
   *                                  OR if the given String is null
   */
  void damage(String name, int amount) throws IllegalArgumentException;

  /**
   * Adds to the hp value of a the {@code Character} in the party that has the given name.
   * @param name the name of the character to damage
   * @param amount the amount of hp to reduce
   * @throws IllegalArgumentException if the amount is less than 0
   *                                  OR if the given String is null
   */
  void heal(String name, int amount) throws IllegalArgumentException;


}
