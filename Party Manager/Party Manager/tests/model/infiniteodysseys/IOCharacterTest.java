package model.infiniteodysseys;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import model.Character;
import org.junit.Before;
import org.junit.Test;

public class IOCharacterTest {

  protected Character luna;
  protected Character rose;
  protected Character bryan;
  protected Character jake;
  protected Character sarah;
  protected Character dre;
  protected Character steven;

  @Before
  public void init() {
    this.jake = new IOCharacter("Jake Walker", "Jacob",
        IORoles.WARRIOR, "Soldier",
        12, 5, 1, 1, 4, 7);

    this.luna = new IOCharacter("Lunarose", "Luna",
        IORoles.WIZARD, "Fire Mage",
        5, 5, 10, 5, 0, 0);

    this.dre = new IOCharacter("Dre", "Andre",
        IORoles.BARD, "DJ",
        2, 5, 5, 15, 3, 0);

    this.bryan = new IOCharacter("Manuel", "Bryan",
        IORoles.ENGINEER, "Mechanic",
        9, 6, 1, 3, 4, 7);

    this.rose = new IOCharacter("Rose Walker", "Day",
        IORoles.ROGUE, "Assassin",
        12, 5, 1, 1, 7, 4);

    this.sarah = new IOCharacter("Mazikeen", "Sarah",
        IORoles.MONK, "",
        6, 2, 3, 10, 4, 5);

    this.steven = new IOCharacter("Onion", "Steven",
        IORoles.HUMAN, "",
        1, 1, 1, 1, 1, 1);
  }

  @Test
  public void invalidConstructor() {
    try {
      new IOCharacter(-1, -2, -3, -4, -5, -6);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("Stat value cannot be less than 0.", e.getMessage());
    }

    try {
      new IOCharacter(5, -2, -3, -4, -5, -6);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("Stat value cannot be less than 0.", e.getMessage());
    }

    try {
      new IOCharacter(5, 2, -3, -4, -5, -6);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("Stat value cannot be less than 0.", e.getMessage());
    }

    try {
      new IOCharacter(5, 2, 3, -4, -5, -6);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("Stat value cannot be less than 0.", e.getMessage());
    }

    try {
      new IOCharacter(5, 2, 3, 4, -5, -6);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("Stat value cannot be less than 0.", e.getMessage());
    }

    try {
      new IOCharacter(5, 2, 3, 4, 5, -6);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("Stat value cannot be less than 0.", e.getMessage());
    }

    try {
      new IOCharacter(5, 6, 6, 6, 5, 6);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("Overall stats must add up to or less than 30.", e.getMessage());
    }

    try {
      new IOCharacter(null, null, null, null, 5, 6, 6, 6, 5, 6);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("Character name, Player name, Role, or Role specification cannot be null.",
          e.getMessage());
    }

    try {
      new IOCharacter("Luna", null, null, null, 5, 6, 6, 6, 5, 6);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("Character name, Player name, Role, or Role specification cannot be null.",
          e.getMessage());
    }

    try {
      new IOCharacter("Luna", "Jean", null, null, 5, 6, 6, 6, 5, 6);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("Character name, Player name, Role, or Role specification cannot be null.",
          e.getMessage());
    }

    try {
      new IOCharacter("Luna", "Jean", IORoles.WIZARD, null, 5, 6, 6, 6, 5, 6);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("Character name, Player name, Role, or Role specification cannot be null.",
          e.getMessage());
    }

    try {
      new IOCharacter(" ", "Jean", IORoles.WIZARD, "The Human", 5, 6, 6, 6, 5, 6);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("Character name or Player name cannot be whitespace.", e.getMessage());
    }

    try {
      new IOCharacter(" ", " ", IORoles.WIZARD, "The Human", 5, 6, 6, 6, 5, 6);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("Character name or Player name cannot be whitespace.", e.getMessage());
    }
  }

  @Test
  public void getName() {
    assertEquals("Jake Walker", this.jake.getName());
    assertEquals("Lunarose", this.luna.getName());
    assertEquals("Dre", this.dre.getName());
    assertEquals("Manuel", this.bryan.getName());
    assertEquals("Rose Walker", this.rose.getName());
    assertEquals("Mazikeen", this.sarah.getName());
    assertEquals("Onion", this.steven.getName());
  }

  @Test
  public void getPlayerName() {
    assertEquals("Jacob", this.jake.getPlayerName());
    assertEquals("Luna", this.luna.getPlayerName());
    assertEquals("Andre", this.dre.getPlayerName());
    assertEquals("Bryan", this.bryan.getPlayerName());
    assertEquals("Day", this.rose.getPlayerName());
    assertEquals("Sarah", this.sarah.getPlayerName());
    assertEquals("Steven", this.steven.getPlayerName());
  }

  @Test
  public void getValueOf() {
    try {
      this.jake.getValueOf("Special");
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("The stat Special does not exist for Jake Walker (Jacob).",
          e.getMessage());
    }

    try {
      this.sarah.getValueOf("Ether");
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("The stat Ether does not exist for Mazikeen (Sarah).",
          e.getMessage());
    }

    assertEquals(12, this.jake.getValueOf("Strength"));
    assertEquals(12, this.jake.getValueOf("strength"));

    assertEquals(6, this.bryan.getValueOf("Intelligence"));
    assertEquals(6, this.bryan.getValueOf("intelligence"));

    assertEquals(10, this.luna.getValueOf("Creativity"));
    assertEquals(10, this.luna.getValueOf("creativity"));

    assertEquals(15, this.dre.getValueOf("Charisma"));
    assertEquals(15, this.dre.getValueOf("charisma"));

    assertEquals(7, this.rose.getValueOf("Stealth"));
    assertEquals(7, this.rose.getValueOf("stealth"));

    assertEquals(5, this.sarah.getValueOf("Intimidation"));
    assertEquals(5, this.sarah.getValueOf("Intimidation"));
  }

  @Test
  public void getRole() {
    assertEquals("Warrior", this.jake.getRole());
    assertEquals("Wizard", this.luna.getRole());
    assertEquals("Bard", this.dre.getRole());
    assertEquals("Engineer", this.bryan.getRole());
    assertEquals("Rogue", this.rose.getRole());
    assertEquals("Monk", this.sarah.getRole());
    assertEquals("Human", this.steven.getRole());
  }

  @Test
  public void getRoleValueException() {
    try {
      this.jake.getRoleValueOf("Special");
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("The stat Special does not exist for the Warrior role.",
          e.getMessage());
    }

    try {
      this.luna.getRoleValueOf("Ether");
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("The stat Ether does not exist for the Wizard role.",
          e.getMessage());
    }
  }

  @Test
  public void getRoleValueWarrior() {
    assertEquals(5, this.jake.getRoleValueOf("Defense"));
    assertEquals(5, this.jake.getRoleValueOf("defense"));

    assertEquals(0, this.jake.getRoleValueOf("Strength"));
    assertEquals(0, this.jake.getRoleValueOf("strength"));

    assertEquals(0, this.jake.getRoleValueOf("Intelligence"));
    assertEquals(0, this.jake.getRoleValueOf("intelligence"));

    assertEquals(0, this.jake.getRoleValueOf("Creativity"));
    assertEquals(0, this.jake.getRoleValueOf("creativity"));

    assertEquals(0, this.jake.getRoleValueOf("Charisma"));
    assertEquals(0, this.jake.getRoleValueOf("charisma"));

    assertEquals(0, this.jake.getRoleValueOf("Stealth"));
    assertEquals(0, this.jake.getRoleValueOf("stealth"));

    assertEquals(0, this.jake.getRoleValueOf("Intimidation"));
    assertEquals(0, this.jake.getRoleValueOf("Intimidation"));
  }

  @Test
  public void getRoleValueWizard() {
    assertEquals(0, this.luna.getRoleValueOf("Defense"));
    assertEquals(0, this.luna.getRoleValueOf("defense"));

    assertEquals(0, this.luna.getRoleValueOf("Strength"));
    assertEquals(0, this.luna.getRoleValueOf("strength"));

    assertEquals(0, this.luna.getRoleValueOf("Intelligence"));
    assertEquals(0, this.luna.getRoleValueOf("intelligence"));

    assertEquals(5, this.luna.getRoleValueOf("Creativity"));
    assertEquals(5, this.luna.getRoleValueOf("creativity"));

    assertEquals(0, this.luna.getRoleValueOf("Charisma"));
    assertEquals(0, this.luna.getRoleValueOf("charisma"));

    assertEquals(0, this.luna.getRoleValueOf("Stealth"));
    assertEquals(0, this.luna.getRoleValueOf("stealth"));

    assertEquals(0, this.luna.getRoleValueOf("Intimidation"));
    assertEquals(0, this.luna.getRoleValueOf("Intimidation"));
  }

  @Test
  public void getRoleValueBard() {
    assertEquals(1, this.dre.getRoleValueOf("Defense"));
    assertEquals(1, this.dre.getRoleValueOf("defense"));

    assertEquals(0, this.dre.getRoleValueOf("Strength"));
    assertEquals(0, this.dre.getRoleValueOf("strength"));

    assertEquals(0, this.dre.getRoleValueOf("Intelligence"));
    assertEquals(0, this.dre.getRoleValueOf("intelligence"));

    assertEquals(0, this.dre.getRoleValueOf("Creativity"));
    assertEquals(0, this.dre.getRoleValueOf("creativity"));

    assertEquals(4, this.dre.getRoleValueOf("Charisma"));
    assertEquals(4, this.dre.getRoleValueOf("charisma"));

    assertEquals(0, this.dre.getRoleValueOf("Stealth"));
    assertEquals(0, this.dre.getRoleValueOf("stealth"));

    assertEquals(0, this.dre.getRoleValueOf("Intimidation"));
    assertEquals(0, this.dre.getRoleValueOf("Intimidation"));
  }

  @Test
  public void getRoleValueEngineer() {
    assertEquals(0, this.bryan.getRoleValueOf("Defense"));
    assertEquals(0, this.bryan.getRoleValueOf("defense"));

    assertEquals(0, this.bryan.getRoleValueOf("Strength"));
    assertEquals(0, this.bryan.getRoleValueOf("strength"));

    assertEquals(3, this.bryan.getRoleValueOf("Intelligence"));
    assertEquals(3, this.bryan.getRoleValueOf("intelligence"));

    assertEquals(2, this.bryan.getRoleValueOf("Creativity"));
    assertEquals(2, this.bryan.getRoleValueOf("creativity"));

    assertEquals(0, this.bryan.getRoleValueOf("Charisma"));
    assertEquals(0, this.bryan.getRoleValueOf("charisma"));

    assertEquals(0, this.bryan.getRoleValueOf("Stealth"));
    assertEquals(0, this.bryan.getRoleValueOf("stealth"));

    assertEquals(0, this.bryan.getRoleValueOf("Intimidation"));
    assertEquals(0, this.bryan.getRoleValueOf("Intimidation"));
  }

  @Test
  public void getRoleValueRogue() {
    assertEquals(-5, this.rose.getRoleValueOf("Defense"));
    assertEquals(-5, this.rose.getRoleValueOf("defense"));

    assertEquals(2, this.rose.getRoleValueOf("Strength"));
    assertEquals(2, this.rose.getRoleValueOf("strength"));

    assertEquals(0, this.rose.getRoleValueOf("Intelligence"));
    assertEquals(0, this.rose.getRoleValueOf("intelligence"));

    assertEquals(0, this.rose.getRoleValueOf("Creativity"));
    assertEquals(0, this.rose.getRoleValueOf("creativity"));

    assertEquals(0, this.rose.getRoleValueOf("Charisma"));
    assertEquals(0, this.rose.getRoleValueOf("charisma"));

    assertEquals(3, this.rose.getRoleValueOf("Stealth"));
    assertEquals(3, this.rose.getRoleValueOf("stealth"));

    assertEquals(0, this.rose.getRoleValueOf("Intimidation"));
    assertEquals(0, this.rose.getRoleValueOf("Intimidation"));
  }

  @Test
  public void getRoleValueMonk() {
    assertEquals(2, this.sarah.getRoleValueOf("Defense"));
    assertEquals(2, this.sarah.getRoleValueOf("defense"));

    assertEquals(0, this.sarah.getRoleValueOf("Strength"));
    assertEquals(0, this.sarah.getRoleValueOf("strength"));

    assertEquals(0, this.sarah.getRoleValueOf("Intelligence"));
    assertEquals(0, this.sarah.getRoleValueOf("intelligence"));

    assertEquals(0, this.sarah.getRoleValueOf("Creativity"));
    assertEquals(0, this.sarah.getRoleValueOf("creativity"));

    assertEquals(3, this.sarah.getRoleValueOf("Charisma"));
    assertEquals(3, this.sarah.getRoleValueOf("charisma"));

    assertEquals(0, this.sarah.getRoleValueOf("Stealth"));
    assertEquals(0, this.sarah.getRoleValueOf("stealth"));

    assertEquals(0, this.sarah.getRoleValueOf("Intimidation"));
    assertEquals(0, this.sarah.getRoleValueOf("Intimidation"));
  }

  @Test
  public void getRoleValueHuman() {
    assertEquals(0, this.steven.getRoleValueOf("Defense"));
    assertEquals(0, this.steven.getRoleValueOf("defense"));

    assertEquals(1, this.steven.getRoleValueOf("Strength"));
    assertEquals(1, this.steven.getRoleValueOf("strength"));

    assertEquals(1, this.steven.getRoleValueOf("Intelligence"));
    assertEquals(1, this.steven.getRoleValueOf("intelligence"));

    assertEquals(1, this.steven.getRoleValueOf("Creativity"));
    assertEquals(1, this.steven.getRoleValueOf("creativity"));

    assertEquals(1, this.steven.getRoleValueOf("Charisma"));
    assertEquals(1, this.steven.getRoleValueOf("charisma"));

    assertEquals(1, this.steven.getRoleValueOf("Stealth"));
    assertEquals(1, this.steven.getRoleValueOf("stealth"));

    assertEquals(1, this.steven.getRoleValueOf("Intimidation"));
    assertEquals(1, this.steven.getRoleValueOf("Intimidation"));
  }

  @Test
  public void getSpecification() {
    assertEquals("Soldier", this.jake.getSpecification());
    assertEquals("Fire Mage", this.luna.getSpecification());
    assertEquals("DJ", this.dre.getSpecification());
    assertEquals("Mechanic", this.bryan.getSpecification());
    assertEquals("Assassin", this.rose.getSpecification());
    assertEquals("", this.sarah.getSpecification());
    assertEquals("", this.steven.getSpecification());
  }

  @Test
  public void getAndSetHP() {
    assertEquals(100, this.jake.getHP());
    assertEquals(100, this.luna.getHP());
    assertEquals(100, this.dre.getHP());
    assertEquals(100, this.bryan.getHP());
    assertEquals(100, this.rose.getHP());
    assertEquals(100, this.sarah.getHP());
    assertEquals(100, this.steven.getHP());

    int[] hps = new int[]{92, 57, 18, 99, 7, 33, 41};
    Character[] c = new Character[]{this.jake, this.luna, this.dre,
        this.bryan, this.rose, this.sarah, this.steven};

    for (int i = 0; i < c.length; i++) {
      c[i].setHP(hps[i]);
    }

    assertEquals(92, this.jake.getHP());
    assertEquals(57, this.luna.getHP());
    assertEquals(18, this.dre.getHP());
    assertEquals(99, this.bryan.getHP());
    assertEquals(7, this.rose.getHP());
    assertEquals(33, this.sarah.getHP());
    assertEquals(41, this.steven.getHP());

    this.jake.setHP(10000);
    this.luna.setHP(-10000);
    assertEquals(100, this.jake.getHP());
    assertEquals(0, this.luna.getHP());
  }

  @Test
  public void getMaxHp() {
    assertEquals(100, this.jake.getMaxHP());
    assertEquals(100, this.luna.getMaxHP());
    assertEquals(100, this.dre.getMaxHP());
    assertEquals(100, this.bryan.getMaxHP());
    assertEquals(100, this.rose.getMaxHP());
    assertEquals(100, this.sarah.getMaxHP());
    assertEquals(100, this.steven.getMaxHP());
  }

  @Test
  public void testToString() {
    assertEquals("Jake Walker (Jacob)", this.jake.toString());
    assertEquals("Lunarose (Luna)", this.luna.toString());
    assertEquals("Dre (Andre)", this.dre.toString());
    assertEquals("Manuel (Bryan)", this.bryan.toString());
    assertEquals("Rose Walker (Day)", this.rose.toString());
    assertEquals("Mazikeen (Sarah)", this.sarah.toString());
    assertEquals("Onion (Steven)", this.steven.toString());
  }

  @Test
  public void testToStringAll() {
    assertEquals("Jake Walker (Jacob)\n"
        + "Class: Warrior (Soldier)\n"
        + "Hp: 100/100 (+5 Def)\n"
        + "Strength: 12\n"
        + "Intelligence: 5\n"
        + "Creativity: 1\n"
        + "Charisma: 1\n"
        + "Stealth: 4\n"
        + "Intimidation: 7\n", this.jake.toStringAll());
    assertEquals("Lunarose (Luna)\n"
        + "Class: Wizard (Fire Mage)\n"
        + "Hp: 100/100\n"
        + "Strength: 5\n"
        + "Intelligence: 5\n"
        + "Creativity: 10 (+5)\n"
        + "Charisma: 5\n"
        + "Stealth: 0\n"
        + "Intimidation: 0\n", this.luna.toStringAll());
    assertEquals("Dre (Andre)\n"
        + "Class: Bard (DJ)\n"
        + "Hp: 100/100 (+1 Def)\n"
        + "Strength: 2\n"
        + "Intelligence: 5\n"
        + "Creativity: 5\n"
        + "Charisma: 15 (+4)\n"
        + "Stealth: 3\n"
        + "Intimidation: 0\n", this.dre.toStringAll());
    assertEquals("Manuel (Bryan)\n"
        + "Class: Engineer (Mechanic)\n"
        + "Hp: 100/100\n"
        + "Strength: 9\n"
        + "Intelligence: 6 (+3)\n"
        + "Creativity: 1 (+2)\n"
        + "Charisma: 3\n"
        + "Stealth: 4\n"
        + "Intimidation: 7\n", this.bryan.toStringAll());
    assertEquals("Rose Walker (Day)\n"
        + "Class: Rogue (Assassin)\n"
        + "Hp: 100/100 (-5 Def)\n"
        + "Strength: 12 (+2)\n"
        + "Intelligence: 5\n"
        + "Creativity: 1\n"
        + "Charisma: 1\n"
        + "Stealth: 7 (+3)\n"
        + "Intimidation: 4\n", this.rose.toStringAll());
    assertEquals("Mazikeen (Sarah)\n"
        + "Class: Monk\n"
        + "Hp: 100/100 (+2 Def)\n"
        + "Strength: 6\n"
        + "Intelligence: 2\n"
        + "Creativity: 3\n"
        + "Charisma: 10 (+3)\n"
        + "Stealth: 4\n"
        + "Intimidation: 5\n", this.sarah.toStringAll());
    assertEquals("Onion (Steven)\n"
        + "Class: Human\n"
        + "Hp: 100/100\n"
        + "Strength: 1 (+1)\n"
        + "Intelligence: 1 (+1)\n"
        + "Creativity: 1 (+1)\n"
        + "Charisma: 1 (+1)\n"
        + "Stealth: 1 (+1)\n"
        + "Intimidation: 1 (+1)\n", this.steven.toStringAll());
  }
}