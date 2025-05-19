package edu.ntnu.idat2003.model;

import edu.ntnu.idat2003.model.tileactions.TileAction;
import edu.ntnu.idat2003.observer.PartyGameObserver;
import java.util.ArrayList;
import java.util.HashSet;

public class PartyGame {

  private HashSet<Player> players;
  private Board board;
  private Player currentPlayer;
  private PartyGameObserver observer;
  private Dice dice;
  private boolean gameOver;
  private boolean isPlayerMoving;
  private boolean isMiniGameActive;

  public PartyGame(HashSet<Player> players, Board board, PartyGameObserver observer) {
    this.players = players;
    this.board = board;
    this.currentPlayer = players.iterator().next();
    this.observer = observer;
    this.dice = new Dice(1);
    this.gameOver = false;
    this.isPlayerMoving = false;
    this.isMiniGameActive = false;
  }

  public void init() {
    for (Player player : players) {
      Vector2 startPosition = new Vector2(0, 0);
      player.setPosition(startPosition);
    }
  }

  public void rollDice() {
    if (isPlayerMoving) {
      return;
    }

    isPlayerMoving = true;
    int steps = dice.roll();
    observer.onDiceRolled(steps);
    movePlayer(steps - 1);
  }

  public boolean isGameOver() {
    return gameOver;
  }

  public void movePlayer(int remainder) {
    Vector2 position = currentPlayer.getPosition();
    Tile tile = board.getTile(position);
    Vector2 nextPosition = tile.getNextPosition();
    currentPlayer.setPosition(nextPosition);
    tile = board.getTile(nextPosition);

    if (position.getX() >= 10 && position.getY() >= 9) {
      gameOver = true;
      observer.onPlayerWon(currentPlayer);
      return;
    }

    observer.onPlayerMoved(currentPlayer, remainder);
  }

  public void executeTileAction() {
    Vector2 position = currentPlayer.getPosition();
    TileAction action = board.getAction(position);

    if (action != null) {
      action.execute(currentPlayer);
      observer.onTileActionExecuted(currentPlayer, action);
    }
  }

  public HashSet<Player> getPlayers() {
    HashSet<Player> playersSet = new HashSet<>();
    for (Player player : players) {
      playersSet.add(player);
    }
    return playersSet;
  }

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
}
