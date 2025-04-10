package edu.ntnu.idat2003.repo;

import edu.ntnu.idat2003.model.Board;
import edu.ntnu.idat2003.model.Figure;
import edu.ntnu.idat2003.model.Tile;
import edu.ntnu.idat2003.model.Vector2;
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
    FigureRepo.saveFigures(figures);
  }
  
  public static void makeBoards() {
    Board board1 = new Board("Default Board");
    Tile previous = null;
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 10; j++) {
        if (i % 2 == 1) {
          Vector2 pos = new Vector2(9-j, i);
          Tile tile = new Tile(pos, null, null);        
          board1.addTile(pos, tile);
          if (previous != null) {
            previous.setNextPosition(tile.getPosition());
          }
          previous = tile;
        } else {
          Vector2 pos = new Vector2(j, i);
          Tile tile = new Tile(pos, null, null);        
          board1.addTile(pos, tile);
          if (previous != null) {
            previous.setNextPosition(tile.getPosition());
          }
          previous = tile;
        }
      }
    }

    HashSet<Board> boards = new HashSet<>();
    Collections.addAll(boards, board1);
    BoardRepo.saveBoards(boards);
  }
}
