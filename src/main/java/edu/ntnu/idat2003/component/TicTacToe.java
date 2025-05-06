package edu.ntnu.idat2003.component;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class TicTacToe {
    public static void inti(Pane root) {
        root.getChildren().clear();

        HBox menuPane = new HBox();
        menuPane.prefWidthProperty().bind(root.widthProperty());
        menuPane.prefHeightProperty().bind(root.heightProperty());
        menuPane.setAlignment(Pos.CENTER);
        menuPane.setStyle("-fx-background-color: gray;");
        
    }
}
