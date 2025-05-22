package edu.ntnu.idat2003.view;

import edu.ntnu.idat2003.controller.TicTacToeController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Class representing the visal panes used in the TicTacToe game. The class constructes the
 * different panes, text and buttons which will be displayed during the TicTacToe.
 */
public class TicTacToe {

  private final BorderPane root;

  public TicTacToe(BorderPane borderPane) {
    this.root = borderPane;
  }

  /**
   * Intizilases the panes and adds them to the root scene.
   *
   * @param root Main scene
   */
  public void init() {
    root.getChildren().clear();

    HBox menuPane = new HBox();
    menuPane.prefWidthProperty().bind(root.widthProperty());
    menuPane.prefHeightProperty().bind(root.heightProperty());
    menuPane.setAlignment(Pos.CENTER);

    StackPane playerOnePanal = new StackPane();
    playerOnePanal.prefWidthProperty().bind(root.widthProperty().multiply(0.2));
    playerOnePanal.prefHeightProperty().bind(root.heightProperty());
    playerOnePanal.setId("playerOnePanal");

    StackPane playerTwoPanal = new StackPane();
    playerTwoPanal.prefWidthProperty().bind(root.widthProperty().multiply(0.2));
    playerTwoPanal.prefHeightProperty().bind(root.heightProperty());
    playerTwoPanal.setId("playerTwoPanal");

    VBox playingPane = new VBox();
    playingPane.prefWidthProperty().bind(root.widthProperty().multiply(0.6));
    playingPane.prefHeightProperty().bind(root.heightProperty());
    playingPane.setStyle("-fx-background-color: gray;");
    playingPane.setAlignment(Pos.CENTER);

    GridPane playingBoard = new GridPane();
    playingBoard.setPadding(new Insets(5, 5, 5, 5));
    playingBoard.setVgap(5);
    playingBoard.setHgap(5);
    playingBoard.setAlignment(Pos.CENTER);
    playingPane.setId("ticTacToeBoard");

    Text gameText = new Text("TicTackToe");
    gameText.setId("ticTacToeGameText");

    Text playerOneScore = new Text();
    playerOneScore.setId("ticTacToeGameText");

    Text playerTwoScore = new Text();
    playerTwoScore.setId("ticTacToeGameText");

    Button exitButton = new Button("Exit the game");

    playerOnePanal.getChildren().add(playerOneScore);
    playerTwoPanal.getChildren().add(playerTwoScore);
    playingPane.getChildren().addAll(gameText, playingBoard, exitButton);
    menuPane.getChildren().addAll(playerOnePanal, playingPane, playerTwoPanal);
    root.setCenter(menuPane);

    TicTacToeController controller =
        new TicTacToeController(
            root,
            gameText,
            playerOneScore,
            playerTwoScore,
            exitButton,
            playerOnePanal,
            playerTwoPanal,
            playingBoard,
            playingPane);
    controller.init();
  }
}
