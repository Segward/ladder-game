package edu.ntnu.idat2003.io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

import edu.ntnu.idat2003.exception.DataWriteException;
import edu.ntnu.idat2003.model.Board;
import edu.ntnu.idat2003.util.GsonUtil;
import java.util.HashSet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

class BoardWriterTest {

  @Test
  @DisplayName("Test saveQuizBoards")
  void testSaveLadderBoards() throws DataWriteException {
    Board board = new Board("Ladder 1");
    HashSet<Board> boards = new HashSet<>();
    boards.add(board);

    try (MockedStatic<GsonUtil> mockedGsonUtil = mockStatic(GsonUtil.class)) {
      BoardWriter.saveLadderBoards(boards);

      mockedGsonUtil.verify(() -> GsonUtil.writeFile("data/ladderboard.json", boards), times(1));
    }
  }

  @Test
  @DisplayName("Test saveLadderBoards")
  void testSaveQuizBoards() throws DataWriteException {
    Board board = new Board("Quiz 1");
    HashSet<Board> boards = new HashSet<>();
    boards.add(board);

    try (MockedStatic<GsonUtil> mockedGsonUtil = mockStatic(GsonUtil.class)) {
      BoardWriter.saveQuizBoards(boards);

      mockedGsonUtil.verify(() -> GsonUtil.writeFile("data/quizboard.json", boards), times(1));
    }
  }

  @Test
  @DisplayName("Test saveLadderBoards with empty set")
  void testSaveLadderBoardsWithEmptySet() throws DataWriteException {
    HashSet<Board> boards = new HashSet<>();

    try (MockedStatic<GsonUtil> mockedGsonUtil = mockStatic(GsonUtil.class)) {
      BoardWriter.saveLadderBoards(boards);
      fail("Expected IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Board set cannot be null or empty", e.getMessage());
    }
  }

  @Test
  @DisplayName("Test saveQuizBoards with empty set")
  void testSaveQuizBoardsWithEmptySet() throws DataWriteException {
    HashSet<Board> boards = new HashSet<>();

    try (MockedStatic<GsonUtil> mockedGsonUtil = mockStatic(GsonUtil.class)) {
      BoardWriter.saveQuizBoards(boards);
      fail("Expected IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Board set cannot be null or empty", e.getMessage());
    }
  }

  @Test
  @DisplayName("Test saveLadderBoards with DataWriteException")
  void testSaveLadderBoardsWithDataWriteException() {
    Board board = new Board("Ladder 1");
    HashSet<Board> boards = new HashSet<>();
    boards.add(board);

    try (MockedStatic<GsonUtil> mockedGsonUtil = mockStatic(GsonUtil.class)) {
      doThrow(new DataWriteException("Error writing file")).when(GsonUtil.class);
      GsonUtil.writeFile("data/ladderboard.json", boards);

      BoardWriter.saveLadderBoards(boards);
      fail("Expected DataWriteException");
    } catch (DataWriteException e) {
      assertEquals("Error writing file", e.getMessage());
    }
  }

  @Test
  @DisplayName("Test saveQuizBoards with DataWriteException")
  void testSaveQuizBoardsWithDataWriteException() {
    Board board = new Board("Quiz 1");
    HashSet<Board> boards = new HashSet<>();
    boards.add(board);

    try (MockedStatic<GsonUtil> mockedGsonUtil = mockStatic(GsonUtil.class)) {
      doThrow(new DataWriteException("Error writing file")).when(GsonUtil.class);
      GsonUtil.writeFile("data/quizboard.json", boards);

      BoardWriter.saveQuizBoards(boards);
      fail("Expected DataWriteException");
    } catch (DataWriteException e) {
      assertEquals("Error writing file", e.getMessage());
    }
  }
}