package edu.ntnu.idat2003.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BoardTest {

  @Test
  public void testGetTile() {
    Board board = new Board("Mlem");
    board.addTile(0, new Tile(0));
    board.addTile(1, new Tile(1));
    Tile tile1 = board.getTile(0);
    assertEquals(0, tile1.getPosition());
    Tile tile2 = board.getTile(1);
    assertEquals(1, tile2.getPosition());
  }

  @Test
  public void testGetTileCount() {
    Game game = new Game();
    game.createBoard();
    Board board = game.getBoard();
    assertEquals(100, board.getTileCount());
  }

  @Test
  public void testAddTile() {
    Board board = new Board("Mlem");
    board.addTile(0, new Tile(0));
    assertEquals(1, board.getTileCount());
  }

  @Test
  public void testGetTilesCount() {
    Board board = new Board("Mlem");
    board.addTile(0, new Tile(0));
    board.addTile(1, new Tile(1));
    assertEquals(2, board.getTileCount());
  }
}
