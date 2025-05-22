package edu.ntnu.idat2003.observer;

import edu.ntnu.idat2003.model.Player;
import edu.ntnu.idat2003.model.tileactions.QuestionAction;
import edu.ntnu.idat2003.model.tileactions.TileAction;

public interface QuizGameObserver {
  void onPlayerMoved(Player player, int remainder);

  void onTileActionExecuted(Player player, TileAction action);

  void onPlayerWon(Player player);

  void onDiceRolled(int diceValue);

  void onQuestion(Player player, QuestionAction action);
}