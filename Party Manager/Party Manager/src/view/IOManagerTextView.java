package view;

import java.io.IOException;
import model.Character;
import model.Manager;
import model.Party;
import utils.RomanNumerals;

public class IOManagerTextView implements TextView {

  private final Manager model;
  private final Appendable output;

  /**
   * Constructs a new {@code IOManagerTextView} with the given {@code Manager} and
   * {@code Appendable}.
   *
   * @param model  the manager to view
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
   * Constructs a new {@code GamePhase1View} with the given {@code Manager} and sets the output to
   * be {@code System.out}.
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
    this.display("This means that the Manager has the following rules when\n");
    this.display("it comes to creating Characters:\n");
    this.statRule();
    this.roleRule();
  }

  private void statRule() throws IOException {
    this.display("1. All Characters have the following stats:\n");

    this.display("\t" + RomanNumerals.get(1) + ". Hp (integer between 0-100)\n");
    this.display("\t" + RomanNumerals.get(2) + ". Defense (which is dictated by Class)\n");

    for (int i = 2; i < this.model.getStats().length; i++) {
      this.display("\t" + RomanNumerals.get(i + 1) + ". " + this.model.getStats()[i] + "\n");
    }

    this.display("\n2. The sum of every stat's value must NOT exceed 30.\n");
    this.display("\n3. No stat can have a value less than 0.\n");
  }

  private void roleRule() throws IOException {

    this.display("\n4. All Characters must have one of the Classes listed below.\n");

    for (int i = 0; i < this.model.getRoles().length; i++) {
      String role = "";

      role = this.model.getRoles()[i];
      this.display("\t" + RomanNumerals.get(1 + i) + ". " + role + "\n");
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
    if (!this.model.hasStartedACampaign()) {
      this.model.getAllCharacters();
    }

    Character c = this.model.findCharByName(name);

    assert c != null;
    this.display(c.toStringAll());
  }

  @Override
  public void displayParty(String name)
      throws IOException, IllegalArgumentException, IllegalStateException {
    if (!this.model.hasStartedACampaign()) {
      this.model.getAllParties();
    }

    Party party = this.model.findPartyByName(name);

    for (int i = 0; i < party.size(); i++) {
      Character character = party.getParty()[i];
      this.displayCharacter(character.getName());

      if (i != party.size() - 1) {
        this.display("\n");
      }
    }
  }

  @Override
  public void displayActiveParty() throws IOException, IllegalStateException {
    try {
      this.display("Active Party: " + this.model.getActiveParty().getName() + "\n\n");
      this.displayParty(this.model.getActiveParty().getName());
    }
    catch (NullPointerException e) {
      this.display("Active Party: None\n");
    }
  }
}
