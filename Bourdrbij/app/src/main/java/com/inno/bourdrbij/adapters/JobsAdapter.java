package com.inno.bourdrbij.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.inno.bourdrbij.R;
import com.inno.bourdrbij.models.Job;
import com.inno.bourdrbij.models.User;
import com.inno.bourdrbij.servercommunication.HTTPManager;
import com.inno.bourdrbij.views.MetamorphousTextView;

import java.util.List;

public class JobsAdapter extends ArrayAdapter<Job> {

    private Context context;
    private List<Job> jobList;

    public JobsAdapter(Context context,
                       List<Job> listItems) {
        super(context, R.layout.list_item_job, listItems);
        this.context = context;
        this.jobList = listItems;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            viewHolder = new ViewHolder();

            Job job = this.jobList.get(position);
            view = inflater.inflate(R.layout.list_item_job, parent, false);
            viewHolder.tvJobStarter = (MetamorphousTextView) view.findViewById(R.id.tv_job_starter);
            viewHolder.tvJobTitle = (MetamorphousTextView) view.findViewById(R.id.tv_job_title);


            SharedPreferences sharedPreferences = context.getSharedPreferences("currentUser", Context.MODE_PRIVATE);
            int userId = sharedPreferences.getInt("ProfileId", 0);
            Object userFromDB = HTTPManager.doGet("/user/" + userId);
            User user = (User) userFromDB;

            viewHolder.tvJobStarter.setText(user.getUsername());
            viewHolder.tvJobTitle.setText(job.getName());
        }
        return view;
    }

    private static class ViewHolder {
        MetamorphousTextView tvJobStarter, tvJobTitle;
    }
}