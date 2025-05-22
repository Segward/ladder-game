package edu.ntnu.idat2003.model.tileactions;

import edu.ntnu.idat2003.model.Player;
import edu.ntnu.idat2003.model.Vector2;

public class QuestionAction extends TileAction {
  private final String question;
  private final String answer;
  private String given;

  public QuestionAction(Vector2 start, String question, String answer) {
    super(start);
    this.question = question;
    this.answer = answer;
  }

  public void setGiven(String given) {
    this.given = given;
  }

  public String getQuestion() {
    return question;
  }

  public String getAnswer() {
    return answer;
  }

  @Override
  public void execute(Player player) {
    if (given != null && given.equals(answer)) {
      player.setExtraDice(true);
    } else {
      player.setExtraDice(false);
    }
  }
}