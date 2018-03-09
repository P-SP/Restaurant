package com.example.eigenaar.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MenuItemActivity extends AppCompatActivity {
    String clickedCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_item);

        // retrieve clicked category
        Intent intent = getIntent();
        MenuItem retrievedItem = (MenuItem) intent.getSerializableExtra("clicked_item");
        clickedCategory = intent.getStringExtra("clicked_category");

        // get places
        ImageView photo = findViewById(R.id.photo);
        TextView name = findViewById(R.id.name);
        TextView price = findViewById(R.id.price);
        TextView description = findViewById(R.id.description);
        TextView category = findViewById(R.id.category);

        // attach right value
        Picasso.with(this)
                .load(retrievedItem.getImageUrl())
                .error(R.drawable.no_pic)
                .into(photo);
        name.setText(retrievedItem.getName());
        price.setText("$ "+retrievedItem.getPrice());
        description.setText(retrievedItem.getDescription());
        category.setText(retrievedItem.getCategory());
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MenuItemActivity.this, MenuActivity.class);
        intent.putExtra("clicked_category", clickedCategory);
        startActivity(intent);
        finish();
    }
}
