package edu.ntnu.idat2003.model;

import edu.ntnu.idat2003.model.tileactions.TileAction;
import edu.ntnu.idat2003.observer.LadderGameObserver;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Class representing the Ladder game. It holds all of the different game data used in the ladder
 * game. Incluedes different methods for interaction with the logic of the game.
 */
public class LadderGame {

  private HashSet<Player> players;
  private Board board;
  private Player currentPlayer;
  private LadderGameObserver observer;
  private Dice dice;
  private boolean gameOver;
  private boolean isPlayerMoving;

  /**
   * Constructor for the LadderGame class. Takes a list of players, a board and an observer as
   * parameters. Defines other basic game variables as false, and creates a new singel die.
   *
   * @param players HashSet<Player> representing all players in the game
   * @param board Board object representing game board
   * @param observer LadderGameObserver object representing the games observer
   */
  public LadderGame(HashSet<Player> players, Board board, LadderGameObserver observer) {
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
   * Method for executing tile action. Retreves current player position and tile action based on
   * players position. Then checks if tile action is null before executing action on current player.
   */
  public void executeTileAction() {
    Vector2 position = currentPlayer.getPosition();
    TileAction action = board.getAction(position);

    if (action != null) {
      action.execute(currentPlayer);
      observer.onTileActionExecuted(currentPlayer, action);
    }
  }

  /**
   * Getter for all players. Utilizes a for loop on a local HashSet that stores all of the players,
   * and adds them to a new HashSet.
   *
   * @return HashSet<Player> representing all game players
   */
  public HashSet<Player> getPlayers() {
    HashSet<Player> playersSet = new HashSet<>();
    for (Player player : players) {
      playersSet.add(player);
    }
    return playersSet;
  }

  /**
   * Method for setting next current player. Sets player movement to false, before removing extra
   * dice from current player. Then checks for the index of current player a playerList. Then
   * utilizing this index value calculates the index of the next current player and sets next player
   * based on new index.
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
