package edu.ntnu.idat2003.io;

import edu.ntnu.idat2003.model.Figure;
import java.util.HashSet;

public class FigureFactory {
  public static void makeFigures() {
    HashSet<Figure> figures = new HashSet<>();
    figures.add(new Figure("Knight", "/figure/knight.png"));
    figures.add(new Figure("Rook", "/figure/rook.png"));
    figures.add(new Figure("Queen", "/figure/queen.png"));
    figures.add(new Figure("King", "/figure/king.png"));
    figures.add(new Figure("Pawn", "/figure/pawn.png"));
    FigureWriter.saveFigures(figures);
  }
}
