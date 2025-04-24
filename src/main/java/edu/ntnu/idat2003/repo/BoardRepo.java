package edu.ntnu.idat2003.repo;

import edu.ntnu.idat2003.model.Board;
import edu.ntnu.idat2003.service.GsonService;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.HashSet;

public class BoardRepo {
  private static final String path = "src/main/resources/board.json";
  
  public static HashSet<Board> getBoards() {
    Type boardSetType = new TypeToken<HashSet<Board>>() {}.getType();
    HashSet<Board> boards = GsonService.getObjects(path, boardSetType);
    return boards != null ? boards : new HashSet<>();
  }

  public static void saveBoards(HashSet<Board> boards) {
    GsonService.saveObjects(boards, path);
  }
}