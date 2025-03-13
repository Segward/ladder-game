package edu.ntnu.idat2003.views;

import edu.ntnu.idat2003.models.Vector2;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class Graphics {

  public static Button createButton(String text, Vector2 position, Vector2 size, Pane parent) {
    Button button = new Button(text);
    button.setLayoutX(position.getX());
    button.setLayoutY(position.getY());
    button.setPrefWidth(size.getX());
    button.setPrefHeight(size.getY());
    parent.getChildren().add(button);
    return button;
  }

  public static Rectangle createRectangle(Vector2 position, Vector2 size, Pane parent) {
    Rectangle rectangle = new Rectangle();
    rectangle.setX(position.getX());
    rectangle.setY(position.getY());
    rectangle.setWidth(size.getX());
    rectangle.setHeight(size.getY());
    parent.getChildren().add(rectangle);
    return rectangle;
  }
}