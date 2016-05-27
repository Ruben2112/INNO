package com.inno.bourdrbij.models;

import android.graphics.drawable.Drawable;

/**
 * Created by Ruben on 20-5-2016.
 */
public class DrawerItem {

    String itemText;
    Drawable icon;

    public DrawerItem(String itemText) {
        super();
        this.itemText = itemText;
    }

    public DrawerItem(String itemText, Drawable icon) {
        super();
        this.itemText = itemText;
        this.icon = icon;
    }

    public DrawerItem() {
        super();
    }

    public String getItemText() {
        return itemText;
    }

    public Drawable getIcon() {
        return icon;
    }
}
