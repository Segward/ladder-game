package edu.ntnu.idat2003.model;

import java.util.ArrayList;
import java.util.HashSet;

import edu.ntnu.idat2003.model.dice.Dice;
import edu.ntnu.idat2003.model.player.Player;
/**
 *  Class representing the data used in the tictactoe game.
 *  it saves the players, players scores and dice in class variables. 
 * 
 */
public class TicTacToe {
    private ArrayList<Player> players;
    private Player currentPlayer;
    private Dice dice;
    private int playerOneScore = 0;
    private int playerTwoScore = 0;

    /**
     *  Costructor for the TicTacToe class.
     *  It uses the parapeters to set both the 
     *  players and dice to class virables.
     * 
     *  @param players ArrayList<Player> representing players
     *  @param dice Dice object to be used in game
     */
    public TicTacToe(ArrayList<Player> players, Dice dice) {
        this.players = players;
        this.dice = dice;
    }
    
    /**
     *  Getter for current Player.
     *  Retreves the curren player object from
     *  the currentPlayer class variable.
     * 
     *  @return
     */
    public Player getCurrentPlayer(){
        return currentPlayer;
    }

    /**
     *  Method for rolling the dice.
     *  Uses the roll() method from the Dice class,
     *  retrieved from the dice class object.
     *  
     * @return int representing the dice amount
     */
    public int rollDice() {
        return dice.roll();
    }

    /**
     *  Setter for the currentPlayer class variable.
     *  Uses the Player parameter to set current player.
     * 
     * @param player Player object to be set
     */
    public void setCurrentPlayer(Player player) {
        currentPlayer = player;
    }

     /**
     *  Getter for players array.
     *  Retreves the array that inclueds
     *  all of the players.
     * 
     *  @return ArrayList<Player> representing the players
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     *  Getter for the player one score.
     *  Retreves an int from the playerOneScore class variable
     *  representing player ones score.
     * 
     *  @return int representing playerOne score
     */
    public int getPlayerOneScore() {
        return playerOneScore;
    }

    /**
     *  Getter for the player two score.
     *  Retreves an int from the playerTwoScore class variable
     *  representing player two score.
     * 
     *  @return int representing playerTwo score
     */
    public int getPlayerTwoScore() {
        return playerTwoScore;
    }

    /**
     *  Setter for the player one score.
     *  Uses the int parameter to set the
     *  playerOneScore class variable,
     *  representing player one score.
     * 
     *  @param score int score value
     */
    public void setPlayerOneScore(int score) {
        this.playerOneScore = score;
    }

     /**
     *  Setter for the player two score.
     *  Uses the int parameter to set the
     *  playerTwoScore class variable,
     *  representing player two score.
     * 
     *  @param score int score value
     */
    public void setPlayerTwoScore(int score) {
        this.playerTwoScore = score;
    }
}
