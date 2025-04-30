package edu.ntnu.idat2003.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import edu.ntnu.idat2003.model.player.Figure;

public class FigureTest {

  public static Figure figure;

  @BeforeAll
  public static void setup() {
    figure = new Figure("Test Figure");
  }

  @Test
  public void testGetName() {
    assertEquals("Test Figure", figure.getName());
  }

  @Test
  public void testEquals() {
    Figure figure2 = new Figure("Test Figure");
    assertEquals(figure, figure2);
  }

  @Test
  void testHashCode() {
    Figure figure2 = new Figure("Test Figure");
    assertEquals(figure.hashCode(), figure2.hashCode());
  }
}
