package edu.ntnu.idat2003.repo;

import edu.ntnu.idat2003.util.GsonUtil;
import edu.ntnu.idat2003.model.Board;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class BoardRepository {
  private static final string path = "src/main/resources/board.json";
  
  public static HashSet<Board> getBoards() {
    Type boardSetType = new TypeToken<HashSet<Board>>() {}.getType();
    HashSet<Board> boards = GsonUtil.getObjects(path, boardSetType);
    return boards != null ? boards : new HashSet<>();
  }

  public static void saveBoards(HashSet<Board> boards) {
    GsonUtil.saveObjects(path, boards);
  }
}
