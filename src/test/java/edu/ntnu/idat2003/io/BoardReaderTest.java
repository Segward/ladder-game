package edu.ntnu.idat2003.io;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import edu.ntnu.idat2003.exception.DataReadException;
import edu.ntnu.idat2003.model.Board;
import edu.ntnu.idat2003.util.GsonUtil;
import java.lang.reflect.Type;
import java.util.HashSet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

public class BoardReaderTest {

  @Test
  @DisplayName("Test getQuizBoards")
  void testGetQuizBoards() throws DataReadException {
    Board board1 = new Board("Quiz 1");
    Board board2 = new Board("Quiz 2");
    HashSet<Board> mockBoards = new HashSet<>();
    mockBoards.add(board1);
    mockBoards.add(board2);

    try (MockedStatic<GsonUtil> mockedGsonUtil = mockStatic(GsonUtil.class)) {
      mockedGsonUtil
          .when(() -> GsonUtil.readFile(eq("data/quizboard.json"), any(Type.class)))
          .thenReturn(mockBoards);
      HashSet<Board> result = BoardReader.getQuizBoards();

      assertEquals(2, result.size());
      assertTrue(result.contains(board1));
      assertTrue(result.contains(board2));
    }
  }

  @Test
  @DisplayName("Test getLadderBoards")
  void testGetLadderBoards() throws DataReadException {
    Board board1 = new Board("Ladder 1");
    HashSet<Board> mockBoards = new HashSet<>();
    mockBoards.add(board1);

    try (MockedStatic<GsonUtil> mockedGsonUtil = mockStatic(GsonUtil.class)) {
      mockedGsonUtil
          .when(() -> GsonUtil.readFile(eq("data/ladderboard.json"), any(Type.class)))
          .thenReturn(mockBoards);
      HashSet<Board> result = BoardReader.getLadderBoards();

      assertEquals(1, result.size());
      assertTrue(result.contains(board1));
    }
  }
}
