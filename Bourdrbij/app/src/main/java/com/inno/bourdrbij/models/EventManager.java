package com.inno.bourdrbij.models;

import com.inno.bourdrbij.servercommunication.HTTPRestClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

import cz.msebera.android.httpclient.Header;

/**
 * Created by sebas on 6/2/2016.
 */
public class EventManager {

    public static ArrayList<Event> GetAll()
    {
        //TODO: Hoe request je alle objecten?
        return null;
    }

    public static Event SearchEvent(String searchCriteria){
        //ToDo: Hoe zoek je met search criteria?
        return null;
    }

    public static Event NewEvent(int ownerProfileID, int addressID, String name, String description, Date startTime, Date endTime, ArrayList<Integer> userIDs){
        RequestParams params = new RequestParams();


        params.put("ownerProfileID", ownerProfileID);
        params.put("location", addressID);
        params.put("name", name);
        params.put("description", description);
        params.put("startTime", startTime);
        params.put("endTime", endTime);
        //Todo: Hoe post je arrays?

        HTTPRestClient.post("event/", params, new JsonHttpResponseHandler() {
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

        Event newEvent = new Event(ownerProfileID, userIDs, addressID, name, description, startTime, endTime);
        /*Todo: hoe weet je welke id de nieuwe entry heef gekregen? newEvent.setId(newID);*/
        return newEvent;
    }
}