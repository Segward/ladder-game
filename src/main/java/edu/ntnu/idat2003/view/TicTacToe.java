package edu.ntnu.idat2003.view;

import edu.ntnu.idat2003.controller.TicTacToeController;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
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
   * Intizilases the panes and adds them to the root scene. It sets up the different panes for each
   * player, a pane for the buttons and a gridpane for tictactoe.
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
    playerOnePanal.setId("playerPane");

    StackPane playerTwoPanal = new StackPane();
    playerTwoPanal.prefWidthProperty().bind(root.widthProperty().multiply(0.2));
    playerTwoPanal.prefHeightProperty().bind(root.heightProperty());
    playerTwoPanal.setId("playerPane");

    FlowPane buttonPane = new FlowPane();
    buttonPane.prefWidthProperty().bind(root.widthProperty().multiply(0.6));
    buttonPane.prefHeightProperty().bind(root.heightProperty());
    buttonPane.setOrientation(Orientation.VERTICAL);
    buttonPane.setAlignment(Pos.CENTER);
    buttonPane.setVgap(20);

    GridPane playingBoard = new GridPane();
    playingBoard.setPadding(new Insets(5, 5, 5, 5));
    playingBoard.setVgap(5);
    playingBoard.setHgap(5);
    playingBoard.setAlignment(Pos.CENTER);
    buttonPane.setId("ticTacToeBoard");

    Text gameText = new Text("TicTackToe");
    gameText.setStyle("-fx-font-size: 30px; -fx-fill: WHITE; ");
    gameText.setId("ticTacToeGameText");

    Text playerOneScore = new Text();
    playerOneScore.setStyle("-fx-font-size: 30px; -fx-fill: WHITE; -fx-font-weight: bold; ");

    Text playerTwoScore = new Text();
    playerTwoScore.setStyle("-fx-font-size: 30px;  -fx-fill: WHITE; -fx-font-weight: bold; ");

    Button exitButton = new Button("Exit the game");
    exitButton.setPrefSize(400, 50);

    Button playAgainButton = new Button("Play again");
    playAgainButton.setPrefSize(400, 50);

    playerOnePanal.getChildren().add(playerOneScore);
    playerTwoPanal.getChildren().add(playerTwoScore);
    buttonPane.getChildren().addAll(gameText, playingBoard, exitButton, playAgainButton);
    menuPane.getChildren().addAll(playerOnePanal, buttonPane, playerTwoPanal);
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
            playAgainButton);
    controller.init();
  }
}
