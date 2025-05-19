package edu.ntnu.idat2003.view;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class UserInterface {

  private final Stage primaryStage;
  private final int WIDTH = 1000;
  private final int HEIGHT = 800;
  private final BorderPane root;

  public UserInterface(Stage primaryStage) {
    this.primaryStage = primaryStage;
    this.root = new BorderPane();
  }

  public void init() {
    primaryStage.setTitle("Board Games");
    primaryStage.setMinHeight(HEIGHT);
    primaryStage.setMinWidth(WIDTH);
    primaryStage.setResizable(false);

    MainFrame mainFrame = new MainFrame(root);
    mainFrame.init();
  }

  public void start() {
    Scene scene = new Scene(root);
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
