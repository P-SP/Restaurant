package com.example.eigenaar.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements MenuRequest.Callback {
    String retrievedCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // retrieve clicked category
        Intent intent = getIntent();
        retrievedCategory = intent.getStringExtra("clicked_category");

        // get menu
        MenuRequest request = new MenuRequest(this);
        request.getMenu(this, retrievedCategory);
    }

    @Override
    public void gotMenu(ArrayList<MenuItem> menuItems) {
        ListView menuView = findViewById(R.id.menuItems);

        // instantiate and attach adapter
        MenuAdapter adapter = new MenuAdapter(this, R.layout.list_row, menuItems);
        menuView.setAdapter(adapter);

        // connect listener
        menuView.setOnItemClickListener(new clickListener());
    }

    private class clickListener implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            MenuItem clickedItem= (MenuItem) adapterView.getItemAtPosition(i);

            // pass information to next activity
            Intent intent = new Intent(MenuActivity.this, MenuItemActivity.class);
            intent.putExtra("clicked_item", clickedItem);
            intent.putExtra("clicked_category", retrievedCategory);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void gotMenuError(String message) {
        Toast.makeText(MenuActivity.this, "Something went wrong: " +
                message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MenuActivity.this, CategoriesActivity.class);
        startActivity(intent);
        finish();
    }
}
