package com.inno.bourdrbij.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.inno.bourdrbij.R;
import com.inno.bourdrbij.models.Job;
import com.inno.bourdrbij.models.Profile;
import com.inno.bourdrbij.views.MetamorphousTextView;

import java.util.List;

public class ProfilesAdapter extends ArrayAdapter<Profile> {

    private Context context;
    private List<Profile> profileList;

    public ProfilesAdapter(Context context,
                           List<Profile> listItems) {
        super(context, R.layout.list_item_profile, listItems);
        this.context = context;
        this.profileList = listItems;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            viewHolder = new ViewHolder();

            Profile profile = this.profileList.get(position);
            view = inflater.inflate(R.layout.list_item_profile, parent, false);
            viewHolder.tvProfileName = (MetamorphousTextView) view.findViewById(R.id.tv_profile_name);

            //TODO: get properties of profile
            //viewHolder.tvJobStarter.setText(profile.getName());
        }
        return view;
    }

    private static class ViewHolder {
        MetamorphousTextView tvProfileName;
    }
}