package com.inno.bourdrbij.models;

import com.inno.bourdrbij.servercommunication.HTTPRestClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Jurgen on 6/2/2016.
 */
public class JobManager {

    public static ArrayList<Job> GetAll()
    {
        ArrayList<Job> jobs = new ArrayList<>();
        //TODO: Hoe request je alle objecten?
        return jobs;
    }

    public static void GetLast(int count)
    {
        //TODO: GetLast als in meest recent waarin count aantal is?
    }

    public static Job NewJob(int usernameID, String name, String description, String reward)
    {
        RequestParams params = new RequestParams();


        params.put("ownerProfileID", usernameID);
        params.put("name", name);
        params.put("description", description);
        params.put("reward", reward);

        HTTPRestClient.post("job/", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    if (response.getBoolean("success")) {
                        System.out.println("Added new event to database.");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });

        Job newJob = new Job(usernameID, name, description, reward);
        /*Todo: hoe weet je welke id de nieuwe entry heef gekregen? newJob.setId(newID);*/
        return newJob;
    }

    public static void DeleteJob(int id)
    {
        RequestParams params = new RequestParams();

        params.put("id", id);

        HTTPRestClient.delete("job/" + id, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            }
        });
    }

    public static void UpdateJob(Job job, String description, String reward)
    {
        job.setDescription(description);
        job.setReward(reward);

        RequestParams params = new RequestParams();

        params.put("id", job.getId());
        params.put("description", description);
        params.put("reward", reward);

        //Todo: route toevoegen voor job Update
        HTTPRestClient.put("job/" + job.getId() + "/update", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            }
        });
    }

    public static void SearchJob(String searchCriteria)
    {
        //ToDo: Hoe zoek je met search criteria?
    }
}
