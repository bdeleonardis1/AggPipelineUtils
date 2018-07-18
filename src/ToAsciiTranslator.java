import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class ToAsciiTranslator implements Translator {
	public JsonObject translate(JsonElement e) {
		JsonElement input = Main.translateJsonElement(e);
		JsonArray branches = new JsonArray();
		for (int i = 65; i <= 122; i++) {
			if (i == 36) {
				continue;
			}
			System.out.println(((char) + i) + "");
			JsonObject eqObject = Utils.wrapInEq(new JsonPrimitive(((char) i) + ""), input);
			JsonElement then = new JsonPrimitive(i);
			JsonObject branch = Utils.wrapInBranch(eqObject, then);
			branches.add(branch);
		}
		return Utils.wrapInSwitch(branches);
	}
}
