package edu.ntnu.idat2003.engine;

import edu.ntnu.idat2003.models.Game;
import edu.ntnu.idat2003.services.StateService;

public class LadderGameEngine {
  private final Game game;
  private final StateService stateService;

  public LadderGameEngine(Game game) {
    this.game = game;
  }

  public void rollDice() {
    int diceValue = (int) (Math.random() * 6) + 1;
    // Move the player
  }

  public void renderBoard(BoardPane boardPane) {
    // Render the board
  }
}
