package controller;

/**
 * An interface that just contains the seed set by the {@code SetSeed} command. The
 * {@code IOManagerController} uses this seed for the dice related commands.
 */
@Deprecated
public final class IOManagerSeedHolder {

  private int seed = 0;
  private boolean hasSet = false;
  private boolean usingSeed = false;
  private static IOManagerSeedHolder instance;

  private IOManagerSeedHolder() {
  }

  public static synchronized IOManagerSeedHolder getInstance() {
    if (instance == null) {
      instance = new IOManagerSeedHolder();
    }
    return instance;
  }

  public boolean hasSetSeed() {
    return this.hasSet;
  }

  public boolean isUsingSeed() {
    return this.hasSet && this.usingSeed;
  }

  public void usingSeed(boolean b) {
    this.usingSeed = b;
  }

  public void setSeed(int seed) {
    this.seed = seed;
    this.hasSet = true;
  }

  public int getSeed() {
    return this.seed;
  }


  public void reset() {
    this.seed = 0;
    this.usingSeed = false;
    this.hasSet = false;
  }
}
