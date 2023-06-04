package model;

/**
 * An interface that represents a player Character in an Infinite Odyssey's campaign.
 */
public interface Character {

   /**
    * Returns the name of this {@code Character}.
    * @return the name of this {@code Character}.
    */
   String getName();

   /**
    * Returns the name of the player who is playing this {@code Character}.
    * @return the name of the player who is playing this {@code Character}.
    */
   String getPlayerName();

   /**
    * Returns the value of the given stat that this {@code Character} has. The String's
    * case should be irrelevant.
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
    * Returns the value of the given stat that this {@code Character} has.
    * @param stat returns the value of the given stat.
    * @return the value of the given stat
    * @throws IllegalArgumentException if the given stat does not exist for this character
    */
   int getRoleValueOf(String stat) throws IllegalArgumentException;

   /**
    * Returns this {@code Role}'s specification.
    * @return this {@code Role}'s specification.
    */
   String getSpecification();

   /**
    * Returns the current health points this {@code Character} has.
    * @return the current health points this {@code Character} has.
    */
   int getHP();

   /**
    * Sets the value of this {@code Character}'s hp to the given integer. If the integer
    * is less than 0, the hp will be set to 0. If the integer is greater than the
    * {@code Character}'s max hp.
    * @param hp the new hp value
    */
   void setHP(int hp);

   /**
    * Returns the maximum health point value this {@code Character} can have.
    * @return the maximum health point value this {@code Character} can have.
    */
   int getMaxHP();

   /**
    * Returns the value of this {@code Character}'s defense.
    * @return the value of this {@code Character}'s defense.
    */
   int getDefense();

}
