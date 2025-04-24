package edu.ntnu.idat2003.component;

import edu.ntnu.idat2003.controller.LadderGameController;
import edu.ntnu.idat2003.model.Board;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
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
    boardPane.prefWidthProperty().bind(root.widthProperty().multiply(0.7));
    boardPane.prefHeightProperty().bind(root.heightProperty());
    boardPane.setOrientation(Orientation.VERTICAL);
    boardPane.setAlignment(Pos.CENTER);

    Image backImage = new Image("testBack.png");
    BackgroundImage background = new BackgroundImage(
      backImage,
       BackgroundRepeat.REPEAT,
       BackgroundRepeat.ROUND,
         BackgroundPosition.CENTER,
          null);
    boardPane.setBackground(new Background(background));

    FlowPane playerPane = new FlowPane();
    hBox.getChildren().add(playerPane);
    playerPane.setStyle("-fx-background-color: beige;");
    playerPane.prefWidthProperty().bind(root.widthProperty().multiply(0.3));
    playerPane.prefHeightProperty().bind(root.heightProperty());
    playerPane.setOrientation(Orientation.VERTICAL);
    

    GridPane bord = new GridPane();
    bord.setPadding(new Insets(5, 5, 5, 5));
    bord.setVgap(5);
    bord.setHgap(5);
    bord.setStyle("-fx-background-color: gray");
    boardPane.getChildren().add(bord);
    
    Button endGameButton = new Button("End game");
    playerPane.getChildren().add(endGameButton);

    Text text = new Text("Press the die to roll!");
    text.setFont(new Font(32));
    playerPane.getChildren().add(text);

    Image diceImage =
        new Image(LadderGame.class.getResource("/firstDice.png").toExternalForm());
    ImageView diceView = new ImageView(diceImage);
    diceView.setFitHeight(100);
    diceView.setPreserveRatio(true);

    Button rollDiceButton = new Button();
    rollDiceButton.setGraphic(diceView);
    rollDiceButton.setPrefSize(50, 50);
    rollDiceButton.setStyle("-fx-background-color: transparent;");
    playerPane.getChildren().add(rollDiceButton);

    text.setTranslateX(150);
    text.setTranslateY(150);

    rollDiceButton.setTranslateX(175);
    rollDiceButton.setTranslateY(200);

    endGameButton.setTranslateX(250);
    endGameButton.setTranslateY(550);

    LadderGameController controller =
        new LadderGameController(root, board, text, bord, rollDiceButton, endGameButton);
    controller.init();
  }
}
