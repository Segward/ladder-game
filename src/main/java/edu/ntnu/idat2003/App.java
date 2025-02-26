package edu.ntnu.idat2003;

import edu.ntnu.idat2003.models.Game;

public class App {
  public static void main(String[] args) {
    Game game = new Game();
    game.createBoard();
    game.createPlayers();
    game.createDice();
    game.startGame();
  }
}
