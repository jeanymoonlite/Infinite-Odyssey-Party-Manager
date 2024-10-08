package controller.files.savefiles;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import model.Character;
import model.Manager;
import model.infiniteodysseys.IOCharacter;
import model.infiniteodysseys.IOManager;
import model.infiniteodysseys.IORoles;

/**
 * A functional object that takes in a file path and checks to ensure that the file is formatted
 * properly. This object checks for Ver. 1.0.0 .iom files.
 */
public class Ver100FileValid implements ManagerFileLoader {

  private Manager model;

  @Override
  public boolean isValid(String input) {
    if (input == null) {
      throw new IllegalArgumentException("File path cannot be null.");
    }

    if (input.isBlank() || input.isEmpty()) {
      throw new IllegalArgumentException("File name cannot be whitespace.");
    }

    if (!this.isIOMFile(input)) {
      this.model = null;
      return false;
    }

    return this.isIOMFormatted(input);
  }

  @Override
  public Manager load(String filePath) throws IllegalArgumentException {
    Manager m = this.model;
    this.model = null;
    return m;
  }

  private boolean isIOMFile(String filePath) {
    if (!filePath.contains(".")) {
      return false;
    }

    int file = filePath.split(Pattern.quote(".")).length;

    return (filePath.split(Pattern.quote("."))[file - 1].equals("iom"));
  }

  private boolean isIOMFormatted(String filePath) {
    this.model = new IOManager();
    try {
      Scanner sc = new Scanner(new File(filePath));

      if (!sc.hasNextLine()) {
        return false;
      }

      if (!sc.nextLine().equals("Infinite Odysseys Party Manager")) {
        return false;
      }
      if (!sc.nextLine().equals("Ver. 1.0.0")) {
        return false;
      }

      String totalChars = sc.nextLine();

      if (!totalChars.split(":")[0].equals("Total Characters")) {
        return false;
      }
      if (totalChars.split(":").length != 2) {
        return false;
      }
      int characters = Integer.parseInt(totalChars.split(":")[1].trim());

      String totalParties = sc.nextLine();

      if (!totalParties.split(":")[0].equals("Total Parties")) {
        return false;
      }
      if (totalParties.split(":").length != 2) {
        return false;
      }
      int parties = Integer.parseInt(totalParties.split(":")[1].trim());

      if (!sc.nextLine().isEmpty()) {
        return false;
      }

      if (!this.charactersFormatted(sc, characters)) {
        return false;
      }
      if (!this.partiesFormatted(sc, parties)) {
        return false;
      }
    }
    catch (Exception e) {
      return false;
    }
    return true;
  }

  private boolean charactersFormatted(Scanner sc, int characters) {
    String[] attributes = new String[]{"Name", "Player", "Class", "Class Specification", "Hp",
        "Strength", "Intelligence", "Creativity",
        "Charisma", "Stealth", "Intimidation"};

    for (int i = 0; i < characters; i++) {
      String[] textAttributes = new String[4];
      int[] stats = new int[7];

      for (int a = 0; a < attributes.length; a++) {
        String attribute = sc.nextLine();
        if (!attribute.contains(attributes[a])) {
          return false;
        }

        if (!attributes[a].equalsIgnoreCase("Class Specification")) {
          if (attribute.split(": ")[1].isEmpty()
              || attribute.split(": ")[1].isBlank()) {
            return false;
          }
        }

        if (a < 4) {
          if (!attributes[a].equalsIgnoreCase("Class Specification")) {

          }
          try {
            textAttributes[a] = attribute.split(": ")[1];
          }
          catch (ArrayIndexOutOfBoundsException e) {
            if (!attributes[a].equalsIgnoreCase("Class Specification")) {
              return false;
            }
            textAttributes[a] = "";
          }

          //invalid class
          if (a == 2) {
            int finalA = a;
            Predicate<String> pred = (s) -> s.equalsIgnoreCase(textAttributes[finalA]);
            if (Arrays.stream(IORoles.getAll()).noneMatch(pred)) {
              return false;
            }
          }
        }
        else if (attributes[a].equals("Hp")) {
          stats[0] = Integer.parseInt(attribute.split(":")[1]
              .split(Pattern.quote("/"))[0].trim());
        }
        else {
          stats[a - 4] = Integer.parseInt(attribute.split(": ")[1]);
        }
      }

      Character temp = new IOCharacter(textAttributes[0], textAttributes[1],
          IORoles.valueOf(textAttributes[2].toUpperCase()), textAttributes[3],
          stats[1], stats[2], stats[3], stats[4], stats[5], stats[6]);
      this.model.addCharacter(temp);

      if (!sc.nextLine().isEmpty()) {
        return false;
      }
    }
    return true;
  }

  private boolean partiesFormatted(Scanner sc, int parties) {
    if (parties == 0) {
      return true;
    }

    for (int i = 0; i < parties; i++) {
      String curParty = sc.nextLine();

      if (!curParty.contains("|")) {
        return false;
      }

      int partySize = this.getPartySize(curParty);

      String name = curParty.split(Pattern.quote("|"))[0].trim();
      Character[] partyMembers = new Character[partySize];

      for (int c = 1; c < partySize + 1; c++) {
        String curChar = curParty.split(Pattern.quote("|"))[c].trim();
        String curName = curChar.split(Pattern.quote("("))[0].trim();

        if (!this.model.doesCharacterExist(curName)) {
          return false;
        }

        partyMembers[c - 1] = this.model.findCharByName(curName);
      }

      this.model.addParty(name, partyMembers);
    }

    return true;
  }

  private int getPartySize(String curParty) {
    int partySize = 0;
    for (char c : curParty.toCharArray()) {
      if (c == '|') {
        partySize++;
      }
    }
    return partySize;
  }

}
