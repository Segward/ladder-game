package edu.ntnu.idat2003.model;

import java.util.ArrayList;
import java.util.HashSet;

public class Game {

  HashSet<Player> players;
  private Board board;
  private Dice dice;
  private Player currentPlayer;

  public Game(HashSet<Player> players, Board board) {
    this.players = players;
    this.board = board;
    this.dice = new Dice(1);
    this.currentPlayer = players.iterator().next();
  }

  public int roll() {
    int steps = dice.roll();
    for (int i = 0; i < steps; i++) {
      Vector2 position = currentPlayer.getPosition();
      Tile tile = board.getTile(position);
      int tileNumber = tile.getPosition().getNumber();
      if (tileNumber >= 90) {
        continue; 
      }
      Vector2 nextPosition = tile.getNextPosition();
      currentPlayer.setPosition(nextPosition);
    }
    currentPlayer = getNextPlayer();
    return steps;
  }
  
  public HashSet<Player> getPlayers() {
    HashSet<Player> playersSet = new HashSet<>();
    for (Player player : players) {
      playersSet.add(player);
    }
    return playersSet;
  }

  private Player getNextPlayer() {
    ArrayList<Player> playerList = new ArrayList<>(players);
    int currentIndex = playerList.indexOf(currentPlayer);
    int nextIndex = (currentIndex + 1) % playerList.size();
    return playerList.get(nextIndex); 
  }

  public Board getBoard() {
    return board;
  }

  public boolean isGameOver() {
    for (Player player : players) {
      if (player.getPosition().getNumber() >= 90) {
        return true;
      }
    }
    return false;
  }
}
