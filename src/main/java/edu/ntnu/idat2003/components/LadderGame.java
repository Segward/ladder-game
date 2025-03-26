package edu.ntnu.idat2003.components;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.FlowPane;
import javafx.geometry.Pos;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import edu.ntnu.idat2003.util.LadderGameEngine;
import javafx.scene.text.Text;
import javafx.scene.layout.HBox;

public class LadderGame {
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
    
    // Pick the players
    pickPlayers(root, flowPane);
  }

  public static void pickPlayers(Pane root, FlowPane flowPane) {
    // Clear the flow pane
    flowPane.getChildren().clear();

    // Create the content
    Button addPlayerButton = new Button("Add player");
    flowPane.getChildren().add(addPlayerButton);

    Button pickBoardButton = new Button("Pick board");
    flowPane.getChildren().add(pickBoardButton);

    // Load the players

    // Event handler
    addPlayerButton.setOnAction(e -> addPlayer(root, flowPane));
    pickBoardButton.setOnAction(e -> pickBoard(root, flowPane));
  }

  public static void addPlayer(Pane root, FlowPane flowPane) {
    // Clear the flow pane
    flowPane.getChildren().clear();
    
    // Create the content
    TextField textField = new TextField();
    flowPane.getChildren().add(textField);
    textField.setPromptText("Enter player name");

    Button pickFigureButton = new Button("Pick figure");
    flowPane.getChildren().add(pickFigureButton);
    pickFigureButton.setOnAction(e -> pickFigure(root, flowPane, textField.getText()));
  }

  public static void pickFigure(Pane root, FlowPane flowPane, String playerName) {
    // Clear the flow pane
    flowPane.getChildren().clear();

    // Create the content
    Button addPlayerButton = new Button("Finish adding player");
    flowPane.getChildren().add(addPlayerButton);

    // Event handler
    addPlayerButton.setOnAction(e -> {
      savePlayer(playerName, "Figure");
      pickPlayers(root, flowPane);
    });
  }

  private static void savePlayer(String playerName, String figure) {
    // Save the player
  }

  public static void pickBoard(Pane root, FlowPane flowPane) {
    // Clear the flow pane
    flowPane.getChildren().clear();

    // Create the content
    Button playGameButton = new Button("Play game");
    flowPane.getChildren().add(playGameButton);

    // Load the boards

    // Event handler
    playGameButton.setOnAction(e -> playGame(root));
  }

  public static void playGame(Pane root) {
    // Clear the root pane
    root.getChildren().clear();

    // Create the content
    HBox hBox = new HBox();
    root.getChildren().add(hBox);

    // Create the sections
    FlowPane boardPane = new FlowPane();
    hBox.getChildren().add(boardPane);
    boardPane.setStyle("-fx-background-color: red;");
    boardPane.prefWidthProperty().bind(root.widthProperty().multiply(0.7));    
    boardPane.prefHeightProperty().bind(root.heightProperty());
    boardPane.setOrientation(Orientation.VERTICAL);

    FlowPane playerPane = new FlowPane();
    hBox.getChildren().add(playerPane);
    playerPane.setStyle("-fx-background-color: yellow;");
    playerPane.prefWidthProperty().bind(root.widthProperty().multiply(0.3));
    playerPane.prefHeightProperty().bind(root.heightProperty());
    playerPane.setOrientation(Orientation.VERTICAL);

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
