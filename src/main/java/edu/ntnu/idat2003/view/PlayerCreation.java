package edu.ntnu.idat2003.view;

import edu.ntnu.idat2003.controller.PlayerCreationController;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * This class represents the player creation screen in the application. This is where the user
 * creates a new player by entering a name and selecting a figure.
 */
public class PlayerCreation {

  private final BorderPane root;

  private final int WIDTH = 1000;
  private final int HEIGHT = 800;
  private final double HEIGHT_RATIO = 0.4;

  /**
   * Constructor for the PlayerCreation class.
   *
   * @param borderPane The root pane of the application.
   */
  public PlayerCreation(BorderPane borderPane) {
    this.root = borderPane;
  }

  /**
   * Initializes the player creation screen. It sets up the layout and adds the necessary
   * components, including a return button and a controller for handling player creation.
   */
  public void init() {
    root.setCenter(null);

    Button returnButton = new Button("Return");
    returnButton.setPrefSize(300, 50);

    TextField playerName = new TextField();
    playerName.setPromptText("Enter player name");
    playerName.setPrefSize(300, 50);

    FlowPane buttonPane = new FlowPane();
    buttonPane.setPrefSize(WIDTH, HEIGHT * (1 - HEIGHT_RATIO));
    buttonPane.setOrientation(Orientation.VERTICAL);
    buttonPane.setAlignment(Pos.CENTER);
    buttonPane.setVgap(10);
    buttonPane.getChildren().addAll(returnButton, playerName);

    HBox hBox = new HBox();
    hBox.setPrefSize(WIDTH, HEIGHT * HEIGHT_RATIO);
    hBox.setAlignment(Pos.CENTER);

    VBox vBox = new VBox(buttonPane, hBox);
    vBox.setPrefSize(WIDTH, HEIGHT);
    root.setCenter(vBox);

    PlayerCreationController playerCreationController =
        new PlayerCreationController(root, hBox, playerName);
    playerCreationController.init(returnButton);
  }
}
