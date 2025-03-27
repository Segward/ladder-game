package edu.ntnu.idat2003.services;

import edu.ntnu.idat2003.models.Board;
import edu.ntnu.idat2003.models.Figure;
import edu.ntnu.idat2003.models.Tile;
import java.util.Collections;
import java.util.HashSet;

public class GameFactory {

  public static HashSet<Figure> makeFigures() {
    HashSet<Figure> figures = new HashSet<>();

    Figure figure1 = new Figure("red");
    Figure figure2 = new Figure("blue");
    Figure figure3 = new Figure("green");
    Figure figure4 = new Figure("yellow");
    Figure figure5 = new Figure("purple");

    Collections.addAll(figures, figure1, figure2, figure3, figure4, figure5);
    return figures;
  }

  public static HashSet<Board> makeBoards() {
    HashSet<Board> boards = new HashSet<>();

    Board board1 = new Board("daddy choke me");
    for (int i = 0; i < 90; i++) {
      Tile tile = new Tile(i);
      board1.addTile(i, tile);
    }

    Board board2 = new Board("mommy spank me");
    for (int i = 0; i < 90; i++) {
      Tile tile = new Tile(i);
      board2.addTile(i, tile);
    }

    Board board3 = new Board("uncle touch me");
    for (int i = 0; i < 90; i++) {
      Tile tile = new Tile(i);
      board3.addTile(i, tile);
    }

    Collections.addAll(boards, board1, board2, board3);
    return boards;
  }
}
