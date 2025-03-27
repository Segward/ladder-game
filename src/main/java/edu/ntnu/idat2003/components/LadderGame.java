package edu.ntnu.idat2003.components;

import edu.ntnu.idat2003.models.Board;
import edu.ntnu.idat2003.models.Figure;
import edu.ntnu.idat2003.models.Player;
import edu.ntnu.idat2003.models.Tile;
import edu.ntnu.idat2003.util.DataStorage;
import edu.ntnu.idat2003.util.LadderGameEngine;
import java.util.HashSet;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class LadderGame {
  // private static FlowPane sideMenu;

  /**
   * Initializes game panes and children.
   *
   * @param root Main oane
   */
  public static void init(Pane root) {
    // Clear the stack pane
    root.getChildren().clear();

    // Create panes for laddergame

    // Main pain where bord, player game menu assets are displayed
    FlowPane menuPane = new FlowPane();
    menuPane.prefWidthProperty().bind(root.widthProperty());
    menuPane.prefHeightProperty().bind(root.heightProperty());
    menuPane.setAlignment(Pos.CENTER);
    menuPane.setStyle("-fx-background-color: gray;");
    menuPane.setOrientation(Orientation.VERTICAL);
    root.getChildren().add(menuPane);

    // Pick the players
    pickPlayers(root, menuPane);
  }

  /**
   * Pick player or bord menu.
   *
   * @param root Main pain
   * @param menuPane flowpane box
   */
  public static void pickPlayers(Pane root, FlowPane menuPane) {
    // Clear the flow pane
    menuPane.getChildren().clear();

    // Create the content
    Button addPlayerButton = new Button("Add player");
    menuPane.getChildren().add(addPlayerButton);

    Button pickBoardButton = new Button("Pick board");
    menuPane.getChildren().add(pickBoardButton);

    // Load the players
    String filePath = "src/main/resources/players.json";
    HashSet<Player> players = DataStorage.getPlayers(filePath);

    for (Player player : players) {
      FlowPane playerFlowPane = new FlowPane();
      menuPane.getChildren().add(playerFlowPane);
      playerFlowPane.setOrientation(Orientation.HORIZONTAL);

      Text text = new Text(player.getName());
      playerFlowPane.getChildren().add(text);

      Button deletePlayerButton = new Button("Delete player");
      playerFlowPane.getChildren().add(deletePlayerButton);

      // Event handler
      deletePlayerButton.setOnAction(
          e -> {
            deletePlayer(player);
            pickPlayers(root, menuPane);
          });
    }

    // Event handler
    addPlayerButton.setOnAction(e -> addPlayer(root, menuPane));
    pickBoardButton.setOnAction(e -> pickBoard(root, menuPane));
  }

  /**
   * Add player menu.
   *
   * @param root Main pane
   * @param menuPane Flowpane box
   */
  public static void addPlayer(Pane root, FlowPane menuPane) {
    // Clear the flow pane
    menuPane.getChildren().clear();

    // Create the content
    TextField textField = new TextField();
    menuPane.getChildren().add(textField);
    textField.setPromptText("Enter player name");

    Button pickFigureButton = new Button("Pick figure");
    menuPane.getChildren().add(pickFigureButton);
    pickFigureButton.setOnAction(e -> pickFigure(root, menuPane, textField.getText()));
  }

  private static void deletePlayer(Player player) {
    // Delete the player
    String filePath = "src/main/resources/players.json";
    DataStorage.deletePlayer(player, filePath);
  }

  public static void pickFigure(Pane root, FlowPane menuPane, String playerName) {
    // Clear the flow pane
    menuPane.getChildren().clear();

    // Create the content
    Button addPlayerButton = new Button("Finish adding player");
    menuPane.getChildren().add(addPlayerButton);

    // Event handler
    addPlayerButton.setOnAction(
        e -> {
          savePlayer(playerName, "Figure");
          pickPlayers(root, menuPane);
        });
  }

  private static void savePlayer(String playerName, String figure) {
    // Save the player
    String filePath = "src/main/resources/players.json";
    Figure figureObject = new Figure(figure);
    Player player = new Player(playerName, figureObject);
    DataStorage.savePlayer(player, filePath);
  }

  /**
   * Menu for picking bord.
   *
   * @param root main pane
   * @param menuPane main menu pane
   */
  public static void pickBoard(Pane root, FlowPane menuPane) {
    // Clear the flow pane
    menuPane.getChildren().clear();

    // Create the content
    Button playGameButton = new Button("Play game");
    menuPane.getChildren().add(playGameButton);

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
    playGameButton.setOnAction(e -> playGame(root, menuPane));
  }

  /**
   * Initializes game panes and children.
   *
   * @param root main pane
   * @param menuPane main menu pane
   */
  public static void playGame(Pane root, FlowPane menuPane) {
    // Clear the flow pane
    menuPane.getChildren().clear();

    // Create the content
    HBox hBox = new HBox();
    root.getChildren().add(hBox);

    // Create the sections

    // Main panal where bord game is displayed
    FlowPane boardPane = new FlowPane();
    hBox.getChildren().add(boardPane);
    boardPane.setStyle("-fx-background-color: WHITE;");
    boardPane.prefWidthProperty().bind(root.widthProperty().multiply(0.7));
    boardPane.prefHeightProperty().bind(root.heightProperty());
    boardPane.setOrientation(Orientation.VERTICAL);
    boardPane.setAlignment(Pos.CENTER);

    // Side panal/menu
    FlowPane playerPane = new FlowPane();
    hBox.getChildren().add(playerPane);
    playerPane.setStyle("-fx-background-color: beige;");
    playerPane.prefWidthProperty().bind(root.widthProperty().multiply(0.3));
    playerPane.prefHeightProperty().bind(root.heightProperty());
    playerPane.setOrientation(Orientation.VERTICAL);

    // Trym Experiment grid
    GridPane bord = new GridPane();
    bord.setPadding(new Insets(5, 5, 5, 5));
    bord.setVgap(5);
    bord.setHgap(5);
    bord.setStyle("-fx-background-color: gray");

    int tileNum = 90;
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 10; j++) {
        FlowPane gameTile = new FlowPane();
        gameTile.setAlignment(Pos.CENTER);
        gameTile.setPrefWidth(50);
        gameTile.setPrefHeight(50);
        Text text = new Text(String.valueOf(tileNum));
        tileNum--;

        gameTile.setStyle("-fx-background-color: WHITE;");
        gameTile.getChildren().add(text);
        bord.add(gameTile, j, i);
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
   * Takes you to start menu of application.
   *
   * @param root main pain
   */
  private static void endGame(Pane root) {
    // End the game
    MainFrame.init(root);
  }
}
