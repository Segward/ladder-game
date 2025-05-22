package edu.ntnu.idat2003.observer;

import javafx.scene.control.Button;

/**
 *  Interface representing the TicTacToe Game Observer
 */
public interface TicTacToeObserver {
  void onClick(Button tile);

  void retreveTiles();

  void gameStatus();
}
