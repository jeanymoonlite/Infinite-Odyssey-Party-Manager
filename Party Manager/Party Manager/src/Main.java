import controller.Controller;
import controller.IOManagerController;
import java.io.InputStreamReader;
import model.Manager;
import model.infiniteodysseys.IOManager;
import view.IOManagerTextView;
import view.TextView;

/**
 * The starting point of the {@code Manager}.
 */
public class Main {

  public static double version = 1.0;

  public static void main(String[] args) {
    Manager model = new IOManager();
    TextView view = new IOManagerTextView(model, System.out);
    Readable rd = new InputStreamReader(System.in);
    Controller controller = new IOManagerController(model, view, rd);

    controller.start();
  }
}
