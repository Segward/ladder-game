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
  //private static FlowPane sideMenu;

  /**
   *  Initializes game panes and children.
   * 
   * @param root Main oane
   */
  public static void init(Pane root) {
    // Clear the stack pane
    root.getChildren().clear();
    
    //Create panes for laddergame
    
    //Main pain where bord, player game menu assets are displayed
    FlowPane menuPain = new FlowPane();
    menuPain.prefWidthProperty().bind(root.widthProperty());
    menuPain.prefHeightProperty().bind(root.heightProperty());
    menuPain.setAlignment(Pos.CENTER);
    menuPain.setStyle("-fx-background-color: gray;");
    root.getChildren().add(menuPain);

    // Pick the players
    pickPlayers(root, menuPain);
  }

  /**
   *  Pick player or bord menu.
   * 
   * @param root Main pain
   * @param menuPain flowpane box
   */
  public static void pickPlayers(Pane root, FlowPane menuPain) {
    // Clear the flow pane
    menuPain.getChildren().clear();

    // Create the content
    Button addPlayerButton = new Button("Add player");
    menuPain.getChildren().add(addPlayerButton);

    Button pickBoardButton = new Button("Pick board");
    menuPain.getChildren().add(pickBoardButton);

    // Load the players

    // Event handler
    addPlayerButton.setOnAction(e -> addPlayer(root, menuPain));
    pickBoardButton.setOnAction(e -> pickBoard(root, menuPain));
  }

  /**
   *  Add player menu.
   * 
   * @param root Main pane
   * @param menuPain Flowpane box
   */
  public static void addPlayer(Pane root, FlowPane menuPain) {
    // Clear the flow pane
    menuPain.getChildren().clear();
    
    // Create the content
    TextField textField = new TextField();
    menuPain.getChildren().add(textField);
    textField.setPromptText("Enter player name");

    Button pickFigureButton = new Button("Pick figure");
    menuPain.getChildren().add(pickFigureButton);
    pickFigureButton.setOnAction(e -> pickFigure(root, menuPain, textField.getText()));
  }

  public static void pickFigure(Pane root, FlowPane menuPain, String playerName) {
    // Clear the flow pane
    menuPain.getChildren().clear();

    // Create the content
    Button addPlayerButton = new Button("Finish adding player");
    menuPain.getChildren().add(addPlayerButton);

    // Event handler
    addPlayerButton.setOnAction(e -> {
      savePlayer(playerName, "Figure");
      pickPlayers(root, menuPain);
    });
  }

  private static void savePlayer(String playerName, String figure) {
    // Save the player
  }


  /**
   *  Menu for picking bord.
   * 
   * @param root main pane
   * @param menuPain main menu pane
   */
  public static void pickBoard(Pane root, FlowPane menuPain) {
    // Clear the flow pane
    menuPain.getChildren().clear();

    // Create the content
    Button playGameButton = new Button("Play game");
    menuPain.getChildren().add(playGameButton);

    // Load the boards

    // Event handler
    playGameButton.setOnAction(e -> playGame(root, menuPain));
  }

  /**
   * Initializes game panes and children.
   * 
   * @param root main pane
   * @param menuPain main menu pane
   */
  public static void playGame(Pane root, FlowPane menuPain) {
    // Clear the flow pane
    menuPain.getChildren().clear();
    
    // Create the content
    HBox hBox = new HBox();
    root.getChildren().add(hBox);

    // Create the sections

    //Main panal where bord game is displayed
    FlowPane boardPane = new FlowPane();
    hBox.getChildren().add(boardPane);
    boardPane.setStyle("-fx-background-color: WHITE;");
    boardPane.prefWidthProperty().bind(root.widthProperty().multiply(0.7));    
    boardPane.prefHeightProperty().bind(root.heightProperty());
    boardPane.setOrientation(Orientation.VERTICAL);
    boardPane.setAlignment(Pos.CENTER);

    //Side panal/menu
    FlowPane playerPane = new FlowPane();
    hBox.getChildren().add(playerPane);
    playerPane.setStyle("-fx-background-color: beige;");
    playerPane.prefWidthProperty().bind(root.widthProperty().multiply(0.3));
    playerPane.prefHeightProperty().bind(root.heightProperty());
    playerPane.setOrientation(Orientation.VERTICAL);
     
    //Trym Experiment grid
    GridPane bord = new GridPane();
    bord.setPadding(new Insets(5, 5, 5, 5));
    bord.setVgap(5);
    bord.setHgap(5);
    bord.setStyle("-fx-background-color: gray");

    int tileNum = 90;
    for(int i = 0;i<9;i++) {
       for(int j = 0; j<10;j++) {
           FlowPane gameTile = new FlowPane();
           gameTile.setAlignment(Pos.CENTER);
           gameTile.setPrefWidth(50);
           gameTile.setPrefHeight(50);
           Text text = new Text(String.valueOf(tileNum));
           tileNum--;

           gameTile.setStyle("-fx-background-color: WHITE;");
           gameTile.getChildren().add(text);
           bord.add(gameTile, j,i);
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

  /**
   *  Takes you to start menu of application.
   * 
   * @param root main pain
   */
  private static void endGame(Pane root) {
    // End the game
    MainFrame.init(root);
  }
}
