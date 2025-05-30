package edu.ntnu.idat2003.io;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;

import edu.ntnu.idat2003.exception.DataReadException;
import edu.ntnu.idat2003.exception.DataWriteException;
import edu.ntnu.idat2003.model.Figure;
import edu.ntnu.idat2003.model.Player;
import edu.ntnu.idat2003.util.CsvUtil;
import edu.ntnu.idat2003.util.FileUtil;
import java.util.HashSet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.MockedStatic;

public class PlayerWriterTest {

  @Test
  @DisplayName("Test savePlayers")
  void testSavePlayers() throws DataWriteException {
    Player player1 = new Player("Kim Dokja", new Figure("reader", "/path/reader.png"));
    Player player2 =
        new Player("Yoo Joonghyuk", new Figure("protagonist", "/path/protagonist.png"));
    HashSet<Player> players = new HashSet<>();
    players.add(player1);
    players.add(player2);

    try (MockedStatic<CsvUtil> csvUtilMock = mockStatic(CsvUtil.class)) {
      ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

      PlayerWriter.savePlayers(players);

      csvUtilMock.verify(() -> CsvUtil.writeFile(eq("data/player.csv"), captor.capture()));
      String writtenData = captor.getValue();
      assertTrue(writtenData.contains("Kim Dokja,reader"));
      assertTrue(writtenData.contains("Yoo Joonghyuk,protagonist"));
    }
  }

  @Test
  @DisplayName("Test savePlayers")
  void testAddPlayer() throws DataWriteException, DataReadException {
    Player player = new Player("Kim Dokja", new Figure("reader", "/path/reader.png"));
    HashSet<Player> players = new HashSet<>();
    players.add(player);

    try (MockedStatic<CsvUtil> csvUtilMock = mockStatic(CsvUtil.class)) {
      ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

      PlayerWriter.addPlayer(player);

      csvUtilMock.verify(() -> CsvUtil.writeFile(eq("data/player.csv"), captor.capture()));
      String writtenData = captor.getValue();
      assertTrue(writtenData.contains("Kim Dokja,reader"));
    }
  }

  @Test
  @DisplayName("Test removePlayer")
  void testRemovePlayer() throws DataReadException, DataWriteException {
    Player player1 = new Player("Kim Dokja", new Figure("reader", "/path/reader.png"));
    Player player2 =
        new Player("Yoo Joonghyuk", new Figure("protagonist", "/path/protagonist.png"));
    HashSet<Player> players = new HashSet<>();
    players.add(player1);
    players.add(player2);

    try (MockedStatic<CsvUtil> csvUtilMock = mockStatic(CsvUtil.class);
        MockedStatic<FileUtil> fileUtilMock = mockStatic(FileUtil.class);
        MockedStatic<PlayerReader> playerReaderMock = mockStatic(PlayerReader.class)) {
      playerReaderMock.when(PlayerReader::getPlayers).thenReturn(players);

      PlayerWriter.removePlayer(player1);

      csvUtilMock.verify(
          () ->
              CsvUtil.writeFile(
                  eq("data/player.csv"),
                  argThat(data -> data.contains("Yoo Joonghyuk") && !data.contains("Kim Dokja"))),
          times(1));
      fileUtilMock.verifyNoInteractions();
    }
  }

  @Test
  @DisplayName("Test removePlayer with no player")
  void testRemovePlayerWithNoPlayer() throws DataReadException, DataWriteException {
    try (MockedStatic<CsvUtil> csvUtilMock = mockStatic(CsvUtil.class)) {
      PlayerWriter.removePlayer(null);
    } catch (IllegalArgumentException e) {
      assertTrue(e.getMessage().contains("Player cannot be null"));
    }
  }

  @Test
  @DisplayName("Test savePlayers with empty set")
  void testSavePlayersWithEmptySet() throws DataWriteException {
    HashSet<Player> players = new HashSet<>();

    try (MockedStatic<CsvUtil> csvUtilMock = mockStatic(CsvUtil.class)) {
      PlayerWriter.savePlayers(players);
    } catch (IllegalArgumentException e) {
      assertTrue(e.getMessage().contains("Player set cannot be null or empty"));
    }
  }

  @Test
  @DisplayName("Test savePlayers with null set")
  void testSavePlayersWithNullSet() throws DataWriteException {
    HashSet<Player> players = null;

    try (MockedStatic<CsvUtil> csvUtilMock = mockStatic(CsvUtil.class)) {
      PlayerWriter.savePlayers(players);
    } catch (IllegalArgumentException e) {
      assertTrue(e.getMessage().contains("Player set cannot be null or empty"));
    }
  }

  @Test
  @DisplayName("Test addPlayer with null player")
  void testAddPlayerWithNullPlayer() throws DataWriteException, DataReadException {
    try (MockedStatic<CsvUtil> csvUtilMock = mockStatic(CsvUtil.class)) {
      PlayerWriter.addPlayer(null);
    } catch (IllegalArgumentException e) {
      assertTrue(e.getMessage().contains("Player cannot be null"));
    }
  }
}
