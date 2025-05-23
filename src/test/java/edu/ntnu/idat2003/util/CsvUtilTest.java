package edu.ntnu.idat2003.util;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.*;

import edu.ntnu.idat2003.exception.DataReadException;
import edu.ntnu.idat2003.exception.DataWriteException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;

public class CsvUtilTest {

  private static final String path = "test-data/player.csv";

  @BeforeEach
  void setup() throws IOException {
    Files.createDirectories(Path.of("test-data"));
  }

  @AfterEach
  void cleanup() throws IOException {
    Files.deleteIfExists(Path.of(path));
  }

  @Test
  @DisplayName("Test readFile and writeFile")
  void testWriteThenReadFile() throws Exception {
    String data = "Name,Path\nKim Dokja,/reader.png";

    CsvUtil.writeFile(path, data);
    String result = CsvUtil.readFile(path);

    assertEquals(data, result);
  }

  @Test
  @DisplayName("Test readFile throws exception")
  void testReadFileThrowsException() {
    try (MockedStatic<CsvUtil> mocked = mockStatic(CsvUtil.class)) {
      mocked
          .when(() -> CsvUtil.readFile("test-data/nothing.csv"))
          .thenThrow(new DataReadException("File not found"));

      assertThrows(DataReadException.class, () -> CsvUtil.readFile("test-data/nothing.csv"));
    }
  }

  @Test
  @DisplayName("Test writeFile throws on null path")
  void testWriteFileThrowsOnNull() {
    try (MockedStatic<CsvUtil> mocked = mockStatic(CsvUtil.class)) {
      mocked
          .when(() -> CsvUtil.writeFile(anyString(), isNull()))
          .thenThrow(new DataWriteException("Null data"));

      assertThrows(DataWriteException.class, () -> CsvUtil.writeFile(path, null));
    }
  }
}
