package edu.ntnu.idat2003.views;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class UserInterface {

  private Stage primaryStage;
  private Pane root;

  public UserInterface(Stage primaryStage) {
    this.primaryStage = primaryStage;
    this.root = new Pane();
  }

  public void init() {
    // Set the root Pane as the scene of the primaryStage
    primaryStage.setTitle("Ladder Game");
    primaryStage.setMinWidth(400);
    primaryStage.setMinHeight(400);

    // Bind the root Pane to the primaryStage's size
    root.prefWidthProperty().bind(primaryStage.widthProperty());
    root.prefHeightProperty().bind(primaryStage.heightProperty());

    // Add the Stylesheet to the root Pane
    root.getStylesheets().add(getClass().getResource("/Stylesheet.css").toExternalForm());
  }

  public void start() {
    primaryStage.show();
  }
}