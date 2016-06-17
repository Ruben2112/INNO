package com.inno.bourdrbij.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.inno.bourdrbij.R;
import com.inno.bourdrbij.models.Event;
import com.inno.bourdrbij.models.User;
import com.inno.bourdrbij.servercommunication.HTTPManager;
import com.inno.bourdrbij.views.MetamorphousTextView;

import java.util.ArrayList;

public class EventsAdapter extends ArrayAdapter<Event> {

    private Context context;
    private ArrayList<Event> eventsList;

    public EventsAdapter(Context context,
                         ArrayList<Event> listItems) {
        super(context, R.layout.list_item_event, listItems);
        this.context = context;
        this.eventsList = listItems;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            viewHolder = new ViewHolder();

            Event event = this.eventsList.get(position);
            view = inflater.inflate(R.layout.list_item_event, parent, false);
            viewHolder.tvEventTitle = (MetamorphousTextView) view.findViewById(R.id.tv_event_title);
            viewHolder.tvEventStart = (MetamorphousTextView) view.findViewById(R.id.tv_event_start);
            viewHolder.tvEventEnd = (MetamorphousTextView) view.findViewById(R.id.tv_event_end);

            SharedPreferences sharedPreferences = context.getSharedPreferences("currentUser", Context.MODE_PRIVATE);
            int userId = sharedPreferences.getInt("ProfileId", 0);
            Object userFromDB = HTTPManager.doGet("/user/" + userId);
            User user = (User) userFromDB;

            viewHolder.tvEventTitle.setText(event.getLocation());
            viewHolder.tvEventStart.setText(event.getName());
            viewHolder.tvEventEnd.setText("");
        }
        return view;
    }

    private static class ViewHolder {
        MetamorphousTextView tvEventTitle, tvEventStart, tvEventEnd;
    }
}