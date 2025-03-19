package edu.ntnu.idat2003.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.ntnu.idat2003.old.models.Figure;

public class FigureTest {

  @Test
  public void testGetColor() {
    Figure figure = new Figure("Test");
    assertEquals("Test", figure.getColor());
  }
}
