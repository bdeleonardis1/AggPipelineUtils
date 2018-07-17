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
	
	public static String difference(String str1, String str2) {
	    if (str1 == null) {
	        return str2;
	    }
	    if (str2 == null) {
	        return str1;
	    }
	    int at = indexOfDifference(str1, str2);
	    
	    return str2.substring(at);
	}

	public static int indexOfDifference(CharSequence cs1, CharSequence cs2) {
	    if (cs1 == cs2) {
	        //return INDEX_NOT_FOUND;
	    }
	    if (cs1 == null || cs2 == null) {
	        return 0;
	    }
	    int i;
	    for (i = 0; i < cs1.length() && i < cs2.length(); ++i) {
	        if (cs1.charAt(i) != cs2.charAt(i)) {
	            break;
	        }
	    }
	    if (i < cs2.length() || i < cs1.length()) {
	        return i;
	    }
	    //return INDEX_NOT_FOUND;
	    return 0;
	}
}
