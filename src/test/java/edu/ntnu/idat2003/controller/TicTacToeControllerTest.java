package edu.ntnu.idat2003.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import org.testfx.framework.junit5.ApplicationTest;

import edu.ntnu.idat2003.model.Vector2;
import edu.ntnu.idat2003.model.player.Player;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TicTacToeControllerTest extends ApplicationTest {
    private TicTacToeController testGame;

    @Override
    public void start(Stage stage) {
        Pane testPane = new Pane();
        Text testText = new Text();
        Button testButton = new Button();
        StackPane testStackPane = new StackPane();
        GridPane testGridPane = new GridPane();
        VBox testVBox = new VBox();

        testGame = new TicTacToeController(testPane, testText, 
        testText, testText, testButton, testStackPane, testStackPane, testGridPane, testVBox);
    }

    @Test
    void testCreateBoard() {
        
    }

    @Test
    void testDiceImage() {

    }

    @Test
    void testDisableAll() {

    }

    @Test
    void testGameStartSetup() {

    }

    @Test
    void testGameStatus() {

    }

    @Test
    void testInit() {

    }

    @Test
    void testOnClick() {

    }

    @Test
    void testRetreveTiles() {

    }

    @Test
    void testSetNextPlayer() {

    }

    @Test
    void testSetPlayersScore() {

    }

    @Test
    void testWin() {
        testGame.createBoard();
        testGame.retreveTiles();
        HashMap<Vector2, Button> tiles = testGame.getResultHash();
        
        for(Node node : testGame.getResultHash().values()) {
            if(node instanceof Button) {
                Button tile = (Button) node;
                tile.setUserData("X");
            }
        }
        assertFalse(testGame.win() instanceof Player);
    }
}
