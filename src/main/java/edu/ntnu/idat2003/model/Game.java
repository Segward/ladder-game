package edu.ntnu.idat2003.model;

import java.util.ArrayList;
import java.util.HashSet;

public class Game {

  private ArrayList<Player> players;
  private Board board;
  private Dice dice;
  private Player currentPlayer;
  private int currentPlayerIndex;
  
  public Game(HashSet<Player> players, Board board, Dice dice) {
    this.players = new ArrayList<>(players);
    this.board = board;
    this.dice = dice;
    this.currentPlayerIndex = 0;
    this.currentPlayer = this.players.get(currentPlayerIndex); 
  }

  public void roll() {
    int steps = dice.roll();
    currentPlayer.move(steps);
    currentPlayerIndex++;
    if (currentPlayerIndex >= players.size()) {
      currentPlayerIndex = 0;
    }
    currentPlayer = players.get(currentPlayerIndex);
  }
  
  public HashSet<Player> getPlayers2() {
    HashSet<Player> playersSet = new HashSet<>();
    for (Player player : players) {
      playersSet.add(player);
    }
    return playersSet;
  }
















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

  public ArrayList<Player> getPlayers() {
    return players;
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
}
