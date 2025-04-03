package edu.ntnu.idat2003.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ntnu.idat2003.model.Figure;
import edu.ntnu.idat2003.model.Player;
import edu.ntnu.idat2003.model.LadderAction;

public class LadderActionTest {

  Player player;

  @BeforeEach
  public void setUp() {
    player = new Player("Test", new Figure("Test"));
  }

  @Test
  public void testGetLadderAction() {
    String action = "LadderAction";
    LadderAction ladderAction = new LadderAction(action, 5);
    assertEquals(action, ladderAction.getAction());
  }

  @Test
  public void testExecute() {
    String action = "LadderAction";
    LadderAction ladderAction = new LadderAction(action, 5);
    ladderAction.execute(player);
    assertEquals(5, player.getPosition());
  }
}
