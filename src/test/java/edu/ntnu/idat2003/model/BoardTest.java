package edu.ntnu.idat2003.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoardTest {

  public static Board board;

  @BeforeAll
  @DisplayName("Setup Board")
  public static void setup() {
    board = new Board("Test Board");
    Tile previous = null;
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 10; j++) {
        if (i % 2 == 1) {
          Vector2 pos = new Vector2(9 - j, i);
          Tile tile = new Tile(pos, null);
          board.addTile(pos, tile);
          if (previous != null) {
            previous.setNextPosition(tile.getPosition());
          }
          previous = tile;
        } else {
          Vector2 pos = new Vector2(j, i);
          Tile tile = new Tile(pos, null);
          board.addTile(pos, tile);
          if (previous != null) {
            previous.setNextPosition(tile.getPosition());
          }
          previous = tile;
        }
      }
    }
  }

  @Test
  @DisplayName("Test getName")
  public void testGetName() {
    assertEquals("Test Board", board.getName());
  }

  @Test
  @DisplayName("Test getTile")
  public void testGetTile() {
    Tile tile = board.getTile(new Vector2(0, 0));
    assertEquals(new Vector2(0, 0), tile.getPosition());

    Tile tile2 = board.getTile(new Vector2(1, 5));
    assertEquals(new Vector2(1, 5), tile2.getPosition());

    Tile tile3 = board.getTile(new Vector2(7, 4));
    assertEquals(new Vector2(7, 4), tile3.getPosition());
  }

  @Test
  @DisplayName("Test getTiles")
  public void testGetTiles() {
    HashMap<Integer, Tile> tiles = board.getTiles();
    assertEquals(90, tiles.size());
  }
}
