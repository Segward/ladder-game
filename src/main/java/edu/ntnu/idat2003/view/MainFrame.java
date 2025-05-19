package edu.ntnu.idat2003.view;

import edu.ntnu.idat2003.controller.MainFrameController;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

public class MainFrame {

  private final BorderPane root;

  public MainFrame(BorderPane borderPane) {
    this.root = borderPane;
  }

  public void init() {
    root.setCenter(null);

    Button startLadderGame = new Button("Start Ladder Game");
    startLadderGame.setPrefSize(200, 50);

    Button startPartyGame = new Button("Start Party Game");
    startPartyGame.setPrefSize(200, 50);

    Button startTicTacToeGame = new Button("Start Tic Tac Toe Game");
    startTicTacToeGame.setPrefSize(200, 50);

    Button exitGame = new Button("Exit Game");
    exitGame.setPrefSize(200, 50);

    FlowPane buttonPane = new FlowPane();
    buttonPane.setOrientation(Orientation.VERTICAL);
    buttonPane.setAlignment(Pos.CENTER);
    buttonPane.setVgap(10);
    buttonPane.getChildren().addAll(startLadderGame, startPartyGame, startTicTacToeGame, exitGame);
    root.setCenter(buttonPane);

    MainFrameController mainFrameController = new MainFrameController(root);
    mainFrameController.init(startLadderGame, startPartyGame, startTicTacToeGame, exitGame);
  }
}
