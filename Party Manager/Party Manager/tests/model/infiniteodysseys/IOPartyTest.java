package model.infiniteodysseys;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import model.Party;
import org.junit.Before;
import org.junit.Test;

public class IOPartyTest extends IOCharacterTest {

  protected Party p1;
  protected Party p2;
  protected Party p7;

  @Before
  public void initParty() {
    this.init();
    this.p1 = new IOParty("p1", this.luna, this.bryan, this.jake, this.steven);
    this.p2 = new IOParty("p2", this.luna, this.bryan, this.jake, this.steven, this.rose, this.sarah);
    this.p7 = new IOParty("p7", this.luna, this.jake, this.bryan);
  }

  @Test
  public void invalidConstructor() {
    try {
      new IOParty(null);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("Party name cannot be null.", e.getMessage());
    }

    try {
      new IOParty("");
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("Party name cannot be whitespace.", e.getMessage());
    }

    try {
      new IOParty(" ");
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("Party name cannot be whitespace.", e.getMessage());
    }

    try {
      new IOParty("\n");
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("Party name cannot be whitespace.", e.getMessage());
    }

    try {
      new IOParty("p0");
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("A Party needs at least 1 Character.", e.getMessage());
    }

    try {
      new IOParty("temp", this.jake, this.bryan, this.luna, this.steven, null);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("A Party cannot contain any null Characters.", e.getMessage());
    }

    try {
      new IOParty("temp", this.jake, null, this.luna, this.steven, null);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("A Party cannot contain any null Characters.", e.getMessage());
    }
  }

  @Test
  public void getParty() {
    assertEquals(this.luna, this.p1.getParty()[0]);
    assertEquals(this.bryan, this.p2.getParty()[1]);
    assertEquals(this.jake, this.p7.getParty()[1]);
  }

  @Test
  public void getName() {
    assertEquals("p1", this.p1.getName());
    assertEquals("p2", this.p2.getName());
    assertEquals("p7", this.p7.getName());
  }

  @Test
  public void size() {
    assertEquals(4, this.p1.size());
    assertEquals(6, this.p2.size());
    assertEquals(3, this.p7.size());
  }

  @Test
  public void getPartyMember() {
    try {
      this.p1.getPartyMember(null);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("The given name cannot be null.",
          e.getMessage());
    }

    try {
      this.p1.getPartyMember("Sir Dylan");
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("There is no Character named Sir Dylan in this Party.",
          e.getMessage());
    }

    try {
      this.p1.getPartyMember("Elizabeth");
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("There is no Character named Elizabeth in this Party.",
          e.getMessage());
    }

    try {
      this.p1.getPartyMember("Mo");
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("There is no Character named Mo in this Party.",
          e.getMessage());
    }

    assertEquals(this.luna, this.p1.getPartyMember("Lunarose"));
    assertEquals(this.bryan, this.p1.getPartyMember("Manuel"));
    assertEquals(this.rose, this.p2.getPartyMember("Rose Walker"));
  }

  @Test
  public void hasCharacter() {
    try {
      this.p1.hasCharacter(null);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("The given name cannot be null.",
          e.getMessage());
    }

    assertFalse(this.p1.hasCharacter("Rose Walker"));
    assertFalse(this.p7.hasCharacter("Rose Walker"));
    assertFalse(this.p7.hasCharacter("Onion"));

    assertTrue(this.p1.hasCharacter("Manuel"));
    assertTrue(this.p1.hasCharacter("Lunarose"));
    assertTrue(this.p7.hasCharacter("Jake Walker"));
  }
}