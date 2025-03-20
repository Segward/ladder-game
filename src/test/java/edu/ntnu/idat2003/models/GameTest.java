package edu.ntnu.idat2003.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GameTest {

  @Test
  public void testAddPlayer() {
    Game game = new Game();
    Player player1 = new Player("Player 1", new Figure("red"));
    Player player2 = new Player("Player 2", new Figure("blue"));
    game.addPlayer(player1);
    game.addPlayer(player2);
    assertEquals(2, game.getPlayers().size());
  }

  @Test
  public void testCreateBoard() {
    Game game = new Game();
    game.createBoard();
    assertEquals(100, game.getBoard().getTileCount());
  }

  @Test
  public void testCreateDice() {
    Game game = new Game();
    game.createDice();
    assertEquals(6, game.getDice().getDie());
  }

  @Test
  public void testNextPlayer() {
    Game game = new Game();
    Player player1 = new Player("Player 1", new Figure("red"));
    Player player2 = new Player("Player 2", new Figure("blue"));
    game.addPlayer(player1);
    game.addPlayer(player2);
    assertEquals(0, game.getCurrentPlayerIndex());
    game.nextPlayer();
    assertEquals(1, game.getCurrentPlayerIndex());
    game.nextPlayer();
    assertEquals(0, game.getCurrentPlayerIndex());
  }
}
