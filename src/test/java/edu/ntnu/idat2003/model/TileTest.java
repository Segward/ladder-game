package edu.ntnu.idat2003.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TileTest {

  public Vector2 position = new Vector2(1, 2);
  public Vector2 nextPosition = new Vector2(3, 4);
  public Tile tile;

  @BeforeEach
  @DisplayName("Setup Tile")
  public void setup() {
    position = new Vector2(1, 2);
    nextPosition = new Vector2(3, 4);
    tile = new Tile(position, "text");
    tile.setNextPosition(nextPosition);
  }

  @Test
  @DisplayName("Test getPosition")
  public void testGetPosition() {
    assertEquals(position, tile.getPosition());
  }

  @Test
  @DisplayName("Test setPosition")
  public void testSetPosition() {
    Vector2 newPosition = new Vector2(5, 6);
    tile.setPosition(newPosition);
    assertEquals(newPosition, tile.getPosition());
  }

  @Test
  @DisplayName("Test getNextPosition")
  public void testGetNextPosition() {
    assertEquals(nextPosition, tile.getNextPosition());
  }

  @Test
  @DisplayName("Test setNextPosition")
  public void testSetNextPosition() {
    Vector2 newNextPosition = new Vector2(5, 6);
    tile.setNextPosition(newNextPosition);
    assertEquals(newNextPosition, tile.getNextPosition());
  }
}
