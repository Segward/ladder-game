package edu.ntnu.idat2003.controller;

import java.io.File;
import java.util.HashSet;

import edu.ntnu.idat2003.model.TicTacToe;
import edu.ntnu.idat2003.model.dice.Dice;
import edu.ntnu.idat2003.model.player.Figure;
import edu.ntnu.idat2003.model.player.Player;
import edu.ntnu.idat2003.observer.TicTacToeObserver;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class TicTacToeController implements TicTacToeObserver{
    private Pane root;
    private StackPane playerOnePanal;
    private StackPane playerTwoPanal;
    private GridPane playingBord;
    private StackPane playingPane;
    private TicTacToe game;

    public TicTacToeController(Pane root, StackPane playerOnePanal, StackPane playerTwoPanal, GridPane playingBord, StackPane playingPane) {
        this.root = root;
        this.playerOnePanal = playerOnePanal;
        this.playerTwoPanal = playerTwoPanal;
        this.playingBord = playingBord;
        this.playingPane = playingPane;
    }

    public void init() {
        HashSet<Player> players = new HashSet<>();
        players.add(new Player("PlayerOne", new Figure("Queen")));
        players.add(new Player("PlayerTwo", new Figure("King")));
        this.game = new TicTacToe(players, new Dice(1));
        updateBoard();

    }

    public void updateBoard() {
        for(int i = 0; i<3; i++) {
            for(int j = 0; j<3; j++) {
                Button tile = new Button();
                tile.setPrefSize(100, 100);
                tile.setOnAction(this::onClick);
                playingBord.add(tile, i, j);
            }
        }
    }

    public void onClick(ActionEvent event) {
        Player currentPlayer = game.getCurrentPlayer();
        if (currentPlayer.getName().equals("PlayerOne")) {
            Image xMark = new Image(getClass().getResource("/icons/xMark.png").toExternalForm());
        } else {
            Image circle = new Image(getClass().getResource("/icons/circleRed.png").toExternalForm());
        }
        ImageView iconView = new ImageView();
        
    }
}
