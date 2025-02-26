package edu.ntnu.idat2003;

import edu.ntnu.idat2003.models.Figure;
import edu.ntnu.idat2003.models.Game;
import edu.ntnu.idat2003.models.Player;

public class App {
  public static void main(String[] args) {
    Game game = new Game();
    game.createBoard();
    Player player1 = new Player("Player 1", new Figure("red"));
    Player player2 = new Player("Player 2", new Figure("blue"));
    game.addPlayer(player1);
    game.addPlayer(player2);
    game.createDice();
    game.startGame();
  }
}
