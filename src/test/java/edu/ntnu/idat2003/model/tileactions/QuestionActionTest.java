package edu.ntnu.idat2003.model.tileactions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.ntnu.idat2003.model.Figure;
import edu.ntnu.idat2003.model.Player;
import edu.ntnu.idat2003.model.Vector2;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QuestionActionTest {

  public static QuestionAction questionAction;

  @BeforeAll
  @DisplayName("Setup QuestionAction")
  public static void setup() {
    questionAction = new QuestionAction(new Vector2(1, 2), "Test Question", "Test Answer");
  }

  @Test
  @DisplayName("Test getQuestion")
  public void testGetQuestion() {
    assertEquals("Test Question", questionAction.getQuestion());
  }

  @Test
  @DisplayName("Test getAnswer")
  public void testGetAnswer() {
    assertEquals("Test Answer", questionAction.getAnswer());
  }

  @Test
  @DisplayName("Test setGiven and getGiven")
  public void testSetAndGetGiven() {
    questionAction.setGiven(
        "tell me, you fool. if I continue to regress will I get to ever meet you again?");
    assertEquals(
        "tell me, you fool. if I continue to regress will I get to ever meet you again?",
        questionAction.getGiven());
  }

  @Test
  @DisplayName("Test getStart")
  public void testGetStart() {
    assertEquals(new Vector2(1, 2), questionAction.getStart());
  }

  @Test
  @DisplayName("Test execute")
  public void testExecute() {
    Player player = new Player("Test Player", new Figure("Test", "Test Figure"));
    questionAction.setGiven("Test Answer");
    player.setExtraDice(false);
    questionAction.execute(player);
    assertEquals(true, player.hasExtraDice());
  }
}
