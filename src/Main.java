import java.util.Map;

import org.w3c.dom.ElementTraversal;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Main {
	
	static final Map<String, Translator> translators = Map.of("$toCharArray", new ToCharArrayTranslator());
	static final Map<String, String> wrappers = Map.of("$toCharArray", "$map");

	public static void main(String[] args) {
		try {
			// Get pipeline as string
			String inputPipeline = "[{$project:{charArr: {$toCharArray: \"$num\"}}}]";
			JsonArray pipeline = parseString(inputPipeline);
			JsonArray translatedPipeline = translateJsonArray(pipeline);
			// Output pipeline
			System.out.println(translatedPipeline);
		} catch (Exception e) {
			
		}
		
	}
	
	public static JsonArray parseString(String pipeline) {
		JsonParser parser = new JsonParser();
		return parser.parse(pipeline).getAsJsonArray();
	}
	
	public static JsonArray translateJsonArray(JsonArray arr) {
		JsonArray translatedArr = new JsonArray();
		for (JsonElement e : arr) {
			translatedArr.add(translateJsonElement(e));
		}
		
		return translatedArr;
	}
	
	public static JsonObject translateJsonObject(JsonObject obj) {
		JsonObject translatedObject = new JsonObject();
		for (Map.Entry<String, JsonElement> element : obj.entrySet()) {
			String key = element.getKey();
			JsonElement val = element.getValue();
			if (translators.containsKey(key)) {
				JsonObject specialTranslation = translators.get(key).translate(val);
				translatedObject.add(wrappers.get(key), specialTranslation.get(wrappers.get(key)));
			} else {
				translatedObject.add(key, translateJsonElement(val));
			}
		}
		return translatedObject;
	}
	
	public static JsonElement translateJsonElement(JsonElement e) {
		if (e.isJsonObject()) {
			return translateJsonObject(e.getAsJsonObject());
		} else if (e.isJsonArray()) {
			return translateJsonArray(e.getAsJsonArray());
		} else {
			return e;
		}
	}
}
