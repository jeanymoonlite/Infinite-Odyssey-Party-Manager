package model;

/**
 * Represents a group of {@code Character}s that participate in a campaign. This interface
 * contains methods for getting members of the party and damaging/healing members of a party.
 */
public interface Party {

  /**
   * Returns all the {@code Character}s found in this
   * @return
   */
  Character[] getParty();

  /**
   * Returns the {@code Character} that has the given name (not player name).
   * @param name the name of the character to get
   * @return the {@code Character} that has the given name.
   * @throws IllegalArgumentException if there's no character with the given name
   */
  Character getPartyMember(String name) throws IllegalArgumentException;

  /**
   * Reduces the hp of the {@code Character} in the party that has the given name.
   * @param name the name of the character to damage
   * @param amount the amount of hp to reduce
   */
  void damage(String name, int amount);

  /**
   * Adds to the hp value of a the {@code Character} in the party that has the given name.
   * @param name the name of the character to damage
   * @param amount the amount of hp to reduce
   */
  void heal(String name, int amount);


}
