package com.inno.bourdrbij.models;

import com.inno.bourdrbij.servercommunication.HTTPRestClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import cz.msebera.android.httpclient.Header;

/**
 * Created by sebas on 5/20/2016.
 */
public class Encounter {
    /**
     * Profile encountered.
     */
    private Profile receiverProfile;
    /**
     * Profile of sender.
     */
    private Profile senderProfile;
    /**
     * Date of encounter.
     */
    private Date date;
    /**
     * Longitude of encounter.
     */
    private int longitude;

    /**
     * Latitude of encounter.
     */
    private int latitude;

    /**
     * Boolean to check if sender confirmed that he wants to be your friend.
     */
    private boolean senderConfirm;

    /**
     * Boolean to check if receiver confirmed that he wants to be your friend.
     */
    private boolean receiverConfirm;

    public static Encounter receivedEncounter = null;

    public Encounter(Profile receiverProfile, Profile senderProfile) {
        this.receiverProfile = receiverProfile;
        this.senderProfile = senderProfile;
        this.date = Calendar.getInstance().getTime();
        // TODO: Set location
        this.senderConfirm = false;
        this.receiverConfirm = false;
    }

    public Encounter (Profile receiverProfile, Profile senderProfile, Date date, int longitude, int latitude, int senderConfirm, int receiverConfirm) {
        this.receiverProfile = receiverProfile;
        this.senderProfile = senderProfile;

        this.date = date;
        this.longitude = longitude;
        this.latitude = latitude;
        if(senderConfirm == 1) {
            this.senderConfirm = true;
        } else {
            this.senderConfirm = false;
        }
        if(receiverConfirm == 1) {
            this.receiverConfirm = true;
        } else {
            this.receiverConfirm = false;
        }
    }

    public void createEncounter() {

    }

    /**
     * Retrieve Encounter information from the server and store it in the retrievedEncounter field.
     * @param meetingID ID of the encounter we are looking for.
     * @return retrievedEncounter field or null.
     */
    public Encounter getInfo(int meetingID) {
        // TODO: Direct HTTPRestClient to the right address.
        HTTPRestClient.get("retrievedProfile/" + meetingID, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    if (response.getBoolean("success")) {
                        JSONObject json = response.getJSONObject("data");

                        int profileIDReceiver = json.getInt("receiverID");
                        int profileIDSender = json.getInt("senderID");
                        String dateString = json.getString("date");
                        DateFormat df = new SimpleDateFormat("");
                        Date date = df.parse(dateString);
                        int longitude = json.getInt("longitude");
                        int latitude = json.getInt("latitude");
                        int senderConfirm = json.getInt("senderconfirm");
                        int receiverConfirm = json.getInt("receiverconfirm");
                        int id = json.getInt("id");

                        Profile receiverProfile = new Profile();
                        receiverProfile = receiverProfile.getProfile(profileIDReceiver);

                        Profile senderProfile = new Profile();
                        senderProfile = senderProfile.getProfile(profileIDSender);

                        Encounter.receivedEncounter = new Encounter(receiverProfile, senderProfile, date, longitude, latitude, senderConfirm, receiverConfirm);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });
        return Encounter.receivedEncounter;
    }

    /**
     * Send friends request.
     * @param username Username of profile we want to invite.
     */
    public void invite(String username) {

    }

    /**
     * Accept friends request.
     * @param inviteID
     */
    public void accept(int inviteID) {

    }
    // Is deze nodig?
    public void ignore(String username) {

    }

    /**
     * Rejects friends request.
     * @param inviteID
     */
    public void reject(int inviteID) {

    }
}
