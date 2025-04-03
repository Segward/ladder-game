package edu.ntnu.idat2003.services;

import javafx.scene.text.Text;

public class LadderGameService {
  public static void rollDice(Text text) {
    int dice = (int) (Math.random() * 6) + 1;
    text.setText("You rolled a " + dice);
  }
}
