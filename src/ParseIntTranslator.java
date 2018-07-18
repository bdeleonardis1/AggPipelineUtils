import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class ParseIntTranslator implements Translator {
	
	public JsonObject translate(JsonElement e) {
		JsonElement input = Main.translateJsonElement(e);
		
		JsonArray switchBranches = new JsonArray();
		for (int i = 0; i <= 9; i++) {
			JsonArray eqArray = new JsonArray();
			eqArray.add(i + "");
			eqArray.add("$$this");
			JsonObject eqObject = new JsonObject();
			eqObject.add("$eq", eqArray);
			JsonObject branch = Utils.wrapInBranch(eqObject, new JsonPrimitive(i));
			switchBranches.add(branch);
		}
		JsonObject switchObject = Utils.wrapInSwitch(switchBranches);
		JsonObject substrObject = Utils.wrapInSubstr(input, "$$this", 1);
		JsonObject rangeObject = Utils.wrapInRange(0, Utils.lengthObject(input), 1);
		JsonObject toCharArrayMapObject = Utils.wrapInMap(substrObject, rangeObject);
		JsonObject numArrayObject = Utils.wrapInMap(switchObject, toCharArrayMapObject);
		JsonObject multiplyObject = Utils.wrapInMult(new JsonPrimitive("$$value"), new JsonPrimitive(10));
		JsonObject sumObject = Utils.wrapInSum(multiplyObject, new JsonPrimitive("$$this"));
		return Utils.wrapInReduce(numArrayObject, 0, sumObject);
	}
}
