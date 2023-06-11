package model.infiniteodysseys;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class IORolesTest {

  @Test
  public void getAll() {
    String[] roles = new String[]{"Warrior", "Wizard", "Bard",
        "Engineer", "Rogue", "Monk", "Human"};
    for (int i = 0; i < roles.length; i++) {
      assertEquals(roles[i], IORoles.getAll()[i]);
    }
  }
}