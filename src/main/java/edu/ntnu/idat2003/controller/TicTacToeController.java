package edu.ntnu.idat2003.controller;

import java.io.File;
import java.util.ArrayList;
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
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("PlayerOne", new Figure("Queen")));
        players.add(new Player("PlayerTwo", new Figure("King")));
        this.game = new TicTacToe(players, new Dice(1));
        
        int randStart = game.rollDice();
        if(randStart >3) {
            game.setCurrentPlayer(players.get(1));
        }else {
            game.setCurrentPlayer(players.get(0));
        }

        updateBoard();

    }

    public void updateBoard() {
        for(int i = 0; i<3; i++) {
            for(int j = 0; j<3; j++) {
                Button tile = new Button();
                tile.setPrefSize(100, 100);
                tile.setOnAction(e -> onClick(tile));
                playingBord.add(tile, i, j);
            }
        }
    }

    @Override
    public void onClick(Button tile) {
        Player currentPlayer = game.getCurrentPlayer();
        ImageView iconView = new ImageView();
        iconView.setFitHeight(tile.getHeight()*0.8);
        iconView.setFitWidth(tile.getWidth()*0.8);
        if (currentPlayer.getName().equals("PlayerOne")) {
            Image xMark = new Image(getClass().getResource("/icons/xMark.png").toExternalForm());
            iconView.setImage(xMark);
        } else {
            Image circle = new Image(getClass().getResource("/icons/circleRed.png").toExternalForm());
            iconView.setImage(circle);
        }
        tile.setGraphic(iconView);
        tile.setDisable(true);
        tile.setStyle("-fx-opacity: 1");
        
        setNextPlayer();
    }

    public void setNextPlayer() {
        Player currentPlayer = game.getCurrentPlayer();
        if(currentPlayer.getName().equals(game.getPlayers().stream().findFirst().get().getName())) {
            game.setCurrentPlayer(game.getPlayers().get(1));
        }else {
            game.setCurrentPlayer(game.getPlayers().stream().findFirst().get());
        }
    }
}
