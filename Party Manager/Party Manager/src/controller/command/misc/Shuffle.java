package controller.command.misc;

import controller.command.ACommand;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import model.Character;
import model.Manager;
import view.TextView;

/**
 * A command object that displays the currently active party in a random order.
 */
public class Shuffle extends ACommand {

  /**
   * Constructs a new {@code PartyCommand}.
   *
   * @param model the model to use
   * @param view  the view to use to render messages
   */
  public Shuffle(Manager model, TextView view) {
    super(model, view);
    this.signature = "shuffle";
    this.description = "Shuffles the order of the party.";
  }

  @Override
  public void run() {
    try {
      if (!this.model.hasStartedACampaign()) {
        this.view.display("Invalid state: This command can only be used during a campaign.\n");
        this.view.display("Use the start command to start a campaign.\n");
        return;
      }

      List<Character> characterList = new ArrayList<Character>(
          List.of(this.model.getActiveParty().getParty()));

      Collections.shuffle(characterList);

      this.view.display("New Turn Order:\n");
      for (int i = 0; i < characterList.size(); i++) {
        this.view.display(characterList.get(i).toString());

        if (i != characterList.size() - 1) {
          this.view.display("\n");
        }
      }
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }
}
