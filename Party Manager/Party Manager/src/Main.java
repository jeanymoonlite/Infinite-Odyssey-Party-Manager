import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import controller.Controller;
import controller.IOManagerController;
import controller.command.misc.SaveFile;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import model.Manager;
import model.infiniteodysseys.IOManager;
import view.IOManagerTextView;
import view.TextView;

/**
 * The starting point of the {@code Manager}.
 */
public class Main {

  public static void main(String[] args) {
    setIcon();
    Manager model = new IOManager();
    TextView view = new IOManagerTextView(model, System.out);
    Readable rd = new InputStreamReader(System.in);
    Controller controller = new IOManagerController(model, view, rd);

    controller.start();

  }

  public static void setIcon() {
    ProtectionDomain protectionDomain = SaveFile.class.getProtectionDomain();
    CodeSource codeSource = protectionDomain.getCodeSource();
    String jarFilePath = codeSource.getLocation().getPath();

    File file = new File(jarFilePath);
    String filePath = file.getParent().replace("%20", " ");
    String iconFilePath = "";

    String os = System.getProperty("os.name");
    System.out.println("Operating System: " + os);

    if (os.contains("Windows")) {
      iconFilePath = "./res/Infinite Odysseys Party Manager Icon.ico";
      changeIconWIN(jarFilePath, iconFilePath);
    }
    else if (os.contains("Mac")) {
      iconFilePath = "./res/Infinite Odysseys Party Manager Icon.icns";
      changeIconMAC(jarFilePath, iconFilePath);
    }
    else {
      System.out.println("Unknown platform detected");
    }
  }

  public interface Shell32 extends Library {

    Shell32 INSTANCE = (Shell32) Native.load("shell32", Shell32.class,
        W32APIOptions.DEFAULT_OPTIONS);

    int SHChangeIcon(String pszPath, String pszIconFile);
  }

  public static void changeIconWIN(String filePath, String iconFilePath) {
    Shell32.INSTANCE.SHChangeIcon(filePath, iconFilePath);
  }

  public static void changeIconMAC(String filePath, String iconFilePath) {
    String[] command = {"osascript", "-e",
        "tell application \"Finder\" to set icon file of POSIX file \"" +
            filePath + "\" to POSIX file \"" + iconFilePath + "\""};
    try {
      Runtime.getRuntime().exec(command);
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
}
