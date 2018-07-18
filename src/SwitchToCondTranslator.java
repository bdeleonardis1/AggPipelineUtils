import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class SwitchToCondTranslator implements Translator {
	
	
	/*
	[{$project:{type:{$switchToCond:{$switch:{branches:[{case:{$lt:["$num",0]},then:"neg"},{case:{$gt:["$num",0]},then:"pos"},{case:{$eq:["$num",0]},then:"eq"}]}}}}}]
	*/
	public JsonObject translate(JsonElement e) {
		JsonElement input = Main.translateJsonElement(e);
		
		JsonObject switchObject = input.getAsJsonObject();
		switchObject = switchObject.get("$switch").getAsJsonObject();
		JsonArray branches = switchObject.get("branches").getAsJsonArray();		
		JsonObject branch = branches.get(0).getAsJsonObject();
		JsonObject lastCond = Utils.wrapInCond(branch.get("case"), branch.get("then"));
		
		JsonObject topCond = lastCond;
		branches.remove(0);
		for (JsonElement el : branches) {
			branch = el.getAsJsonObject();
			JsonObject currCond = Utils.wrapInCond(branch.get("case"), branch.get("then"));
			lastCond.get("$cond").getAsJsonObject().add("else", currCond);
			lastCond = currCond;
		}
		if (switchObject.has("default")) {
			lastCond.get("$cond").getAsJsonObject().add("else", switchObject.get("default"));	
		} else {
			lastCond.get("$cond").getAsJsonObject().add("else", new JsonPrimitive("null"));
		}
		return topCond;
	}
}
