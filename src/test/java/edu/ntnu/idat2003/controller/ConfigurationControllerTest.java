package edu.ntnu.idat2003.controller;

import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ConfigurationControllerTest extends ApplicationTest{
    public static ConfigurationController configTester;
    
    @Override
    public void start(Stage stage) {
        configTester = new ConfigurationController(new BorderPane(), new HBox());
    }


    @Test
    void testRemovePlayer() {
        
    }
}
