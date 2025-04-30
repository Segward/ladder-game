package edu.ntnu.idat2003.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import edu.ntnu.idat2003.model.player.Figure;

public class FigureRepoTest {

  @BeforeAll
  public static void setup() {
    HashSet<Figure> figures = FigureRepo.getFigures();
    Figure figure = new Figure("Test Figure");
    figures.add(figure);
    FigureRepo.saveFigures(figures);
  }

  @Test
  public void testGetFigures() {
    HashSet<Figure> figures = FigureRepo.getFigures();
    boolean exists = figures.stream().anyMatch(figure -> figure.getName().equals("Test Figure"));
    assertEquals(true, exists);
  }

  @AfterAll
  public static void clean() {
    HashSet<Figure> figures = FigureRepo.getFigures();
    int size = figures.size();
    figures.removeIf(figure -> figure.getName().equals("Test Figure"));
    FigureRepo.saveFigures(figures);
    figures = FigureRepo.getFigures();
    assertEquals(size - 1, figures.size());
  }
}
