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
   * Returns the name of this {@code Party}.
   * @return the name of this {@code Party}
   */
  String getName();

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
   * Determines if there's a {@code Character} with the given name.
   * @param name the name of the character to find
   * @return true if there's a character in this party with the given name, otherwise false
   */
  boolean hasCharacter(String name) throws IllegalArgumentException;

}
