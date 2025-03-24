package edu.ntnu.idat2003.components;

import javafx.scene.layout.StackPane;
import javafx.scene.control.Button;

public class MainFrame {
  public static void init(StackPane stackPane) {
    // Clear the stack pane
    stackPane.getChildren().clear();
    
    // Add a button to the stack pane
    Button button = new Button("Click me!");
    
    stackPane.getChildren().add(button);

  }    
}
