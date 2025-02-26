package edu.ntnu.idat2003.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DiceTest {

  @Test
  public void testRoll() {
    Dice dice = new Dice(6);
    int roll = dice.roll();
    assertEquals(true, roll >= 1 && roll <= 6);
  }

  @Test
  public void testGetSides() {
    Dice dice = new Dice(6);
    assertEquals(6, dice.getSides());
  }
}
