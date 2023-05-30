package model;

/**
 * An interface that represents a player Character in an Infinite Odyssey's campaign.
 */
public interface Character {

   /**
    * Returns the value of the given stat that this {@code Character} has.
    * @param stat returns the value of the given stat.
    * @return the value of the given stat
    * @throws IllegalArgumentException if the given stat does not exist for this character
    */
   int getValueOf(String stat) throws IllegalArgumentException;

   /**
    * Returns the role of this {@code Character}.
    * @return the role of this {@code Character}.
    */
   String getRole();

   /**
    * Returns the current health points this {@code Character} has.
    * @return the current health points this {@code Character} has.
    */
   int getHP();

   /**
    * Returns the maximum health point value this {@code Character} can have.
    * @return the maximum health point value this {@code Character} can have.
    */
   int getMaxHp();

   /**
    * Returns the value of this {@code Character}'s defense.
    * @return the value of this {@code Character}'s defense.
    */
   int getDefense();

}
