package edu.ntnu.idat2003.controller;

import edu.ntnu.idat2003.io.PlayerReader;
import edu.ntnu.idat2003.model.Dice;
import edu.ntnu.idat2003.model.Player;
import edu.ntnu.idat2003.model.TicTacToe;
import edu.ntnu.idat2003.model.Vector2;
import edu.ntnu.idat2003.observer.TicTacToeObserver;
import edu.ntnu.idat2003.view.MainFrame;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * This class represents the logic that happends in the TicTacToe game. Utilizes methods to change
 * the state of the game and visuals.
 */
public class TicTacToeViewController implements TicTacToeObserver {
  private final BorderPane root;
  private StackPane playerOnePanal;
  private StackPane playerTwoPanal;
  private GridPane playingBoard;
  private TicTacToe game;
  private Text gameText;
  private Text playerOneScoreText;
  private Text playerTwoScoreText;
  private Button exitButton;
  private HashMap<Vector2, Button> resultHash;
  private int roundNumber = 1;
  private Button playAgainButton;

  /**
   * Constructor for the TicTacToeController. Uses the different parameters to set the value of
   * class variables. Variables usead for easy access to visuals.
   *
   * @param root Main Scene
   * @param gameText Text representing game state
   * @param playerOneScoreText Text representing playerOne Score
   * @param playerTwoScoreText Text representing playerTwo Score
   * @param exitButton Button for exiting game
   * @param playerOnePanal Pane Representing playerOne State
   * @param playerTwoPanal Pane Representing playerTwo State
   * @param playingBoard GridPane Representing the game board
   * @param playingPane Main Pain for game
   */
  public TicTacToeViewController(
      BorderPane root,
      Text gameText,
      Text playerOneScoreText,
      Text playerTwoScoreText,
      Button exitButton,
      StackPane playerOnePanal,
      StackPane playerTwoPanal,
      GridPane playingBoard,
      Button playAgainButton) {
    this.root = root;
    this.playerOnePanal = playerOnePanal;
    this.playerTwoPanal = playerTwoPanal;
    this.playingBoard = playingBoard;
    this.gameText = gameText;
    this.playerOneScoreText = playerOneScoreText;
    this.playerTwoScoreText = playerTwoScoreText;
    this.exitButton = exitButton;
    this.playAgainButton = playAgainButton;
  }

  /**
   * Initializes the main game data. Retreves the players and creates the game object where all game
   * data is stored. Then initializes the gameStartUp() method.
   */
  public void init() {
    HashSet<Player> playerSet = new HashSet<>();

    try {
      playerSet = PlayerReader.getPlayers();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    ArrayList<Player> players = new ArrayList<>();
    for (Player player : playerSet) {
      players.add(player);
    }

    this.game = new TicTacToe(players, new Dice(1));

    gameStartSetup();
    playAgainButton.setOnAction(e -> gameStartSetup());
  }

  /**
   * Method for initilizing the start visuals. Retreves the player visuals and customizes player
   * panes. States the exit buttons functionality, chooses which player to start and runs the die
   * animation.
   */
  private void gameStartSetup() {
    setPlayersScore();
    String playerOneImage =
        getClass()
            .getResource(
                "/figure/"
                    + game.getPlayers().stream().findFirst().get().getFigure().getName()
                    + ".png")
            .toExternalForm();
    playerOnePanal.setStyle("-fx-background-image: url('" + playerOneImage + "'); ");

    String playerTwoImage =
        getClass()
            .getResource(
                "/figure/"
                    + game.getPlayers().get(game.getPlayers().size() - 1).getFigure().getName()
                    + ".png")
            .toExternalForm();
    playerTwoPanal.setStyle("-fx-background-image: url('" + playerTwoImage + "'); ");

    exitButton.setOnAction(
        e -> {
          MainFrame mainFrame = new MainFrame(root);
          mainFrame.init();
        });

    int randStart = game.rollDice();

    if (randStart > 3) {
      game.setCurrentPlayer(game.getPlayers().get(game.getPlayers().size() - 1));
    } else {
      game.setCurrentPlayer(game.getPlayers().stream().findFirst().get());
    }

    Timeline timeline = new Timeline();
    KeyFrame keyFrame =
        new KeyFrame(Duration.millis(10), e -> diceImage((int) (Math.random() * 6) + 1));
    timeline.getKeyFrames().add(keyFrame);
    timeline.setCycleCount(50);
    timeline.play();
    timeline.setOnFinished(
        e -> {
          diceImage(randStart);
          Button startGame = new Button("Start Game");
          startGame.setPrefSize(400, 50);
          startGame.setOnAction(action -> createBoard());
          playingBoard.add(startGame, 0, 2);
          gameText.setText(game.getCurrentPlayer().getName() + " starts!!");
        });
  }

  /**
   * Method for changing the visual score representation of both player one and two. Changes
   * playerOne and Two Text objects to score data from the game Object.
   */
  private void setPlayersScore() {
    playerOneScoreText.setText(
        game.getPlayers().stream().findFirst().get().getName()
            + " Score: "
            + game.getPlayerOneScore());
    playerTwoScoreText.setText(
        game.getPlayers().get(game.getPlayers().size() - 1).getName()
            + " Score: "
            + game.getPlayerTwoScore());
  }

  /**
   * Mehtod that creates die image. Clears the game board GridPane and creates both an new ImageView
   * and Image representing the die. Uses the int parameter to choose which die image to set.
   *
   * @param diceSide int representing the die side
   */
  private void diceImage(int diceSide) {
    playingBoard.getChildren().clear();
    ImageView diceView = new ImageView();
    diceView.setFitHeight(400);
    diceView.setPreserveRatio(true);

    Image diceImage =
        new Image(getClass().getResource("/dice/" + diceSide + "face.png").toExternalForm());
    diceView.setImage(diceImage);
    playingBoard.getChildren().add(diceView);
  }

  /**
   * Method for creating the game board. Clears the gameBord GridPane then using a two nested for
   * loops creates each game tile in the from of a button. States the button action, and adds the
   * new button to the playingBoard GridPane.
   */
  private void createBoard() {
    playingBoard.getChildren().clear();
    roundNumber = 0;
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        Button tile = new Button();
        tile.setId("ticTacToeButton");
        tile.setPrefSize(100, 100);
        tile.setOnAction(e -> onClick(tile));
        tile.setDisable(false);
        tile.setUserData("");
        playingBoard.add(tile, i, j);
      }
    }
  }

  /**
   * Initializes player game action. Finds current Player through the game object, then creates a
   * new ImageView and sets a new image based on the current player value. Disables tile from future
   * use, and initializes setNextPlayer() and gameStatus() methods.
   *
   * @param tile Button representing tile to be changed
   */
  @Override
  public void onClick(Button tile) {
    Player currentPlayer = game.getCurrentPlayer();
    ImageView iconView = new ImageView();
    iconView.setFitHeight(tile.getHeight() * 0.6);
    iconView.setFitWidth(tile.getWidth() * 0.6);
    if (currentPlayer.getName().equals(game.getPlayers().stream().findFirst().get().getName())) {
      Image xMark = new Image(getClass().getResource("/icons/xMark.png").toExternalForm());
      iconView.setImage(xMark);
      tile.setUserData("X");
    } else {
      Image circle = new Image(getClass().getResource("/icons/circleRed.png").toExternalForm());
      iconView.setImage(circle);
      tile.setUserData("O");
    }
    tile.setGraphic(iconView);

    tile.setDisable(true);
    tile.setStyle("-fx-opacity: 1");

    setNextPlayer();
    gameStatus();
  }

  /**
   * Method for checking the game status. Utilizes the win() method to check if any of the players
   * have won. If the number of rounds are higher or equal to 8 and none of the players have won,
   * the game is registed as a tie, and the players recieve the option to play again through the use
   * of a new button. Else the round number value is increased by one. If the a player has won, Main
   * game text informes the players of outcome, and the players recieve the option to play again
   * through the use of a new button.
   */
  @Override
  public void gameStatus() {
    retreveTiles();
    if (roundNumber >= 8 && win() == null) {
      gameText.setText("It is a TIE!!!");
    } else {
      gameText.setText(game.getCurrentPlayer().getName() + "! it is your turn!");
      roundNumber++;
      if (win() != null) {
        disableAll();
        gameText.setText(win().getName() + " Has Won!!");
        if (win().getName().equals(game.getPlayers().stream().findFirst().get().getName())) {
          game.setPlayerOneScore(game.getPlayerOneScore() + 1);
        } else {
          game.setPlayerTwoScore(game.getPlayerTwoScore() + 1);
        }
      }
    }
    setPlayersScore();
  }

  /**
   * Method for disableing all of the tile buttons. Utilizes a for loop to circle through each node
   * registed in the board GridPane. If the node is a button, the button is set to disable.
   */
  private void disableAll() {
    for (Node node : playingBoard.getChildren()) {
      if (node instanceof Button) {
        node.setDisable(true);
      }
    }
  }

  /**
   * Method for changing current player. Retreves the current player from the game object. If the
   * current player equal the first player from the players array, current player changes to last
   * player from the players array. Else change current player is changed to first player from the
   * players array.
   */
  private void setNextPlayer() {
    Player currentPlayer = game.getCurrentPlayer();
    if (currentPlayer.getName().equals(game.getPlayers().stream().findFirst().get().getName())) {
      game.setCurrentPlayer(game.getPlayers().get(game.getPlayers().size() - 1));
    } else {
      game.setCurrentPlayer(game.getPlayers().stream().findFirst().get());
    }
  }

  /**
   * Method for retreveing used tile buttons form the board. Utilizes a for loop to circle through
   * each node registed in the board GridPane. If the node is a button, the button is added to the
   * resultHash HashMap with a Vector2 object representing the tile board location as the key set.
   */
  @Override
  public void retreveTiles() {
    this.resultHash = new HashMap<>();

    for (Node node : playingBoard.getChildren()) {
      if (node instanceof Button) {
        int row = GridPane.getRowIndex(node); // X
        int colum = GridPane.getColumnIndex(node); // Y

        Button tile = (Button) node;

        if (tile.getUserData() != null) {
          resultHash.put(new Vector2(row, colum), tile);
        }
      }
    }
  }

  /**
   * Method for checking if one of the players have won. Creates 8 different Strings representing
   * each different win condition in tictactoe. Utilizes a foor loop on the resultHash HashMap to
   * cycle through the tile Buttons. Utilizes a switch on each tile Vector2 x cordinate, then checks
   * for the y cordinate by utilizing different if statements. If the y cordinate maches the tile
   * button the userData which is a string representing either the x or o symbol is added to the
   * matching win condition String. After each the loop is done, the method checks if the win
   * condition string match any of the win conditions.
   *
   * @return Player object that has won, null if there is no winner.
   */
  private Player win() {
    String row1 = "";
    String row2 = "";
    String row3 = "";

    String colum1 = "";
    String colum2 = "";
    String colum3 = "";

    String line1 = "";
    String line2 = "";

    for (Vector2 placment : resultHash.keySet()) {
      switch (placment.getX()) {
        case 0:
          if (placment.getY() == 0) {
            row1 += resultHash.get(placment).getUserData();
            colum1 += resultHash.get(placment).getUserData();
            line1 += resultHash.get(placment).getUserData();
          }
          if (placment.getY() == 1) {
            row1 += resultHash.get(placment).getUserData();
            colum2 += resultHash.get(placment).getUserData();
          }
          if (placment.getY() == 2) {
            row1 += resultHash.get(placment).getUserData();
            colum3 += resultHash.get(placment).getUserData();
            line2 += resultHash.get(placment).getUserData();
          }
          break;
        case 1:
          if (placment.getY() == 0) {
            row2 += resultHash.get(placment).getUserData();
            colum1 += resultHash.get(placment).getUserData();
          }
          if (placment.getY() == 1) {
            row2 += resultHash.get(placment).getUserData();
            colum2 += resultHash.get(placment).getUserData();
            line1 += resultHash.get(placment).getUserData();
            line2 += resultHash.get(placment).getUserData();
          }
          if (placment.getY() == 2) {
            row2 += resultHash.get(placment).getUserData();
            colum3 += resultHash.get(placment).getUserData();
          }
          break;
        case 2:
          if (placment.getY() == 0) {
            row3 += resultHash.get(placment).getUserData();
            colum1 += resultHash.get(placment).getUserData();
            line2 += resultHash.get(placment).getUserData();
          }
          if (placment.getY() == 1) {
            row3 += resultHash.get(placment).getUserData();
            colum2 += resultHash.get(placment).getUserData();
          }
          if (placment.getY() == 2) {
            row3 += resultHash.get(placment).getUserData();
            colum3 += resultHash.get(placment).getUserData();
            line1 += resultHash.get(placment).getUserData();
          }
          break;

        default:
          break;
      }
    }
    if (row1.equals("XXX")
        || row2.equals("XXX")
        || row3.equals("XXX")
        || colum1.equals("XXX")
        || colum2.equals("XXX")
        || colum3.equals("XXX")
        || line1.equals("XXX")
        || line2.equals("XXX")) {
      return game.getPlayers().stream().findFirst().get();
    }
    if (row1.equals("OOO")
        || row2.equals("OOO")
        || row3.equals("OOO")
        || colum1.equals("OOO")
        || colum2.equals("OOO")
        || colum3.equals("OOO")
        || line1.equals("OOO")
        || line2.equals("OOO")) {
      return game.getPlayers().get(game.getPlayers().size() - 1);
    }
    return null;
  }
}
