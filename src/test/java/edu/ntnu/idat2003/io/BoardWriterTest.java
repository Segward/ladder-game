package edu.ntnu.idat2003.io;

import static org.mockito.Mockito.*;

import edu.ntnu.idat2003.exception.DataWriteException;
import edu.ntnu.idat2003.model.Board;
import edu.ntnu.idat2003.util.GsonUtil;
import java.util.HashSet;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

class BoardWriterTest {

  @Test
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
  void testSaveLadderBoardsException() throws DataWriteException {
    Board board = new Board("FailBoard");
    HashSet<Board> boards = new HashSet<>();
    boards.add(board);
    try (MockedStatic<GsonUtil> mockedGsonUtil = mockStatic(GsonUtil.class)) {
      mockedGsonUtil
          .when(() -> GsonUtil.writeFile("data/ladderboard.json", boards))
          .thenThrow(new DataWriteException("Simulated write failure"));
      BoardWriter.saveLadderBoards(boards);
    }
  }
}