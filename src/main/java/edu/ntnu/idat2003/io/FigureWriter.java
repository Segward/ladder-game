package edu.ntnu.idat2003.io;

import edu.ntnu.idat2003.exception.DataWriteException;
import edu.ntnu.idat2003.model.Figure;
import edu.ntnu.idat2003.util.CsvUtil;
import java.util.HashSet;

public class FigureWriter {

  private static final String path = "data/figure.csv";

  public static void saveFigures(HashSet<Figure> figures) {
    StringBuilder data = new StringBuilder();
    for (Figure figure : figures) {
      data.append(figure.toString()).append("\n");
    }

    String dataString = data.toString();
    if (dataString.charAt(dataString.length() - 1) == '\n') {
      dataString = dataString.substring(0, dataString.length() - 1);
    }

    try {
      CsvUtil.writeFile(path, data.toString());
    } catch (DataWriteException e) {
      e.printStackTrace();
    }
  }
}
