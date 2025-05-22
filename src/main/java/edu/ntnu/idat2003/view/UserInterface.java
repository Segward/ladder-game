package edu.ntnu.idat2003.view;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Class representing the user interface of the application. It is vital to the userinterface as it
 * builds the foundation. It creates the main stage and sets up the layout for the application. The
 * class is responsible for initializing the main frame and setting the scene for the primary stage.
 * It also starts the graphics.
 */
public class UserInterface {

  private final Stage primaryStage;
  private final int WIDTH = 1000;
  private final int HEIGHT = 800;
  private final BorderPane root;

  /**
   * Constructor for the UserInterface class.
   *
   * @param primaryStage The main stage of the application.
   */
  public UserInterface(Stage primaryStage) {
    this.primaryStage = primaryStage;
    this.root = new BorderPane();
  }

  /**
   * Initializes the user interface by setting the title, minimum size, and resizability of the
   * primary stage. It also sets the ID for the root pane and initializes the main frame.
   */
  public void init() {
    primaryStage.setTitle("Board Games");
    primaryStage.setMinHeight(HEIGHT);
    primaryStage.setMinWidth(WIDTH);
    primaryStage.setResizable(false);
    root.setId("mainFrame");

    MainFrame mainFrame = new MainFrame(root);
    mainFrame.init();
  }

  /**
   * Starts the user interface by creating a new scene and setting it to the primary stage. It also
   * adds the stylesheet for styling the application.
   */
  public void start() {
    Scene scene = new Scene(root);
    scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
