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
 * Class for the API request of the menu items.
 */

public class MenuRequest implements Response.Listener<JSONObject>, Response.ErrorListener{
    private Context context;
    private Callback callbackActivity;

    // callback
    public interface Callback {
        void gotMenu(ArrayList<MenuItem> menuItems);
        void gotMenuError(String message);
    }

    // Constructor
    public MenuRequest(Context aContext){
        context = aContext;
    }

    // attempts to retrieve menu from API
    public void getMenu(Callback activity, String category){
        callbackActivity = activity;

        // create new queue
        RequestQueue queue = Volley.newRequestQueue(context);

        // create JSON object
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                "https://resto.mprog.nl/menu?category="+category, null,
                this, this);
        queue.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        callbackActivity.gotMenuError(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        JSONArray menuArray = new JSONArray();
        ArrayList<MenuItem> menu = new ArrayList<MenuItem>();

        // extract the array
        try {
            menuArray = response.getJSONArray("items");
        } catch (JSONException e) {
            callbackActivity.gotMenuError(e.getMessage());
        }

        // get the menu items
        for (int i = 0; i < menuArray.length(); i++) {
            try{
                // get the object
                JSONObject objectItem = menuArray.getJSONObject(i);

                String name = objectItem.getString("name");
                String description = objectItem.getString("description");
                String url = objectItem.getString("image_url");
                String category = objectItem.getString("category");
                Double price = objectItem.getDouble("price");

                // attach the item to the menu
                menu.add(new MenuItem(name, description, url, category, price));

            } catch (JSONException e) {
                callbackActivity.gotMenuError(e.getMessage());
            }
        }
        // send it back
        callbackActivity.gotMenu(menu);
    }
}
