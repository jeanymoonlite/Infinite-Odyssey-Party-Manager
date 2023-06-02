package view;

import java.io.IOException;
import model.Manager;
import model.Character;
import model.Party;

public class IOManagerTextView implements TextView {

  private final Manager model;
  private final Appendable output;

  /**
   * Constructs a new {@code IOManagerTextView} with the given {@code Manager} and
   * {@code Appendable}.
   *
   * @param model the manager to view
   * @param output the appendable to output to
   * @throws IllegalArgumentException if either arguments are null
   */
  public IOManagerTextView(Manager model, Appendable output) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Manager cannot be null.");
    }

    if (output == null) {
      throw new IllegalArgumentException("Appendable cannot be null.");
    }

    this.model = model;
    this.output = output;
  }

  /**
   * Constructs a new {@code GamePhase1View} with the given {@code Manager} and sets the output
   * to be {@code System.out}.
   *
   * @param model the manager to view
   * @throws IllegalArgumentException if either arguments are null
   */
  public IOManagerTextView(Manager model) throws IllegalArgumentException {
    this(model, System.out);
  }

  @Override
  public void display(String message) throws IOException, IllegalArgumentException {
    if (message == null) {
      throw new IllegalArgumentException("Message cannot be null.");
    }

    try {
      this.output.append(message);
    }
    catch (IOException e) {
      //hopefully this never occurs.
      throw new RuntimeException("An error occurred when trying to display this message: " +
          message + ".");
    }
  }

  @Override
  public void displayAllCharacters() throws IOException, IllegalStateException {
    for (Character c : this.model.getAllCharacters()) {
      this.display(c.getName() + " (" + c.getPlayerName() + ")\n");
    }
    this.display("Total Characters: " + this.model.getAllCharacters().length + "\n");
  }

  @Override
  public void displayAllParties() throws IOException, IllegalStateException {
    for (Party p : this.model.getAllParties()) {
      this.display(p.getName() + ":");

      for (int i = 0; i < p.getParty().length; i++) {
        Character c = p.getParty()[i];
        this.display(" " + c.getName() + " (" + c.getPlayerName() + ")");

        if (i != p.getParty().length - 1) {
          this.display(",");
        }
      }
      this.display("\n");
    }
    this.display("Total Parties: " + this.model.getAllParties().length + "\n");
  }

  @Override
  public void displayCharacter(String name)
      throws IOException, IllegalArgumentException, IllegalStateException {
    this.model.getAllCharacters();

    if (!this.model.doesCharacterExist(name)) {
      throw new IllegalArgumentException("This Manager doesn't have a Character named " + name + ".");
    }

    Character c = this.findCharByName(name);

    String playerName = c.getName() + " (" + c.getPlayerName() + ")";

    String role = "Class: " + c.getRole();
    String roleSpecification = (c.getSpecification().isBlank()) ?
        "" : " (" + c.getSpecification() + ")";

    String health = "Hp: " + c.getHP() + "/" + c.getMaxHP();

    String defenseSign = (c.getDefense() >= 0) ? "+" : "-";
    String defense = (c.getDefense() == 0) ?
        "" : " (" + defenseSign + " " + Math.abs(c.getDefense()) + " Def)";

    String[] stats = new String[] {"Strength", "Intelligence", "Creativity",
        "Charisma", "Stealth", "Intimidation"};

    this.display(playerName + "\n");
    this.display(role + roleSpecification + "\n");
    this.display(health + defense + "\n");

    for (String s : stats) {
      this.display(s + ": " + c.getValueOf(s));

      int roleValue = c.getRoleValueOf(s);

      if (roleValue != 0) {
        String roleSign = (roleValue >= 0) ? "+" : "-";

        this.display(" (" + roleSign + roleValue + ")");
      }
      this.display("\n");
    }
  }

  private Character findCharByName(String name) {
    for (Character character : this.model.getAllCharacters()) {
      if (character.getName().equalsIgnoreCase(name)) {
        return character;
      }
    }
    throw new IllegalArgumentException("This Manager doesn't have a Character named " + name + ".");
  }

  @Override
  public void displayParty(String name)
      throws IOException, IllegalArgumentException, IllegalStateException {

  }

  @Override
  public void displayActiveParty() throws IOException, IllegalStateException {

  }
}
