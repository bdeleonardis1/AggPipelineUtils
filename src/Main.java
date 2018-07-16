import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Main {
	
	static final Map<String, Translator> translators = Map.of("toCharArray", new ToCharArrayTranslator()
																);

	public static void main(String[] args) {
		// Get pipeline as string
		String inputPipeline = "[{$project:{charArr:{$sum:[0, 1, {strLenCP: Hello}]}}}]";
		JsonArray pipeline = parseString(inputPipeline);
		JsonArray translatedPipeline = translatePipeline(pipeline);
		// Output pipeline
		System.out.println(translatedPipeline);
	}
	
	public static JsonArray parseString(String pipeline) {
		JsonParser parser = new JsonParser();
		return parser.parse(pipeline).getAsJsonArray();
	}
	
	public static JsonArray translatePipeline(JsonArray pipeline) {
		return pipeline;
	}
}
