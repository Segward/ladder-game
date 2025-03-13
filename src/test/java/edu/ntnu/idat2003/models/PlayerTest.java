package edu.ntnu.idat2003.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.ntnu.idat2003.old.models.Figure;
import edu.ntnu.idat2003.old.models.Player;

public class PlayerTest {

  @Test
  public void testGetName() {
    Player player = new Player("Test", new Figure("Test"));
    assertEquals("Test", player.getName());
  }

  @Test
  public void testGetFigure() {
    Figure figure = new Figure("Test");
    Player player = new Player("Test", figure);
    assertEquals(figure, player.getFigure());
  }

  @Test
  public void testGetPosition() {
    Player player = new Player("Test", new Figure("Test"));
    assertEquals(0, player.getPosition());
  }

  @Test
  public void testMove() {
    Player player = new Player("Test", new Figure("Test"));
    player.move(5);
    assertEquals(5, player.getPosition());
  }

  @Test
  public void testSetPosition() {
    Player player = new Player("Test", new Figure("Test"));
    player.setPosition(5);
    assertEquals(5, player.getPosition());
  }
}
