package edu.ntnu.idat2003.views;

import edu.ntnu.idat2003.models.Vector2;
import java.util.HashSet;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class UserInterface {

  private Stage primaryStage;
  private HashSet<Rectangle> rectangles;

  public UserInterface(Stage primaryStage) {
    this.primaryStage = primaryStage;
  }

  public void init() {
    primaryStage.setTitle("Ladder Game");
    primaryStage.setMinWidth(400);
    primaryStage.setMinHeight(400);
    rectangles = new HashSet<>();
  }

  public void start() {
    Pane root = new Pane();
    root.setPrefSize(800, 600);

    Vector2 position = new Vector2(0, 0);
    Vector2 size = new Vector2(100, 100);

    Rectangle rectangle = new Rectangle();
    rectangle.setX(position.getX());
    rectangle.setY(position.getY());
    rectangle.setWidth(size.getX());
    rectangle.setHeight(size.getY());
    root.getChildren().add(rectangle);

    rectangles.add(rectangle);

    Scene scene = new Scene(root);
    primaryStage.setScene(scene);
    primaryStage.show();

    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(16.67), event -> update()));
    timeline.setCycleCount(Timeline.INDEFINITE);
    timeline.play();
  }

  private void update() {
    for (Rectangle rectangle : rectangles) {
      rectangle.setX(rectangle.getX() + 1);
    }
  }
}