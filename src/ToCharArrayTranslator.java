import com.google.gson.JsonObject;

public class ToCharArrayTranslator implements Translator {
	public JsonObject translate(JsonObject obj) {
		return obj;
	}
}
