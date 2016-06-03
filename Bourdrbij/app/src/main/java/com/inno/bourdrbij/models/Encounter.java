package com.inno.bourdrbij.models;

import com.inno.bourdrbij.servercommunication.HTTPRestClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
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
     * Id of profile.
     */
    private int id;
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

    public Encounter (int id, Profile receiverProfile, Profile senderProfile, Date date, int longitude, int latitude, int senderConfirm, int receiverConfirm) {
        this.id = id;
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

    public int getId() {
        return this.id;
    }

    public Profile getReceiverProfile() {
        return this.receiverProfile;
    }

    public boolean getReceiverConfirm() {
        return this.receiverConfirm;
    }

    public boolean getSenderConfirm() {
        return this.senderConfirm;
    }

    public Profile getSenderProfile() {
        return this.senderProfile;
    }

    public Date getDate() {
        return this.date;
    }

    public int getLongitude() {
        return this.longitude;
    }

    public int getLatitude() {
        return this.latitude;
    }

    /**
     * Puts a Encounter in the database on the server.
     */
    public void createEncounter() {

        RequestParams params = new RequestParams();


        params.put("receiverProfileId", this.getReceiverProfile().getId());
        params.put("senderProfileId", this.getSenderProfile().getId());
        params.put("date", this.getDate());
        params.put("longitude", this.getLongitude());
        params.put("latitude", this.getLatitude());
        params.put("senderConfirm", this.getSenderConfirm());
        params.put("receiverConfirm", this.getReceiverConfirm());

        HTTPRestClient.post("encounter/", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    if (response.getBoolean("success")) {
                        System.out.println("Added an encounter to database.");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });
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

                        Encounter.receivedEncounter = new Encounter(id, receiverProfile, senderProfile, date, longitude, latitude, senderConfirm, receiverConfirm);
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
        this.receiverProfile = this.getProfile(username);
        this.senderConfirm = true;
        
        this.updateEncounter();
    }

    /**
     * Accept friends request.
     * @param inviteID
     */
    public void accept(int inviteID) {
        this.senderProfile.addFriend(this.receiverProfile.getId());
        this.receiverConfirm = true;

        this.updateEncounter();
    }
    // Is deze nodig?
    public void ignore(String username) {

    }

    /**
     * Rejects friends request.
     * @param inviteID
     */
    public void reject(int inviteID) {
        // TODO: Remove Encounter?
    }

    /**
     * Updates the Encounter in the database on the server.
     * Update senderConfirm to true
     * Update receiverConfirm to true or false.
     */
    private void updateEncounter() {

        RequestParams params = new RequestParams();

        params.put("receiverProfileId", this.getReceiverProfile().getId());
        params.put("senderConfirm", this.getSenderConfirm());
        params.put("receiverConfirm", this.getReceiverConfirm());
        HTTPRestClient.put("updateEncounter/" + this.getId(), params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    if (response.getBoolean("success")) {
                        System.out.println("Encounter updated.");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });
    }

    /**
     * Find profile with [@param] username in server database.
     * @param username
     * @return Profile that matches the [@param]
     */
    private Profile getProfile(String username) {
        HTTPRestClient.get("retrievedProfile/" + username, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    if (response.getBoolean("success")) {
                        JSONObject json = response.getJSONObject("data");
                        String username = json.getString("username");
                        String profession = json.getString("profession");
                        String address = json.getString("address");
                        JSONArray intrestsJsonArray = json.getJSONArray("intrests");
                        String[] intrests = new String[intrestsJsonArray.length()];
                        for (int i = 0; i < intrestsJsonArray.length(); i++) {
                            intrests[i] = intrestsJsonArray.getString(i);
                        }
                        int id = json.getInt("id");
                        // String picturePath = json.getString("picture");

                        Profile.retrievedProfile = new Profile(id, username, profession, address, intrests);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });
        return Profile.retrievedProfile;
    }
}
