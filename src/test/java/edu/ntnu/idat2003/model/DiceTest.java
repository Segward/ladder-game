package edu.ntnu.idat2003.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DiceTest {

  public static Dice dice;

  @BeforeAll
  @DisplayName("Setup Dice")
  public static void setup() {
    dice = new Dice(2);
  }

  @Test
  @DisplayName("Test getDie")
  public void testGetDie() {
    assertEquals(2, dice.getDie());
  }

  @Test
  @DisplayName("Test roll")
  public void testRoll() {
    int roll = dice.roll();
    assertEquals(true, roll >= 1 && roll <= 12);
  }
}
