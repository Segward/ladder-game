package edu.ntnu.idat2003.repo;

import edu.ntnu.idat2003.model.Board;
import edu.ntnu.idat2003.util.GsonUtil;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.HashSet;

public class BoardRepo {
  private static final String path = "data/board.json";
  
  public static HashSet<Board> getBoards() {
    Type boardSetType = new TypeToken<HashSet<Board>>() {}.getType();
    HashSet<Board> boards = GsonUtil.getObjects(path, boardSetType);
    return boards != null ? boards : new HashSet<>();
  }

  public static void saveBoards(HashSet<Board> boards) {
    GsonUtil.saveObjects(boards, path);
  }
}