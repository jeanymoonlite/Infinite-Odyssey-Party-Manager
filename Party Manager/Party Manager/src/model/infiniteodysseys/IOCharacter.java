package model.infiniteodysseys;

import java.util.HashMap;
import java.util.Map.Entry;
import model.Character;
import utils.Clamp;

/**
 * An implementation of the {@code Character} interface. This class represents an Infinite Odyssey's
 * character, which has a Strength, Intelligence, Creativity, Charisma, Stealth, and Intimidation
 * stat.
 */
public final class IOCharacter implements Character {

  private final HashMap<String, Integer> stats;
  private int hp;
  private final IORoles role;
  private final String roleSpecification;
  private final String name;
  private final String playerName;

  /**
   * Constructs a new {@code IOCharacter} with the given integers.
   *
   * @param name              character's name
   * @param playerName        the name of the character who's playing
   * @param role              the role of the character
   * @param roleSpecification the specification of the character's role
   * @param str               character strength
   * @param intel             character intelligence
   * @param cre               character creativity
   * @param cha               character charisma
   * @param ste               character stealth
   * @param intim             character intimidation
   * @throws IllegalArgumentException if any of the given integers are negative OR if they add up to
   *                                  a value greater than 30 OR if any of the given Strings are
   *                                  null OR if the name or playerName is whitespace OR if the
   *                                  IORoles is null
   */
  public IOCharacter(String name, String playerName, IORoles role, String roleSpecification,
      int str, int intel, int cre, int cha, int ste, int intim)
      throws IllegalArgumentException {

    if (name == null || playerName == null || role == null || roleSpecification == null) {
      throw new IllegalArgumentException(
          "Character name, Player name, Role, or Role specification cannot be null.");
    }

    if (name.isBlank() || playerName.isBlank()) {
      throw new IllegalArgumentException("Character name or Player name cannot be whitespace.");
    }

    if ((str < -1) || (intel < -1) || (cre < -1)
        || (cha < -1) || (ste < -1) || (intim < -1)) {
      throw new IllegalArgumentException("Stat value cannot be less than 0.");
    }

    if (str + intel + cre + cha + ste + intim > 30) {
      throw new IllegalArgumentException("Overall stats must add up to or less than 30.");
    }

    this.name = name;
    this.playerName = playerName;
    this.role = role;
    this.roleSpecification = roleSpecification;

    this.stats = new HashMap<String, Integer>();
    this.stats.put("Max HP", 100);
    this.hp = this.stats.get("Max HP");

    this.stats.put("Hp", this.hp);
    this.stats.put("Strength", str);
    this.stats.put("Intelligence", intel);
    this.stats.put("Creativity", cre);
    this.stats.put("Charisma", cha);
    this.stats.put("Stealth", ste);
    this.stats.put("Intimidation", intim);
    this.stats.put("Defense", this.getRoleValueOf("Defense"));

  }

  public IOCharacter(int str, int intel, int cre, int cha, int ste, int intim)
      throws IllegalArgumentException {

    this("Test", "Tester", IORoles.HUMAN, "Programmer",
        str, intel, cre, cha, ste, intim);
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public String getPlayerName() {
    return this.playerName;
  }

  @Override
  public int getValueOf(String stat) throws IllegalArgumentException {
    for (Entry<String, Integer> e : this.stats.entrySet()) {
      if (e.getKey().equalsIgnoreCase(stat)) {
        return e.getValue();
      }
    }

    throw new IllegalArgumentException("The stat "
        + stat
        + " does not exist for "
        + this.name + " (" + this.playerName + ").");
  }

  @Override
  public String getRole() {
    return this.role.toString().charAt(0)
        + this.role.toString().substring(1).toLowerCase();
  }

  @Override
  public int getRoleValueOf(String stat) throws IllegalArgumentException {
    HashMap<String, Integer> temp = this.getRoleStats();

    for (Entry<String, Integer> e : temp.entrySet()) {
      if (e.getKey().equalsIgnoreCase(stat)) {
        return e.getValue();
      }
    }

    throw new IllegalArgumentException("The stat "
        + stat
        + " does not exist for the "
        + this.getRole() + " role.");
  }

  private HashMap<String, Integer> getRoleStats() {
    HashMap<String, Integer> temp = new HashMap<String, Integer>();

    temp.put("Defense", 0);
    temp.put("Strength", 0);
    temp.put("Intelligence", 0);
    temp.put("Creativity", 0);
    temp.put("Charisma", 0);
    temp.put("Stealth", 0);
    temp.put("Intimidation", 0);
    switch (this.role) {
      case WARRIOR:
        temp.put("Defense", 5);
        break;
      case WIZARD:
        temp.put("Creativity", 5);
        break;
      case BARD:
        temp.put("Charisma", 4);
        temp.put("Defense", 1);
        break;
      case ENGINEER:
        temp.put("Intelligence", 3);
        temp.put("Creativity", 2);
        break;
      case ROGUE:
        temp.put("Stealth", 3);
        temp.put("Strength", 2);
        temp.put("Defense", -5);
        break;
      case MONK:
        temp.put("Charisma", 3);
        temp.put("Defense", 2);
        break;
      case HUMAN:
        temp.put("Strength", 1);
        temp.put("Intelligence", 1);
        temp.put("Creativity", 1);
        temp.put("Charisma", 1);
        temp.put("Stealth", 1);
        temp.put("Intimidation", 1);
        break;
    }
    return temp;
  }

  @Override
  public String getSpecification() {
    return this.roleSpecification;
  }

  @Override
  public int getHP() {
    return this.hp;
  }

  @Override
  public void setHP(int hp) {
    this.hp = Clamp.run(hp, 0, this.getMaxHP());
  }

  @Override
  public int getMaxHP() {
    return this.stats.get("Max HP");
  }

  @Override
  public String toString() {
    return this.name + " (" + this.playerName + ")";
  }

  @Override
  public String toStringAll() {
    String role = "Role: " + this.getRole();
    String roleSpecification = (this.getSpecification().isBlank()) ?
        "" : " (" + this.getSpecification() + ")";

    String health = "Hp: " + this.getHP() + "/" + this.getMaxHP();

    String defenseSign = (this.getValueOf("Defense") >= 0) ? "+" : "-";
    String defense = (this.getValueOf("Defense") == 0) ?
        "" : " (" + defenseSign + Math.abs(this.getValueOf("Defense")) + " Def)";
    String stats = "";

    String[] statsArray = new String[IOStats.getAll().length - 2];
    System.arraycopy(IOStats.getAll(), 2,
        statsArray, 0, statsArray.length);

    for (String s : statsArray) {
      stats = stats.concat(s + ": " + this.getValueOf(s));

      int roleValue = this.getRoleValueOf(s);

      if (roleValue != 0) {
        String roleSign = (roleValue >= 0) ? "+" : "-";

        stats = stats.concat(" (" + roleSign + Math.abs(roleValue) + ")");
      }
      stats = stats.concat("\n");
    }

    return this.toString() + "\n"
        + role + roleSpecification + "\n"
        + health + defense + "\n"
        + stats;
  }

}
