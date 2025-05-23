package edu.ntnu.idat2003.model;

import edu.ntnu.idat2003.model.tileactions.QuestionAction;
import edu.ntnu.idat2003.model.tileactions.TileAction;
import edu.ntnu.idat2003.observer.QuizGameObserver;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * The QuizGame class represents the main game logic for a quiz game. It manages players, the game
 * board, and the game state.
 */
public class QuizGame {

  private HashSet<Player> players;
  private Board board;
  private Player currentPlayer;
  private QuizGameObserver observer;
  private Dice dice;
  private boolean gameOver;
  private boolean isPlayerMoving;

  /**
   * Constructor for the QuizGame class. Takes a list of players, a board and an observer as
   * parameters. Defines other basic game variables as false, and creates a new singel die.
   *
   * @param players HashSet<Player> representing all players in the game
   * @param board Board object representing game board
   * @param observer QuizGameObserver object representing the games observer
   */
  public QuizGame(HashSet<Player> players, Board board, QuizGameObserver observer) {
    this.players = players;
    this.board = board;
    this.currentPlayer = players.iterator().next();
    this.observer = observer;
    this.dice = new Dice(2);
    this.gameOver = false;
    this.isPlayerMoving = false;
  }

  /**
   * Method for initializing the game. Utilizes a for loop to set all players start position to
   * (0,0) which is the starting tile.
   */
  public void init() {
    for (Player player : players) {
      Vector2 startPosition = new Vector2(0, 0);
      player.setPosition(startPosition);
    }
  }

  /**
   * Method for rolling the die. Checks if player is already moving, if not it sets player movement
   * to true, rolls the die and notifies the observer about the rolled value. Then calls the move
   * player method with the rolled value.
   */
  public void rollDice() {
    if (isPlayerMoving) {
      return;
    }

    isPlayerMoving = true;
    int steps = dice.roll();
    observer.onDiceRolled(steps);
    movePlayer(steps - 1);
  }

  /**
   * Getter for game Status. Retreves a boolean value from a local gameOver variable, represents the
   * game status (is game finished or not).
   *
   * @return boolean representing game status
   */
  public boolean isGameOver() {
    return gameOver;
  }

  /**
   * Method for changing player position. Retreves all the different position values, for tile,
   * player and next tile. Then changes player position to next tile position, and checks if the
   * players new position is equal to 90. Then intitilazes an observer onPlayerMoved class method,
   * for animation.
   *
   * @param remainder int representing animation iteration amount
   */
  public void movePlayer(int remainder) {
    if (gameOver) {
      observer.onPlayerWon(currentPlayer);
      return;
    }

    Vector2 position = currentPlayer.getPosition();
    Tile tile = board.getTile(position);
    Vector2 nextPosition = tile.getNextPosition();
    currentPlayer.setPosition(nextPosition);
    tile = board.getTile(nextPosition);

    if (tile.getText().equals("90")) {
      gameOver = true;
    }

    observer.onPlayerMoved(currentPlayer, remainder);
  }

  /**
   * Method for executing the tile action. Checks if the current player is on a tile with an action,
   * and if so, notifies the observer about the question and the tile action executed.
   */
  public void executeTileAction() {
    Vector2 position = currentPlayer.getPosition();
    TileAction action = board.getAction(position);

    if (action != null) {
      QuestionAction questionAction = (QuestionAction) action;
      observer.onQuestion(currentPlayer, questionAction);
    }
  }

  /**
   * Getter for all players. Returned in a hashset to ensure uniqueness.
   *
   * @return HashSet<Player> representing all players in the game
   */
  public HashSet<Player> getPlayers() {
    HashSet<Player> playersSet = new HashSet<>();
    for (Player player : players) {
      playersSet.add(player);
    }
    return playersSet;
  }

  /**
   * Method for changing the current player. If the current player has an extra die, they remain the
   * current player but lose the extra dice.
   */
  public void nextPlayer() {
    isPlayerMoving = false;
    if (currentPlayer.hasExtraDice()) {
      currentPlayer.setExtraDice(false);
      return;
    }

    ArrayList<Player> playerList = new ArrayList<>(players);
    int currentIndex = playerList.indexOf(currentPlayer);
    int nextIndex = (currentIndex + 1) % playerList.size();
    currentPlayer = playerList.get(nextIndex);
  }

  /**
   * Getter for the board. Retreves the board object from the class variable. Used by frontend to
   * make the visual board.
   *
   * @return Board object representing the game board
   */
  public Board getBoard() {
    return board;
  }
}
