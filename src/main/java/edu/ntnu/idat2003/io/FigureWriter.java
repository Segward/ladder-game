package edu.ntnu.idat2003.io;

import java.util.HashSet;

import edu.ntnu.idat2003.exception.DataWriteException;
import edu.ntnu.idat2003.model.Figure;
import edu.ntnu.idat2003.util.CsvUtil;

/**
 *  Class for Writting Figure csv files.
 *  Inclueds method for writting Figure objects to csv.
 */
public class FigureWriter {

    private static final String path = "data/figure.csv";

    /**
     *  Method for saving figure objects to spesific csv file.
     *  Takes HasSet<Figure> as parameter.
     *  Using a for loop, saves all figure data as StringBuilder.
     *  Then utilizing different string methods fomats string to csv format,
     *  before utilising CsvUtil method to write data to spesific csv file.
     * 
     *  @param figures
     */
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
