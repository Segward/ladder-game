package edu.ntnu.idat2003.controller;

import edu.ntnu.idat2003.observer.TicTacToeObserver;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class TicTacToeController implements TicTacToeObserver{
    private Pane root;
    private Button tile;

    TicTacToeController(Pane root, Button tile) {
        this.root = root;
        this.tile = tile;
    }

    public void init() {
        
    }
}
