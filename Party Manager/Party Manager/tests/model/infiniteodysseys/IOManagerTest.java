package model.infiniteodysseys;

import static org.junit.Assert.*;

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
      this.m.setActiveParty("The Boys");
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("The party The Boys does not exist.", e.getMessage());
    }

    this.addCharsAndParties();

    this.m.setActiveParty("The Boys");

    assertEquals("The Boys", this.m.getActiveParty().getName());

    this.m.setActiveParty("The Infinite Odyssey");

    assertEquals("The Infinite Odyssey", this.m.getActiveParty().getName());
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
  }

  private void addCharsAndParties() {
    this.m.addCharacter(this.luna);
    this.m.addCharacter(this.jake);
    this.m.addCharacter(this.bryan);
    this.m.addCharacter(this.rose);
    this.m.addCharacter(this.steven);

    this.m.addParty("The Boy", this.luna, this.jake, this.bryan, this.steven);
    this.m.addParty("The Infinite Odyssey", this.luna, this.rose);
  }

  @Test
  public void doesCharacterExist() {
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
    assertFalse(this.m.doesPartyExist("The Boy"));
    assertFalse(this.m.doesPartyExist("The Infinite Odyssey"));

    this.addCharsAndParties();

    assertTrue(this.m.doesCharacterExist("Lunarose"));
    assertTrue(this.m.doesCharacterExist("Jake Walker"));
    assertTrue(this.m.doesCharacterExist("Manuel"));
    assertTrue(this.m.doesCharacterExist("Rose Walker"));
    assertTrue(this.m.doesCharacterExist("Onion"));

    assertTrue(this.m.doesPartyExist("The Boy"));
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
  }

  @Test
  public void addParty() {
    try {
      this.m.addParty(null);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("Null cannot be added as a Character.", e.getMessage());
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
      assertEquals("None of a Party's Characters can be null.", e.getMessage());
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

    this.m.addParty("The Boys", this.luna, this.jake, this.bryan, this.steven);
    this.m.setActiveParty("The Boys");

    assertEquals(4, this.m.getActiveParty().size());
    assertEquals(this.luna, this.m.getActiveParty().getParty()[0]);
    assertEquals(this.jake, this.m.getActiveParty().getParty()[1]);
    assertEquals(this.bryan, this.m.getActiveParty().getParty()[2]);
    assertEquals(this.steven, this.m.getActiveParty().getParty()[3]);
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
  }

  @Test
  public void removeParty() {
    try {
      this.m.removeParty(null);
      fail();
    }
    catch (IllegalStateException e) {
      assertEquals("This operation cannot be performed "
          + "because this Manager doesn't have Parties.", e.getMessage());
    }

    this.m.addParty("The Boys", this.luna, this.steven, this.bryan, this.jake);

    try {
      this.m.removeParty(null);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("The given String cannot be null.", e.getMessage());
    }

    assertTrue(this.m.doesCharacterExist("Lunarose"));

    assertTrue(this.m.doesPartyExist("The Boys"));
    this.m.removeParty("The Boys");
    assertFalse(this.m.doesPartyExist("The Boys"));
  }
}