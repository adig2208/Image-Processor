import controller.GUIController;
import controller.IController;
import controller.ImageController;
import model.IImageModel;
import model.ImageModel;
import view.ConsoleView;
import view.GUIView;
import view.IGUIView;
import view.IView;

/**
 * The main class representing the entry point of the image processing application.
 */
public class Application {
  /**
   * The main method that initializes the model, view, and controller based on the command-line
   * arguments.
   *
   * @param args Command-line arguments to specify the mode of the application.
   */
  public static void main(String[] args) {
    IImageModel model = new ImageModel();
    IView view;
    IController controller;

    // Check command-line arguments to determine the application mode
    if (args.length == 2 && "-file".equals(args[0])) {
      // Run in script mode if the "-file" option is provided
      String scriptFilePath = args[1];
      view = new ConsoleView();
      controller = new ImageController(model, view);
      ((ImageController) controller).runScript(scriptFilePath);
    } else if (args.length == 1 && "-text".equals(args[0])) {
      // Run in text mode if the "-text" option is provided
      view = new ConsoleView();
      controller = new ImageController(model, view);
      controller.execute();
    } else if (args.length == 0) {
      // Run in GUI mode if no command-line options are provided
      view = new GUIView();
      controller = new GUIController(model, (IGUIView) view);
      controller.execute();
    } else {
      // Print usage information for invalid command-line arguments
      System.err.println("Invalid arguments. Usage:");
      System.err.println("java -jar Program.jar -file path-of-script-file");
      System.err.println("java -jar Program.jar -text");
      System.err.println("java -jar Program.jar");
      System.exit(1); // Exit with an error status
    }
  }
}
