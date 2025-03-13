package edu.ntnu.idat2003.tileactions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ntnu.idat2003.old.models.Figure;
import edu.ntnu.idat2003.old.models.Player;
import edu.ntnu.idat2003.old.tileactions.LadderAction;

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
