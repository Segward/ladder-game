package edu.ntnu.idat2003.io;

import com.google.gson.reflect.TypeToken;
import edu.ntnu.idat2003.exception.DataReadException;
import edu.ntnu.idat2003.model.Board;
import edu.ntnu.idat2003.util.GsonUtil;
import java.lang.reflect.Type;
import java.util.HashSet;

public class BoardReader {

  private static final String quizBoardPath = "data/quizboard.json";
  private static final String ladderBoardPath = "data/ladderboard.json";

  public static HashSet<Board> getQuizBoards() {
    Type boardSetType = new TypeToken<HashSet<Board>>() {}.getType();
    try {
      return GsonUtil.readFile(quizBoardPath, boardSetType);

    } catch (DataReadException e) {
      e.printStackTrace();
      return new HashSet<>();
    }
  }

  public static HashSet<Board> getLadderBoards() {
    Type boardSetType = new TypeToken<HashSet<Board>>() {}.getType();
    try {
      return GsonUtil.readFile(ladderBoardPath, boardSetType);

    } catch (DataReadException e) {
      e.printStackTrace();
      return new HashSet<>();
    }
  }
}
