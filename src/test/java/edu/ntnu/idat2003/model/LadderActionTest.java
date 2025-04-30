package edu.ntnu.idat2003.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.ntnu.idat2003.model.player.Figure;
import edu.ntnu.idat2003.model.player.Player;
import edu.ntnu.idat2003.model.tileactions.LadderAction;

import org.junit.jupiter.api.BeforeAll;

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
        Player player = new Player("Test Player", new Figure("Test Figure"));
        player.setPosition(new Vector2(1, 2));
        ladderAction.execute(player);
        assertEquals(new Vector2(3, 4), player.getPosition());
    }
}
