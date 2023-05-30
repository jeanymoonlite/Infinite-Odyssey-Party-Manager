package model;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class IOCharacterTest {

  @Test
  public void invalidConstructor() {
    try {
      new IOCharacter(-1, -2, -3, -4, -5, -6);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Stat value cannot be less than 0.", e.getMessage());
    }

    try {
      new IOCharacter(5, -2, -3, -4, -5, -6);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Stat value cannot be less than 0.", e.getMessage());
    }

    try {
      new IOCharacter(5, 2, -3, -4, -5, -6);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Stat value cannot be less than 0.", e.getMessage());
    }

    try {
      new IOCharacter(5, 2, 3, -4, -5, -6);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Stat value cannot be less than 0.", e.getMessage());
    }

    try {
      new IOCharacter(5, 2, 3, 4, -5, -6);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Stat value cannot be less than 0.", e.getMessage());
    }

    try {
      new IOCharacter(5, 2, 3, 4, 5, -6);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Stat value cannot be less than 0.", e.getMessage());
    }

    try {
      new IOCharacter(5, 6, 6, 6, 5, 6);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Overall stats must add up to or less than 30.", e.getMessage());
    }


  }

  @Test
  public void getValueOf() {
  }

  public void getHP() {
  }


  public void getMaxHp() {
  }


  public void getDefense() {
  }
}