package edu.ntnu.idat2003.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.ntnu.idat2003.model.Player;
import edu.ntnu.idat2003.model.Vector2;
import java.util.HashMap;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

public class TicTacToeControllerTest extends ApplicationTest {
  private TicTacToeController testGame;

  @Override
  public void start(Stage stage) {
    BorderPane basePane = new BorderPane();
    Text testText = new Text();
    Button testButton = new Button();
    StackPane testStackPane = new StackPane();
    GridPane testGridPane = new GridPane();
    VBox testVBox = new VBox();

    testGame =
        new TicTacToeController(
            basePane,
            testText,
            testText,
            testText,
            testButton,
            testStackPane,
            testStackPane,
            testGridPane,
            testVBox);
  }

  @Test
  void testCreateBoard() {
    testGame.createBoard();
    testGame.retreveTiles();
    int tileNum = 0;
    for (Node node : testGame.getResultHash().values()) {
      if (node instanceof Button) {
        tileNum++;
      }
    }
    assertTrue(tileNum == 9);
  }

  @Test
  void testDisableAll() {
    testGame.createBoard();
    testGame.disableAll();
    testGame.retreveTiles();
    int tileDisableNumb = 0;

    for (Node node : testGame.getResultHash().values()) {
      if (node instanceof Button) {
        if (node.isDisable()) {
          tileDisableNumb++;
        }
      }
    }
    assertEquals(tileDisableNumb, 9);
  }

  @Test
  void testInitFalse() {
    testGame.init();
    assertFalse(testGame.getGame().getPlayers().isEmpty());
  }

  @Test
  void testInitTrue() {
    testGame.init();
    assertTrue(!testGame.getGame().getPlayers().isEmpty());
  }

  @Test
  void testSetNextPlayerFasle() {
    testGame.init();
    Player currentPlayer = testGame.getGame().getCurrentPlayer();
    testGame.setNextPlayer();
    assertFalse(currentPlayer == testGame.getGame().getCurrentPlayer());
  }

  @Test
  void testSetNextPlayerTrue() {
    testGame.init();
    Player currentPlayer = testGame.getGame().getCurrentPlayer();
    testGame.setNextPlayer();
    assertTrue(currentPlayer != testGame.getGame().getCurrentPlayer());
  }

  @Test
  void testWinTrue() {
    testGame.init();
    testGame.createBoard();
    testGame.retreveTiles();
    HashMap<Vector2, Button> tiles = testGame.getResultHash();

    for (Node node : testGame.getResultHash().values()) {
      if (node instanceof Button) {
        Button tile = (Button) node;
        tile.setUserData("X");
      }
    }
    assertTrue(testGame.win() instanceof Player);
  }

  @Test
  void testWinFalse() {
    testGame.init();
    testGame.createBoard();
    testGame.retreveTiles();
    HashMap<Vector2, Button> tiles = testGame.getResultHash();

    for (Node node : testGame.getResultHash().values()) {
      if (node instanceof Button) {
        Button tile = (Button) node;
        tile.setUserData("");
      }
    }
    assertFalse(testGame.win() instanceof Player);
  }
}
