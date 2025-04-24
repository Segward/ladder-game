package edu.ntnu.idat2003.model;

import edu.ntnu.idat2003.observer.LadderGameObserver;
import java.util.ArrayList;
import java.util.HashSet;

public class LadderGame {

  private HashSet<Player> players;
  private Board board;
  private Player currentPlayer;
  private LadderGameObserver observer;
  private Dice dice;
  private boolean gameOver;
  private boolean isPlayerMoving;

  public LadderGame(HashSet<Player> players, Board board, LadderGameObserver observer) {
    this.players = players;
    this.board = board;
    this.currentPlayer = players.iterator().next();
    this.observer = observer;
    this.dice = new Dice(1);
    this.gameOver = false;
    this.isPlayerMoving = false;
  }

  public void rollDice() {
    if (isPlayerMoving) {
      return;
    }

    isPlayerMoving = true;
    int steps = dice.roll();
    System.out.println("Rolled: " + steps);
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
    observer.onPlayerMoved(currentPlayer, remainder);
  }

  public void hasWon() {
    if (currentPlayer.getPosition().getNumber() >= 90) {
      gameOver = true;
      observer.onPlayerWon(currentPlayer);
    }
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
      return;
    }

    ArrayList<Player> playerList = new ArrayList<>(players);
    int currentIndex = playerList.indexOf(currentPlayer);
    int nextIndex = (currentIndex + 1) % playerList.size();
    currentPlayer = playerList.get(nextIndex);
  }
}
