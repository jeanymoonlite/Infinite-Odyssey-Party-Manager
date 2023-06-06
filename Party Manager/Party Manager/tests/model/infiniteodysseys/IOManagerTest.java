package model.infiniteodysseys;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import model.Character;
import model.Manager;
import org.junit.Before;
import org.junit.Test;

public class IOManagerTest extends IOPartyTest {

  private Manager m;

  @Before
  public void initManager() {
    this.initParty();
    this.m = new IOManager();
  }

  private void addCharsAndParties() {
    this.m.addCharacter(this.luna);
    this.m.addCharacter(this.jake);
    this.m.addCharacter(this.bryan);
    this.m.addCharacter(this.rose);
    this.m.addCharacter(this.steven);

    this.m.addParty("The Boys", this.luna, this.jake, this.bryan, this.steven);
    this.m.addParty("The Infinite Odyssey", this.luna, this.rose);
  }

  protected void addCharsAndParties(Manager m) {
    m.addCharacter(this.luna);
    m.addCharacter(this.jake);
    m.addCharacter(this.bryan);
    m.addCharacter(this.rose);
    m.addCharacter(this.steven);

    m.addParty("The Boys", this.luna, this.jake, this.bryan, this.steven);
    m.addParty("The Infinite Odyssey", this.luna, this.rose);
    m.addParty("Future", this.luna, this.dre, this.steven, this.rose);
  }

  @Test
  public void startCampaignAndHasStartedCampaign() {
    this.addCharsAndParties();
    assertNull(this.m.getActiveParty());

    assertFalse(this.m.hasStartedACampaign());
    try {
      this.m.startCampaign(true);
      fail();
    }
    catch (IllegalStateException e) {
      assertEquals("A active party needs to be set first before starting a campaign.", e.getMessage());
    }

    this.m.setActiveParty("The Boys");
    assertEquals("The Boys", this.m.getActiveParty().getName());

    this.m.startCampaign(true);

    assertTrue(this.m.hasStartedACampaign());

    this.m.startCampaign(false);
    assertFalse(this.m.hasStartedACampaign());
  }

  @Test
  public void getAndSetActiveParty() {
    try {
      this.m.setActiveParty(null);
      fail();
    }
    catch (IllegalStateException e) {
      assertEquals("This Manager doesn't have any Parties!", e.getMessage());
    }

    try {
      this.m.getActiveParty();
      fail();
    }
    catch (IllegalStateException e) {
      assertEquals("This Manager doesn't have any Parties!", e.getMessage());
    }

    this.addCharsAndParties();

    try {
      this.m.setActiveParty(null);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("The given String cannot be null.", e.getMessage());
    }

    try {
      this.m.setActiveParty("");
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("The party  does not exist.", e.getMessage());
    }

    try {
      this.m.setActiveParty("stuff");
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("The party stuff does not exist.", e.getMessage());
    }

    try {
      this.m.setActiveParty("Le Boys");
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("The party Le Boys does not exist.", e.getMessage());
    }

    this.m.setActiveParty("The Boys");

    assertEquals("The Boys", this.m.getActiveParty().getName());

    this.m.setActiveParty("The Infinite Odyssey");

    assertEquals("The Infinite Odyssey", this.m.getActiveParty().getName());

    this.m.startCampaign(true);

    try {
      this.m.setActiveParty("Le Boys");
      fail();
    }
    catch (IllegalStateException e) {
      assertEquals("Unable to use this method since a campaign is in progress.", e.getMessage());
    }
  }

  @Test
  public void getAllParties() {
    try {
      this.m.getAllParties();
      fail();
    }
    catch (IllegalStateException e) {
      assertEquals("This Manager doesn't have any Parties!", e.getMessage());
    }

    this.addCharsAndParties();

    assertEquals(4, this.m.getAllParties()[0].size());
    assertEquals(2, this.m.getAllParties()[1].size());

    this.m.setActiveParty("The Infinite Odyssey");
    this.m.startCampaign(true);

    try {
      this.m.setActiveParty("Le Boys");
      fail();
    }
    catch (IllegalStateException e) {
      assertEquals("Unable to use this method since a campaign is in progress.", e.getMessage());
    }
  }

  @Test
  public void getAllCharacters() {
    try {
      this.m.getAllCharacters();
      fail();
    }
    catch (IllegalStateException e) {
      assertEquals("This Manager doesn't have any Characters!", e.getMessage());
    }

    this.addCharsAndParties();

    assertEquals(this.luna, this.m.getAllCharacters()[0]);
    assertEquals(this.jake, this.m.getAllCharacters()[1]);
    assertEquals(this.bryan, this.m.getAllCharacters()[2]);
    assertEquals(this.rose, this.m.getAllCharacters()[3]);
    assertEquals(this.steven, this.m.getAllCharacters()[4]);

    this.m.setActiveParty("The Infinite Odyssey");
    this.m.startCampaign(true);

    try {
      this.m.setActiveParty("Le Boys");
      fail();
    }
    catch (IllegalStateException e) {
      assertEquals("Unable to use this method since a campaign is in progress.", e.getMessage());
    }
  }

  @Test
  public void getStats() {
    assertEquals("Hp", this.m.getStats()[0]);
    assertEquals("Defense", this.m.getStats()[1]);
    assertEquals("Strength", this.m.getStats()[2]);
    assertEquals("Intelligence", this.m.getStats()[3]);
    assertEquals("Creativity", this.m.getStats()[4]);
    assertEquals("Charisma", this.m.getStats()[5]);
    assertEquals("Stealth", this.m.getStats()[6]);
    assertEquals("Intimidation", this.m.getStats()[7]);
  }

  @Test
  public void getRoles() {
    assertEquals("Warrior", this.m.getRoles()[0]);
    assertEquals("Wizard", this.m.getRoles()[1]);
    assertEquals("Bard", this.m.getRoles()[2]);
    assertEquals("Engineer", this.m.getRoles()[3]);
    assertEquals("Rogue", this.m.getRoles()[4]);
    assertEquals("Monk", this.m.getRoles()[5]);
    assertEquals("Human", this.m.getRoles()[6]);
  }

  @Test
  public void findCharByName() {
    try {
      this.m.findCharByName("");
      fail();
    }
    catch (IllegalStateException e) {
      assertEquals("This Manager doesn't have any Characters!", e.getMessage());
    }

    this.addCharsAndParties();

    try {
      this.m.findCharByName(null);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("The given String cannot be null.", e.getMessage());
    }

    try {
      this.m.findCharByName("null");
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("This Manager doesn't have a Character named null.", e.getMessage());
    }
  }

  @Test
  public void findPartyByName() {
    try {
      this.m.findPartyByName("");
      fail();
    }
    catch (IllegalStateException e) {
      assertEquals("This Manager doesn't have any Parties!", e.getMessage());
    }

    this.addCharsAndParties();

    try {
      this.m.findPartyByName(null);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("The given String cannot be null.", e.getMessage());
    }

    try {
      this.m.findPartyByName("null");
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("This Manager doesn't have a Party named null.", e.getMessage());
    }
  }

  @Test
  public void doesCharacterExist() {
    try {
      this.m.doesCharacterExist(null);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("The given String cannot be null.", e.getMessage());
    }

    assertFalse(this.m.doesCharacterExist("Lunarose"));
    assertFalse(this.m.doesCharacterExist("Jake Walker"));
    assertFalse(this.m.doesCharacterExist("Manuel"));
    assertFalse(this.m.doesCharacterExist("Rose Walker"));

    this.addCharsAndParties();

    assertTrue(this.m.doesCharacterExist("Lunarose"));
    assertTrue(this.m.doesCharacterExist("Jake Walker"));
    assertTrue(this.m.doesCharacterExist("Manuel"));
    assertTrue(this.m.doesCharacterExist("Rose Walker"));
  }

  @Test
  public void doesPartyExist() {
    try {
      this.m.doesPartyExist(null);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("The given String cannot be null.", e.getMessage());
    }

    assertFalse(this.m.doesPartyExist("The Boys"));
    assertFalse(this.m.doesPartyExist("The Infinite Odyssey"));

    this.addCharsAndParties();

    assertTrue(this.m.doesCharacterExist("Lunarose"));
    assertTrue(this.m.doesCharacterExist("Jake Walker"));
    assertTrue(this.m.doesCharacterExist("Manuel"));
    assertTrue(this.m.doesCharacterExist("Rose Walker"));
    assertTrue(this.m.doesCharacterExist("Onion"));

    assertTrue(this.m.doesPartyExist("The Boys"));
    assertTrue(this.m.doesPartyExist("The Infinite Odyssey"));
  }

  @Test
  public void addCharacter() {
    try {
      this.m.addCharacter(null);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("Null cannot be added as a Character.", e.getMessage());
    }

    assertFalse(this.m.doesCharacterExist("Lunarose"));
    this.m.addCharacter(this.luna);
    assertTrue(this.m.doesCharacterExist("Lunarose"));

    Character c = new IOCharacter("Lunarose", "Jean", IORoles.ROGUE, "",
        0, 0, 0, 0, 0, 0);

    try {
      this.m.addCharacter(c);
      fail();
    }
    catch (IllegalStateException e) {
      assertEquals("Unable to add Lunarose since there's already a Character with that name.",
          e.getMessage());
    }

    this.initManager();
    this.addCharsAndParties();
    this.m.setActiveParty("The Infinite Odyssey");
    this.m.startCampaign(true);

    try {
      this.m.setActiveParty("Le Boys");
      fail();
    }
    catch (IllegalStateException e) {
      assertEquals("Unable to use this method since a campaign is in progress.", e.getMessage());
    }
    this.m.startCampaign(false);

    try {
      this.m.addCharacter(new MockNonIOCharacter());
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("This Character does not have all of the stats this Manager requires.",
          e.getMessage());
    }
  }

  @Test
  public void addParty() {
    try {
      this.m.addParty(null);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("The given String cannot be null.", e.getMessage());
    }

    try {
      this.m.addParty("");
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("A Party's name cannot be whitespace.", e.getMessage());
    }

    try {
      this.m.addParty(" ");
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("A Party's name cannot be whitespace.", e.getMessage());
    }

    try {
      this.m.addParty("\n");
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("A Party's name cannot be whitespace.", e.getMessage());
    }

    try {
      this.m.addParty("The Boys");
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("A Party must have at least one Character.", e.getMessage());
    }

    try {
      this.m.addParty("The Boys", null);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("A Party must have at least one Character.", e.getMessage());
    }

    try {
      this.m.addParty("The Boys", null, this.luna);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("None of a Party's Characters can be null.", e.getMessage());
    }

    try {
      this.m.addParty("The Boys", this.luna, this.jake, null, this.bryan);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("None of a Party's Characters can be null.", e.getMessage());
    }

    assertFalse(this.m.doesCharacterExist("Lunarose"));
    assertFalse(this.m.doesCharacterExist("Jake Walker"));
    assertFalse(this.m.doesCharacterExist("Manuel"));
    assertFalse(this.m.doesCharacterExist("Onion"));

    this.m.addParty("The Boys", this.luna, this.jake, this.bryan, this.steven);
    this.m.setActiveParty("The Boys");

    assertTrue(this.m.doesCharacterExist("Lunarose"));
    assertTrue(this.m.doesCharacterExist("Jake Walker"));
    assertTrue(this.m.doesCharacterExist("Manuel"));
    assertTrue(this.m.doesCharacterExist("Onion"));

    assertEquals(4, this.m.getActiveParty().size());
    assertEquals(this.luna, this.m.getActiveParty().getParty()[0]);
    assertEquals(this.jake, this.m.getActiveParty().getParty()[1]);
    assertEquals(this.bryan, this.m.getActiveParty().getParty()[2]);
    assertEquals(this.steven, this.m.getActiveParty().getParty()[3]);

    try {
      this.m.addParty("The Boys", this.luna, this.jake);
      fail();
    }
    catch (IllegalStateException e) {
      assertEquals("Unable to add The Boys since there's already a Party with that name.",
          e.getMessage());
    }

    this.initManager();
    this.addCharsAndParties();
    this.m.setActiveParty("The Infinite Odyssey");
    this.m.startCampaign(true);

    try {
      this.m.setActiveParty("Le Boys");
      fail();
    }
    catch (IllegalStateException e) {
      assertEquals("Unable to use this method since a campaign is in progress.", e.getMessage());
    }
  }

  @Test
  public void removeCharacter() {
    try {
      this.m.removeCharacter(null);
      fail();
    }
    catch (IllegalStateException e) {
      assertEquals("This Manager doesn't have any Characters!", e.getMessage());
    }

    this.m.addCharacter(this.luna);
    assertTrue(this.m.doesCharacterExist("Lunarose"));
    this.m.removeCharacter("Lunarose");
    assertFalse(this.m.doesCharacterExist("Lunarose"));

    this.addCharsAndParties();
    this.m.addParty("Future", this.luna, this.dre, this.steven, this.rose);

    try {
      this.m.removeCharacter(null);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("The given String cannot be null.", e.getMessage());
    }

    assertTrue(this.m.doesCharacterExist("Lunarose"));
    this.m.removeCharacter("Lunarose");
    assertFalse(this.m.doesCharacterExist("Lunarose"));

    this.m.setActiveParty("The Boys");
    assertEquals("The Boys", this.m.getActiveParty().getName());
    assertEquals(3, this.m.getActiveParty().size());
    assertEquals(this.jake, this.m.getActiveParty().getParty()[0]);
    assertEquals(this.bryan, this.m.getActiveParty().getParty()[1]);
    assertEquals(this.steven, this.m.getActiveParty().getParty()[2]);

    this.m.setActiveParty("The Infinite Odyssey");
    assertEquals("The Infinite Odyssey", this.m.getActiveParty().getName());
    assertEquals(1, this.m.getActiveParty().size());
    assertEquals(this.rose, this.m.getActiveParty().getParty()[0]);

    this.m.setActiveParty("Future");
    assertEquals("Future", this.m.getActiveParty().getName());
    assertEquals(3, this.m.getActiveParty().size());
    assertEquals(this.dre, this.m.getActiveParty().getParty()[0]);
    assertEquals(this.steven, this.m.getActiveParty().getParty()[1]);

    this.m.setActiveParty("The Infinite Odyssey");
    assertEquals("The Infinite Odyssey", this.m.getActiveParty().getName());
    this.m.removeCharacter("Rose Walker");

    assertFalse(this.m.doesPartyExist("The Infinite Odyssey"));
    assertEquals("The Boys", this.m.getActiveParty().getName());

    this.m.removeCharacter("Dre");
    this.m.removeCharacter("Onion");
    assertFalse(this.m.doesPartyExist("Future"));
    assertFalse(this.m.doesCharacterExist("Dre"));

    this.initManager();
    this.addCharsAndParties();
    this.m.setActiveParty("The Infinite Odyssey");
    this.m.startCampaign(true);

    try {
      this.m.setActiveParty("Le Boys");
      fail();
    }
    catch (IllegalStateException e) {
      assertEquals("Unable to use this method since a campaign is in progress.", e.getMessage());
    }
  }

  @Test
  public void removeParty() {
    try {
      this.m.removeParty(null);
      fail();
    }
    catch (IllegalStateException e) {
      assertEquals("This Manager doesn't have any Parties!", e.getMessage());
    }

    this.addCharsAndParties();
    this.m.addParty("Future", this.luna, this.dre, this.steven);

    try {
      this.m.removeParty(null);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("The given String cannot be null.", e.getMessage());
    }

    assertTrue(this.m.doesCharacterExist("Lunarose"));

    this.m.setActiveParty("The Boys");
    assertTrue(this.m.doesPartyExist("The Boys"));

    this.m.removeParty("The Boys");
    assertFalse(this.m.doesPartyExist("The Boys"));

    assertEquals("The Infinite Odyssey", this.m.getActiveParty().getName());
    this.m.removeParty("The Infinite Odyssey");

    assertEquals("Future", this.m.getActiveParty().getName());
    this.m.removeParty("Future");

    try {
      this.m.getActiveParty();
      fail();
    }
    catch (IllegalStateException e) {
      assertEquals("This Manager doesn't have any Parties!", e.getMessage());
    }

    this.initManager();
    this.addCharsAndParties();
    this.m.setActiveParty("The Infinite Odyssey");
    this.m.startCampaign(true);

    try {
      this.m.setActiveParty("Le Boys");
      fail();
    }
    catch (IllegalStateException e) {
      assertEquals("Unable to use this method since a campaign is in progress.", e.getMessage());
    }
  }

  @Test
  public void damageAndHeal() {
    try {
      this.m.damage(null, -1);
      fail();
    }
    catch (IllegalStateException e) {
      assertEquals("This Manager doesn't have any Characters!",
          e.getMessage());
    }

    this.addCharsAndParties();

    try {
      this.m.damage(null, -1);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("The given String cannot be null.",
          e.getMessage());
    }

    try {
      this.m.heal(null, -1);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("The given String cannot be null.",
          e.getMessage());
    }

    try {
      this.m.damage("", -1);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("This Manager doesn't have a Character named .",
          e.getMessage());
    }

    try {
      this.m.heal("", -1);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("This Manager doesn't have a Character named .",
          e.getMessage());
    }

    try {
      this.m.damage("Lunarose", -1);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("Damage amount should be a positive number.",
          e.getMessage());
    }

    try {
      this.m.heal("Lunarose", -1);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("Heal amount should be a positive number.",
          e.getMessage());
    }

    assertEquals(100, this.luna.getHP());
    this.m.damage("Lunarose", 50);

    assertEquals(50, this.luna.getHP());

    this.m.damage("Lunarose", 20);

    assertEquals(30, this.luna.getHP());

    this.m.damage("Lunarose", 50);

    assertEquals(0, this.luna.getHP());

    this.m.heal("Lunarose", 50);

    assertEquals(50, this.luna.getHP());

    this.m.heal("Lunarose", 500);

    assertEquals(100, this.luna.getHP());
  }

  @Test
  public void healthPersistence() {
    this.addCharsAndParties();

    this.m.setActiveParty("The Boys");

    assertEquals(100,
        this.m.getActiveParty().getPartyMember("Lunarose").getHP());

    this.m.damage("Lunarose", 50);

    assertEquals(50,
        this.m.getActiveParty().getPartyMember("Lunarose").getHP());

    this.m.setActiveParty("The Infinite Odyssey");

    assertEquals(50,
        this.m.getActiveParty().getPartyMember("Lunarose").getHP());

  }
}