package edu.ntnu.idat2003.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.ntnu.idat2003.exception.DataCreateException;
import edu.ntnu.idat2003.exception.DataReadException;
import edu.ntnu.idat2003.exception.DataWriteException;
import edu.ntnu.idat2003.model.TileActionAdapter;
import edu.ntnu.idat2003.model.tileactions.TileAction;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.HashSet;

/**
 * Utility class for reading and writing JSON files using Gson. This class provides methods to read
 * and write data in JSON format, specifically for collections of objects.
 */
public class GsonUtil {

  private static final Gson gson =
      new GsonBuilder()
          .registerTypeAdapter(TileAction.class, new TileActionAdapter())
          .setPrettyPrinting()
          .create();

  /**
   * Writes a collection of objects to a JSON file. If the file does not exist, it will be created.
   *
   * @param filePath the path to the file where the data will be written
   * @param objects the collection of objects to be written to the file
   * @param <T> the type of objects in the collection
   * @throws DataWriteException if an error occurs while writing to the file
   */
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

  /**
   * Reads a collection of objects from a JSON file. If the file does not exist, an empty collection
   * will be returned.
   *
   * @param filePath the path to the file from which the data will be read
   * @param type the type of objects in the collection
   * @param <T> the type of objects in the collection
   * @return a collection of objects read from the file
   * @throws DataReadException if an error occurs while reading from the file
   */
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
