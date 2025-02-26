package edu.ntnu.idat2003.models;

import java.util.ArrayList;

import edu.ntnu.idat2003.tileactions.LadderAction;

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
    board.setTileAction(1, new LadderAction("Climb the ladder to tile 38", 38));
    board.setTileAction(4, new LadderAction("Climb the ladder to tile 14", 14));
    board.setTileAction(9, new LadderAction("Climb the ladder to tile 31", 31));
    board.setTileAction(16, new LadderAction("Climb the ladder to tile 6", 6));
    board.setTileAction(28, new LadderAction("Climb the ladder to tile 84", 84));
    board.setTileAction(21, new LadderAction("Climb the ladder to tile 42", 42));
    board.setTileAction(36, new LadderAction("Climb the ladder to tile 44", 44));
    board.setTileAction(47, new LadderAction("Climb the ladder to tile 26", 26));
    board.setTileAction(49, new LadderAction("Climb the ladder to tile 11", 11));
    board.setTileAction(51, new LadderAction("Climb the ladder to tile 67", 67));
    board.setTileAction(56, new LadderAction("Climb the ladder to tile 53", 53));
    board.setTileAction(62, new LadderAction("Climb the ladder to tile 19", 19));
    board.setTileAction(64, new LadderAction("Climb the ladder to tile 60", 60));
    board.setTileAction(71, new LadderAction("Climb the ladder to tile 91", 91));
    board.setTileAction(80, new LadderAction("Climb the ladder to tile 100", 100));
  }

  public void createDice() {
    dice = new Dice(6);
  }

  public void createPlayers() {
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

  public Board getBoard() {
    return board;
  }

  public Dice getDice() {
    return dice;
  }

  public Player getCurrentPlayer() {
    return currentPlayer;
  }

  public int getCurrentPlayerIndex() {
    return currentPlayerIndex;
  }

  public ArrayList<Player> getPlayers() {
    return players;
  }
}