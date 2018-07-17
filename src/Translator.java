import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public interface Translator {
	JsonObject translate(JsonElement e);
}
