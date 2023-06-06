package model.infiniteodysseys;

/**
 * Represents one of the 6 stats featured in Infinite Odysseys and implements the
 * Roles interface. Every role has 5 bonus stat points that get distributed to a
 * {@code Character}'s stats based on the role. The role also determines the
 * {@code Character}'s defense stat.
 */
public enum IOStats {

  HP,
  DEFENSE,
  STRENGTH,
  INTELLIGENCE,
  CREATIVITY,
  CHARISMA,
  STEALTH,
  INTIMIDATION;


  public static String[] getAll() {
    String[] result = new String[IOStats.values().length];

    for (int i = 0; i < result.length; i++) {
      IOStats s = IOStats.values()[i];
      result[i] = s.toString().charAt(0) + s.toString().substring(1).toLowerCase();
    }

    return result;
  }
}
