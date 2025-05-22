package edu.ntnu.idat2003.controller;

import org.testfx.framework.junit5.ApplicationTest;

import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class PlayerCreationControllerTest extends ApplicationTest{
    public static PlayerCreationController playerCreationTester;

    @Override
    public void start(Stage stage) {
        playerCreationTester = new PlayerCreationController
        (new BorderPane(), new HBox(), new TextField());
    }


}
