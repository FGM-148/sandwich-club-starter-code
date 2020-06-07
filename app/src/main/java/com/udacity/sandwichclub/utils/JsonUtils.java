package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String TAG = "JSonUtils";

    public static Sandwich parseSandwichJson(String json) {
        try {
            JSONObject jsonSandwich = new JSONObject(json);

            JSONObject name = jsonSandwich.getJSONObject("name");
            String mainName = name.getString("mainName");
            List<String> alsoKnowsAs = convertJsonArrayToStringList(
                    name.getJSONArray("alsoKnownAs"));

            String placeOfOrigin = jsonSandwich.getString("placeOfOrigin");
            String description = jsonSandwich.getString("description");
            String image = jsonSandwich.getString("image");
            List<String> ingredients = convertJsonArrayToStringList(
                    jsonSandwich.getJSONArray("ingredients"));

            Sandwich sandwich = new Sandwich(mainName, alsoKnowsAs, placeOfOrigin, description,
                    image, ingredients);
            return sandwich;
        } catch (JSONException e) {
            Log.e(TAG, "Unable to parse input string");
            return null;
        }
    }

    private static List<String> convertJsonArrayToStringList(JSONArray arr) throws JSONException {
        List<String> list = new ArrayList<String>();
        for(int i = 0; i < arr.length(); i++) {
            list.add(arr.getString(i));
        }
        return list;
    }
}
