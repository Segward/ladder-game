package edu.ntnu.idat2003.components;

import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.FlowPane;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;

public class MainFrame {
  public static void init(Pane root) {
    // Clear the stack pane
    root.getChildren().clear();
    
    // Create the content
    StackPane stackPane = new StackPane();
    root.getChildren().add(stackPane);
    stackPane.prefWidthProperty().bind(root.widthProperty());    
    stackPane.prefHeightProperty().bind(root.heightProperty());
    stackPane.setAlignment(Pos.CENTER);

    FlowPane flowPane = new FlowPane();
    stackPane.getChildren().add(flowPane);
    flowPane.setOrientation(Orientation.VERTICAL);
    flowPane.setAlignment(Pos.CENTER); 

    Button startGameButton = new Button("Start Game");
    flowPane.getChildren().add(startGameButton);
    
    Button exitAppButton = new Button("Exit");
    flowPane.getChildren().add(exitAppButton);

    // Handle button click events
    startGameButton.setOnAction(e -> PlayerSelection.init(root));
    exitAppButton.setOnAction(e -> exitApp());
  }

  private static void exitApp() {
    // Exit the application
    System.exit(0);
  }
}
