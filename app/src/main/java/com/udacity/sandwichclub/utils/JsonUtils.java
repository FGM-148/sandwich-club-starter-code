package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static final String JSON_NAME_KEY = "name";
    public static final String JSON_MAIN_NAME_KEY = "mainName";
    public static final String JSON_AKA_KEY = "alsoKnownAs";
    public static final String JSON_ORIGIN_KEY = "placeOfOrigin";
    public static final String JSON_DESCRIPTION_KEY = "description";
    public static final String JSON_IMAGE_KEY = "image";
    public static final String JSON_INGREDIENTS_KEY = "ingredients";
    private static final String TAG = "JSonUtils";

    public static Sandwich parseSandwichJson(String json) {
        try {
            JSONObject jsonSandwich = new JSONObject(json);

            JSONObject name = jsonSandwich.optJSONObject(JSON_NAME_KEY);
            String mainName = name.optString(JSON_MAIN_NAME_KEY);
            List<String> alsoKnowsAs = convertJsonArrayToStringList(
                    name.optJSONArray(JSON_AKA_KEY));

            String placeOfOrigin = jsonSandwich.optString(JSON_ORIGIN_KEY);
            String description = jsonSandwich.optString(JSON_DESCRIPTION_KEY);
            String image = jsonSandwich.optString(JSON_IMAGE_KEY);
            List<String> ingredients = convertJsonArrayToStringList(
                    jsonSandwich.optJSONArray(JSON_INGREDIENTS_KEY));

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
