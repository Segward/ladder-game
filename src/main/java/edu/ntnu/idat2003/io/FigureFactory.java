package edu.ntnu.idat2003.io;

import edu.ntnu.idat2003.model.Figure;
import java.util.HashSet;

/**
 *  Class representing a factory where Figure objects are made.
 */
public class FigureFactory {
  /**
   *  Method that creates a set of Figures.
   *  Hard codes 5 unique Figure objects and adds them to an HasSet,
   *  before utilizing a FigureWritter method to save them as csv.
   */
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
