package edu.ntnu.idat2003.component;

import edu.ntnu.idat2003.model.Game;
import edu.ntnu.idat2003.model.Board;
import edu.ntnu.idat2003.model.Figure;
import edu.ntnu.idat2003.model.Player;
import edu.ntnu.idat2003.model.Tile;
import java.util.HashSet;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class LadderGame {
  public static void init(Pane root, Board board) {
    root.getChildren().clear();

    FlowPane menuPane = new FlowPane();
    menuPane.prefWidthProperty().bind(root.widthProperty());
    menuPane.prefHeightProperty().bind(root.heightProperty());
    menuPane.setAlignment(Pos.CENTER);
    menuPane.setStyle("-fx-background-color: gray;");
    menuPane.setOrientation(Orientation.VERTICAL);
    root.getChildren().add(menuPane);

    HBox hBox = new HBox();
    root.getChildren().add(hBox);

    FlowPane boardPane = new FlowPane();
    hBox.getChildren().add(boardPane);
    boardPane.setStyle("-fx-background-color: WHITE;");
    boardPane.prefWidthProperty().bind(root.widthProperty().multiply(0.7));
    boardPane.prefHeightProperty().bind(root.heightProperty());
    boardPane.setOrientation(Orientation.VERTICAL);
    boardPane.setAlignment(Pos.CENTER);

    FlowPane playerPane = new FlowPane();
    hBox.getChildren().add(playerPane);
    playerPane.setStyle("-fx-background-color: beige;");
    playerPane.prefWidthProperty().bind(root.widthProperty().multiply(0.3));
    playerPane.prefHeightProperty().bind(root.heightProperty());
    playerPane.setOrientation(Orientation.VERTICAL);

    // Game game = new Game(board, players);
    // LadderGameService.renderBoard(boardPane, board);
    // LadderGameService.renderPlayers(playerPane, players);
    // Button.onAction(e -> LadderGameService.rollDice());

    GridPane bord = new GridPane();
    bord.setPadding(new Insets(5, 5, 5, 5));
    bord.setVgap(5);
    bord.setHgap(5);
    bord.setStyle("-fx-background-color: gray");

    int tileNum = 90;
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 10; j++) {
        FlowPane gameTile = new FlowPane();
        gameTile.setAlignment(Pos.CENTER);
        gameTile.setPrefWidth(50);
        gameTile.setPrefHeight(50);
        Text text = new Text(String.valueOf(tileNum));
        tileNum--;

        gameTile.setStyle("-fx-background-color: WHITE;");
        gameTile.getChildren().add(text);
        bord.add(gameTile, j, i);
      }
    }

    boardPane.getChildren().addAll(bord);

    Button endGameButton = new Button("End game");
    playerPane.getChildren().add(endGameButton);

    Text text = new Text("Hello, World!");
    playerPane.getChildren().add(text);

    Button rollDiceButton = new Button("Roll Dice");
    playerPane.getChildren().add(rollDiceButton);

    // rollDiceButton.setOnAction(e -> LadderGameService.rollDice(text));
    endGameButton.setOnAction(e -> MainFrame.init(root));
  }
}
