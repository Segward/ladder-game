package edu.ntnu.idat2003.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class FileUtil {
  public static void writeToFile(HashMap<Integer, String> map, String path) {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
      for (Integer key : map.keySet()) {
        bw.write(key + ":" + map.get(key).toString());
        bw.newLine();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static HashMap<Integer, String> readFromFile(String path) {
    HashMap<Integer, String> map = new HashMap<>();
    try (BufferedReader br = new BufferedReader(new FileReader(path))) {
      String line;
      while (br.readLine() != null) {
        line = br.readLine();
        String[] parts = line.split(":");
        map.put(Integer.parseInt(parts[0]), parts[1]);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return map;
  }
}
