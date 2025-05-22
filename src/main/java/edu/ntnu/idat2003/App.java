package edu.ntnu.idat2003;

import edu.ntnu.idat2003.view.UserInterface;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main class for the TicTacToe application. This class is responsible for launching the JavaFX
 * application. It initializes the UserInterface. This project was made for a final assignemnt.
 *
 * @author Segward
 * @author Trympe
 */
public class App extends Application {

  /**
   * Starts the JavaFX application.
   *
   * @param primaryStage The primary stage for this application, onto which the application scene
   *     can be set.
   */
  @Override
  public void start(Stage primaryStage) {
    UserInterface ui = new UserInterface(primaryStage);
    ui.init();
    ui.start();
  }

  /**
   * The main method is the entry point for the JavaFX application. It launches the application.
   *
   * @param args Command line arguments (not used).
   */
  public static void main(String[] args) {
    launch(args);
  }
}
