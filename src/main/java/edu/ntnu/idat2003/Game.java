package edu.ntnu.idat2003;

import java.util.ArrayList;

public class Game {

  private ArrayList<Player> players;
  private Board board;
  private Dice dice;
  private Player currentPlayer;
  private int currentPlayerIndex;

  public Game() {
    this.players = new ArrayList<>();
    this.board = new Board();
    this.dice = new Dice(6);
    this.currentPlayerIndex = 0;
  }

  public void createBoard() {}

  public void createDice() {}

  public void addPlayer() {}

  public void startGame() {}

  public void nextPlayer() {}
}
