package model.infiniteodysseys;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class IOStatsTest {

  @Test
  public void getAll() {
    String[] stats = new String[] {"Hp", "Defense", "Strength", "Intelligence", "Creativity", "Charisma", "Stealth", "Intimidation"};
    for (int i = 0; i < stats.length; i++) {
      assertEquals(stats[i], IOStats.getAll()[i]);
    }
  }
}