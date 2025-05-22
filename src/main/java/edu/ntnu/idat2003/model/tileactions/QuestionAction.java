package edu.ntnu.idat2003.model.tileactions;

import edu.ntnu.idat2003.model.Player;
import edu.ntnu.idat2003.model.Vector2;

/**
 * Class representing a question action in the game. This class extends TileAction and is used to
 * ask a question to the player. If the player answers correctly, they receive an extra die.
 */
public class QuestionAction extends TileAction {
  private final String question;
  private final String answer;
  private String given;

  /**
   * Constructor for the QuestionAction class.
   *
   * @param start The starting position of the action.
   * @param question The question to be asked.
   * @param answer The correct answer to the question.
   */
  public QuestionAction(Vector2 start, String question, String answer) {
    super(start);
    this.question = question;
    this.answer = answer;
  }

  /**
   * Sets the given answer from the player. It is used to check if the player's answer is correct.
   *
   * @param given The answer provided by the player.
   */
  public void setGiven(String given) {
    this.given = given;
  }

  /**
   * Gets the question to be asked.
   *
   * @return The question.
   */
  public String getQuestion() {
    return question;
  }

  /**
   * Gets the correct answer to the question.
   *
   * @return The correct answer.
   */
  public String getAnswer() {
    return answer;
  }

  /**
   * Gets the answer given by the player.
   *
   * @return The answer given by the player.
   */
  public String getGiven() {
    return given;
  }

  /**
   * Executes the action. If the player's answer is correct, they receive an extra die.
   *
   * @param player The player who is answering the question.
   */
  @Override
  public void execute(Player player) {
    if (given != null && given.equalsIgnoreCase(answer)) {
      player.setExtraDice(true);
    } else {
      player.setExtraDice(false);
    }
  }
}