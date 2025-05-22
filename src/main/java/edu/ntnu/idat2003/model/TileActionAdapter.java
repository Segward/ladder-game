package edu.ntnu.idat2003.model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import edu.ntnu.idat2003.model.tileactions.ExtraDiceAction;
import edu.ntnu.idat2003.model.tileactions.LadderAction;
import edu.ntnu.idat2003.model.tileactions.QuestionAction;
import edu.ntnu.idat2003.model.tileactions.TileAction;
import java.lang.reflect.Type;

public class TileActionAdapter implements JsonSerializer<TileAction>, JsonDeserializer<TileAction> {

  @Override
  public JsonElement serialize(TileAction src, Type typeOfSrc, JsonSerializationContext context) {
    JsonObject result = new JsonObject();
    result.add("type", new JsonPrimitive(src.getClass().getSimpleName()));
    result.add("data", context.serialize(src));
    return result;
  }

  @Override
  public TileAction deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
      throws JsonParseException {
    JsonObject obj = json.getAsJsonObject();
    String type = obj.get("type").getAsString();
    JsonElement data = obj.get("data");

    switch (type) {
      case "ExtraDiceAction":
        return context.deserialize(data, ExtraDiceAction.class);
      case "LadderAction":
        return context.deserialize(data, LadderAction.class);
      case "QuestionAction":
        return context.deserialize(data, QuestionAction.class);
      default:
        throw new JsonParseException("Unknown type: " + type);
    }
  }
}
