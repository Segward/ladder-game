package edu.ntnu.idat2003.io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mockStatic;

import edu.ntnu.idat2003.exception.DataReadException;
import edu.ntnu.idat2003.model.Figure;
import edu.ntnu.idat2003.util.CsvUtil;
import java.util.HashSet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

public class FigureReaderTest {

  @Test
  @DisplayName("Test getFigures")
  void testGetFigures() throws DataReadException {
    String mockCsv = "Kim Dokja,/path/reader.png\nYoo Joonghyuk,/path/protagonist.png";
    try (MockedStatic<CsvUtil> csvUtilMock = mockStatic(CsvUtil.class)) {
      csvUtilMock.when(() -> CsvUtil.readFile("data/figure.csv")).thenReturn(mockCsv);

      HashSet<Figure> figures = FigureReader.getFigures();

      assertEquals(2, figures.size());
      assertTrue(
          figures.stream()
              .anyMatch(
                  f -> f.getName().equals("Kim Dokja") && f.getPath().equals("/path/reader.png")));

      assertTrue(
          figures.stream()
              .anyMatch(
                  f ->
                      f.getName().equals("Yoo Joonghyuk")
                          && f.getPath().equals("/path/protagonist.png")));
    }
  }

  @Test
  @DisplayName("Test getAvailableFigures")
  void testGetAvailableFigures() throws DataReadException {
    String mockCsv = "Kim Dokja,/path/reader.png\nYoo Joonghyuk,/path/protagonist.png";

    try (MockedStatic<CsvUtil> csvUtilMock = mockStatic(CsvUtil.class)) {
      csvUtilMock.when(() -> CsvUtil.readFile("data/figure.csv")).thenReturn(mockCsv);

      HashSet<Figure> figures = FigureReader.getAvailableFigures();

      assertEquals(2, figures.size());
      assertTrue(
          figures.stream()
              .anyMatch(
                  f -> f.getName().equals("Kim Dokja") && f.getPath().equals("/path/reader.png")));

      assertTrue(
          figures.stream()
              .anyMatch(
                  f ->
                      f.getName().equals("Yoo Joonghyuk")
                          && f.getPath().equals("/path/protagonist.png")));
    }
  }
}
