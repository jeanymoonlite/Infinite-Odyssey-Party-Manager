package controller.command.party;

import controller.command.ACommand;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.Manager;
import model.Party;
import model.infiniteodysseys.IOParty;
import view.TextView;

/**
 * A command that creates a new {@code Party} and adds it to the given {@code Manager}.
 */
public final class CreateParty extends ACommand {

  private final Scanner sc;

  /**
   * Constructs a new {@code CreateParty}.
   *
   * @param model the model to use
   * @param view  the view to use to render messages
   */
  public CreateParty(Manager model, TextView view, Scanner sc) {
    super(model, view);
    this.sc = sc;
  }

  @Override
  public void run() {
    try {
      this.sc.nextLine();
      this.view.display("Party name: ");
      String name = this.sc.nextLine();

      if (this.model.doesPartyExist(name)) {
        this.view.display("Invalid input: There is already a Party named " + name + ".\n");
        this.view.display("Please input a different name.\n");
        this.run();
      }

      this.view.display("Characters (Each by a comma): ");
      List<String> characters = new ArrayList<String>();

      if (this.sc.hasNext(",")) {
        while (this.sc.hasNext(",")) {
          characters.add(this.sc.next(",").trim());
        }
      }
      else {
        characters.add(this.sc.nextLine());
      }

      List<String> charsAdd = new ArrayList<String>();
      List<String> charsRemoved = new ArrayList<String>();

      for (String s : characters) {
        if (this.model.doesCharacterExist(s)) {
          charsAdd.add(s);
        }
        else {
          charsRemoved.add(s);
        }
      }

      this.view.display("BOO");
    }
    catch (IOException e) {
      throw new RuntimeException("Fatal Error: IOException occurred.");
    }
  }
}
