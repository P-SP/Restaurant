package com.example.eigenaar.restaurant;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Class for the API request of the categories.
 */

public class CategoriesApiRequest implements Response.Listener<JSONObject>, Response.ErrorListener{
    private Context context;
    private Callback callbackActivity;

    // callback
    public interface Callback {
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }

    // Constructor
    public CategoriesApiRequest(Context aContext){
        context = aContext;
    }

    // attempts to retrieve categories from API
    public void getCategories(Callback activity){
        callbackActivity = activity;

        // create new queue
        RequestQueue queue = Volley.newRequestQueue(context);

        // create JSON object
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                "https://resto.mprog.nl/categories", null, this, this);
        queue.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        callbackActivity.gotCategoriesError(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        JSONArray categoriesArray = new JSONArray();
        ArrayList<String> categories = new ArrayList<>();

        // extract the array
        try {
            categoriesArray = response.getJSONArray("categories");
        } catch (JSONException e) {
            callbackActivity.gotCategoriesError(e.getMessage());
        }

        // get the categories
        for (int i = 0; i < categoriesArray.length(); i++) {
            try{
                categories.add(categoriesArray.getString(i));
            } catch (JSONException e) {
                callbackActivity.gotCategoriesError(e.getMessage());
            }
        }

        // send it back
        callbackActivity.gotCategories(categories);
    }
}
