package com.inno.bourdrbij.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.inno.bourdrbij.models.DrawerItem;
import com.inno.bourdrbij.views.MetamorphousTextView;
import com.inno.bourdrbij.R;

import java.util.List;

public class NavigationDrawerAdapter extends ArrayAdapter<DrawerItem> {

    Context context;
    List<DrawerItem> drawerItemList;
    int layoutResID;

    public NavigationDrawerAdapter(Context context, int layoutResourceID,
                                   List<DrawerItem> listItems) {
        super(context, layoutResourceID, listItems);
        this.context = context;
        this.drawerItemList = listItems;
        this.layoutResID = layoutResourceID;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        DrawerItemHolder drawerHolder = null;
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            drawerHolder = new DrawerItemHolder();

            DrawerItem dItem = this.drawerItemList.get(position);
            view = inflater.inflate(layoutResID, parent, false);
            drawerHolder.itemText = (MetamorphousTextView) view.findViewById(R.id.drawer_item_name);
            drawerHolder.ivIcon = (ImageView) view.findViewById(R.id.iv_icon);

            drawerHolder.itemText.setText(dItem.getItemText());
            drawerHolder.ivIcon.setImageDrawable(dItem.getIcon());
        }

        if (view != null) {
            view.setTag(drawerHolder);
        }
        return view;
    }

    private static class DrawerItemHolder {
        MetamorphousTextView itemText;
        ImageView ivIcon;
    }
}