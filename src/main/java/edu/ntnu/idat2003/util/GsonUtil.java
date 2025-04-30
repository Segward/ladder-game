package edu.ntnu.idat2003.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.ntnu.idat2003.exception.DataCreateException;
import edu.ntnu.idat2003.exception.DataReadException;
import edu.ntnu.idat2003.exception.DataWriteException;
import edu.ntnu.idat2003.model.board.TileActionAdapter;
import edu.ntnu.idat2003.model.tileactions.TileAction;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.HashSet;

public class GsonUtil {

  private static final Gson gson =
      new GsonBuilder()
          .registerTypeAdapter(TileAction.class, new TileActionAdapter())
          .setPrettyPrinting()
          .create();

  public static <T> void writeFile(String filePath, HashSet<T> objects) throws DataWriteException {
    if (!FileUtil.fileExists(filePath)) {
      try {
        FileUtil.createFile(filePath);
      } catch (DataCreateException e) {
        throw new DataWriteException("Failed to create file at " + filePath, e);
      }
    }

    try (FileWriter writer = new FileWriter(filePath)) {
      gson.toJson(objects, writer);
      writer.flush();
    } catch (Exception e) {
      throw new DataWriteException("Failed to save data to " + filePath, e);
    }
  }

  public static <T> HashSet<T> readFile(String filePath, Type type) throws DataReadException {
    HashSet<T> objects = new HashSet<>();
    if (!FileUtil.fileExists(filePath)) {
      return objects;
    }

    try (FileReader reader = new FileReader(filePath)) {
      objects = gson.fromJson(reader, type);
    } catch (Exception e) {
      throw new DataReadException("Failed to read data from " + filePath, e);
    }

    if (objects == null) {
      objects = new HashSet<>();
    }

    return objects;
  }
}
