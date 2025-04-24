package edu.ntnu.idat2003.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Vector2Test {

  @Test
  public void testGetX() {
    Vector2 vector = new Vector2(1, 2);
    assertEquals(1, vector.getX());
  }

  @Test
  public void testGetY() {
    Vector2 vector = new Vector2(1, 2);
    assertEquals(2, vector.getY());
  }

  @Test
  public void testSetX() {
    Vector2 vector = new Vector2(1, 2);
    vector.setX(3);
    assertEquals(3, vector.getX());
  }

  @Test
  public void testSetY() {
    Vector2 vector = new Vector2(1, 2);
    vector.setY(4);
    assertEquals(4, vector.getY());
  }

  @Test
  public void testSet() {
    Vector2 vector = new Vector2(1, 2);
    Vector2 newVector = new Vector2(5, 6);
    vector.set(newVector);
    assertEquals(5, vector.getX());
    assertEquals(6, vector.getY());
  }

  @Test
  public void testGetNumber() {
    Vector2 vector = new Vector2(5, 6);
    assertEquals(66, vector.getNumber());

    Vector2 vector2 = new Vector2(4, 7);
    assertEquals(76, vector2.getNumber());
  }
}
