package edu.ntnu.idat2003.components;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.concurrent.Flow;

import edu.ntnu.idat2003.util.LadderGameEngine;
import javafx.scene.text.Text;
import javafx.scene.layout.HBox;

public class LadderGame {
  private static FlowPane bordBox;
  private static FlowPane sideMenu;

  public static void init(Pane root) {
    // Clear the stack pane
    root.getChildren().clear();
    
    //Create panes for laddergame
    bordBox = new FlowPane();
    bordBox.prefWidthProperty().bind(root.widthProperty().multiply(0.7));
    bordBox.prefHeightProperty().bind(root.heightProperty());
    bordBox.setAlignment(Pos.CENTER);
    bordBox.setStyle("-fx-background-color: gray;");
    
    sideMenu = new FlowPane();
    sideMenu.prefWidthProperty().bind(root.widthProperty().multiply(0.3));
    sideMenu.prefHeightProperty().bind(root.heightProperty());
    sideMenu.setAlignment(Pos.CENTER);
    sideMenu.setStyle("-fx-background-color: beige;");

    HBox bordAndMenu = new HBox();
    bordAndMenu.getChildren().addAll(bordBox, sideMenu);
    StackPane.setAlignment(bordBox, Pos.CENTER_LEFT);
    StackPane.setAlignment(sideMenu, Pos.CENTER_RIGHT);

    root.getChildren().add(bordAndMenu);
    /* 
    // Create the content
    StackPane stackPane = new StackPane();
    root.getChildren().add(stackPane);
    stackPane.prefWidthProperty().bind(root.widthProperty());    
    stackPane.prefHeightProperty().bind(root.heightProperty());
    stackPane.setAlignment(Pos.CENTER);

    FlowPane flowPane = new FlowPane();
    stackPane.getChildren().add(flowPane);
    flowPane.setOrientation(Orientation.VERTICAL);
    flowPane.setAlignment(Pos.CENTER_LEFT); 
    */

    // Pick the players
    pickPlayers(root, bordBox);
  }

  /**
   *  Pick player or bord menu.
   * 
   * @param root Main pain
   * @param box flowpane box
   */
  public static void pickPlayers(Pane root, FlowPane box) {
    // Clear the flow pane
    box.getChildren().clear();
    box.prefWidthProperty().bind(root.widthProperty());

    // Create the content
    Button addPlayerButton = new Button("Add player");
    box.getChildren().add(addPlayerButton);

    Button pickBoardButton = new Button("Pick board");
    box.getChildren().add(pickBoardButton);

    // Load the players

    // Event handler
    addPlayerButton.setOnAction(e -> addPlayer(root, box));
    pickBoardButton.setOnAction(e -> pickBoard(root, box));
  }

  /**
   *  Add player menu.
   * 
   * @param root Main pane
   * @param box Flowpane box
   */
  public static void addPlayer(Pane root, FlowPane box) {
    // Clear the flow pane
    box.getChildren().clear();
    
    // Create the content
    TextField textField = new TextField();
    box.getChildren().add(textField);
    textField.setPromptText("Enter player name");

    Button pickFigureButton = new Button("Pick figure");
    box.getChildren().add(pickFigureButton);
    pickFigureButton.setOnAction(e -> pickFigure(root, box, textField.getText()));
  }

  public static void pickFigure(Pane root, FlowPane box, String playerName) {
    // Clear the flow pane
    box.getChildren().clear();

    // Create the content
    Button addPlayerButton = new Button("Finish adding player");
    box.getChildren().add(addPlayerButton);

    // Event handler
    addPlayerButton.setOnAction(e -> {
      savePlayer(playerName, "Figure");
      pickPlayers(root, box);
    });
  }

  private static void savePlayer(String playerName, String figure) {
    // Save the player
  }

  public static void pickBoard(Pane root, FlowPane box) {
    // Clear the flow pane
    box.getChildren().clear();

    // Create the content
    Button playGameButton = new Button("Play game");
    box.getChildren().add(playGameButton);

    // Load the boards

    // Event handler
    playGameButton.setOnAction(e -> playGame(root, box));
  }

  public static void playGame(Pane root, FlowPane box) {
    // Clear the flow pane
    box.getChildren().clear();
    box.prefWidthProperty().bind(root.widthProperty().multiply(0.7));
    
    // Create the content
    HBox hBox = new HBox();
    root.getChildren().add(hBox);

    // Create the sections
    FlowPane boardPane = new FlowPane();
    hBox.getChildren().add(boardPane);
    boardPane.setStyle("-fx-background-color: grey;");
    boardPane.prefWidthProperty().bind(root.widthProperty().multiply(0.7));    
    boardPane.prefHeightProperty().bind(root.heightProperty());
    boardPane.setOrientation(Orientation.VERTICAL);
    boardPane.setAlignment(Pos.CENTER);

    FlowPane playerPane = new FlowPane();
    hBox.getChildren().add(playerPane);
    playerPane.setStyle("-fx-background-color: beige;");
    playerPane.prefWidthProperty().bind(root.widthProperty().multiply(0.3));
    playerPane.prefHeightProperty().bind(root.heightProperty());
    playerPane.setOrientation(Orientation.VERTICAL);
     
     //Trym Experiment grid
     GridPane bord = new GridPane();
     bord.prefWidthProperty().bind(root.widthProperty().multiply(0.5));
     bord.prefHeightProperty().bind(root.heightProperty().multiply(0.5));
     bord.setPadding(new Insets(5, 5, 5, 5));
     bord.setVgap(5);
     bord.setHgap(5);
     bord.setAlignment(Pos.CENTER);
 
     for(int i = 0;i<9;i++) {
         for(int j = 0; j<10;j++) {
             Rectangle gameTile = new Rectangle();
             gameTile.setWidth(50);
             gameTile.setHeight(50);
             gameTile.setFill(Color.WHITE);
             bord.add(gameTile, i,j);
         }
     }
     boardPane.getChildren().addAll(bord);
    

    Button endGameButton = new Button("End game");
    playerPane.getChildren().add(endGameButton);

    Text text = new Text("Hello, World!");
    playerPane.getChildren().add(text);

    Button rollDiceButton = new Button("Roll Dice");
    playerPane.getChildren().add(rollDiceButton);

    // Event handler
    rollDiceButton.setOnAction(e -> LadderGameEngine.rollDice(text));
    endGameButton.setOnAction(e -> endGame(root));
  }

  private static void endGame(Pane root) {
    // End the game
    MainFrame.init(root);
  }
}
