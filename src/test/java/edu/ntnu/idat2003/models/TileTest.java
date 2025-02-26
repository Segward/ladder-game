package edu.ntnu.idat2003.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.ntnu.idat2003.tileactions.LadderAction;
import org.junit.jupiter.api.Test;

public class TileTest {

  @Test
  public void testGetAction() {
    LadderAction ladderAction = new LadderAction("LadderAction", 5);
    Tile tile = new Tile(1, ladderAction);
    assertEquals(ladderAction, tile.getAction());
  }

  @Test
  public void testGetPosition() {
    LadderAction ladderAction = new LadderAction("LadderAction", 5);
    Tile tile = new Tile(1, ladderAction);
    assertEquals(1, tile.getPosition());
  }

  @Test
  public void testHasAction() {
    LadderAction ladderAction = new LadderAction("LadderAction", 5);
    Tile tile = new Tile(1, ladderAction);
    assertEquals(true, tile.hasAction());
  }
}
