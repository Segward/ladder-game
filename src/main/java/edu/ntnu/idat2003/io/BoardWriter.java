package edu.ntnu.idat2003.io;

import edu.ntnu.idat2003.exception.DataWriteException;
import edu.ntnu.idat2003.model.Board;
import edu.ntnu.idat2003.util.GsonUtil;
import java.util.HashSet;

/**
 *  Class for writting board json files.
 *  Inclueds method for converting HasSets with board object in to json files.
 */
public class BoardWriter {

  private static final String ladderBoardPath = "data/ladderboard.json";
  private static final String partyBoardPath = "data/partyboard.json";

  /**
   *  Method for converting LadderGames boards in to json.
   *  Takes a hasSet<Board> as parameter.
   *  Uses parameter with method for GsonUtil to write 
   *  ladder game board data into a jsonFile.
   * 
   *  @param boards HasSet<Board> set with laddergame boards
   */
  public static void saveLadderBoards(HashSet<Board> boards) {
    try {
      GsonUtil.writeFile(ladderBoardPath, boards);
    } catch (DataWriteException e) {
      e.printStackTrace();
    }
  }

  
  /**
   *  Method for converting Quiz boards in to json.
   *  Takes a hasSet<Board> as parameter.
   *  Uses parameter with method for GsonUtil to write 
   *  quiz game board data into a jsonFile.
   * 
   *  @param boards HasSet<Board> set with quiz boards
   */
  public static void savePartyBoards(HashSet<Board> boards) {
    try {
      GsonUtil.writeFile(partyBoardPath, boards);
    } catch (DataWriteException e) {
      e.printStackTrace();
    }
  }
}
