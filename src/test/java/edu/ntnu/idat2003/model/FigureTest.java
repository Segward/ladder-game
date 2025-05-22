package edu.ntnu.idat2003.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FigureTest {

  public static Figure figure;

  @BeforeAll
  @DisplayName("Setup Figure")
  public static void setup() {
    figure = new Figure("Test", "Test Figure");
  }

  @Test
  @DisplayName("Test getName")
  public void testGetName() {
    assertEquals("Test", figure.getName());
  }

  @Test
  @DisplayName("Test equals")
  public void testEquals() {
    Figure figure2 = new Figure("Test", "Test Figure");
    assertEquals(figure, figure2);
  }

  @Test
  @DisplayName("Test getPath")
  public void testGetPath() {
    assertEquals("Test Figure", figure.getPath());
  }

  @Test
  @DisplayName("Test hashCode")
  void testHashCode() {
    Figure figure2 = new Figure("Test", "Test Figure");
    assertEquals(figure.hashCode(), figure2.hashCode());
  }
}
