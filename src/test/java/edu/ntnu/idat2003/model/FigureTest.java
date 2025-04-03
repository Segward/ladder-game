package edu.ntnu.idat2003.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FigureTest {

  @Test
  public void testGetColor() {
    Figure figure = new Figure("Test");
    assertEquals("Test", figure.getColor());
  }
}
