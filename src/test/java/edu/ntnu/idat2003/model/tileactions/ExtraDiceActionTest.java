package edu.ntnu.idat2003.model.tileactions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.ntnu.idat2003.model.Figure;
import edu.ntnu.idat2003.model.Player;
import edu.ntnu.idat2003.model.Vector2;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ExtraDiceActionTest {

  public static ExtraDiceAction extraDiceAction;

  @BeforeAll
  @DisplayName("Setup ExtraDiceAction")
  public static void setup() {
    extraDiceAction = new ExtraDiceAction(new Vector2(1, 2));
  }

  @Test
  @DisplayName("Test getStart")
  public void testGetStart() {
    assertEquals(new Vector2(1, 2), extraDiceAction.getStart());
  }

  @Test
  @DisplayName("Test execute")
  public void testExecute() {
    Player player = new Player("Test Player", new Figure("Test", "Test Figure"));
    player.setExtraDice(false);
    extraDiceAction.execute(player);
    assertEquals(true, player.hasExtraDice());
  }
}
