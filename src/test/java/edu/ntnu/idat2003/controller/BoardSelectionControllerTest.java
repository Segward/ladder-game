package edu.ntnu.idat2003.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import edu.ntnu.idat2003.model.Board;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BoardSelectionControllerTest extends ApplicationTest{
    public static BoardSelectionController boardSelect;

    @Override
    public void start(Stage stage) throws Exception {
        boardSelect = new BoardSelectionController
    (new BorderPane(), new HBox(), 1);
    }
    
}
