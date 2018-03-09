package com.example.eigenaar.restaurant;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Adapter for the menu.
 */

public class MenuAdapter extends ArrayAdapter<MenuItem> {
    private ArrayList<MenuItem> items;

    public MenuAdapter(@NonNull Context context, int resource, @NonNull ArrayList<MenuItem> objects) {
        super(context, resource, objects);
        items = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_row, parent, false);
        }

        // get places
        ImageView photo = convertView.findViewById(R.id.photo);
        TextView name = convertView.findViewById(R.id.name);
        TextView price = convertView.findViewById(R.id.price);

        // set right photo and name
        MenuItem item = items.get(position);
        Picasso.with(this.getContext())
                .load(item.getImageUrl())
                .error(R.drawable.no_pic)
                .into(photo);
        name.setText(item.getName());
        price.setText("$ "+item.getPrice());

        return convertView;
    }
}
