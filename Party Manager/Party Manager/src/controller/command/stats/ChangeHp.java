package controller.command.stats;

import controller.command.ACommand;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import model.Character;
import model.Manager;
import utils.Clamp;
import view.TextView;

/**
 * A command that deals with restoring and removing hp. This command takes into account a {@code Character}'s
 * defense. The defense is subtracted from the damage amount, but damage is minimized to 1.
 */
public class ChangeHp extends ACommand {

  private final Scanner sc;
  private final boolean heal;

  /**
   * Constructs a new {@code Dice}.
   *
   * @param model the model to use
   * @param view  the view to use to render messages
   * @param sc  the scanner to read input from
   */
  public ChangeHp(Manager model, TextView view, Scanner sc, boolean heal) {
    super(model, view);
    this.sc = sc;
    this.heal = heal;
  }

  @Override
  public void run() {
    try {
      try {
        int amount = this.sc.nextInt();

        if (amount <= 0) {
          this.view.display("Invalid input: Amount cannot be less than 1.\n");
          return;
        }

        String name = this.sc.nextLine().trim();

        try {
          this.model.getAllCharacters();
        }
        catch (IllegalStateException e) {
          this.view.display("The Manager doesn't have any Characters!\n");
          this.view.display("Add Characters using the create-char command.\n");
          return;
        }

        if (!this.model.doesCharacterExist(name)) {
          this.view.display("The Character " + name + " doesn't exist in this Manager.\n");
          return;
        }

        Character c = this.findByName(name);

        if (this.heal) {
          c.setHP(c.getHP() + amount);
          this.view.display(name + " had " + amount + " hp restored.\n");

        }
        else {
          c.setHP(c.getHP() - Clamp.run((amount) - c.getDefense(), 1, 100));
          this.view.display(name + " lost " + Clamp.run((amount) - c.getDefense(), 1, 100) + " hp.\n");
        }

        this.view.display(c.getName() + " (" + c.getPlayerName() + ") ");
        this.view.display("Hp: " + c.getHP() + "/" + c.getMaxHP() + "\n");

      }
      catch (InputMismatchException e) {
        this.sc.next();
        this.view.display("Invalid input: Amount cannot be a decimal.\n");
      }
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }

  private Character findByName(String name) {
    for (Character c : this.model.getAllCharacters()) {
      if (c.getName().equalsIgnoreCase(name)) {
        return c;
      }
    }
    return null;
  }
}
