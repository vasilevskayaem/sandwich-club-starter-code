package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {
    //    {\"name\":{\"mainName\":\"Bosna\",\"alsoKnownAs\":[\"Bosner\"]},\"placeOfOrigin\":\"Austria\",\"description\":\"Bosna
//        is a spicy Austrian fast food dish, said to have originated in either Salzburg or Linz.
//        It is now popular all over western Austria and southern
//        Bavaria.\",\"image\":\"https://upload.wikimedia.org/wikipedia/commons/c/ca/Bosna_mit_2_Bratw%C3%BCrsten.jpg\",\"ingredients\":[\"White
//        bread\",\"Bratwurst\",\"Onions\",\"Tomato ketchup\",\"Mustard\",\"Curry powder\"]}
//
    private static final String NAME = "name";
    private static final String MAIN_NAME = "mainName";
    private static final String ALSO_KNOWN = "alsoKnownAs";
    private static final String PLACE_OF_ORIGIN = "placeOfOrigin";
    private static final String DESCRIPTION = "description";
    private static final String IMAGE = "image";
    private static final String INGRIDIENTS = "ingredients";

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            sandwich = new Sandwich();
            JSONObject jsonName = jsonObject.optJSONObject(NAME);
            if (jsonName != null) {
                sandwich.setMainName(jsonName.optString(MAIN_NAME));
                JSONArray jsonAlsoKnown = jsonName.getJSONArray(ALSO_KNOWN);
                ArrayList<String> alsoKnown = new ArrayList<>();
                for (int i = 0; i < jsonAlsoKnown.length(); i++) {
                    alsoKnown.add(jsonAlsoKnown.getString(i));
                }
                sandwich.setAlsoKnownAs(alsoKnown);
            }
            sandwich.setDescription(jsonObject.optString(DESCRIPTION));
            sandwich.setImage(jsonObject.optString(IMAGE));
            sandwich.setPlaceOfOrigin(jsonObject.optString(PLACE_OF_ORIGIN));
            JSONArray jsonIngredients = jsonObject.getJSONArray(INGRIDIENTS);
            ArrayList<String> ingredients = new ArrayList<>();
            for (int i = 0; i < jsonIngredients.length(); i++) {
                ingredients.add(jsonIngredients.getString(i));
            }
            sandwich.setIngredients(ingredients);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sandwich;
    }
}
