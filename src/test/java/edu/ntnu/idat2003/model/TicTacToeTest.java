package edu.ntnu.idat2003.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TicTacToeTest {

  @Test
  @DisplayName("Test rollDice")
  void testRollDice() {
    ArrayList<Player> players = new ArrayList<>();
    TicTacToe game = new TicTacToe(players, new Dice(1));
    int diceRoll = game.rollDice();
    assertTrue(diceRoll >= 1 && diceRoll <= 6);
  }
}
