package edu.ntnu.idat2003.view;

import edu.ntnu.idat2003.controller.ConfigurationController;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Class representing the configuration screen of the game. This class is responsible for
 * initializing the configuration screen and setting up the buttons and their actions.
 */
public class Configuration {

  private final BorderPane root;
  private final int WIDTH = 1000;
  private final int HEIGHT = 800;
  private final double HEIGHT_RATIO = 0.4;

  /**
   * Constructor for the Configuration class.
   *
   * @param borderPane The main layout of the application.
   */
  public Configuration(BorderPane borderPane) {
    this.root = borderPane;
  }

  /**
   * Initializes the configuration screen. It sets up the layout and adds the necessary components,
   * including buttons for loading players, saving players, adding players, and returning to the
   * previous screen.
   */
  public void init() {
    root.setCenter(null);

    Button returnButton = new Button("Return");
    returnButton.setPrefSize(300, 50);

    Button loadPlayersFromFile = new Button("Load players from file");
    loadPlayersFromFile.setPrefSize(300, 50);

    Button savePlayersToFileButton = new Button("Save players to file");
    savePlayersToFileButton.setPrefSize(300, 50);

    Button addPlayerButton = new Button("Add player");
    addPlayerButton.setPrefSize(300, 50);

    FlowPane buttonPane = new FlowPane();
    buttonPane.setPrefSize(WIDTH, HEIGHT * (1 - HEIGHT_RATIO));
    buttonPane.setOrientation(Orientation.VERTICAL);
    buttonPane.setAlignment(Pos.CENTER);
    buttonPane.setVgap(10);
    buttonPane
        .getChildren()
        .addAll(returnButton, loadPlayersFromFile, savePlayersToFileButton, addPlayerButton);

    HBox hBox = new HBox();
    hBox.setPrefSize(WIDTH, HEIGHT * HEIGHT_RATIO);
    hBox.setAlignment(Pos.CENTER);

    VBox vBox = new VBox(buttonPane, hBox);
    vBox.setPrefSize(WIDTH, HEIGHT);
    root.setCenter(vBox);

    ConfigurationController configurationController = new ConfigurationController(root, hBox);
    configurationController.init(
        returnButton, loadPlayersFromFile, savePlayersToFileButton, addPlayerButton);
  }
}
