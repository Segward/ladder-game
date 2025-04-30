package edu.ntnu.idat2003.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import edu.ntnu.idat2003.model.player.Figure;
import edu.ntnu.idat2003.model.player.Player;
import edu.ntnu.idat2003.model.tileactions.ExtraDiceAction;

public class ExtraDiceActionTest {

  public static ExtraDiceAction extraDiceAction;

  @BeforeAll
  public static void setup() {
    extraDiceAction = new ExtraDiceAction(new Vector2(1, 2));
  }

  @Test
  public void testGetStart() {
    assertEquals(new Vector2(1, 2), extraDiceAction.getStart());
  }

  @Test
  public void testExecute() {
    Player player = new Player("Test Player", new Figure("Test Figure"));
    player.setExtraDice(false);
    extraDiceAction.execute(player);
    assertEquals(true, player.hasExtraDice());
  }
}
