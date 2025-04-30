package edu.ntnu.idat2003.repo;

import com.google.gson.reflect.TypeToken;
import edu.ntnu.idat2003.exception.DataReadException;
import edu.ntnu.idat2003.model.board.Board;
import edu.ntnu.idat2003.util.GsonUtil;
import java.lang.reflect.Type;
import java.util.HashSet;

public class BoardRepo {
  private static final String path = "data/board.json";

  public static HashSet<Board> getBoards() {
    Type boardSetType = new TypeToken<HashSet<Board>>() {}.getType();
    try {
      return GsonUtil.getObjects(path, boardSetType);

    } catch (DataReadException e) {
      e.printStackTrace();
      return new HashSet<>();
    }
  }

  public static void saveBoards(HashSet<Board> boards) {
    try {
      GsonUtil.saveObjects(boards, path);
    } catch (DataReadException e) {
      e.printStackTrace();
    }
  }
}