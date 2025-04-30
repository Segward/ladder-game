package edu.ntnu.idat2003.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import edu.ntnu.idat2003.model.board.Board;

public class BoardRepoTest {

  @BeforeAll
  public static void setup() {
    HashSet<Board> boards = BoardRepo.getBoards();
    Board board = new Board("Test Board");
    boards.add(board);
    BoardRepo.saveBoards(boards);
  }

  @Test
  public void testGetBoards() {
    HashSet<Board> boards = BoardRepo.getBoards();
    boolean exists = boards.stream().anyMatch(board -> board.getName().equals("Test Board"));
    assertEquals(true, exists);
  }

  @AfterAll
  public static void clean() {
    HashSet<Board> boards = BoardRepo.getBoards();
    int size = boards.size();
    boards.removeIf(board -> board.getName().equals("Test Board"));
    BoardRepo.saveBoards(boards);
    boards = BoardRepo.getBoards();
    assertEquals(size - 1, boards.size());
  }
}
