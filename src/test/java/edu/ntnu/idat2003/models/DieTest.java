package edu.ntnu.idat2003.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DieTest {

  @Test
  public void testRoll() {
    Die die = new Die(6);
    int roll = die.roll();
    assertEquals(true, roll >= 1 && roll <= 6);
  }

  @Test
  public void testGetSides() {
    Die die = new Die(6);
    assertEquals(6, die.getSides());
  }
}
