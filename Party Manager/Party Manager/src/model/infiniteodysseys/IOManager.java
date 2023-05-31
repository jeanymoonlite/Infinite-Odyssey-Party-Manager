package model.infiniteodysseys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import model.Character;
import model.Manager;
import model.Party;

/**
 * An implementation of the {@code Manager} for Infinite Odysseys.
 */
public class IOManager implements Manager {

  private final HashMap<String, Party> parties;
  private final List<Character> characters;

  /**
   * Constructs a new {@code IOManager}.
   */
  public IOManager() {
    this.parties = new HashMap<String, Party>();
    this.characters = new ArrayList<Character>();
  }

  @Override
  public void setActiveParty(String name) throws IllegalStateException, IllegalArgumentException {

  }

  @Override
  public Party getActiveParty() throws IllegalStateException {
    return null;
  }

  @Override
  public Party[] getAllParties() throws IllegalStateException {
    return new Party[0];
  }

  @Override
  public Character[] getAllCharacters() throws IllegalStateException {
    return new Character[0];
  }

  @Override
  public boolean doesCharacterExist(String name) {
    return false;
  }

  @Override
  public boolean doesPartyExist(String name) {
    return false;
  }

  @Override
  public void addCharacter(Character c) throws IllegalArgumentException {

  }

  @Override
  public void addParty(String name, Character... c) throws IllegalArgumentException {

  }

  @Override
  public void removeCharacter(String name)
      throws IllegalArgumentException, IllegalStateException {

  }

  @Override
  public void removeParty(String name)
      throws IllegalArgumentException, IllegalStateException {

  }
}
