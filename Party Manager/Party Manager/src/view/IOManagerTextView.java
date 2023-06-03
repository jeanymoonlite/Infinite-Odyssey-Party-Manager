package view;

import java.io.IOException;
import model.Manager;
import model.Character;
import model.Party;
import model.infiniteodysseys.IORoles;

public class IOManagerTextView implements TextView {

  private final Manager model;
  private final Appendable output;
  private final String[] stats = new String[] {"Strength", "Intelligence", "Creativity",
      "Charisma", "Stealth", "Intimidation"};

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
  public void displayManagerRules() throws IOException {
    this.display("This Manager is made to be used for Infinite Odysseys.\n");
    this.display("This means that the Manager has the following rules when it"
        + " comes to creating characters:\n");
    this.statRule();
    this.roleRule();
  }

  private void statRule() throws IOException {
    this.display("1. All characters have the following stats:\n");

    String[] numerals = new String[] {"i", "ii", "iii", "iv", "v",
        "vi", "vii", "viii", "ix", "x"};

    this.display("\t" + numerals[0] + ". Hp (integer between 0-100)\n");
    this.display("\t" + numerals[1] + ". Defense (which is dictated by Role)\n");

    for (int i = 0; i < this.stats.length; i++) {
      this.display("\t" + numerals[i + 2] + ". " + this.stats[i] + "\n");
    }

    this.display("\n2. The sum of every stat's value must NOT exceed 30.\n");
    this.display("\n3. No stat can have a value less than 0.\n");
  }

  private void roleRule() throws IOException {
    String[] numerals = new String[] {"i", "ii", "iii", "iv", "v",
        "vi", "vii", "viii", "ix", "x"};

    this.display("\n4. All characters must have one of the roles listed below.\n");

    IORoles[] r = IORoles.values();
    for (int i = 0; i < r.length; i++) {
      String role = "";

        role = r[i].toString().charAt(0)
            + r[i].toString().substring(1).toLowerCase();
        this.display("\t" + numerals[i] + ". " + role + "\n");
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


    Character c = this.findCharByName(name);

    assert c != null;
    String playerName = c.getName() + " (" + c.getPlayerName() + ")";

    String role = "Class: " + c.getRole();
    String roleSpecification = (c.getSpecification().isBlank()) ?
        "" : " (" + c.getSpecification() + ")";

    String health = "Hp: " + c.getHP() + "/" + c.getMaxHP();

    String defenseSign = (c.getDefense() >= 0) ? "+" : "-";
    String defense = (c.getDefense() == 0) ?
        "" : " (" + defenseSign + Math.abs(c.getDefense()) + " Def)";


    this.display(playerName + "\n");
    this.display(role + roleSpecification + "\n");
    this.display(health + defense + "\n");

    for (String s : this.stats) {
      this.display(s + ": " + c.getValueOf(s));

      int roleValue = c.getRoleValueOf(s);

      if (roleValue != 0) {
        String roleSign = (roleValue >= 0) ? "+" : "-";

        this.display(" (" + roleSign + Math.abs(roleValue) + ")");
      }
      this.display("\n");
    }
  }

  private Character findCharByName(String name) {
    if (!this.model.doesCharacterExist(name)) {
      throw new IllegalArgumentException("This Manager doesn't have a Character named " + name + ".");
    }

    for (Character character : this.model.getAllCharacters()) {
      if (character.getName().equalsIgnoreCase(name)) {
        return character;
      }
    }
    return null;
  }

  @Override
  public void displayParty(String name)
      throws IOException, IllegalArgumentException, IllegalStateException {
    this.model.getAllParties();

    Party party = this.findPartyByName(name);

    for (int i = 0; i < party.size(); i++) {
      Character character = party.getParty()[i];
      this.displayCharacter(character.getName());

      if (i != party.size() - 1) {
        this.display("\n");
      }
    }
  }

  private Party findPartyByName(String name) {
    if (!this.model.doesPartyExist(name)) {
      throw new IllegalArgumentException("This Manager doesn't have a Party named " + name + ".");
    }

    for (Party party : this.model.getAllParties()) {
      if (party.getName().equalsIgnoreCase(name)) {
        return party;
      }
    }
    throw new IllegalArgumentException("This Manager doesn't have a Party named " + name + ".");
  }

  @Override
  public void displayActiveParty() throws IOException, IllegalStateException {
    this.display("Active Party: " + this.model.getActiveParty().getName() + "\n\n");
    this.displayParty(this.model.getActiveParty().getName());
  }
}
