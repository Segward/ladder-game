package edu.ntnu.idat2003.io;

import edu.ntnu.idat2003.model.Board;
import edu.ntnu.idat2003.model.Tile;
import edu.ntnu.idat2003.model.Vector2;
import edu.ntnu.idat2003.model.tileactions.ExtraDiceAction;
import edu.ntnu.idat2003.model.tileactions.LadderAction;
import edu.ntnu.idat2003.model.tileactions.QuestionAction;
import java.util.Collections;
import java.util.HashSet;

/**
 * Class representing a factory where bords are made. Inclued methods for creating both ladder game
 * and quiz game boards.
 */
public class BoardFactory {

  /**
   * Method for creating a 10x9 tile game board used in ladder game. Take a String as parameter.
   * Creates a new board with parameter string as name. Then using two nested foor loop creates each
   * tile with a Vector2 object as position.
   *
   * @param name String representing Board name
   * @return Board Object representing game board.
   */
  private static Board makeBoard(String name) {
    Board board = new Board(name);
    Tile previous = null;
    int count = 1;
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 10; j++) {
        int x = i % 2 == 1 ? 9 - j : j;
        Vector2 pos = new Vector2(x, i);
        Tile tile = new Tile(pos, String.valueOf(count));
        board.addTile(pos, tile);
        if (previous != null) {
          previous.setNextPosition(tile.getPosition());
        }
        previous = tile;
        count++;
      }
    }
    return board;
  }

  /**
   * Method for changing "normal" tile into action tiles. Deffines two new Board object with names,
   * Default Board and Difficult board, before hard coding both new ladder and extraDice action.
   * Then adds new actions to boards.
   */
  public static void makeLadderBoards() {
    Board board1 = makeBoard("Default Board");

    Vector2 start1 = new Vector2(4, 0);
    LadderAction ladder1 = new LadderAction(start1, new Vector2(5, 2));
    board1.addAction(start1, ladder1);

    Vector2 start2 = new Vector2(2, 1);
    LadderAction ladder2 = new LadderAction(start2, new Vector2(4, 4));
    board1.addAction(start2, ladder2);

    Vector2 start3 = new Vector2(7, 4);
    LadderAction ladder3 = new LadderAction(start3, new Vector2(5, 5));
    board1.addAction(start3, ladder3);

    Vector2 start4 = new Vector2(2, 6);
    LadderAction ladder4 = new LadderAction(start4, new Vector2(5, 7));
    board1.addAction(start4, ladder4);

    Vector2 start5 = new Vector2(9, 3);
    LadderAction ladder5 = new LadderAction(start5, new Vector2(8, 4));
    board1.addAction(start5, ladder5);

    Vector2 start6 = new Vector2(0, 8);
    LadderAction ladder6 = new LadderAction(start6, new Vector2(1, 7));
    board1.addAction(start6, ladder6);

    Vector2 start7 = new Vector2(1, 5);
    LadderAction ladder7 = new LadderAction(start7, new Vector2(0, 4));
    board1.addAction(start7, ladder7);

    Vector2 start8 = new Vector2(3, 8);
    LadderAction ladder8 = new LadderAction(start8, new Vector2(4, 7));
    board1.addAction(start8, ladder8);

    Vector2 start9 = new Vector2(6, 5);
    LadderAction ladder9 = new LadderAction(start9, new Vector2(4, 2));
    board1.addAction(start9, ladder9);

    Vector2 start10 = new Vector2(8, 1);
    LadderAction ladder10 = new LadderAction(start10, new Vector2(7, 0));
    board1.addAction(start10, ladder10);

    Vector2 start11 = new Vector2(3, 0);
    ExtraDiceAction extraDiceAction1 = new ExtraDiceAction(start11);
    board1.addAction(start11, extraDiceAction1);

    Vector2 start12 = new Vector2(4, 5);
    ExtraDiceAction extraDiceAction2 = new ExtraDiceAction(start12);
    board1.addAction(start12, extraDiceAction2);

    Vector2 start13 = new Vector2(4, 8);
    ExtraDiceAction extraDiceAction3 = new ExtraDiceAction(start13);
    board1.addAction(start13, extraDiceAction3);

    Vector2 start14 = new Vector2(8, 2);
    ExtraDiceAction extraDiceAction4 = new ExtraDiceAction(start14);
    board1.addAction(start14, extraDiceAction4);

    Board board2 = makeBoard("Difficult Board");

    Vector2 start15 = new Vector2(4, 0);
    LadderAction ladder11 = new LadderAction(start15, new Vector2(5, 1));
    board2.addAction(start15, ladder11);

    Vector2 start16 = new Vector2(2, 6);
    LadderAction ladder12 = new LadderAction(start16, new Vector2(4, 3));
    board2.addAction(start16, ladder12);

    Vector2 start17 = new Vector2(7, 5);
    LadderAction ladder13 = new LadderAction(start17, new Vector2(5, 2));
    board2.addAction(start17, ladder13);

    Vector2 start18 = new Vector2(2, 6);
    LadderAction ladder14 = new LadderAction(start18, new Vector2(5, 3));
    board2.addAction(start18, ladder14);

    Vector2 start19 = new Vector2(9, 3);
    LadderAction ladder15 = new LadderAction(start19, new Vector2(8, 0));
    board2.addAction(start19, ladder15);

    Vector2 start20 = new Vector2(0, 8);
    LadderAction ladder16 = new LadderAction(start20, new Vector2(3, 0));
    board2.addAction(start20, ladder16);

    Vector2 start21 = new Vector2(0, 4);
    LadderAction ladder17 = new LadderAction(start21, new Vector2(1, 0));
    board2.addAction(start21, ladder17);

    Vector2 start22 = new Vector2(3, 8);
    LadderAction ladder18 = new LadderAction(start22, new Vector2(4, 3));
    board2.addAction(start22, ladder18);

    Vector2 start23 = new Vector2(6, 5);
    LadderAction ladder19 = new LadderAction(start23, new Vector2(4, 2));
    board2.addAction(start23, ladder19);

    Vector2 start24 = new Vector2(8, 1);
    LadderAction ladder20 = new LadderAction(start24, new Vector2(7, 0));
    board2.addAction(start24, ladder20);

    Vector2 start25 = new Vector2(5, 8);
    LadderAction ladder21 = new LadderAction(start25, new Vector2(0, 0));
    board2.addAction(start25, ladder21);

    Vector2 start26 = new Vector2(3, 8);
    LadderAction ladder22 = new LadderAction(start26, new Vector2(4, 3));
    board2.addAction(start26, ladder22);

    Vector2 start27 = new Vector2(6, 5);
    LadderAction ladder23 = new LadderAction(start27, new Vector2(4, 2));
    board2.addAction(start27, ladder23);

    Vector2 start28 = new Vector2(8, 5);
    LadderAction ladder24 = new LadderAction(start28, new Vector2(7, 0));
    board2.addAction(start28, ladder24);

    Vector2 start29 = new Vector2(8, 8);
    LadderAction ladder25 = new LadderAction(start29, new Vector2(9, 4));
    board2.addAction(start29, ladder25);

    Vector2 start30 = new Vector2(8, 2);
    ExtraDiceAction extraDiceAction5 = new ExtraDiceAction(start30);
    board2.addAction(start30, extraDiceAction5);

    Vector2 start31 = new Vector2(3, 0);
    ExtraDiceAction extraDiceAction6 = new ExtraDiceAction(start31);
    board2.addAction(start31, extraDiceAction6);

    Vector2 start32 = new Vector2(5, 8);
    ExtraDiceAction extraDiceAction7 = new ExtraDiceAction(start32);
    board2.addAction(start32, extraDiceAction7);

    HashSet<Board> boards = new HashSet<>();
    Collections.addAll(boards, board1, board2);
    BoardWriter.saveLadderBoards(boards);
  }

  /**
   * Method that creates quiz game board.
   *
   * @param name String representing Board name
   * @return Board Object representing game board.
   */
  private static Board makeQuizBoard(String name) {
    int rows = 9;
    int cols = 10;
    Board board = new Board(name);
    Tile previous = null;
    int count = 1;

    int left = 0, right = cols - 1, top = 0, bottom = rows - 1;
    while (left <= right && top <= bottom) {
      for (int x = left; x <= right; x++) {
        Vector2 pos = new Vector2(x, top);
        Tile tile = new Tile(pos, String.valueOf(count++));
        board.addTile(pos, tile);
        if (previous != null) previous.setNextPosition(tile.getPosition());
        previous = tile;
      }
      top++;
      for (int y = top; y <= bottom; y++) {
        Vector2 pos = new Vector2(right, y);
        Tile tile = new Tile(pos, String.valueOf(count++));
        board.addTile(pos, tile);
        if (previous != null) previous.setNextPosition(tile.getPosition());
        previous = tile;
      }
      right--;
      if (top <= bottom) {
        for (int x = right; x >= left; x--) {
          Vector2 pos = new Vector2(x, bottom);
          Tile tile = new Tile(pos, String.valueOf(count++));
          board.addTile(pos, tile);
          if (previous != null) previous.setNextPosition(tile.getPosition());
          previous = tile;
        }
        bottom--;
      }
      if (left <= right) {
        for (int y = bottom; y >= top; y--) {
          Vector2 pos = new Vector2(left, y);
          Tile tile = new Tile(pos, String.valueOf(count++));
          board.addTile(pos, tile);
          if (previous != null) previous.setNextPosition(tile.getPosition());
          previous = tile;
        }
        left++;
      }
    }
    return board;
  }

  /** Method to create a quiz board with some harcoded questions. */
  public static void makeQuizBoard() {
    Board board = makeQuizBoard("Default Quiz Board");

    Vector2 start1 = new Vector2(4, 0);
    QuestionAction question1 = new QuestionAction(start1, "5 + 5", "10");
    board.addAction(start1, question1);

    Vector2 start2 = new Vector2(2, 1);
    QuestionAction question2 = new QuestionAction(start2, "319 - 9", "310");
    board.addAction(start2, question2);

    Vector2 start3 = new Vector2(7, 4);
    QuestionAction question3 = new QuestionAction(start3, "5+4", "9");
    board.addAction(start3, question3);

    Vector2 start4 = new Vector2(2, 6);
    QuestionAction question4 = new QuestionAction(start4, "39 + 2", "31");
    board.addAction(start4, question4);

    Vector2 start5 = new Vector2(9, 3);
    QuestionAction question5 = new QuestionAction(start5, "2 * 9", "18");
    board.addAction(start5, question5);

    Vector2 start6 = new Vector2(0, 8);
    QuestionAction question6 = new QuestionAction(start6, "What animal says meow", "cat");
    board.addAction(start6, question6);

    Vector2 start7 = new Vector2(1, 5);
    QuestionAction question7 = new QuestionAction(start7, "What is the capital of Norway?", "Oslo");
    board.addAction(start7, question7);

    Vector2 start8 = new Vector2(3, 8);
    QuestionAction question8 =
        new QuestionAction(start8, "What is the capital of Sweden?", "Stockholm");
    board.addAction(start8, question8);

    Vector2 start9 = new Vector2(6, 5);
    QuestionAction question9 =
        new QuestionAction(start9, "What is the capital of Denmark?", "Copenhagen");
    board.addAction(start9, question9);

    Vector2 start10 = new Vector2(8, 1);
    QuestionAction question10 = new QuestionAction(start10, "What is our planet called", "Earth");
    board.addAction(start10, question10);

    Vector2 start11 = new Vector2(1, 3);
    QuestionAction question11 =
        new QuestionAction(start11, "What is the capital of Finland?", "Helsinki");
    board.addAction(start11, question11);

    Vector2 start12 = new Vector2(4, 5);
    QuestionAction question12 =
        new QuestionAction(start12, "Who is the greek god of wine and ecstasy", "Dionysus");
    board.addAction(start12, question12);

    Vector2 start13 = new Vector2(8, 8);
    QuestionAction question13 =
        new QuestionAction(
            start13, "Who was apollons lover who turned into a flower?", "Hyacinthus");
    board.addAction(start13, question13);

    Vector2 start14 = new Vector2(8, 2);
    QuestionAction question14 =
        new QuestionAction(start14, "What is the capital of Iceland?", "Reykjavik");
    board.addAction(start14, question14);

    Vector2 start15 = new Vector2(3, 0);
    QuestionAction question15 =
        new QuestionAction(start15, "What are the korean folklore goblins called?", "Dokkaebi");
    board.addAction(start15, question15);

    Vector2 start16 = new Vector2(8, 8);
    QuestionAction question16 =
        new QuestionAction(start16, "What is the capital of Japan?", "Tokyo");
    board.addAction(start16, question16);

    HashSet<Board> boards = new HashSet<>();
    boards.add(board);
    BoardWriter.saveQuizBoards(boards);
  }
}
