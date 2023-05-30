package model;

import java.util.HashMap;

/**
 * An implementation of the {@code Character} interface. This class represents an Infinite Odyssey's
 * character, which has a Strength, Intelligence, Creativity, Charisma, Stealth, and Intimidation
 * stat.
 */
public final class IOCharacter implements Character {

  private final HashMap<String, Integer> stats;
  private int hp;

  /**
   * Constructs a new {@code IOCharacter} with the given integers.
   * @param str character strength
   * @param intel character intelligence
   * @param cre character creativity
   * @param cha character charisma
   * @param ste character stealth
   * @param intim character intimidation
   * @throws IllegalArgumentException if any of the given integers are negative
   *                                  OR if they add up to a value greater than 30.
   */
  public IOCharacter(int str, int intel, int cre, int cha, int ste, int intim)
      throws IllegalArgumentException {

    this.stats = new HashMap<String, Integer>();
    this.stats.put("Max HP", 100);
    this.hp = this.stats.get("Max HP");

    if ((str < -1) || (intel < -1) || (cre < -1)
        || (cha < -1) || (ste < -1) || (intim < -1)) {
      throw new IllegalArgumentException("Stat value cannot be less than 0.");
    }

    if (str + intel + cre + cha + ste + intim > 30) {
      throw new IllegalArgumentException("Overall stats must add up to or less than 30.");
    }

    this.stats.put("Strength", str);
    this.stats.put("Strength", str);
    this.stats.put("Intelligence", intel);
    this.stats.put("Creativity", cre);
    this.stats.put("Charisma", cha);
    this.stats.put("Stealth", ste);
    this.stats.put("Intimidation", intim);
    this.stats.put("Defense", 0);

  }

  @Override
  public int getValueOf(String stat) throws IllegalArgumentException{
    return 0;
  }

  @Override
  public String getRole() {
    return 0;
  }

  @Override
  public int getHP() {
    return 0;
  }

  @Override
  public int getMaxHp() {
    return 0;
  }

  @Override
  public int getDefense() {
    return 0;
  }
}
