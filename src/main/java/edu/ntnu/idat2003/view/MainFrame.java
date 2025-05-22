package edu.ntnu.idat2003.view;

import edu.ntnu.idat2003.controller.MainFrameController;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MainFrame {

  private final BorderPane root;

  public MainFrame(BorderPane borderPane) {
    this.root = borderPane;
  }

  public void init() {
    root.setCenter(null);

    Text title = new Text("Omniscient Games");
    title.setFont(new Font("Arial Black", 40));

    Button startLadderGame = new Button("Start Ladder Game");
    startLadderGame.setPrefSize(400, 50);

    Button startPartyGame = new Button("Start Party Game");
    startPartyGame.setPrefSize(400, 50);

    Button startTicTacToe = new Button("Start Tic Tac Toe");
    startTicTacToe.setPrefSize(400, 50);

    Button configureGame = new Button("Configure Game");
    configureGame.setPrefSize(400, 50);

    Button exitGame = new Button("Exit Game");
    exitGame.setPrefSize(400, 50);

    FlowPane buttonPane = new FlowPane();
    buttonPane.setOrientation(Orientation.VERTICAL);
    buttonPane.setAlignment(Pos.CENTER);
    buttonPane.setVgap(20);
    buttonPane
        .getChildren()
        .addAll(title, startLadderGame, startPartyGame, startTicTacToe, configureGame, exitGame);
    root.setCenter(buttonPane);

    MainFrameController mainFrameController = new MainFrameController(root);
    mainFrameController.init(
        startLadderGame, startPartyGame, startTicTacToe, configureGame, exitGame);
  }
}
