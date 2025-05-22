package edu.ntnu.idat2003.model.tileactions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.ntnu.idat2003.model.Figure;
import edu.ntnu.idat2003.model.Player;
import edu.ntnu.idat2003.model.Vector2;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class LadderActionTest {

  public static LadderAction ladderAction;

  @BeforeAll
  public static void setup() {
    ladderAction = new LadderAction(new Vector2(1, 2), new Vector2(3, 4));
  }

  @Test
  public void testGetStart() {
    assertEquals(new Vector2(1, 2), ladderAction.getStart());
  }

  @Test
  public void testGetDestination() {
    assertEquals(new Vector2(3, 4), ladderAction.getDestination());
  }

  @Test
  public void testGetDirection() {
    assertEquals("up", ladderAction.getDirection());
  }

  @Test
  public void testExecute() {
    Player player = new Player("Test Player", new Figure("Test", "Test Figure"));
    player.setPosition(new Vector2(1, 2));
    ladderAction.execute(player);
    assertEquals(new Vector2(3, 4), player.getPosition());
  }
}
