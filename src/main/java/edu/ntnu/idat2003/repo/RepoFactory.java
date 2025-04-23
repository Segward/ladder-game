package edu.ntnu.idat2003.repo;

import edu.ntnu.idat2003.model.Board;
import edu.ntnu.idat2003.model.ExtraDiceAction;
import edu.ntnu.idat2003.model.Figure;
import edu.ntnu.idat2003.model.LadderAction;
import edu.ntnu.idat2003.model.Tile;
import edu.ntnu.idat2003.model.Vector2;
import java.util.Collections;
import java.util.HashSet;

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
          Vector2 pos = new Vector2(9 - j, i);
          Tile tile = new Tile(pos, null);
          board1.addTile(pos, tile);
          if (previous != null) {
            previous.setNextPosition(tile.getPosition());
          }
          previous = tile;
        } else {
          Vector2 pos = new Vector2(j, i);
          Tile tile = new Tile(pos, null);
          board1.addTile(pos, tile);
          if (previous != null) {
            previous.setNextPosition(tile.getPosition());
          }
          previous = tile;
        }
      }
    }

    Vector2 start = new Vector2(5, 0);
    Vector2 end = new Vector2(5, 8);
    LadderAction action = new LadderAction(start, end);
    board1.addLadderAction(action);

    Vector2 start2 = new Vector2(0, 5);
    Vector2 end2 = new Vector2(8, 5);
    LadderAction action2 = new LadderAction(start2, end2);
    board1.addLadderAction(action2);

    Vector2 start3 = new Vector2(9, 0);
    ExtraDiceAction action3 = new ExtraDiceAction(start3);
    board1.addExtraDiceAction(action3);

    Vector2 start4 = new Vector2(0, 8);
    ExtraDiceAction action4 = new ExtraDiceAction(start4);
    board1.addExtraDiceAction(action4);

    HashSet<Board> boards = new HashSet<>();
    Collections.addAll(boards, board1);
    BoardRepo.saveBoards(boards);
  }
}
