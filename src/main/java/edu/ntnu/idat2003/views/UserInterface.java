package edu.ntnu.idat2003.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import edu.ntnu.idat2003.components.MainFrame;

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
    root.setId("root");
  }

  public void start() {
    // Create a stack pane to hold the game board
    StackPane stackPane = new StackPane();
    root.getChildren().add(stackPane);

    //Dynamically set the size of the stackPane
    stackPane.prefWidthProperty().bind(primaryStage.widthProperty());
    stackPane.prefHeightProperty().bind(primaryStage.heightProperty());

    //Trym Experiment grid
    GridPane bord = new GridPane();
    bord.setMinSize(500, 500);
    bord.setPadding(new Insets(10, 10, 10, 10));
    bord.setVgap(10);
    bord.setHgap(10);
    bord.setAlignment(Pos.CENTER);

    for(int i = 0;i<10;i++) {
        for(int j = 0; j<10;j++) {
            Rectangle box = new Rectangle();
            box.setWidth(50);
            box.setHeight(50);
            box.setFill(Color.LIGHTGREEN);
            bord.add(box, i,j);
        }
    }

    // Initialize the MainFrame
    MainFrame.init(stackPane);
    
    // Scene scene = new Scene(bord, 1000, 800);
    primaryStage.setScene(new Scene(root));  
    primaryStage.show();
  }
}
