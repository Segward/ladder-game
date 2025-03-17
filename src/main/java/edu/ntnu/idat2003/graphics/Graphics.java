package edu.ntnu.idat2003.graphics;

import edu.ntnu.idat2003.models.Vector2;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Graphics {

  // Create a Button
  public static Button createButton(String text, Vector2 position, Vector2 size, Pane parent) {
    Button button = new Button(text);
    button.setLayoutX(position.getX());
    button.setLayoutY(position.getY());
    button.setPrefWidth(size.getX());
    button.setPrefHeight(size.getY());
    return button;
  }

  // Create a Rectangle
  public static Rectangle createRectangle(Vector2 position, Vector2 size, Pane parent) {
    Rectangle rectangle = new Rectangle();
    rectangle.setX(position.getX());
    rectangle.setY(position.getY());
    rectangle.setWidth(size.getX());
    rectangle.setHeight(size.getY());
    return rectangle;
  }

  // Create a Text Node
  public static Text createText(String text, Vector2 position, int fontSize, Pane parent) {
    Text textNode = new Text(text);
    textNode.setLayoutX(position.getX());
    textNode.setLayoutY(position.getY());
    textNode.setFont(javafx.scene.text.Font.font(fontSize));
    return textNode;
  }

  // Scale a Pane to match the primaryStage
  public static void scalePaneToStage(Pane pane, Stage primaryStage) {
    pane.prefWidthProperty().bind(primaryStage.widthProperty());
    pane.prefHeightProperty().bind(primaryStage.heightProperty());
    pane.scaleXProperty().bind(primaryStage.widthProperty().divide(primaryStage.getWidth()));
    pane.scaleYProperty().bind(primaryStage.heightProperty().divide(primaryStage.getHeight()));
  }

  // Set the relative position of a Button within its parent Pane
  public static void setRelativePosition(Pane parent, Button button, Vector2 relativePosition) {
    // Bind layoutX and layoutY properties to position the button relative to the parent
    button
        .layoutXProperty()
        .bind(
            parent
                .widthProperty()
                .multiply(relativePosition.getX())
                .subtract(button.widthProperty().divide(2)));
    button
        .layoutYProperty()
        .bind(
            parent
                .heightProperty()
                .multiply(relativePosition.getY())
                .subtract(button.heightProperty().divide(2)));
  }
}