package com.example.eigenaar.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity implements CategoriesApiRequest.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        // get the categories
        CategoriesApiRequest apiRequest = new CategoriesApiRequest(this);
        apiRequest.getCategories(this);
    }

    @Override
    public void gotCategories(ArrayList<String> categories) {
        ListView categoriesList = findViewById(R.id.listViewCategories);

        // instantiate and attach adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, categories);
        categoriesList.setAdapter(adapter);

        // connect listener
        categoriesList.setOnItemClickListener(new clickListener());
    }

    private class clickListener implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            String clickedCategory = (String) adapterView.getItemAtPosition(i);

            // pass information to next activity
            Intent intent = new Intent(CategoriesActivity.this, MenuActivity.class);
            intent.putExtra("clicked_category", clickedCategory);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void gotCategoriesError(String message) {
        Toast.makeText(CategoriesActivity.this, "Something went wrong: " +
                message, Toast.LENGTH_SHORT).show();
    }
}