package model.infiniteodysseys;

import java.util.Arrays;
import java.util.Objects;
import model.Character;
import model.Party;
import utils.Clamp;

/**
 * Represents an Infinite Odysseys party.
 */
public final class IOParty implements Party {

  private final Character[] party;
  private final String name;

  /**
   * Constructs a new {@code IOParty} with the given name and {@code Character}s.
   * @param name the name to give the party
   * @param characters the characters that will be in the party
   * @throws IllegalArgumentException if the given String is null or whitespace
   *                                  OR if any of the Characters are null.
   */
  public IOParty(String name, Character... characters)
      throws IllegalArgumentException {

    if (name == null) {
      throw new IllegalArgumentException("Party name cannot be null.");
    }

    if (name.isBlank()) {
      throw new IllegalArgumentException("Party name cannot be whitespace.");
    }

    if (characters == null || characters.length == 0) {
      throw new IllegalArgumentException("A Party needs at least 1 Character.");
    }

    if (Arrays.stream(characters).anyMatch(Objects::isNull)) {
      throw new IllegalArgumentException("A Party cannot contain any null Characters.");
    }

    this.name = name;
    this.party = new Character[characters.length];

    System.arraycopy(characters, 0, this.party, 0, this.party.length);

  }

  @Override
  public Character[] getParty() {
    Character[] results = new Character[this.party.length];

    System.arraycopy(this.party, 0, results, 0, this.party.length);

    return results;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public int size() {
    return this.party.length;
  }

  @Override
  public Character getPartyMember(String name) throws IllegalArgumentException {
    if (this.hasCharacter(name)) {
      for (Character c : this.party) {
        if (c.getName().equalsIgnoreCase(name)) { return c; }
      }
    }
    else {
      throw new IllegalArgumentException("There is no Character named " + name + " in this Party.");
    }

    return null;
  }

  @Override
  public boolean hasCharacter(String name) throws IllegalArgumentException {
    if (name == null) {
      throw new IllegalArgumentException("The given name cannot be null.");
    }

    for (Character c : this.party) {
      if (c.getName().equalsIgnoreCase(name)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public void damage(String name, int amount) throws IllegalArgumentException {
    Character c = this.getPartyMember(name);

    if (amount < 0) {
      throw new IllegalArgumentException("Damage amount should be a positive number.");
    }

    c.setHP(Clamp.run(c.getHP() - amount, 0, 100));
  }

  @Override
  public void heal(String name, int amount) throws IllegalArgumentException {
    Character c = this.getPartyMember(name);

    if (amount < 0) {
      throw new IllegalArgumentException("ChangeHp amount should be a positive number.");
    }

    c.setHP(Clamp.run(c.getHP() + amount, 0, 100));
  }
}
