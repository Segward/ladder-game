package edu.ntnu.idat2003;

import java.util.ArrayList;

public class Game {

  private ArrayList<Player> players;
  private Board board;
  private Dice dice;
  private Player currentPlayer;
  private int currentPlayerIndex;

  public void createBoard() {
    board = new Board();
    for (int i = 0; i < 100; i++) {
      Tile tile = new Tile();
      board.addTile(i, tile);
    }
  }

  public void createDice() {
    dice = new Dice(6);
  }

  public void addPlayer() {
    players = new ArrayList<>();
    players.add(new Player("Player 1", new Figure("Red")));
    players.add(new Player("Player 2", new Figure("Blue")));
    players.add(new Player("Player 3", new Figure("Green")));
    players.add(new Player("Player 4", new Figure("Yellow")));
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