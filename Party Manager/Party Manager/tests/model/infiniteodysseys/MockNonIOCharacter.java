package model.infiniteodysseys;

import model.Character;

public final class MockNonIOCharacter implements Character {

  @Override
  public String getName() {
    return "mock";
  }

  @Override
  public String getPlayerName() {
    return null;
  }

  @Override
  public int getValueOf(String stat) throws IllegalArgumentException {
    throw new IllegalArgumentException("The stat "
        + stat
        + " does not exist for this mock.");
  }

  @Override
  public String getRole() {
    return null;
  }

  @Override
  public int getRoleValueOf(String stat) throws IllegalArgumentException {
    return 0;
  }

  @Override
  public String getSpecification() {
    return null;
  }

  @Override
  public int getHP() {
    return 0;
  }

  @Override
  public void setHP(int hp) {

  }

  @Override
  public int getMaxHP() {
    return 0;
  }

  @Override
  public String toStringAll() {
    return null;
  }
}
