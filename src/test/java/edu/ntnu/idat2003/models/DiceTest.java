package edu.ntnu.idat2003.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.ntnu.idat2003.old.models.Dice;

public class DiceTest {

  @Test
  public void testRoll() {
    Dice dice = new Dice(2);
    int roll = dice.roll();
    assertEquals(true, roll >= 1 && roll <= 12);
  }

  @Test
  public void testGetSides() {
    Dice dice = new Dice(6);
    assertEquals(6, dice.getDie());
  }
}
