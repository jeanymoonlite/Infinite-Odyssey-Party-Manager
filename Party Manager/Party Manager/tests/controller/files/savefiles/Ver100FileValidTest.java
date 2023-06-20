package controller.files.savefiles;

import static org.junit.Assert.*;

import controller.IOManagerController;
import java.io.File;
import java.io.IOException;
import model.Manager;
import org.junit.Test;
import view.TextView;

public class Ver100FileValidTest {

  private final String res = "C:\\Users\\Jr641\\Desktop\\Programming\\Java Programs\\Infinite-Odyssey-Party-Manager\\Party Manager\\Party Manager\\res\\";
  private final String txtFile = res + "grumps.txt";
  private final String goodIOMFile = res + "grumps.iom";
  private ManagerFileLoader mfl;
  @Test
  public void isValidFails() {
    try {
      new Ver100FileValid().isValid(null);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("File path cannot be null.", e.getMessage());
    }

    try {
      new Ver100FileValid().isValid("");
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("File name cannot be whitespace.", e.getMessage());
    }

    try {
      new Ver100FileValid().isValid("         \n");
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("File name cannot be whitespace.", e.getMessage());
    }

    assertFalse(new Ver100FileValid().isValid(txtFile));
  }

  @Test
  public void load() {
    assertNull(new Ver100FileValid().load(null));

    this.mfl = new Ver100FileValid();
    assertFalse(this.mfl.isValid(txtFile));
    assertNull(this.mfl.load(null));
    assertTrue(this.mfl.isValid(this.goodIOMFile));
    assertNotNull(this.mfl.load(null));
  }

  @Test
  public void badHeader() {
    this.mfl = new Ver100FileValid();
    File f = new File(this.res);
    assertTrue(f.isDirectory());
    assertFalse(this.mfl.isValid(this.res + "empty.iom"));
  }

  @Test
  public void badIOMFiles() {
    this.mfl = new Ver100FileValid();

    assertFalse(this.mfl.isValid(this.res + "badGrumps.iom"));
    assertFalse(this.mfl.isValid(this.res + "badRoleGrumps.iom"));
    assertFalse(this.mfl.isValid(this.res + "extraCharGrumps.iom"));
    assertFalse(this.mfl.isValid(this.res + "badPartyGrumps.iom"));
  }

  @Test
  public void goodIOMFiles() {
    this.mfl = new Ver100FileValid();
    assertTrue(this.mfl.isValid(this.res + "noPartiesGrumps.iom"));
    assertTrue(this.mfl.isValid(this.res + "noSpecGrumps.iom"));
  }
}