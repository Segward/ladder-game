package edu.ntnu.idat2003.io;

import com.google.gson.reflect.TypeToken;
import edu.ntnu.idat2003.exception.DataReadException;
import edu.ntnu.idat2003.model.Board;
import edu.ntnu.idat2003.util.GsonUtil;
import java.lang.reflect.Type;
import java.util.HashSet;

/**
 *  Class for reading board json files, and converting data to a hashset with board objects.
 */
public class BoardReader {

  private static final String partyBoardPath = "data/partyboard.json";
  private static final String ladderBoardPath = "data/ladderboard.json";

  /**
   *  Utilazes GsonUtil method to read quizboards board json data,
   *  and convert them into an hashset with board objects.
   * 
   *  @return HashSet<Board> all saved quiz boards.
   */
  public static HashSet<Board> getPartyBoards() {
    Type boardSetType = new TypeToken<HashSet<Board>>() {}.getType();
    try {
      return GsonUtil.readFile(partyBoardPath, boardSetType);

    } catch (DataReadException e) {
      e.printStackTrace();
      return new HashSet<>();
    }
  }

   /**
   *  Utilazes GsonUtil method to read laddergame board json data,
   *  and convert them into an hashset with board objects.
   * 
   *  @return HashSet<Board> all saved laddergame boards.
   */
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
