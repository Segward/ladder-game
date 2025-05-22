package edu.ntnu.idat2003.io;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mockStatic;

import edu.ntnu.idat2003.exception.DataWriteException;
import edu.ntnu.idat2003.model.Figure;
import edu.ntnu.idat2003.util.CsvUtil;
import java.util.HashSet;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

public class FigureWriterTest {

  @Test
  void testSaveFigures() throws DataWriteException {
    Figure figure1 = new Figure("Kim Dokja", "/path/reader.png");
    Figure figure2 = new Figure("Yoo Joonghyuk", "/path/protagonist.png");
    HashSet<Figure> figures = new HashSet<>();
    figures.add(figure1);
    figures.add(figure2);

    try (MockedStatic<CsvUtil> csvUtilMock = mockStatic(CsvUtil.class)) {
      FigureWriter.saveFigures(figures);
      csvUtilMock.verify(
          () ->
              CsvUtil.writeFile(
                  eq("data/figure.csv"),
                  argThat(
                      data ->
                          data.contains("Kim Dokja,/path/reader.png")
                              && data.contains("Yoo Joonghyuk,/path/protagonist.png"))));
    }
  }
}
