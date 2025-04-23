package edu.ntnu.idat2003.repo;

import edu.ntnu.idat2003.model.Board;
import edu.ntnu.idat2003.model.Figure;
import edu.ntnu.idat2003.model.LadderAction;
import edu.ntnu.idat2003.model.Tile;
import edu.ntnu.idat2003.model.LadderAction;
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

    Tile destination = board1.getTile(new Vector2(3, 1));
    LadderAction ladder = new LadderAction(destination);
    board1.setTileAction(new Vector2(2, 0), ladder);

    Tile destination2 = board1.getTile(new Vector2(4, 3));
    LadderAction ladder2 = new LadderAction(destination2);
    board1.setTileAction(new Vector2(6, 1), ladder2);

    Tile destination3 = board1.getTile(new Vector2(4, 0));
    LadderAction ladder3 = new LadderAction(destination3);
    board1.setTileAction(new Vector2(5, 1), ladder3);

    Tile destination4 = board1.getTile(new Vector2(0, 3));
    LadderAction ladder4 = new LadderAction(destination4);
    board1.setTileAction(new Vector2(1, 1), ladder4);

    Tile destination5 = board1.getTile(new Vector2(0, 0));
    LadderAction ladder5 = new LadderAction(destination5);
    board1.setTileAction(new Vector2(1, 0), ladder5);

    Tile destination6 = board1.getTile(new Vector2(3, 3));
    LadderAction ladder6 = new LadderAction(destination6);
    board1.setTileAction(new Vector2(4, 1), ladder6);

    Tile destination7 = board1.getTile(new Vector2(9, 5));
    LadderAction ladder7 = new LadderAction(destination7);
    board1.setTileAction(new Vector2(5, 3), ladder7);

    Tile destination8 = board1.getTile(new Vector2(0, 6));
    LadderAction ladder8 = new LadderAction(destination8);
    board1.setTileAction(new Vector2(1, 5), ladder8);

    Tile destination9 = board1.getTile(new Vector2(0, 7));
    LadderAction ladder9 = new LadderAction(destination9);
    board1.setTileAction(new Vector2(2, 6), ladder9);

    Tile destination10 = board1.getTile(new Vector2(0, 8));
    LadderAction ladder10 = new LadderAction(destination10);
    board1.setTileAction(new Vector2(2, 7), ladder10);

    Tile destination11 = board1.getTile(new Vector2(5, 6));
    LadderAction ladder11 = new LadderAction(destination11);
    board1.setTileAction(new Vector2(6, 8), ladder11);

    Tile destination12 = board1.getTile(new Vector2(4, 6));
    LadderAction ladder12 = new LadderAction(destination12);
    board1.setTileAction(new Vector2(5, 7), ladder12);

    Tile destination13 = board1.getTile(new Vector2(3, 7));
    LadderAction ladder13 = new LadderAction(destination13);
    board1.setTileAction(new Vector2(4, 8), ladder13);

    Tile destination14 = board1.getTile(new Vector2(1, 7));
    LadderAction ladder14 = new LadderAction(destination14);
    board1.setTileAction(new Vector2(5, 8), ladder14);

    HashSet<Board> boards = new HashSet<>();
    Collections.addAll(boards, board1);
    BoardRepo.saveBoards(boards);
  }
}
