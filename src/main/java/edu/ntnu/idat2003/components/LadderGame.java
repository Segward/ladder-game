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
import edu.ntnu.idat2003.util.DataStorage;
import edu.ntnu.idat2003.models.Player;
import edu.ntnu.idat2003.models.Figure;
import java.util.HashSet;
import edu.ntnu.idat2003.models.Board;
import edu.ntnu.idat2003.models.Tile;


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
    String filePath = "src/main/resources/players.json";
    HashSet<Player> players = DataStorage.getPlayers(filePath);
    
    for (Player player : players) {
      FlowPane playerFlowPane = new FlowPane();
      flowPane.getChildren().add(playerFlowPane);
      playerFlowPane.setOrientation(Orientation.HORIZONTAL);
    
      Text text = new Text(player.getName());
      playerFlowPane.getChildren().add(text);

      Button deletePlayerButton = new Button("Delete player");
      playerFlowPane.getChildren().add(deletePlayerButton);
    
      // Event handler
      deletePlayerButton.setOnAction(e -> {
        deletePlayer(player);
        pickPlayers(root, flowPane);
      });
    }

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

  private static void deletePlayer(Player player) {
    // Delete the player
    String filePath = "src/main/resources/Data.json";
    DataStorage.deletePlayer(player, filePath);
  }

  public static void pickFigure(Pane root, FlowPane flowPane, String playerName) {
    // Clear the flow pane
    flowPane.getChildren().clear();

    // Create the content
    Button addPlayerButton = new Button("Finish adding player");
    flowPane.getChildren().add(addPlayerButton);

    // Event handler
    addPlayerButton.setOnAction(e -> {
      savePlayer(playerName, "Figure 1");
      pickPlayers(root, flowPane);
    });
  }

  private static void savePlayer(String playerName, String figure) {
    // Save the player
    String filePath = "src/main/resources/players.json";
    Figure figureObject = new Figure(figure);
    Player player = new Player(playerName, figureObject);
    DataStorage.savePlayer(player, filePath);
  }

  public static void pickBoard(Pane root, FlowPane flowPane) {
    // Clear the flow pane
    flowPane.getChildren().clear();

    // Create the content
    Button playGameButton = new Button("Play game");
    flowPane.getChildren().add(playGameButton);

    // Load the boards
    
    // Save crappy board to test datastorage
    Board board = new Board();
    for (int i = 0; i < 90; i++) {
      Tile tile = new Tile(i);
      board.addTile(i, tile);
    }
    
    String filePath = "src/main/resources/boards.json";
    DataStorage.saveBoard(board, filePath);

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
