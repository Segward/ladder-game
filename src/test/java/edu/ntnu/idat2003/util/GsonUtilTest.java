package edu.ntnu.idat2003.util;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

import com.google.gson.reflect.TypeToken;
import edu.ntnu.idat2003.exception.DataWriteException;
import edu.ntnu.idat2003.model.Board;
import edu.ntnu.idat2003.model.Tile;
import edu.ntnu.idat2003.model.Vector2;
import edu.ntnu.idat2003.model.tileactions.LadderAction;
import java.lang.reflect.Type;
import java.util.HashSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

public class GsonUtilTest {

  private HashSet<Board> boards;

  @BeforeEach
  void setup() {
    Tile tile1 = new Tile(new Vector2(0, 0), "1");
    Tile tile2 = new Tile(new Vector2(1, 1), "2");
    Tile tile3 = new Tile(new Vector2(2, 2), "3");

    LadderAction action = new LadderAction(tile1.getPosition(), tile2.getPosition());

    Board board = new Board("Test Board");
    board.addTile(tile1.getPosition(), tile1);
    board.addTile(tile2.getPosition(), tile2);
    board.addTile(tile3.getPosition(), tile3);
    board.addAction(tile1.getPosition(), action);

    boards = new HashSet<>();
    boards.add(board);
  }

  @Test
  @DisplayName("Test readFile and writeFile")
  void testWriteFileAndReadFile() throws Exception {
    String fakeFilePath = "/fake/path/board.json";
    Type boardSetType = new TypeToken<HashSet<Board>>() {}.getType();

    try (MockedStatic<GsonUtil> mockedGsonUtil = Mockito.mockStatic(GsonUtil.class)) {
      mockedGsonUtil
          .when(() -> GsonUtil.writeFile(eq(fakeFilePath), eq(boards)))
          .thenThrow(new DataWriteException("Failed to write file"));

      mockedGsonUtil
          .when(() -> GsonUtil.readFile(eq(fakeFilePath), eq(boardSetType)))
          .thenReturn(boards);

      assertThrows(DataWriteException.class, () -> GsonUtil.writeFile(fakeFilePath, boards));

      HashSet<Board> result = GsonUtil.readFile(fakeFilePath, boardSetType);
      Board board = result.iterator().next();

      assertEquals("Test Board", board.getName());
      assertEquals(3, board.getTiles().size());
    }
  }
}
