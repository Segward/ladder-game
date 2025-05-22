package edu.ntnu.idat2003.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Vector2Test {

  @Test
  @DisplayName("Test getX")
  public void testGetX() {
    Vector2 vector = new Vector2(1, 2);
    assertEquals(1, vector.getX());
  }

  @Test
  @DisplayName("Test getY")
  public void testGetY() {
    Vector2 vector = new Vector2(1, 2);
    assertEquals(2, vector.getY());
  }

  @Test
  @DisplayName("Test setX")
  public void testSetX() {
    Vector2 vector = new Vector2(1, 2);
    vector.setX(3);
    assertEquals(3, vector.getX());
  }

  @Test
  @DisplayName("Test setY")
  public void testSetY() {
    Vector2 vector = new Vector2(1, 2);
    vector.setY(4);
    assertEquals(4, vector.getY());
  }

  @Test
  @DisplayName("Test set")
  public void testSet() {
    Vector2 vector = new Vector2(1, 2);
    Vector2 newVector = new Vector2(5, 6);
    vector.set(newVector);
    assertEquals(5, vector.getX());
    assertEquals(6, vector.getY());
  }

  @Test
  @DisplayName("Test add")
  public void testAdd() {
    Vector2 vector1 = new Vector2(1, 2);
    Vector2 vector2 = new Vector2(3, 4);
    Vector2 result = vector1.add(vector2);
    assertEquals(4, result.getX());
    assertEquals(6, result.getY());
  }

  @Test
  @DisplayName("Test equals")
  public void testEquals() {
    Vector2 vector1 = new Vector2(1, 2);
    Vector2 vector2 = new Vector2(1, 2);
    assertEquals(vector1, vector2);
  }

  @Test
  @DisplayName("Test hashCode")
  public void testHashCode() {
    Vector2 vector = new Vector2(1, 2);
    int expectedHashCode = 1 + 2 * 100;
    assertEquals(expectedHashCode, vector.hashCode());
  }
}
