package model.infiniteodysseys;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import model.Character;
import model.Manager;
import model.Party;
import utils.Clamp;

/**
 * An implementation of the {@code Manager} for Infinite Odysseys.
 */
public class IOManager implements Manager {

  private final List<Party> parties;
  private final List<Character> characters;
  private Party curParty;
  private boolean startedCampaign;

  /**
   * Constructs a new {@code IOManager}.
   */
  public IOManager() {
    this.parties = new ArrayList<Party>();
    this.characters = new ArrayList<Character>();
    this.startedCampaign = false;
  }

  @Override
  public boolean hasStartedACampaign() {
    return this.startedCampaign;
  }

  @Override
  public void startCampaign(boolean start) throws IllegalStateException {
    if ((start) && (this.curParty == null)) {
      throw new IllegalStateException(
          "A active party needs to be set first before starting a campaign.");
    }

    this.startedCampaign = start;
  }

  @Override
  public void setActiveParty(String name)
      throws IllegalStateException, IllegalArgumentException {

    if (this.parties.size() == 0) {
      throw new IllegalStateException("This Manager doesn't have any Parties!");
    }

    if (this.startedCampaign) {
      throw new IllegalStateException("Unable to use this method since a campaign is in progress.");
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

    if (this.startedCampaign) {
      throw new IllegalStateException("Unable to use this method since a campaign is in progress.");
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

    if (this.startedCampaign) {
      throw new IllegalStateException("Unable to use this method since a campaign is in progress.");
    }

    Character[] result = new Character[this.characters.size()];

    for (int i = 0; i < this.characters.size(); i++) {
      result[i] = this.characters.get(i);
    }

    return result;
  }

  @Override
  public String[] getStats() {
    return IOStats.getAll();
  }

  @Override
  public String[] getRoles() {
    return IORoles.getAll();
  }

  @Override
  public void damage(String name, int amount) throws IllegalArgumentException {
    Character c = this.findCharByName(name);

    if (amount < 0) {
      throw new IllegalArgumentException("Damage amount should be a positive number.");
    }

    c.setHP(Clamp.run(c.getHP() - amount, 0, 100));
  }

  @Override
  public void heal(String name, int amount) throws IllegalArgumentException {
    Character c = this.findCharByName(name);

    if (amount < 0) {
      throw new IllegalArgumentException("Heal amount should be a positive number.");
    }

    c.setHP(Clamp.run(c.getHP() + amount, 0, 100));
  }

  @Override
  public boolean doesCharacterExist(String name) throws IllegalArgumentException {
    if (name == null) {
      throw new IllegalArgumentException("The given String cannot be null.");
    }

    for (Character c : this.characters) {
      if (c.getName().equalsIgnoreCase(name)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean doesPartyExist(String name) throws IllegalArgumentException {
    if (name == null) {
      throw new IllegalArgumentException("The given String cannot be null.");
    }

    for (Party p : this.parties) {
      if (p.getName().equalsIgnoreCase(name)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean hasCharacters() {
    return this.characters.size() > 0;
  }

  @Override
  public boolean hasParties() {
    return this.parties.size() > 0;
  }

  @Override
  public Character findCharByName(String name)
      throws IllegalArgumentException, IllegalStateException {
    if (!this.hasCharacters()) {
      throw new IllegalStateException("This Manager doesn't have any Characters!");
    }

    if (name == null) {
      throw new IllegalArgumentException("The given String cannot be null.");
    }

    for (Character c : this.characters) {
      if (c.getName().equalsIgnoreCase(name)) {
        return c;
      }
    }

    throw new IllegalArgumentException("This Manager doesn't have a Character named " + name + ".");
  }

  @Override
  public Party findPartyByName(String name)
      throws IllegalArgumentException, IllegalStateException {
    if (!this.hasParties()) {
      throw new IllegalStateException("This Manager doesn't have any Parties!");
    }

    if (name == null) {
      throw new IllegalArgumentException("The given String cannot be null.");
    }

    for (Party p : this.parties) {
      if (p.getName().equalsIgnoreCase(name)) {
        return p;
      }
    }

    throw new IllegalArgumentException("This Manager doesn't have a Party named " + name + ".");
  }

  @Override
  public void addCharacter(Character c)
      throws IllegalArgumentException, IllegalStateException {

    if (this.startedCampaign) {
      throw new IllegalStateException("Unable to use this method since a campaign is in progress.");
    }

    if (c == null) {
      throw new IllegalArgumentException("Null cannot be added as a Character.");
    }

    if (this.doesCharacterExist(c.getName())) {
      throw new IllegalStateException("Unable to add "
          + c.getName() + " since there's already a Character with that name.");
    }

    try {
      String[] stats = this.getStats();

      for (String s : stats) {
        c.getValueOf(s);
      }
    }
    catch (IllegalArgumentException e) {
      throw new IllegalArgumentException(
          "This Character does not have all of the stats this Manager requires.");
    }

    this.characters.add(c);
  }

  @Override
  public void addParty(String name, Character... c)
      throws IllegalArgumentException, IllegalStateException {

    if (this.startedCampaign) {
      throw new IllegalStateException("Unable to use this method since a campaign is in progress.");
    }

    if (name == null) {
      throw new IllegalArgumentException("The given String cannot be null.");
    }

    if (name.isBlank() || name.isEmpty()) {
      throw new IllegalArgumentException("A Party's name cannot be whitespace.");
    }

    if (this.doesPartyExist(name)) {
      throw new IllegalStateException("Unable to add "
          + name + " since there's already a Party with that name.");
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

    if (this.startedCampaign) {
      throw new IllegalStateException("Unable to use this method since a campaign is in progress.");
    }

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

    this.removeFromAllParties(name);

    this.characters.removeIf((c) -> c.getName().equalsIgnoreCase(name));

  }

  private void removeFromAllParties(String name) {
    List<String> partiesToRemove = new ArrayList<String>();

    for (int p = 0; p < this.parties.size(); p++) {

      Party curParty = this.parties.get(p);

      //ensures that the character we're looking for, exists in the current looped
      //party. If it's not in the current looped party, that party is skipped.
      if (!curParty.hasCharacter(name)) {
        continue;
      }

      Character[] temp = new Character[curParty.size() - 1];

      int counter = 0;

      //loops through every character in the curParty.
      for (int c = 0; c < curParty.getParty().length; c++) {
        Character curChar = curParty.getParty()[c];

        if (curChar.getName().equalsIgnoreCase(name)) {
          if (curParty.size() == 1) {
            partiesToRemove.add(curParty.getName());
            break;
          }
        }
        else {
          temp[counter] = curParty.getParty()[c];
          counter++;
        }
      }

      if (temp.length > 0) {
        this.parties.set(p, new IOParty(curParty.getName(), temp));
      }
    }

    for (String s : partiesToRemove) {
      this.removeParty(s);
    }
  }

  @Override
  public void removeParty(String name)
      throws IllegalArgumentException, IllegalStateException {

    if (this.startedCampaign) {
      throw new IllegalStateException("Unable to use this method since a campaign is in progress.");
    }

    if (this.parties.size() == 0) {
      throw new IllegalStateException("This Manager doesn't have any Parties!");
    }

    if (name == null) {
      throw new IllegalArgumentException("The given String cannot be null.");
    }

    this.parties.removeIf((p) -> p.getName().equalsIgnoreCase(name));

    if ((this.curParty != null) && !this.doesPartyExist(this.curParty.getName())) {
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
