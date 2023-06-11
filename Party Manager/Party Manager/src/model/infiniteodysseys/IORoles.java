package model.infiniteodysseys;

/**
 * Represents one of the 7 roles featured in Infinite Odysseys and implements the Roles interface.
 * Every role has 5 bonus stat points that get distributed to a {@code Character}'s stats based on
 * the role. The role also determines the {@code Character}'s defense stat.
 */
public enum IORoles {

  WARRIOR,
  WIZARD,
  BARD,
  ENGINEER,
  ROGUE,
  MONK,
  HUMAN;

  public static String[] getAll() {
    String[] result = new String[IORoles.values().length];

    for (int i = 0; i < result.length; i++) {
      IORoles s = IORoles.values()[i];
      result[i] = s.toString().charAt(0) + s.toString().substring(1).toLowerCase();
    }

    return result;
  }
}
