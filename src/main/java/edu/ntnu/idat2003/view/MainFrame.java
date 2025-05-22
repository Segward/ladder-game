package edu.ntnu.idat2003.view;

import edu.ntnu.idat2003.controller.MainFrameController;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Class which builds the scene for the main menu. It doesn't include any logic but connects with a
 * controller. It is the main menu so it displays all game options to the user. It is also the first
 * scene the player views when opening the application.
 */
public class MainFrame {

  private final BorderPane root;

  /**
   * Constructor for the MainFrame class.
   *
   * @param borderPane The root pane of the application.
   */
  public MainFrame(BorderPane borderPane) {
    this.root = borderPane;
  }

  /**
   * Initializes the main menu screen. It sets up the layout and adds the necessary components,
   * including buttons for starting different games and a controller for handling game selection.
   * The different options presented to the user is starting the ladder game, quiz game, tic tac toe
   * game, configuring the game and exiting the game.
   */
  public void init() {
    root.setCenter(null);

    Text title = new Text("Omniscient Games");
    title.setFont(new Font("Arial Black", 40));

    Button startLadderGame = new Button("Start Ladder Game");
    startLadderGame.setPrefSize(400, 50);

    Button startQuizGame = new Button("Start Quiz Game");
    startQuizGame.setPrefSize(400, 50);

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
        .addAll(title, startLadderGame, startQuizGame, startTicTacToe, configureGame, exitGame);
    root.setCenter(buttonPane);

    MainFrameController mainFrameController = new MainFrameController(root);
    mainFrameController.init(
        startLadderGame, startQuizGame, startTicTacToe, configureGame, exitGame);
  }
}
