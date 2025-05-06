package edu.ntnu.idat2003.model;

import java.util.HashSet;

import edu.ntnu.idat2003.model.dice.Dice;
import edu.ntnu.idat2003.model.player.Player;

public class TicTacToe {
    private HashSet<Player> players;
    private Player curentPlayer;
    private Dice dice;
    private boolean gameOver;

    public TicTacToe(HashSet<Player> players, Dice dice) {
        this.players = players;
        this.dice = dice;
    }
    
}
