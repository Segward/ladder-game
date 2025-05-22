package edu.ntnu.idat2003.io;

import edu.ntnu.idat2003.exception.DataWriteException;
import edu.ntnu.idat2003.model.Board;
import edu.ntnu.idat2003.util.GsonUtil;
import java.util.HashSet;

public class BoardWriter {

  private static final String ladderBoardPath = "data/ladderboard.json";
  private static final String quizBoardPath = "data/quizboard.json";

  public static void saveLadderBoards(HashSet<Board> boards) {
    try {
      GsonUtil.writeFile(ladderBoardPath, boards);
    } catch (DataWriteException e) {
      e.printStackTrace();
    }
  }

  public static void saveQuizBoards(HashSet<Board> boards) {
    try {
      GsonUtil.writeFile(quizBoardPath, boards);
    } catch (DataWriteException e) {
      e.printStackTrace();
    }
  }
}
