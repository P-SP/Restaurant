package com.example.eigenaar.restaurant;

import java.io.Serializable;

/**
 * Class for a menu item.
 */

public class MenuItem implements Serializable {

    // properties
    private String name, description, imageUrl, category;
    private double price;

    // constructors
    public  MenuItem() {}

    public MenuItem(String aName, String aDescription, String anImageUrl, String aCategory, double aPrice) {
        name = aName;
        description = aDescription;
        imageUrl = anImageUrl;
        category = aCategory;
        price = aPrice;

    }

    // add getter functions
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    // add setter functions
    public void setName(String aName) {
        name = aName;
    }

    public void setDescription(String aDescription) {
        description = aDescription;
    }

    public void setImageUrl(String anImageUrl) {
        imageUrl = anImageUrl;
    }

    public void setCategory(String aCategory) {
        category = aCategory;
    }

    public void setPrice(double aPrice) {
        price = aPrice;
    }

}
