package edu.ntnu.idat2003.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import edu.ntnu.idat2003.component.MainFrame;
import edu.ntnu.idat2003.model.TicTacToe;
import edu.ntnu.idat2003.model.Vector2;
import edu.ntnu.idat2003.model.dice.Dice;
import edu.ntnu.idat2003.model.player.Figure;
import edu.ntnu.idat2003.model.player.Player;
import edu.ntnu.idat2003.observer.TicTacToeObserver;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class TicTacToeController implements TicTacToeObserver{
    private Pane root;
    private StackPane playerOnePanal;
    private StackPane playerTwoPanal;
    private GridPane playingBord;
    private VBox playingPane;
    private TicTacToe game;
    private Text gameText;
    private Text playerOneScoreText;
    private Text playerTwoScoreText;
    private Button exitButton;
    private HashMap<Vector2, Button> resultHash;
    private int roundNumber = 1;

    public TicTacToeController(Pane root, Text gameText, Text playerOneScoreText, Text playerTwoScoreText, Button exitButton, StackPane playerOnePanal, StackPane playerTwoPanal, GridPane playingBord, VBox playingPane) {
        this.root = root;
        this.playerOnePanal = playerOnePanal;
        this.playerTwoPanal = playerTwoPanal;
        this.playingBord = playingBord;
        this.playingPane = playingPane;
        this.gameText = gameText;
        this.playerOneScoreText = playerOneScoreText;
        this.playerTwoScoreText = playerTwoScoreText;
        this.exitButton = exitButton;
    }

    public void init() {
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("PlayerOne", new Figure("Queen")));
        players.add(new Player("PlayerTwo", new Figure("King")));
        this.game = new TicTacToe(players, new Dice(1));
        
        gameStartSetup();
    }

    public void gameStartSetup() {
        String playerOneImage = getClass().getResource("/figure/" + game.getPlayers().stream().
        findFirst().get().getFigure().getName() + ".png").toExternalForm();
        playerOnePanal.setStyle("-fx-background-image: url('" + playerOneImage + "'); ");

        String playerTwoImage = getClass().getResource("/figure/" + game.getPlayers().get(1).getFigure().getName() +".png").toExternalForm();
        playerTwoPanal.setStyle("-fx-background-image: url('" + playerTwoImage + "'); ");

        exitButton.setOnAction(e -> MainFrame.init(root));
        
        int randStart = game.rollDice();

        if(randStart >3) {
            game.setCurrentPlayer(game.getPlayers().get(1));
        }else {
            game.setCurrentPlayer(game.getPlayers().get(0));
        }

        Timeline timeline = new Timeline();
        KeyFrame keyFrame =
            new KeyFrame(Duration.millis(10), e -> diceImage((int) (Math.random() * 6) +1));
        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(50);
        timeline.play();
        timeline.setOnFinished(e -> {
            diceImage(randStart);
            Button startGame = new Button("Start Game");
            startGame.setOnAction(action -> createBoard());
            playingBord.add(startGame, 0, 2);
            gameText.setText(game.getCurrentPlayer().getName() + " starts!!");
        }
        );
    }

    public void setPlayersScore() {
        playerOneScoreText.setText(game.getPlayers().stream().findFirst().get().getName() +" Score: " + game.getPlayerOneScore());
        playerTwoScoreText.setText(game.getPlayers().get(1).getName() + " Score: " + game.getPlayerTwoScore());
    }
 
    /**
     *  Mehtod for changing the die image
     * 
     * @param diceSide
     */
    public void diceImage(int diceSide){
        playingBord.getChildren().clear();
        ImageView diceView = new ImageView();
        diceView.setFitHeight(200);
        diceView.setPreserveRatio(true);

        Image diceImage =
            new Image(getClass().getResource("/dice/" + diceSide + "face.png").toExternalForm());
        diceView.setImage(diceImage);
        playingBord.getChildren().add(diceView);
    }

    public void createBoard() {
        playingBord.getChildren().clear();
        roundNumber = 0;
        for(int i = 0; i<3; i++) {
            for(int j = 0; j<3; j++) {
                Button tile = new Button();
                tile.setPrefSize(100, 100);
                tile.setOnAction(e -> onClick(tile));
                tile.setDisable(false);
                playingBord.add(tile, i, j);
            }
        }
        setPlayersScore();
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
            tile.setUserData("X");
        } else {
            Image circle = new Image(getClass().getResource("/icons/circleRed.png").toExternalForm());
            iconView.setImage(circle);
            tile.setUserData("O");
        }
        tile.setGraphic(iconView);
        
        tile.setDisable(true);
        tile.setStyle("-fx-opacity: 1");

        setNextPlayer();
        gameStatus();
    }   

    @Override
    public void gameStatus() {
        gameScoreCheck();
        if(roundNumber >= 8 && win() == null) {
            gameText.setText("It is a TIE!!!");
            Button newGame = new Button("New game");
            newGame.setOnAction(e -> init());
            playingBord.add(newGame, 1, 1);
        } else {
            gameText.setText(game.getCurrentPlayer().getName() + "! it is your turn!");
            roundNumber++;
            if(win() != null){
                disableAll();
                gameText.setText(win().getName() + " Has Won!!");
                if(win().getName().equals(game.getPlayers().stream().findFirst().get().getName())) {
                    game.setPlayerOneScore(game.getPlayerOneScore() +1);
                } else {
                    game.setPlayerTwoScore(game.getPlayerTwoScore() +1);
                }
                Button newGame = new Button("New game");
                newGame.setOnAction(e -> gameStartSetup());
                playingBord.add(newGame, 1, 1);
            }
        }
        setPlayersScore();
        
    }

    public void disableAll() {
        for(Node node : playingBord.getChildren()) {
            if(node instanceof Button) {
                node.setDisable(true);
            }
        }
    }

    public void setNextPlayer() {
        Player currentPlayer = game.getCurrentPlayer();
        if(currentPlayer.getName().equals(game.getPlayers().stream().findFirst().get().getName())) {
            game.setCurrentPlayer(game.getPlayers().get(1));
        }else {
            game.setCurrentPlayer(game.getPlayers().stream().findFirst().get());
        }
    }

    @Override
    public boolean gameScoreCheck() {  
        this.resultHash = new HashMap<>();

        for(Node node : playingBord.getChildren()) {
            if(node instanceof Button) {
                int row = GridPane.getRowIndex(node); //X
                int colum = GridPane.getColumnIndex(node); //Y
                
                Button tile = (Button) node;

                if(tile.getUserData() != null) {
                    resultHash.put(new Vector2(row, colum), tile);
                }
                
            }
        }
        return false;
    }

    public Player win() {
        String row1 = "";
        String row2 = "";
        String row3 = "";

        String colum1 = "";
        String colum2 = "";
        String colum3 = "";

        String line1 = "";
        String line2 = "";

        for(Vector2 placment : resultHash.keySet()) {
            switch (placment.getX()) {
                case 0:
                    if(placment.getY() == 0) {
                        row1 += resultHash.get(placment).getUserData();
                        colum1 += resultHash.get(placment).getUserData();
                        line1 += resultHash.get(placment).getUserData();
                    } if(placment.getY() == 1) {
                        row1 += resultHash.get(placment).getUserData();
                        colum2 += resultHash.get(placment).getUserData();
                    } if(placment.getY() == 2) {
                        row1 += resultHash.get(placment).getUserData();
                        colum3 += resultHash.get(placment).getUserData();
                        line2 += resultHash.get(placment).getUserData();
                    } 
                    break;
                case 1:
                    if(placment.getY() == 0) {
                        row2 += resultHash.get(placment).getUserData();
                        colum1 += resultHash.get(placment).getUserData();
                    } if(placment.getY() == 1) {
                        row2 += resultHash.get(placment).getUserData();
                        colum2 += resultHash.get(placment).getUserData();
                        line1 += resultHash.get(placment).getUserData();
                        line2 += resultHash.get(placment).getUserData();
                    } if(placment.getY() == 2) {
                        row2 += resultHash.get(placment).getUserData();
                        colum3 += resultHash.get(placment).getUserData();
                    } 
                    break;
                case 2:
                    if(placment.getY() == 0) {
                        row3 += resultHash.get(placment).getUserData();
                        colum1 += resultHash.get(placment).getUserData();
                        line2 += resultHash.get(placment).getUserData();
                    } if(placment.getY() == 1) {
                        row3 += resultHash.get(placment).getUserData();
                        colum2 += resultHash.get(placment).getUserData();
                    } if(placment.getY() == 2) {
                        row3 += resultHash.get(placment).getUserData();
                        colum3 += resultHash.get(placment).getUserData();
                        line1 += resultHash.get(placment).getUserData();
                    } 
                    break;

                default:
                    break;
            }
        }
        if(row1.equals("XXX") || row2.equals("XXX") || row3.equals("XXX") 
        || colum1.equals("XXX") || colum2.equals("XXX") || colum3.equals("XXX") 
        || line1.equals("XXX") || line2.equals("XXX")) {
            return game.getPlayers().stream().findFirst().get();
        }
        if(row1.equals("OOO") || row2.equals("OOO") || row3.equals("OOO") 
        || colum1.equals("OOO") || colum2.equals("OOO") || colum3.equals("OOO") 
        || line1.equals("OOO") || line2.equals("OOO")) {
            return game.getPlayers().get(1);
        }
        return null;
    }
}
