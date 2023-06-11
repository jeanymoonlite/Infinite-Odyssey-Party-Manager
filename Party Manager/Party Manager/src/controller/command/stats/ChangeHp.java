package controller.command.stats;

import controller.command.ACommand;
import controller.input.validation.CharacterValid;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import model.Character;
import model.Manager;
import utils.Clamp;
import view.TextView;

/**
 * A command that deals with restoring and removing hp. This command takes into account a
 * {@code Character}'s defense. The defense is subtracted from the damage amount, but damage is
 * minimized to 1.
 */
public class ChangeHp extends ACommand {

  private final Scanner sc;
  private final boolean heal;
  private final boolean all;

  /**
   * Constructs a new {@code Dice}.
   *
   * @param model the model to use
   * @param view  the view to use to render messages
   * @param sc    the scanner to read input from
   * @param heal  whether to heal or damage
   * @param all   whether the command affects one character or a party
   */
  public ChangeHp(Manager model, TextView view, Scanner sc, boolean heal, boolean all) {
    super(model, view);
    this.sc = sc;
    this.heal = heal;
    this.all = all;

    this.signature = ((this.heal) ? "heal" : "damage")
        + ((this.all) ? "-all (amount)" : " (amount name)");
    this.description = ((this.heal) ? "Adds" : "Subtracts")
        + ((this.all) ? " the amount to every character's hp in the active party." :
        " the amount to the character's hp with the given name.")
        + "\nThis command rejects any non-integers/negative numbers.";
  }

  @Override
  public void run() {
    try {
      try {
        int amount = this.sc.nextInt();

        if (amount <= 0) {
          this.sc.nextLine();
          this.view.display("Invalid input: Amount cannot be less than 1.\n");
          return;
        }

        if (this.all) {
          this.party(amount);
        }
        else {
          this.single(amount);
        }
      }
      catch (InputMismatchException e) {
        this.sc.nextLine();
        this.view.display("Invalid input: Amount must be an integer (whole number).\n");
      }
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }

  private void single(int amount) {
    String name = this.sc.nextLine().trim();
    if (!new CharacterValid(this.model, this.view, this.sc).isValid(name)) {
      return;
    }

    Character c = this.model.findCharByName(name);

    this.changeHp(c, amount);

  }

  private void party(int amount) {
    try {
      if (!this.model.hasParties()) {
        this.view.display("Invalid state: This command can only be used during a campaign.\n");
        this.view.display("Use the start command to start a campaign.\n");
        return;
      }

      for (Character c : this.model.getActiveParty().getParty()) {
        this.changeHp(c, amount);
      }
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }

  private void changeHp(Character c, int amount) {
    try {
      int defense = c.getValueOf("Defense");

      if (this.heal) {
        c.setHP(c.getHP() + amount);
        this.view.display(c.getName() + " had " + amount + " hp restored.\n");
      }
      else {
        c.setHP(c.getHP() - Clamp.run((amount) - defense, 1, 100));
        this.view.display(c.getName() + " lost "
            + Clamp.run((amount) - defense, 1, 100) + " hp.\n");
      }

      this.view.display(c.getName() + " (" + c.getPlayerName() + ") ");
      this.view.display("Hp: " + c.getHP() + "/" + c.getMaxHP() + "\n");
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }
}
