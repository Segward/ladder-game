package edu.ntnu.idat2003.io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mockStatic;

import edu.ntnu.idat2003.exception.DataReadException;
import edu.ntnu.idat2003.model.Player;
import edu.ntnu.idat2003.util.CsvUtil;
import java.util.HashSet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

public class PlayerReaderTest {

  @Test
  @DisplayName("Test getPlayers")
  void testGetFigures() throws DataReadException {
    String mockCsv = "Kim Dokja,reader\nYoo Joonghyuk,protagonist";
    try (MockedStatic<CsvUtil> csvUtilMock = mockStatic(CsvUtil.class)) {
      csvUtilMock.when(() -> CsvUtil.readFile("data/player.csv")).thenReturn(mockCsv);

      HashSet<Player> players = PlayerReader.getPlayers();

      assertEquals(2, players.size());
      assertTrue(
          players.stream()
              .anyMatch(
                  p ->
                      p.getName().equals("Kim Dokja") && p.getFigure().getName().equals("reader")));

      assertTrue(
          players.stream()
              .anyMatch(
                  p ->
                      p.getName().equals("Yoo Joonghyuk")
                          && p.getFigure().getName().equals("protagonist")));
    }
  }

  @Test
  @DisplayName("Test getPlayers with empty file")
  void testGetPlayersWithEmptyFile() throws DataReadException {
    String mockCsv = "";
    try (MockedStatic<CsvUtil> csvUtilMock = mockStatic(CsvUtil.class)) {
      csvUtilMock.when(() -> CsvUtil.readFile("data/player.csv")).thenReturn(mockCsv);

      HashSet<Player> players = new HashSet<>();

      try {
        players = PlayerReader.getPlayers();
      } catch (DataReadException e) {
        assertEquals("File not found", e.getMessage());
      }

      assertEquals(true, players.isEmpty());
    }
  }
}
