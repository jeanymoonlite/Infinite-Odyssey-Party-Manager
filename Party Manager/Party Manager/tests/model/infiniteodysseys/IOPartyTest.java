package model.infiniteodysseys;

import static org.junit.Assert.*;

import model.IOCharacterTest;
import model.Party;
import org.junit.Before;
import org.junit.Test;

public class IOPartyTest extends IOCharacterTest {

  private Party p1;
  private Party p2;
  private Party p7;

  @Before
  public void initParty() {
    this.init();
    this.p1 = new IOParty(this.luna, this.bryan, this.jake, this.steven);
    this.p2 = new IOParty(this.luna, this.bryan, this.jake, this.steven, this.rose, this.sarah);
    this.p7 = new IOParty(this.luna, this.jake, this.bryan);
  }

  @Test
  public void invalidConstructor() {
    try {
      new IOParty(null);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("A Party cannot contain any null Characters.", e.getMessage());
    }

    try {
      new IOParty(this.jake, this.bryan, this.luna, this.steven, null);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("A Party cannot contain any null Characters.", e.getMessage());
    }

    try {
      new IOParty(this.jake, null, this.luna, this.steven, null);
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
  public void size() {
    assertEquals(4, this.p1.size());
    assertEquals(7, this.p2.size());
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
  public void damageAndHeal() {
    try {
      this.p1.damage(null, -1);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("The given name cannot be null.",
          e.getMessage());
    }

    try {
      this.p1.heal(null, -1);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("The given name cannot be null.",
          e.getMessage());
    }

    try {
      this.p1.damage("", -1);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("There is no Character named  in this Party.",
          e.getMessage());
    }

    try {
      this.p1.heal("", -1);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("There is no Character named  in this Party.",
          e.getMessage());
    }

    try {
      this.p1.damage("Lunarose", -1);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("Damage amount should be a positive number.",
          e.getMessage());
    }

    try {
      this.p1.heal("Lunarose", -1);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("Heal amount should be a positive number.",
          e.getMessage());
    }

    assertEquals(100, this.luna.getHP());
    this.p1.damage("Lunarose", 50);

    assertEquals(50, this.luna.getHP());

    this.p1.damage("Lunarose", 20);

    assertEquals(30, this.luna.getHP());

    this.p1.damage("Lunarose", 50);

    assertEquals(0, this.luna.getHP());

    this.p1.heal("Lunarose", 50);

    assertEquals(50, this.luna.getHP());

    this.p1.heal("Lunarose", 500);

    assertEquals(100, this.luna.getHP());
  }

}