package edu.ntnu.idat2003.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.HashSet;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

public class GsonUtilTest {

  @Test
  public void testSaveGetObjects() {
    HashSet<String> objects = new HashSet<>();
    objects.add("Test String");
    try {
      GsonUtil.saveObjects(objects, "data/test.json");
    } catch (Exception e) {
      e.printStackTrace();
    }

    Type objectType = new TypeToken<HashSet<String>>() {}.getType();
    try {
      HashSet<String> loadedObjects = GsonUtil.getObjects("data/test.json", objectType);
      assertEquals(objects, loadedObjects);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @AfterAll
  public static void clean() {
    GsonUtil.deleteObjects("data/test.json");
  }
}
