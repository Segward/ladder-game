package edu.ntnu.idat2003.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class PlayerTest {

  public static Player player;

  @BeforeAll
  public static void setup() {
    Figure figure = new Figure("Test", "Test Figure");
    player = new Player("Test Player", figure);
    player.setPosition(new Vector2(1, 2));
    player.setExtraDice(false);
  }

  @Test
  public void testGetName() {
    assertEquals("Test Player", player.getName());
  }

  @Test
  public void testGetFigure() {
    assertNotNull(player.getFigure());
    assertEquals("Test", player.getFigure().getName());
  }

  @Test
  public void testGetPosition() {
    assertEquals(new Vector2(1, 2), player.getPosition());
  }

  @Test
  public void testSetPosition() {
    Vector2 newPosition = new Vector2(3, 4);
    player.setPosition(newPosition);
    assertEquals(newPosition, player.getPosition());
  }

  @Test
  public void testSetHasExtraDice() {
    player.setExtraDice(false);
    assertEquals(false, player.hasExtraDice());
  }

  @Test
  public void testEquals() {
    Player player2 = new Player("Test Player", new Figure("Test", "Another Figure"));
    assertEquals(player, player2);
  }

  @Test
  public void testHashCode() {
    Player player2 = new Player("Test Player", new Figure("Test", "Another Figure"));
    assertEquals(player.hashCode(), player2.hashCode());
  }
}
