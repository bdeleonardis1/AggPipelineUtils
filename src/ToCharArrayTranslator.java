import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

// TODO: Add in null check
public class ToCharArrayTranslator implements Translator {
	public JsonObject translate(JsonElement e) {
		JsonElement translatedString = Main.translateJsonElement(e);
		JsonArray substrInput = new JsonArray();
		substrInput.add(translatedString);
		substrInput.add("$$this");
		substrInput.add(1);
		JsonObject substrObject = new JsonObject();
		substrObject.add("$substr", substrInput);
		return Utils.wrapInMap(substrObject, generateIndexArray(translatedString));
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
