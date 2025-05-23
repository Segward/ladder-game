package edu.ntnu.idat2003.io;

import edu.ntnu.idat2003.exception.DataWriteException;
import edu.ntnu.idat2003.model.Board;
import edu.ntnu.idat2003.util.GsonUtil;
import java.util.HashSet;

/**
 * Class for writting board json files. Inclueds method for converting HasSets with board object in
 * to json files.
 */
public class BoardWriter {

  private static final String ladderBoardPath = "data/ladderboard.json";
  private static final String quizBoardPath = "data/quizboard.json";

  /**
   * Method for converting LadderGames boards in to json. Takes a hasSet<Board> as parameter. Uses
   * parameter with method for GsonUtil to write ladder game board data into a jsonFile.
   *
   * @param boards HasSet<Board> set with laddergame boards
   * @throws DataWriteException if there is an error writing the file
   * @throws IllegalArgumentException if the boards set is null or empty
   */
  public static void saveLadderBoards(HashSet<Board> boards) throws DataWriteException {
    if (boards == null || boards.isEmpty()) {
      throw new IllegalArgumentException("Board set cannot be null or empty");
    }

    GsonUtil.writeFile(ladderBoardPath, boards);
  }

  /**
   * Method for converting Quiz boards in to json. Takes a hasSet<Board> as parameter. Uses
   * parameter with method for GsonUtil to write quiz game board data into a jsonFile.
   *
   * @param boards HasSet<Board> set with quiz boards
   * @throws DataWriteException if there is an error writing the file
   * @throws IllegalArgumentException if the boards set is null or empty
   */
  public static void saveQuizBoards(HashSet<Board> boards) throws DataWriteException {
    if (boards == null || boards.isEmpty()) {
      throw new IllegalArgumentException("Board set cannot be null or empty");
    }

    GsonUtil.writeFile(quizBoardPath, boards);
  }
}
