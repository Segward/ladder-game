package edu.ntnu.idat2003;

public class App {
  public static void main(String[] args) {
    Game game = new Game();
    game.createBoard();
    game.addPlayer();
    game.createDice();
    game.startGame();
  }
}
