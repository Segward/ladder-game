package edu.ntnu.idat2003.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;

import edu.ntnu.idat2003.exception.DataReadException;
import edu.ntnu.idat2003.exception.DataWriteException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

public class CsvUtilTest {

  @Test
  @DisplayName("Test readFile")
  void testReadFile() throws DataReadException {
    String mockData = "Name,Path\nKim Dokja,/reader.png";

    try (MockedStatic<CsvUtil> mockedStatic = mockStatic(CsvUtil.class)) {
      mockedStatic.when(() -> CsvUtil.readFile("data/player.csv")).thenReturn(mockData);

      String result = CsvUtil.readFile("data/player.csv");

      assertEquals(mockData, result);
      mockedStatic.verify(() -> CsvUtil.readFile("data/player.csv"), times(1));
    }
  }

  @Test
  @DisplayName("Test writeFile")
  void testWriteFile() throws DataWriteException {
    String data = "Name,Path\nKim Dokja,/reader.png";

    try (MockedStatic<CsvUtil> mockedStatic = mockStatic(CsvUtil.class)) {
      CsvUtil.writeFile("data/player.csv", data);

      mockedStatic.verify(() -> CsvUtil.writeFile("data/player.csv", data), times(1));
    }
  }
}
