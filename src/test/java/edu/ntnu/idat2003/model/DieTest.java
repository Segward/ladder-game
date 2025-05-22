package edu.ntnu.idat2003.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DieTest {

  public static Die die;

  @BeforeAll
  @DisplayName("Setup Die")
  public static void setup() {
    die = new Die(6);
  }

  @Test
  @DisplayName("Test getSides")
  public void testGetSides() {
    assertEquals(6, die.getSides());
  }

  @Test
  @DisplayName("Test roll")
  public void testRoll() {
    int roll = die.roll();
    assertEquals(true, roll >= 1 && roll <= 6);
  }
}
