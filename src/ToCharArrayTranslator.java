import com.google.gson.JsonElement;

public class ToCharArrayTranslator implements Translator {
	public JsonElement translate(JsonElement e) {
		return e;
	}
}
