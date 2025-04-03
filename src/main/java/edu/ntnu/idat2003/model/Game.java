package edu.ntnu.idat2003.models;

import edu.ntnu.idat2003.tileactions.LadderAction;
import java.util.ArrayList;

public class Game {

  private ArrayList<Player> players;
  private Board board;
  private Dice dice;
  private Player currentPlayer;
  private int currentPlayerIndex;

  public void createBoard(Board board) {
    this.board = board;
  }

  public void createDice() {
    dice = new Dice(6);
  }

  public void addPlayer(Player player) {
    if (players == null) {
      players = new ArrayList<>();
    }
    players.add(player);
  }

  public void startGame() {
    boolean gameIsRunning = true;
    currentPlayerIndex = 0;
    while (gameIsRunning) {
      currentPlayer = players.get(currentPlayerIndex);
      int steps = dice.roll();
      currentPlayer.move(steps);
      int position = currentPlayer.getPosition();
      System.out.println(currentPlayer.getName() + " is at position " + position);
      if (position >= board.getTileCount() - 1) {
        System.out.println(currentPlayer.getName() + " has won the game!");
        gameIsRunning = false;
      }
      Tile tile = board.getTile(position);
      if (tile != null && tile.hasAction()) {
        tile.getAction().execute(currentPlayer);
      }
      nextPlayer();
    }
  }

  public void nextPlayer() {
    currentPlayerIndex++;
    if (currentPlayerIndex >= players.size()) {
      currentPlayerIndex = 0;
    }
  }
}
