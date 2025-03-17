package edu.ntnu.idat2003.scenes;

import edu.ntnu.idat2003.graphics.Graphics;
import edu.ntnu.idat2003.models.Vector2;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class Scene1 {

  private Pane content;

  public Scene1(Runnable onSwitchToScene2) {
    content = new Pane();
    
    // Add a listener to bind the size after the Pane is added to a Scene
    content.sceneProperty().addListener((observable, oldScene, newScene) -> {
        if (newScene != null) {
          content.prefWidthProperty().bind(newScene.widthProperty());
          content.prefHeightProperty().bind(newScene.heightProperty());
        }
      });

    Vector2 position = new Vector2(350, 250);
    Vector2 size = new Vector2(100, 50);

    Button button = Graphics.createButton("Go to Scene2", position, size, content);
    Graphics.setRelativePosition(content, button, new Vector2(0.5, 0.5));

    button.setOnAction(e -> onSwitchToScene2.run());

    content.getChildren().add(button);
  }

  public Pane getContent() {
    return content;
  }
}