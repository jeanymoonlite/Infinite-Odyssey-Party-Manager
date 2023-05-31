package model.infiniteodysseys;

import java.util.Arrays;
import java.util.Objects;
import model.Character;
import model.Clamp;
import model.Party;

/**
 * Represents an Infinite Odysseys party.
 */
public final class IOParty implements Party {

  private final Character[] party;

  public IOParty(Character... characters)
      throws IllegalArgumentException {

    if (characters == null) {
      throw new IllegalArgumentException("A Party cannot contain any null Characters.");
    }

    if (Arrays.stream(characters).anyMatch(Objects::isNull)) {
      throw new IllegalArgumentException("A Party cannot contain any null Characters.");
    }

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
  public int size() {
    return this.party.length;
  }

  @Override
  public Character getPartyMember(String name) throws IllegalArgumentException {
    if (name == null) {
      throw new IllegalArgumentException("The given name cannot be null.");
    }

    for (Character c : this.party) {
      if (c.getName().equalsIgnoreCase(name)) {
        return c;
      }
    }

    throw new IllegalArgumentException("There is no Character named " + name + " in this Party.");
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
      throw new IllegalArgumentException("Heal amount should be a positive number.");
    }

    c.setHP(Clamp.run(c.getHP() + amount, 0, 100));
  }
}
