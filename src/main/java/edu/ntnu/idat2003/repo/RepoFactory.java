package edu.ntnu.idat2003.repo;

import edu.ntnu.idat2003.model.Board;
import edu.ntnu.idat2003.model.Figure;
import edu.ntnu.idat2003.model.Tile;
import java.util.HashSet;
import java.util.Collections;

public class RepoFactory {
  
  public static void makeFigures() {
    HashSet<Figure> figures = new HashSet<>();
    figures.add(new Figure("Knight"));
    figures.add(new Figure("Bishop"));
    figures.add(new Figure("Rook"));
    figures.add(new Figure("Queen"));
    figures.add(new Figure("King"));
    figures.add(new Figure("Pawn"));
    FigureRepository.saveFigures(figures);
  }

  public static void makeBoards() {
    Board board1 = new Board("Default Board");
    for (int i = 0; i < 90; i++) {
      Tile tile = new Tile(i);
      board1.addTile(i, tile);
    }

    Board board2 = new Board("Custom Board");
    for (int i = 0; i < 90; i++) {
      Tile tile = new Tile(i);
      board2.addTile(i, tile);
    }

    Board board3 = new Board("Advanced Board");
    for (int i = 0; i < 90; i++) {
      Tile tile = new Tile(i);
      board3.addTile(i, tile);
    }

    HashSet<Board> boards = new HashSet<>();
    Collections.addAll(boards, board1, board2, board3);
    BoardRepository.saveBoards(boards);
  }
}
