package edu.ntnu.idat2003.model;

import java.util.ArrayList;
import java.util.HashSet;

import edu.ntnu.idat2003.model.dice.Dice;
import edu.ntnu.idat2003.model.player.Player;

public class TicTacToe {
    private ArrayList<Player> players;
    private Player curentPlayer;
    private Dice dice;
    private boolean gameOver;

    public TicTacToe(ArrayList<Player> players, Dice dice) {
        this.players = players;
        this.dice = dice;
        this.curentPlayer = players.stream().findFirst().get();
    }
    
    public Player getCurrentPlayer(){
        return curentPlayer;
    }

    public int rollDice() {
        return dice.roll();
    }

    public void setCurrentPlayer(Player player) {
        curentPlayer = player;
    }
}
