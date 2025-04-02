package edu.ntnu.idat2003.views;

import edu.ntnu.idat2003.components.MainFrame;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class UserInterface {

  private Stage primaryStage;
  private Pane root;

  public UserInterface(Stage primaryStage) {
    this.primaryStage = primaryStage;
    this.root = new Pane();
  }

  public void init() {
    primaryStage.setTitle("Ladder Game");
    primaryStage.setMinWidth(1000);
    primaryStage.setMinHeight(600);

    root.prefWidthProperty().bind(primaryStage.widthProperty());
    root.prefHeightProperty().bind(primaryStage.heightProperty());

    root.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
    root.setId("root");
  }

  public void start() {
    MainFrame.init(root);
    primaryStage.setScene(new Scene(root));
    primaryStage.show();
  }
}
