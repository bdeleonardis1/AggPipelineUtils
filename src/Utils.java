import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class Utils {
	public static JsonElement lengthObject(JsonElement input) {
		JsonObject wrapper = new JsonObject();
		wrapper.add("$strLenCP", input);
		return wrapper;
	}
	
	public static JsonObject wrapInMap(JsonElement in, JsonElement input) {
		JsonObject mapObject = new JsonObject();
		JsonObject value = new JsonObject();
		value.add("in", in);
		value.add("input", input);
		mapObject.add("$map", value);
		return mapObject;
	}
	
	public static JsonObject wrapInSubstr(JsonElement str, String start, int length) {
		return wrapInSubstr(str, new JsonPrimitive(start), new JsonPrimitive(length));
	}
	
	public static JsonObject wrapInSubstr(JsonElement str, JsonElement start, JsonElement length) {
		JsonArray substrInput = new JsonArray();
		substrInput.add(str);
		substrInput.add(start);
		substrInput.add(length);
		JsonObject substrObject = new JsonObject();
		substrObject.add("$substr", substrInput);
		return substrObject;
	}
	
	public static JsonObject wrapInRange(int start, JsonElement end, int step) {
		return wrapInRange(new JsonPrimitive(start), end, new JsonPrimitive(step));
	}
	
	public static JsonObject wrapInRange(JsonElement start, JsonElement end, JsonElement step) {
		JsonObject rangeObject = new JsonObject();
		JsonArray rangeArray = new JsonArray();
		rangeArray.add(start);
		rangeArray.add(end);
		rangeArray.add(step);		
		rangeObject.add("$range", rangeArray);
		return rangeObject;
	}
	
	public static JsonObject wrapInSwitch(JsonArray branches) {
		JsonObject branchesObject = new JsonObject();
		branchesObject.add("branches", branches);
		JsonObject switchObject = new JsonObject();
		switchObject.add("$switch", branchesObject);
		return switchObject;
	}
	
	public static JsonObject wrapInReduce(JsonElement input, int initialValue, JsonElement in) {
		return wrapInReduce(input, new JsonPrimitive(initialValue), in);
	}
	
	public static JsonObject wrapInReduce(JsonElement input, JsonElement initialValue, JsonElement in) {
		JsonObject reduceValue = new JsonObject();
		reduceValue.add("input", input);
		reduceValue.add("initialValue", initialValue);
		reduceValue.add("in", in);
		JsonObject reduceRet = new JsonObject();
		reduceRet.add("$reduce", reduceValue);
		return reduceRet;
	}
	
	public static JsonObject wrapInSum(JsonElement...elements) {
		JsonArray arr = new JsonArray();
		for (JsonElement element : elements) {
			arr.add(element);
		}
		JsonObject sumObject = new JsonObject();
		sumObject.add("$sum", arr);
		return sumObject;
	}
	
	public static JsonObject wrapInMult(JsonElement...elements) {
		JsonArray arr = new JsonArray();
		for (JsonElement element : elements) {
			arr.add(element);
		}
		JsonObject multObject = new JsonObject();
		multObject.add("$multiply", arr);
		return multObject;
	}
	
	public static JsonObject wrapInBranch(JsonElement switchCase, JsonElement then) {
		JsonObject branch = new JsonObject();
		branch.add("case", switchCase);
		branch.add("then", then);
		return branch;
	}
	
	public static JsonObject wrapInEq(JsonElement left, JsonElement right) {
		JsonArray eqArray = new JsonArray();
		eqArray.add(left);
		eqArray.add(right);
		JsonObject eqObject = new JsonObject();
		eqObject.add("eq", eqArray);
		return eqObject;
	}
}
