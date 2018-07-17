import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class ToCharArrayTranslator implements Translator {
	public JsonObject translate(JsonElement e) {
		JsonElement translatedString = Main.translateJsonElement(e);
		JsonObject mapObject = new JsonObject();
		JsonObject mapValue = new JsonObject();
		mapValue.add("input", generateIndexArray(translatedString));
		JsonArray substrInput = new JsonArray();
		substrInput.add(translatedString);
		substrInput.add("$$this");
		substrInput.add(1);
		JsonObject substrObject = new JsonObject();
		substrObject.add("$substr", substrInput);
		mapValue.add("in", substrObject);
		mapObject.add("$map", mapValue);
		return mapObject;
	}
	
	private JsonElement generateIndexArray(JsonElement input) {
		JsonObject rangeObject = new JsonObject();
		JsonArray rangeArray = new JsonArray();
		rangeArray.add(0);
		rangeArray.add(Utils.lengthObject(input));
		rangeArray.add(1);
		rangeObject.add("$range", rangeArray);
		return rangeObject;
	}
	
	
}
