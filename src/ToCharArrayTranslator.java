import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

// TODO: Add in null check
public class ToCharArrayTranslator implements Translator {
	public JsonObject translate(JsonElement e) {
		JsonElement translatedString = Main.translateJsonElement(e);
		JsonObject substrObject = Utils.wrapInSubstr(translatedString, "$$this", 1);
		JsonElement length = Utils.lengthObject(translatedString);
		JsonObject indexArray = Utils.wrapInRange(0, length, 1);
		return Utils.wrapInMap(substrObject, indexArray);
	}	
}
