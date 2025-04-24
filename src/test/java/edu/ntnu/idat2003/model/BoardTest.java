package edu.ntnu.idat2003.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class BoardTest {

  public static Board board;

  @BeforeAll
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
  public void testGetName() {
    assertEquals("Test Board", board.getName());
  }

  @Test
  public void testGetTile() {
    Tile tile = board.getTile(new Vector2(0, 0));
    assertEquals(new Vector2(0, 0), tile.getPosition());

    Tile tile2 = board.getTile(new Vector2(1, 5));
    assertEquals(new Vector2(1, 5), tile2.getPosition());

    Tile tile3 = board.getTile(new Vector2(7, 4));
    assertEquals(new Vector2(7, 4), tile3.getPosition());
  }

  @Test
  public void testSetGetLadderAction() {
    Vector2 start = new Vector2(5, 0);
    Vector2 destination = new Vector2(4, 2);
    LadderAction action1 = new LadderAction(start, destination);
    board.addLadderAction(action1);

    Vector2 start2 = new Vector2(8, 5);
    Vector2 destination2 = new Vector2(6, 3);
    LadderAction action2 = new LadderAction(start2, destination2);
    board.addLadderAction(action2);

    TileAction action3 = board.getAction(start);
    assertEquals(action3, action1);

    TileAction action4 = board.getAction(start2);
    assertEquals(action4, action2);

    HashMap<Integer, LadderAction> ladderActions = board.getLadderActions();
    assertEquals(2, ladderActions.size());
  }

  @Test
  public void testSetGetExtraDiceAction() {
    Vector2 start = new Vector2(8, 2);
    ExtraDiceAction action1 = new ExtraDiceAction(start);
    board.addExtraDiceAction(action1);

    Vector2 start2 = new Vector2(4, 3);
    ExtraDiceAction action2 = new ExtraDiceAction(start2);
    board.addExtraDiceAction(action2);

    TileAction action3 = board.getAction(start);
    assertEquals(action3, action1);

    TileAction action4 = board.getAction(start2);
    assertEquals(action4, action2);

    HashMap<Integer, ExtraDiceAction> extraDiceActions = board.getExtraDiceActions();
    assertEquals(2, extraDiceActions.size());
  }

  @Test
  public void testGetTiles() {
    HashMap<Integer, Tile> tiles = board.getTiles();
    assertEquals(90, tiles.size());
  }
}
