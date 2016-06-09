package com.inno.bourdrbij.servercommunication;

import android.app.AlertDialog;

import com.inno.bourdrbij.models.Job;
import com.inno.bourdrbij.models.Profile;
import com.loopj.android.http.JsonHttpResponseHandler;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by sjorsvanmierlo on 03-06-16.
 */
public class HTTPManager
{
    static Object result;

    public static Object doGet(final String url){
        //Object result = null;
        HTTPRestClient.get(url, null, new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                // Pull out the first event on the public timeline
                try{
                    System.out.println("ARRAY");

                if(url.contains("user")){

                }

                if(url.contains("profile")){
                    JSONObject jsonProfile = (JSONObject)response.get(0);
                    JSONObject data = (JSONObject)jsonProfile.get("data");

                    int pId = (Integer)data.getInt("id");
                    String pName = (String)data.getString("name");
                    String pProfilePicture = (String)data.getString("profilePicture");
                    String pProffesion = (String)data.getString("proffesion");
                    String[] pInterests = data.getString("interests").split(";");

                    JSONObject address = (JSONObject)data.get("get_address");
                    String pAddress = address.getString("street") + " " +address.getString("number") + ", " +address.getString("city") + ", " +address.getString("country");

                    Profile profile = new Profile(pId, pName, pProffesion, pAddress, pInterests);

                    result = profile;
                }

                if(url.contains("job")){
                    JSONObject jsonJob = (JSONObject)response.get(0);
                    JSONObject data = (JSONObject)jsonJob.get("data");

                    int pId = (Integer)data.getInt("id");
                    String pName = (String)data.getString("name");
                    int pOwnerProfileId = (Integer)data.getInt("ownerProfileId");
                    int pAcceptedProfileId = (Integer)data.getInt("acceptedProfileId");
                    String pDescription = (String)data.getString("description");
                    String pReward = (String)data.getString("reward");
                    Boolean pAccepted = (Boolean)data.getBoolean("accepted");

                    Job job = new Job(pId, pOwnerProfileId, pAcceptedProfileId, pName, pDescription, pReward, pAccepted);
                    result = job;
                }

                if(url.contains("event")){
                    JSONObject jsonEvent = (JSONObject)response.get(0);
                    JSONObject data = (JSONObject)jsonEvent.get("data");
                    //id
                    //name
                    //ownerProfileId
                    //addressId
                    //description
                    //startTime
                    //endTime
                }

                if(url.contains("encounter")){

                }

                if(url.contains("chat")){

                }

                }
                catch(Exception ex){

                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray

                System.out.println("OBJECT");
            }

        });


        return result;
    }


}
