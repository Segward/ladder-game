package edu.ntnu.idat2003;

import edu.ntnu.idat2003.view.UserInterface;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

  @Override
  public void start(Stage primaryStage) {
    UserInterface ui = new UserInterface(primaryStage);
    ui.init();
    ui.start();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
