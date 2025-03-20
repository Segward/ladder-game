package edu.ntnu.idat2003.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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
  }

  public void start() {
    //Trym Experiment grid
    GridPane bord = new GridPane();
    bord.setMinSize(500, 500);
    bord.setPadding(new Insets(10, 10, 10, 10));
    bord.setVgap(5);
    bord.setHgap(5);
    bord.setAlignment(Pos.CENTER);
    bord.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));

    for(int i = 0;i<10;i++) {
        for(int j = 0; j<9;j++) {
            Rectangle box = new Rectangle();
            box.setWidth(50);
            box.setHeight(50);
            box.setFill(Color.BEIGE);
            bord.add(box, i,j);
        }
    }

    Scene scene = new Scene(bord, 1000, 800);
    primaryStage.setScene(scene);  
  
    primaryStage.show();
  }
}