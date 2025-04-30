package edu.ntnu.idat2003.repo;

import edu.ntnu.idat2003.model.Vector2;
import edu.ntnu.idat2003.model.board.Board;
import edu.ntnu.idat2003.model.board.Tile;
import edu.ntnu.idat2003.model.player.Figure;
import edu.ntnu.idat2003.model.tileactions.ExtraDiceAction;
import edu.ntnu.idat2003.model.tileactions.LadderAction;
import java.util.Collections;
import java.util.HashSet;

public class RepoFactory {

  public static void makeFigures() {
    HashSet<Figure> figures = new HashSet<>();
    figures.add(new Figure("Knight"));
    figures.add(new Figure("Rook"));
    figures.add(new Figure("Queen"));
    figures.add(new Figure("King"));
    figures.add(new Figure("Pawn"));
    FigureRepo.saveFigures(figures);
  }

  public static void makeBoards() {
    Board board1 = new Board("Default Board");
    Tile previous = null;
    int count = 1;
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 10; j++) {
        int x = i % 2 == 1 ? 9 - j : j;
        Vector2 pos = new Vector2(x, i);
        Tile tile = new Tile(pos, String.valueOf(count));
        board1.addTile(pos, tile);
        if (previous != null) {
          previous.setNextPosition(tile.getPosition());
        }
        previous = tile;
        count++;
      }
    }

    Vector2 start = new Vector2(5, 0);
    Vector2 end = new Vector2(4, 2);
    LadderAction action = new LadderAction(start, end);
    board1.addAction(start, action);

    Vector2 start2 = new Vector2(8, 5);
    Vector2 end2 = new Vector2(6, 3);
    LadderAction action2 = new LadderAction(start2, end2);
    board1.addAction(start2, action2);

    Vector2 start3 = new Vector2(1, 5);
    Vector2 end3 = new Vector2(3, 4);
    LadderAction action3 = new LadderAction(start3, end3);
    board1.addAction(start3, action3);

    Vector2 start4 = new Vector2(3, 6);
    Vector2 end4 = new Vector2(2, 8);
    LadderAction action4 = new LadderAction(start4, end4);
    board1.addAction(start4, action4);

    Vector2 start5 = new Vector2(8, 2);
    ExtraDiceAction action5 = new ExtraDiceAction(start5);
    board1.addAction(start5, action5);

    Vector2 start6 = new Vector2(4, 3);
    ExtraDiceAction action6 = new ExtraDiceAction(start6);
    board1.addAction(start6, action6);

    HashSet<Board> boards = new HashSet<>();
    Collections.addAll(boards, board1);
    BoardRepo.saveBoards(boards);
  }
}
