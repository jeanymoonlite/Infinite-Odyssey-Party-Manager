package model.infiniteodysseys;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import model.Character;
import model.Manager;
import model.Party;

/**
 * An implementation of the {@code Manager} for Infinite Odysseys.
 */
public class IOManager implements Manager {

  private final List<Party> parties;
  private final List<Character> characters;
  private Party curParty;

  /**
   * Constructs a new {@code IOManager}.
   */
  public IOManager() {
    this.parties = new ArrayList<Party>();
    this.characters = new ArrayList<Character>();
  }

  @Override
  public void setActiveParty(String name)
      throws IllegalStateException, IllegalArgumentException {

    if (this.parties.size() == 0) {
      throw new IllegalStateException("This Manager doesn't have any Parties!");
    }

    if (name == null) {
      throw new IllegalArgumentException("The given String cannot be null.");
    }

    for (Party p : this.parties) {
      if (p.getName().equalsIgnoreCase(name)) {
        this.curParty = p;
        return;
      }
    }

    throw new IllegalArgumentException("The party " + name + " does not exist.");
  }

  @Override
  public Party getActiveParty() throws IllegalStateException {
    if (this.parties.size() == 0) {
      throw new IllegalStateException("This Manager doesn't have any Parties!");
    }

    return this.curParty;
  }

  @Override
  public Party[] getAllParties() throws IllegalStateException {
    if (this.parties.size() == 0) {
      throw new IllegalStateException("This Manager doesn't have any Parties!");
    }

    Party[] result = new Party[this.parties.size()];

    for (int i = 0; i < this.parties.size(); i++) {
      result[i] = this.parties.get(i);
    }

    return result;
  }

  @Override
  public Character[] getAllCharacters() throws IllegalStateException {
    if (this.characters.size() == 0) {
      throw new IllegalStateException("This Manager doesn't have any Characters!");
    }

    Character[] result = new Character[this.characters.size()];

    for (int i = 0; i < this.characters.size(); i++) {
      result[i] = this.characters.get(i);
    }

    return result;
  }

  @Override
  public boolean doesCharacterExist(String name) {
    for (Character c : this.characters) {
      if (c.getName().equalsIgnoreCase(name)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean doesPartyExist(String name) {
    for (Party p : this.parties) {
      if (p.getName().equalsIgnoreCase(name)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public void addCharacter(Character c) throws IllegalArgumentException {
    if (c == null) {
      throw new IllegalArgumentException("Null cannot be added as a Character.");
    }

    this.characters.add(c);
  }

  @Override
  public void addParty(String name, Character... c) throws IllegalArgumentException {
    if (name == null) {
      throw new IllegalArgumentException("The given String cannot be null.");
    }

    if (name.isBlank() || name.isEmpty()) {
      throw new IllegalArgumentException("A Party's name cannot be whitespace.");
    }

    if (c == null || c.length == 0) {
      throw new IllegalArgumentException("A Party must have at least one Character.");
    }

    if (Arrays.stream(c).anyMatch(Objects::isNull)) {
      throw new IllegalArgumentException("None of a Party's Characters can be null.");
    }

    for (Character character : c) {
      if (!this.doesCharacterExist(character.getName())) {
        this.addCharacter(character);
      }
    }

    this.parties.add(new IOParty(name, c));
  }

  @Override
  public void removeCharacter(String name)
      throws IllegalArgumentException, IllegalStateException {

    if (this.characters.size() == 0) {
      throw new IllegalStateException("This Manager doesn't have any Characters!");
    }

    if (name == null) {
      throw new IllegalArgumentException("The given String cannot be null.");
    }

    if (this.parties.size() == 0) {
      this.characters.removeIf((c) -> c.getName().equalsIgnoreCase(name));
      return;
    }

    for (int p = 0; p < this.parties.size(); p++) {

      try {
        this.parties.get(p).getPartyMember(name);
        Party curParty = this.parties.get(p);

        if ((this.curParty != null)
            && !this.doesPartyExist(this.curParty.getName())) {
          this.resetActiveParty();
          break;
        }

        Character[] temp = new Character[curParty.size() - 1];

        int counter = 0;

        for (int c = 0; c < curParty.getParty().length; c++) {
          if (!curParty.getParty()[c].getName().equalsIgnoreCase(name)) {
            temp[counter] = curParty.getParty()[c];
            counter++;
          }
        }

      this.parties.set(p, new IOParty(curParty.getName(), temp));
      }
      catch (IllegalArgumentException ignored) {}
    }

    this.characters.removeIf((c) -> c.getName().equalsIgnoreCase(name));
  }

  @Override
  public void removeParty(String name)
      throws IllegalArgumentException, IllegalStateException {

    if (this.parties.size() == 0) {
      throw new IllegalStateException("This Manager doesn't have any Parties!");
    }

    if (name == null) {
      throw new IllegalArgumentException("The given String cannot be null.");
    }

    this.parties.removeIf((p) -> p.getName().equalsIgnoreCase(name));

    if (!this.doesPartyExist(this.curParty.getName())) {
      this.resetActiveParty();
    }
  }

  private void resetActiveParty() {
    if (this.parties.size() == 0) {
      this.curParty = null;
      return;
    }

    this.curParty = this.parties.get(0);
  }
}
