package edu.ntnu.idat2003.util;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.google.gson.reflect.TypeToken;
import edu.ntnu.idat2003.exception.DataReadException;
import edu.ntnu.idat2003.exception.DataWriteException;
import edu.ntnu.idat2003.model.Board;
import edu.ntnu.idat2003.model.Tile;
import edu.ntnu.idat2003.model.Vector2;
import edu.ntnu.idat2003.model.tileactions.LadderAction;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.*;
import java.util.HashSet;
import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;

public class GsonUtilTest {

  private HashSet<Board> boards;
  private Path tempFile;

  @BeforeEach
  @DisplayName("Setup test data")
  void setup() throws IOException {
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

    tempFile = Files.createTempFile("test-board", ".json");
  }

  @AfterEach
  @DisplayName("Cleanup test data")
  void tearDown() throws IOException {
    Files.deleteIfExists(tempFile);
  }

  @Test
  @DisplayName("Test readFile and writeFile")
  void testWriteAndRead() throws Exception {
    GsonUtil.writeFile(tempFile.toString(), boards);

    Type type = new TypeToken<HashSet<Board>>() {}.getType();
    HashSet<Board> readBoards = GsonUtil.readFile(tempFile.toString(), type);

    assertNotNull(readBoards);
    assertEquals(1, readBoards.size());

    Board readBoard = readBoards.iterator().next();
    assertEquals("Test Board", readBoard.getName());
    assertEquals(3, readBoard.getTiles().size());
  }

  @Test
  @DisplayName("Test writeFile throws exception")
  void testWriteFileThrows() {
    try (MockedStatic<GsonUtil> mocked = mockStatic(GsonUtil.class)) {
      mocked
          .when(() -> GsonUtil.writeFile("invalid/path.json", boards))
          .thenThrow(new DataWriteException("Failed"));

      assertThrows(DataWriteException.class, () -> GsonUtil.writeFile("invalid/path.json", boards));
    }
  }

  @Test
  @DisplayName("Test readFile throws exception")
  void testReadFileThrows() {
    Type type = new TypeToken<HashSet<Board>>() {}.getType();

    try (MockedStatic<GsonUtil> mocked = mockStatic(GsonUtil.class)) {
      mocked
          .when(() -> GsonUtil.readFile("invalid.json", type))
          .thenThrow(new DataReadException("Read failed"));

      assertThrows(DataReadException.class, () -> GsonUtil.readFile("invalid.json", type));
    }
  }
}
