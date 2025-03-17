package edu.ntnu.idat2003.views;

import edu.ntnu.idat2003.scenes.Scene1;
import edu.ntnu.idat2003.scenes.Scene2;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class UserInterface {

  private Stage primaryStage;
  private Pane root; // Single root Pane

  public UserInterface(Stage primaryStage) {
    this.primaryStage = primaryStage;
    this.root = new Pane(); // Initialize the root Pane
  }

  public void init() {
    primaryStage.setTitle("Ladder Game");
    primaryStage.setMinWidth(400);
    primaryStage.setMinHeight(400);

    // Bind the root Pane to the primaryStage's size
    root.prefWidthProperty().bind(primaryStage.widthProperty());
    root.prefHeightProperty().bind(primaryStage.heightProperty());
  }

  public void start() {
    // Create Scene1 and Scene2
    Scene1 scene1 = new Scene1(() -> showScene2());
    Scene2 scene2 = new Scene2(() -> showScene1());

    // Set the initial content to Scene1
    showScene1();

    // Create a single Scene with the root Pane
    Scene scene = new Scene(root);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private void showScene1() {
    root.getChildren().clear(); // Clear the root Pane
    Scene1 scene1 = new Scene1(this::showScene2); // Pass callback to switch to Scene2
    root.getChildren().add(scene1.getContent());
  }

  private void showScene2() {
    root.getChildren().clear(); // Clear the root Pane
    Scene2 scene2 = new Scene2(this::showScene1); // Pass callback to switch to Scene1
    root.getChildren().add(scene2.getContent());
  }
}