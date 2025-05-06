package edu.ntnu.idat2003.controller;

import edu.ntnu.idat2003.observer.TicTacToeObserver;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class TicTacToeController implements TicTacToeObserver{
    private Pane root;
    private Button tile;
    private StackPane playerOnePanal;
    private StackPane playerTwoPanal;
    private GridPane playingBord;
    private StackPane playingPane;
    

    public TicTacToeController(Pane root, StackPane playerOnePanal, StackPane playerTwoPanal, GridPane playingBord, StackPane playingPane) {
        this.root = root;
        this.playerOnePanal = playerOnePanal;
        this.playerTwoPanal = playerTwoPanal;
        this.playingBord = playingBord;
        this.playingPane = playingPane;
    }

    public void init() {
        updateBoard();
    }

    public void updateBoard() {
        for(int i = 0; i<3; i++) {
            for(int j = 0; j<3; j++) {
                Button newTile = new Button();
                newTile.setPrefSize(100, 100);
                playingBord.add(newTile, i, j);
            }
        }
    }
}
